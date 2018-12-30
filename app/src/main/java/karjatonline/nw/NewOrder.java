package karjatonline.nw;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewOrder extends AppCompatActivity {
    String dburl="https://nwkirana-3eb2e.firebaseio.com/";

    double total;
    Firebase firebase;
    DatabaseReference dbRef;
    String[] sP,sR,sQ;
    AutoCompleteTextView actvNOproduct;
    EditText etNOquantity;
    TextView tvNOrate,tvNOtotal;
    ArrayAdapter<String> adp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        Firebase.setAndroidContext(this);

        firebase=new Firebase(dburl);
        dbRef = FirebaseDatabase.getInstance().getReference();

        actvNOproduct=findViewById(R.id.etNOproduct);
        etNOquantity=findViewById(R.id.etNOquantity);
        tvNOtotal=findViewById(R.id.tvNOtotal);
        tvNOrate=findViewById(R.id.tvNOrate);

        com.google.firebase.database.Query q=dbRef.child("product");
        q.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {

                int i=0;
                String strP[]=new String[(int)dataSnapshot.getChildrenCount()];
                String strR[]=new String[(int)dataSnapshot.getChildrenCount()];
                String strQ[]=new String[(int)dataSnapshot.getChildrenCount()];
                for(com.google.firebase.database.DataSnapshot data:dataSnapshot.getChildren()){
                    fbase f=data.getValue(fbase.class);

                    strP[i]=f.getPname();
                    strR[i]=f.getPrate();
                    strQ[i]=f.getPquantity();
                    i++;
                    Log.d("logd",f.getPname()+" "+f.getPrate());

                }
                sP=strP;
                sR=strR;
                sQ=strQ;
                fn();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    public void fn(){
        for(int i=0;i<sP.length;i++){
            Log.d("forloop",sP[i]);
        }
        adp=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,sP);
        actvNOproduct.setAdapter(adp);

        actvNOproduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(NewOrder.this, "selected", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < sP.length ; i++) {
                    if(sP[i].equals(parent.getItemAtPosition(position).toString())){
                        tvNOrate.setText(sR[i]);
                    }
                }
                total=Double.parseDouble(tvNOrate.getText().toString())*Double.parseDouble(etNOquantity.getText().toString());
                tvNOtotal.setText(""+total);
            }
        });


       etNOquantity.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

               Toast.makeText(NewOrder.this, "editor", Toast.LENGTH_SHORT).show();
               if(s.toString().length()>0){
                   total=Double.parseDouble(tvNOrate.getText().toString())*Double.parseDouble(etNOquantity.getText().toString());
                   tvNOtotal.setText(""+total);
               }
               else {
                   tvNOtotal.setText("0");
               }
           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });


    }
}
