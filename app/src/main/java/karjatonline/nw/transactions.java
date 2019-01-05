package karjatonline.nw;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class transactions extends AppCompatActivity {
    String dburl="https://nwkirana-3eb2e.firebaseio.com/";
    Firebase firebase;
    DatabaseReference dbRef;

    FloatingActionButton fabNewTrans;
    String name,custkey;

    TextView tvName;
    ListView lvTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);
        dbRef = FirebaseDatabase.getInstance().getReference();

        setTitle("Transactions");

        Bundle extras=getIntent().getExtras();
        name=extras.getString("name");
        custkey=extras.getString("custkey");

        //Toast.makeText(this, ""+name+"\n"+custkey, Toast.LENGTH_SHORT).show();

        tvName=findViewById(R.id.tvnameTransaction);
        tvName.setText(name);

        fabNewTrans=findViewById(R.id.fabNewTransaction);
        fabNewTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),NewTransaction.class);
                //Toast.makeText(CustDetails.this, ""+position, Toast.LENGTH_SHORT).show();
                i.putExtra("name",name);
                i.putExtra("custkey",custkey);
                startActivity(i);
            }
        });

        lvTransaction=findViewById(R.id.lvTransaction);

        Query q=dbRef.child("transactions").child(custkey);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
