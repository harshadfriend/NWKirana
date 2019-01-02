package karjatonline.nw;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CustDetails extends AppCompatActivity {

    String dburl="https://nwkirana-3eb2e.firebaseio.com/";
    Firebase firebase;
    DatabaseReference dbRef;

    TextView tvNameorder;
    FloatingActionButton fab;
    String name,custkey;
    ListView lvCustDetails;

    ArrayAdapter<String> adp,adporderkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_details);

        setTitle("Orders");

        Firebase.setAndroidContext(this);

        firebase=new Firebase(dburl);
        dbRef = FirebaseDatabase.getInstance().getReference();

        Bundle extras=getIntent().getExtras();
        name=extras.getString("name");
        //custkey=extras.getString("custkey");

        fab=findViewById(R.id.fabNewOrder);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),NewOrder.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });

        adporderkey=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        adporderkey.setNotifyOnChange(true);

        com.google.firebase.database.Query k=dbRef.child("cust").orderByChild("name").equalTo(name);
        k.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                for (com.google.firebase.database.DataSnapshot data:dataSnapshot.getChildren()){
                    //fbase pson = data.getValue(fbase.class);
                    custkey=data.getKey();
                    //  Toast.makeText(NewOrder_old.this, ""+custkey, Toast.LENGTH_SHORT).show();
                    Log.d("logd",""+custkey);
                }

                fn();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tvNameorder=findViewById(R.id.tvnameorder);
        tvNameorder.setText(name);

        lvCustDetails=findViewById(R.id.lvCustDetails);




    }

    public void fn(){
        Query q=dbRef.child("orders").child(custkey);
        q.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String[] str=new String[(int)dataSnapshot.getChildrenCount()];
                int i=0;
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    fbase f=data.getValue(fbase.class);
                    Log.d("custorders", data.getKey()+" "+f.getDate());
//                    str[i]=data.getKey();
                    adporderkey.add(data.getKey());
                    str[i]=(i+1)+". Date:-"+f.getDate()+" Total="+f.getOrdertotal();
                    i++;
                }

                adp=new ArrayAdapter<>(CustDetails.this,android.R.layout.simple_list_item_1,str);
                lvCustDetails.setAdapter(adp);
                adp.setNotifyOnChange(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lvCustDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),OrderDetails.class);
                i.putExtra("name",name);
                i.putExtra("orderkey",adporderkey.getItem(position));
                i.putExtra("custkey",custkey);
                startActivity(i);
            }
        });

    }
}
