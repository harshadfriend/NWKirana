package karjatonline.nw;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class OrderDetails extends AppCompatActivity {

    String dburl="https://nwkirana-3eb2e.firebaseio.com/";
    Firebase firebase;
    DatabaseReference dbRef;

    TextView tvODtotal;
    ListView lvOrderDetail;

    ArrayAdapter<String> adp;

    String name,orderkey,custkey,orderno;

    OrderDetailadapter odadp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);
        dbRef = FirebaseDatabase.getInstance().getReference();

        tvODtotal=findViewById(R.id.tvODtotal);
        lvOrderDetail=findViewById(R.id.lvOrderDetail);

        Bundle extras=getIntent().getExtras();
        name=extras.getString("name");
        orderkey=extras.getString("orderkey");
        orderno=extras.getString("orderno");
        custkey=extras.getString("custkey");

        setTitle("Order No."+orderno+" :"+name );

        Query q=dbRef.child("orderdetail").child(custkey).child(orderkey);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String str[][]=new String[(int)dataSnapshot.getChildrenCount()][4];
                int i=0,j=0;
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    fbase f=data.getValue(fbase.class);
//                    str[i]=(i+1)+". "+f.getItem()+" "+f.getPquantity()+"* "+f.getPrate()+" = "+f.getTotal();
                    str[i][j]=f.getItem();
                    j++;
                    str[i][j]=f.getPrate();
                    j++;
                    str[i][j]=f.getPquantity();
                    j++;
                    str[i][j]=f.getTotal();
                    j=0;

                    i++;
                }

//                adp=new ArrayAdapter<>(OrderDetails.this,android.R.layout.simple_list_item_1,str);
//                adp.setNotifyOnChange(true);
                odadp=new OrderDetailadapter(OrderDetails.this,R.layout.order_detail_adapter,str);
                odadp.setNotifyOnChange(true);
                lvOrderDetail.setAdapter(odadp);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Query q1=dbRef.child("orders").child(custkey).child(orderkey);
        q1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fbase f=dataSnapshot.getValue(fbase.class);
                tvODtotal.setText("Grand Total=Rs. "+f.getOrdertotal()+"/-");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
