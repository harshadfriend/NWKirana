package karjatonline.nw;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

import java.util.ArrayList;

public class CustList extends AppCompatActivity {
//    String dburl="https://nwkirana-3eb2e.firebaseio.com/";
//    String dburl="https://kanifnathstore.firebaseio.com/";
String dburl;
    String[] str=new String[10];
    String a,b,c;
    AutoCompleteTextView actv;
    ArrayAdapter<String> adp,ladp;
    ArrayList<String> keyArrayList=new ArrayList<String>();
    Firebase firebase;
    DatabaseReference dbRef;

    FloatingActionButton fabadd;

    LinearLayout ll;
    ListView lvCustList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_list);

        dburl=getResources().getString(R.string.url);

        setTitle("Customers");

        lvCustList=findViewById(R.id.lvCustList);

        fabadd=findViewById(R.id.fabAddCust);
        fabadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddCustomer.class));
            }
        });

       // ll=(LinearLayout)findViewById(R.id.ll);




        actv=findViewById(R.id.actv);
        adp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
//        for(int j=0;j<10;j++)
//            Log.d("logg",""+str[j]);
        adp.setNotifyOnChange(true);
        actv.setAdapter(adp);

//        ladp=new ArrayAdapter<String>(this,R.layout.listbutton);

        //apply style to basic listview items
        ladp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view =super.getView(position, convertView, parent);

                TextView textView=(TextView) view.findViewById(android.R.id.text1);

                //YOUR CHOICE OF COLOR
                textView.setTextColor(Color.WHITE);
                textView.getText().toString().toUpperCase();
                textView.setTypeface(null, Typeface.BOLD);
                textView.setPadding(2,0,0,0);
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user,0,0,0);

                return view;
            }
        };
        ladp.setNotifyOnChange(true);
        lvCustList.setAdapter(ladp);



        Firebase.setAndroidContext(this);

        firebase=new Firebase(dburl);

        dbRef = FirebaseDatabase.getInstance().getReference();
        //Query query=dbRef.child("person").orderByChild("name").equalTo(etSearch.getText().toString());
//        Query query = dbRef.child("siot").orderByChild("name").equalTo("seema");
        Query query = dbRef.child("cust");
        query.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            int i=0;

            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                keyArrayList.clear();i=0;
                adp.clear();
                ladp.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    fbase pson = data.getValue(fbase.class);
                    //Toast.makeText(busGrid.this, "Name: " + pson.getName() + "Address: " + pson.getAddress(), Toast.LENGTH_SHORT).show();


//                   Toast.makeText(getApplicationContext(),""+pson.getadd()+ " "+i, Toast.LENGTH_SHORT).show();  //show coordinates
                    Log.d("logg",""+i);
                    keyArrayList.add(data.getKey());
                    a=pson.getName();
                    adp.add(""+pson.getName());
                    ladp.add(""+pson.getName());
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
        //Toast.makeText(this, ""+ladp.getCount(), Toast.LENGTH_SHORT).show();

//            Log.d("prepo",""+keyArrayList.get(2));

        actv.setFocusable(false);
        actv.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("logd","ll");
                actv.setFocusable(true);
                // actv.setShowSoftInputOnFocus(true);
                actv.setFocusableInTouchMode(true);
                actv.setText("");
                //startActivity(new Intent(getApplicationContext(),sample.class));

                return false;
            }


        });
//        Log.d("logg",""+a+b+c);
        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity.this, ""+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                Log.d("logg",""+parent.getItemAtPosition(position));
                Intent i=new Intent(getApplicationContext(),CustDetails.class);
                i.putExtra("name",""+parent.getItemAtPosition(position));
                // i.putExtra("custkey","0");
                startActivity(i);

                // Toast.makeText(MainActivity.this, ""+keyArrayList.get(position)+ " "+keyArrayList.size(), Toast.LENGTH_SHORT).show();
            }
        });

        lvCustList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("logg",""+parent.getItemAtPosition(position));
                Intent i=new Intent(getApplicationContext(),CustDetails.class);
                i.putExtra("name",""+parent.getItemAtPosition(position));
                // i.putExtra("custkey",keyArrayList.get(position));
                startActivity(i);
            }
        });

    }

 /* commented for new app

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.menuadd){
            startActivity(new Intent(this,AddCustomer.class));
//            startActivity(new Intent(this,MonthCalculate.class));
        }
        if(item.getItemId()==R.id.menudelete){
            startActivity(new Intent(this,DeleteUser.class));
        }
       /* if(item.getItemId()==R.id.menuedit){
//            etnameres.setEnabled(true);
//            etageres.setEnabled(true);
//            etaddres.setEnabled(true);
            //startActivity(new Intent(this,MonthCalculate.class));
        }*/
  /*      return super.onOptionsItemSelected(item);
    }

    */
}
