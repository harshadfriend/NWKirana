package karjatonline.nw;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

        com.google.firebase.database.Query k=dbRef.child("cust").orderByChild("name").equalTo(name);
        k.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                for (com.google.firebase.database.DataSnapshot data:dataSnapshot.getChildren()){
                    //fbase pson = data.getValue(fbase.class);
                    custkey=data.getKey();
                    //  Toast.makeText(NewOrder.this, ""+custkey, Toast.LENGTH_SHORT).show();
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
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    Log.d("custorders", data.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
