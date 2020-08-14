package karjatonline.nw;

import android.annotation.SuppressLint;
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

//this is customer's orders page
public class CustDetails extends AppCompatActivity {

//    String dburl="https://nwkirana-3eb2e.firebaseio.com/";
//    String dburl="https://kanifnathstore.firebaseio.com/";
    String dburl;
    Firebase firebase;
    DatabaseReference dbRef;

    TextView tvNameorder,tvGrandTotal;
    FloatingActionButton fab;
    String name,custkey;
    ListView lvCustDetails;

    ArrayAdapter<String> adp,adporderkey;

    OrderListadapter oladp;
    double grandTotal=0,transtotal=0,balance=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_details);

        dburl=getResources().getString(R.string.url);

        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);
        dbRef = FirebaseDatabase.getInstance().getReference();

        Bundle extras=getIntent().getExtras();
        name=extras.getString("name");
        //custkey=extras.getString("custkey");

        setTitle("Orders: "+name);
        fab=findViewById(R.id.fabNewOrder);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),NewOrder.class);
                i.putExtra("name",name);
                startActivity(i);
            }
        });

        tvNameorder=findViewById(R.id.tvnameorder);
//        tvNameorder.setText(name);

        tvGrandTotal=findViewById(R.id.tvGrandTotal);

        lvCustDetails=findViewById(R.id.lvCustDetails);


        adporderkey=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        adporderkey.setNotifyOnChange(true);

        tvGrandTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),transactions.class);
                //Toast.makeText(CustDetails.this, ""+position, Toast.LENGTH_SHORT).show();
                i.putExtra("name",name);
//                i.putExtra("orderno",""+(position+1));
//                i.putExtra("orderkey",adporderkey.getItem(position));
                i.putExtra("custkey",custkey);
                startActivity(i);

            }
        });

        com.google.firebase.database.Query k=dbRef.child("cust").orderByChild("name").equalTo(name);
        k.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
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
    }

    public void fn(){

        //balance total
        Query b=dbRef.child("transactions").child(custkey);
        b.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                transtotal=0;balance=0;
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    fbase f=data.getValue(fbase.class);
                    Log.e("logtrans",f.getDate()+" "+f.getAmount());
                    transtotal=transtotal+Double.parseDouble(f.getAmount());
                }
                balance=grandTotal-transtotal;
                tvGrandTotal.setText("Balance:  "+balance+"/-Rs.");
                tvNameorder.setText("Total:"+grandTotal+"/- , Credit:"+transtotal+"/-");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //get order details
        Query q=dbRef.child("orders").child(custkey);
        q.addValueEventListener(new ValueEventListener() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                grandTotal=0;balance=0;
//                String[] str=new String[(int)dataSnapshot.getChildrenCount()];
                String[][] str=new String[(int)dataSnapshot.getChildrenCount()][4];
                int i=0,j=0;
                adporderkey.clear();
                for(DataSnapshot data:dataSnapshot.getChildren()){

                    fbase f=data.getValue(fbase.class);
                    Log.d("custorders", data.getKey()+" "+f.getDate());
//                    str[i]=data.getKey();
                    adporderkey.add(data.getKey());
//                    str[i]=(i+1)+". Date:-"+f.getDate()+" Total="+f.getOrdertotal();
                    str[i][j]=f.getDate();
                    j++;
                    str[i][j]=f.getOrdertotal();
                    j++;
                    str[i][j]=custkey;
                    j++;
                    str[i][j]=data.getKey();
                    j=0;

                    //grand total of all order amounts
                    grandTotal=grandTotal+Double.parseDouble(f.getOrdertotal());

                    i++;
                }

                balance=grandTotal-transtotal;
                tvNameorder.setText("Total:"+grandTotal+"/- , Credit:"+transtotal+"/-");
                //setting grandtotal value in textview
//                tvGrandTotal.setText(""+grandTotal+"/-Rs.");
                tvGrandTotal.setText("Balance:  "+balance+"/-Rs.");
                tvGrandTotal.setTypeface(Typeface.DEFAULT_BOLD);
//                adp=new ArrayAdapter<>(CustDetails.this,android.R.layout.simple_list_item_1,str);
//                adp.setNotifyOnChange(true);
                oladp=new OrderListadapter(CustDetails.this,R.layout.order_list_adapter,str);
                oladp.setNotifyOnChange(true);
                lvCustDetails.setAdapter(oladp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lvCustDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),OrderDetails.class);
                //Toast.makeText(CustDetails.this, ""+position, Toast.LENGTH_SHORT).show();
                i.putExtra("name",name);
                i.putExtra("orderno",""+(position+1));
                i.putExtra("orderkey",adporderkey.getItem(position));
                i.putExtra("custkey",custkey);
                startActivity(i);
            }
        });

    }
}