package karjatonline.nw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditCustDialog extends AppCompatActivity {
    EditText etEUDname,etEUDadd,etEUDphone;
    Button btnEUDsave;
    String custkey;

    Firebase firebase;

    String dburl="https://nwkirana-3eb2e.firebaseio.com/";
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cust_dialog);

        dbRef = FirebaseDatabase.getInstance().getReference();

        Firebase.setAndroidContext(this);
        firebase = new Firebase(dburl);

        Bundle extras=getIntent().getExtras();
        custkey=extras.getString("custkey");

        Log.d("eud",""+custkey);

        // Toast.makeText(this, "eud "+custkey, Toast.LENGTH_SHORT).show();

        etEUDname=findViewById(R.id.etEUDname);
        etEUDadd=findViewById(R.id.etEUDadd);
        etEUDphone=findViewById(R.id.etEUDphone);

        btnEUDsave=findViewById(R.id.btnEUDsave);

        Query q=dbRef.child("cust").child(custkey);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot data:dataSnapshot.getChildren()){
                fbase f=dataSnapshot.getValue(fbase.class);   //no need of looping when u directly get the child
                etEUDname.setText(f.getName());
                etEUDadd.setText(f.getcity());
                etEUDphone.setText(f.getmobile());
//                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        Query pkey=dbRef.child("pledge").child(custkey).orderByChild("name").equals()
        btnEUDsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etEUDname.getText().toString().isEmpty()) etEUDname.setError("Name cannot be empty");
                if(etEUDadd.getText().toString().isEmpty()) etEUDadd.setError("City cannot be empty");
                if(etEUDphone.getText().toString().isEmpty()) etEUDphone.setError("Phone cannot be empty");

                if(!etEUDname.getText().toString().isEmpty() && !etEUDadd.getText().toString().isEmpty() &&
                        !etEUDphone.getText().toString().isEmpty()) {

                    Map<String, Object> taskMap = new HashMap<String, Object>();
                    taskMap.put("name", etEUDname.getText().toString().toUpperCase());
                    taskMap.put("city", etEUDadd.getText().toString());
                    taskMap.put("mobile", etEUDphone.getText().toString());
                    firebase.child("cust").child(custkey).updateChildren(taskMap);

                }
                Toast.makeText(EditCustDialog.this, "Success", Toast.LENGTH_SHORT).show();
                onBackPressed();
               // otherset(etEUDname.getText().toString().toUpperCase());
            }
        });


    }
  /*  public void otherset(String s){
        final String ss=s;

//        updating amount name
        Query aq=dbRef.child("amount").child(custkey).orderByChild("ckey").equalTo(custkey);
        aq.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dt:dataSnapshot.getChildren()){
                    fbase f=dt.getValue(fbase.class);
                    Log.d("eud",""+f.getName()+" "+f.getprincamount());
                    Map<String, Object> task = new HashMap<String, Object>();
                    task.put("name", ss);
                    firebase.child("amount").child(custkey).child(dt.getKey()).updateChildren(task);
                }}

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //        updating pledge name
        Query pq=dbRef.child("pledge").child(custkey).orderByChild("ckey").equalTo(custkey);
        pq.addListenerForSingleValueEvent(new ValueEventListener() {
            String pk;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot data:dataSnapshot.getChildren()){
                    fbase f=data.getValue(fbase.class);
                    Log.d("eud",""+f.getName()+" "+f.getprincamount());
                    Map<String, Object> taskMap = new HashMap<String, Object>();
                    taskMap.put("name", ss);
                    pk=data.getKey();
                    oother(ss,pk);

                    //

                    //
                    firebase.child("pledge").child(custkey).child(pk).updateChildren(taskMap);





                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //   Toast.makeText(EditUserDialog.this, "Success", Toast.LENGTH_SHORT).show();
        //  onBackPressed();

    }*/

  /*  public void oother(String s,String p){
        final String ss=s;
        final String pk=p;
        //        updating interest name
        Query iq=dbRef.child("interest").child(custkey).child(pk).orderByChild("ckey").equalTo(custkey);


        iq.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot de : dataSnapshot.getChildren()) {
                    fbase f = de.getValue(fbase.class);
                    Log.d("eud", "" + f.getName() + " " + f.getAmount());
                    Map<String, Object> tMap = new HashMap<String, Object>();
                    tMap.put("name", ss);
                    String ik=de.getKey();
                    Log.d("logpk interest",pk);
                    firebase.child("interest").child(custkey).child(pk).child(ik).updateChildren(tMap);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //        updating transactions name
        Query tq=dbRef.child("transactions").child(custkey).child(pk).orderByChild("ckey").equalTo(custkey);


        tq.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren()){
                    fbase f=d.getValue(fbase.class);
                    Log.d("eud",""+f.getName()+" "+f.getAmount());
                    Map<String, Object> taskMap = new HashMap<String, Object>();
                    taskMap.put("name", ss);
                    Log.d("logpk trans",pk);
                    firebase.child("transactions").child(custkey).child(pk).child(d.getKey()).updateChildren(taskMap);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }*/
}
