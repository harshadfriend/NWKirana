package karjatonline.nw;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
//    String dburl="https://nwkirana-3eb2e.firebaseio.com/";
//    String dburl="https://kanifnathstore.firebaseio.com/";
String dburl;
    Firebase firebase;
    DatabaseReference dbRef;

    FloatingActionButton fabNewTrans;
    String name,custkey;

    TextView tvName,tvTransTotal;
    ListView lvTransaction;
    double total=0;
    ArrayAdapter<String> adpkey;
    OrderListadapter oladp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        dburl=getResources().getString(R.string.url);

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
        tvTransTotal.setTypeface(Typeface.DEFAULT_BOLD);

        //array adapter for keys
        adpkey=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);
        adpkey.setNotifyOnChange(true);

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

        //for deleting credit entry
        lvTransaction.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos=position;
                new AlertDialog.Builder(transactions.this).setTitle("Delete ?")
                        .setMessage("Delete selected transaction ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                firebase.child("transactions").child(custkey).child(adpkey.getItem(pos)).removeValue();
                                Toast.makeText(transactions.this, "Successfully deleted !", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

            }
        });


        Query q=dbRef.child("transactions").child(custkey);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String[][] str=new String[(int)dataSnapshot.getChildrenCount()][2];
                int i=0,j=0;
                total=0;
                adpkey.clear();
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    fbase f=data.getValue(fbase.class);
                    Log.d("logtrans",f.getDate()+" "+f.getAmount());
//                    str[i]=(i+1)+". Date: "+f.getDate()+",  Amount: "+f.getAmount()+"/-";
                    str[i][j]=f.getDate();
                    j++;
                    str[i][j]=f.getAmount();
                    j=0;

                    i++;
                    //add key to adpkey
                    adpkey.add(data.getKey());
                    total=total+Double.parseDouble(f.getAmount());
                }
//                ArrayAdapter<String> adp=new ArrayAdapter<>(transactions.this,android.R.layout.simple_list_item_1,str);
//                adp.setNotifyOnChange(true);
                oladp=new OrderListadapter(transactions.this,R.layout.order_list_adapter,str);
                oladp.setNotifyOnChange(true);
//                lvTransaction.setAdapter(adp);
                lvTransaction.setAdapter(oladp);
                tvTransTotal.setText("Total: "+total+"/-");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
