package karjatonline.nw;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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

    TextView tvName,tvTransTotal;
    ListView lvTransaction;
    double total=0;

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

        tvTransTotal=findViewById(R.id.tvTransTotal);
        tvTransTotal.setText(""+total);

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
                String[] str=new String[(int)dataSnapshot.getChildrenCount()];
                int i=0;
                total=0;
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    fbase f=data.getValue(fbase.class);
                    Log.d("logtrans",f.getDate()+" "+f.getAmount());
                    str[i]=(i+1)+". Date: "+f.getDate()+",  Amount: "+f.getAmount()+"/-";
                    i++;
                    total=total+Double.parseDouble(f.getAmount());
                }
                ArrayAdapter<String> adp=new ArrayAdapter<>(transactions.this,android.R.layout.simple_list_item_1,str);
                adp.setNotifyOnChange(true);
                lvTransaction.setAdapter(adp);
                tvTransTotal.setText(""+total);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
