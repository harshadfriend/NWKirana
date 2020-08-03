package karjatonline.nw;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditCust extends AppCompatActivity {

//    String dburl="https://nwkirana-3eb2e.firebaseio.com/";
//    String dburl="https://kanifnathstore.firebaseio.com/";
String dburl;
    String[] str=new String[10];
    String a,b,c;
    AutoCompleteTextView actvDU;
    ArrayAdapter<String> adpDU,ladpDU;
    ArrayList<String> keyArrayList=new ArrayList<String>();
    Firebase firebase;
    DatabaseReference dbRef;
    String actvDUkey,strName;


    LinearLayout ll;
    ListView lvCustListDU;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cust);

        dburl=getResources().getString(R.string.url_swara);

        setTitle("Edit Customer");

        lvCustListDU=findViewById(R.id.lvCustListDU);

     //   ll=(LinearLayout)findViewById(R.id.ll);

        actvDU=findViewById(R.id.actvDU);
        adpDU=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
//        for(int j=0;j<10;j++)
//            Log.d("logg",""+str[j]);
        adpDU.setNotifyOnChange(true);
        actvDU.setAdapter(adpDU);

        //apply style to basic listview items
        ladpDU=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                /*YOUR CHOICE OF COLOR*/
                textView.setTextColor(Color.WHITE);
                textView.setTypeface(null, Typeface.BOLD);

                return view;
            }
        };
        ladpDU.setNotifyOnChange(true);
        lvCustListDU.setAdapter(ladpDU);



        Firebase.setAndroidContext(this);

        firebase=new Firebase(dburl);

        dbRef = FirebaseDatabase.getInstance().getReference();
        //Query query=dbRef.child("person").orderByChild("name").equalTo(etSearch.getText().toString());
//        Query query = dbRef.child("siot").orderByChild("name").equalTo("seema");
        Query query = dbRef.child("cust");  //query to get cust key
        query.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            int i=0;

            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                keyArrayList.clear();i=0;
                adpDU.clear();
                ladpDU.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    fbase pson = data.getValue(fbase.class);
                    //Toast.makeText(busGrid.this, "Name: " + pson.getName() + "Address: " + pson.getAddress(), Toast.LENGTH_SHORT).show();


//                   Toast.makeText(getApplicationContext(),""+pson.getadd()+ " "+i, Toast.LENGTH_SHORT).show();  //show coordinates
                    Log.d("logg",""+i);
                    keyArrayList.add(data.getKey());
                    a=pson.getName();
                    adpDU.add(""+pson.getName());
                    ladpDU.add(""+pson.getName());
                    Log.d("logg","inside i "+data.getKey());
                    i++;

//                    a=pson.getadd();
//                    if(i==1){ b=pson.getadd();Log.d("logg","inside i eqil 1"+b);}
//                    if(i==2) c=pson.getadd();

                }

            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText(busGrid.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

        actvDU.setFocusable(false);
        actvDU.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("logd","ll");
                actvDU.setFocusable(true);
                // actv.setShowSoftInputOnFocus(true);
                actvDU.setFocusableInTouchMode(true);
                actvDU.setText("");
                //startActivity(new Intent(getApplicationContext(),sample.class));

                return false;
            }


        });
//        Log.d("logg",""+a+b+c);
        actvDU.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, ""+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                Log.d("logg",""+parent.getItemAtPosition(position));
                strName=parent.getItemAtPosition(position).toString();
                Query q=dbRef.child("cust").orderByChild("name").equalTo(parent.getItemAtPosition(position).toString());
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot data:dataSnapshot.getChildren()){
                            //Toast.makeText(DeleteUser.this, ""+data.getKey(), Toast.LENGTH_SHORT).show();
                            actvDUkey=data.getKey();
                        }
                       // Toast.makeText(EditCust.this, "actvdukey"+actvDUkey, Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(EditCust.this,EditCustDialog.class);
                        i.putExtra("custkey",actvDUkey);

                        startActivity(i);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });

                // Toast.makeText(DeleteUser.this, ""+keyArrayList.get(position), Toast.LENGTH_SHORT).show();
                /**/
            }
        });

        lvCustListDU.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int i=0;String strname;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                i=position;
                strname=parent.getItemAtPosition(position).toString();
                //  Toast.makeText(EditUser.this, ""+ladpDU.getItem(position), Toast.LENGTH_SHORT).show();
                Intent i=new Intent(EditCust.this,EditCustDialog.class);
                i.putExtra("custkey",keyArrayList.get(position));
                startActivity(i);

//                new Firebase("https://jewelleryshop-30ff9.firebaseio.com/").child("cust").child(keyArrayList.get(position)).setValue(null);
//                new Firebase("https://jewelleryshop-30ff9.firebaseio.com/").child("pledge").child(keyArrayList.get(position)).setValue(null);
//                new Firebase("https://jewelleryshop-30ff9.firebaseio.com/").child("amount").child(keyArrayList.get(position)).setValue(null);
//                new Firebase("https://jewelleryshop-30ff9.firebaseio.com/").child("transactions").child(keyArrayList.get(position)).setValue(null);
//                new Firebase("https://jewelleryshop-30ff9.firebaseio.com/").child("interest").child(keyArrayList.get(position)).setValue(null);
            }
        });

    }
}
