package karjatonline.nw;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Button;
import android.widget.DatePicker;
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

import java.util.Calendar;

public class NewOrder extends AppCompatActivity {

    String dburl="https://nwkirana-3eb2e.firebaseio.com/";
    Firebase firebase;
    DatabaseReference dbRef;

    double total=0;

    String[] sP,sR,sQ;
    AutoCompleteTextView actvP1,actvP2,actvP3,actvP4,actvP5,actvP6,actvP7,actvP8,actvP9,actvP10,actvP11,actvP12,actvP13,actvP14,actvP15;
    EditText etNOquantity;
    TextView tvNOrate,tvNOtotal,tvNOstock;
    static TextView tvNOname;
    ArrayAdapter<String> adp;
    static String name,date;
    String custkey;
    Button btnPlaceOrder;

    FloatingActionButton fabNOnewItem;
    int fabnewitemcount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        setTitle("New Order");

        Bundle extras=getIntent().getExtras();
        name=extras.getString("name");

        Firebase.setAndroidContext(this);

        firebase=new Firebase(dburl);
        dbRef = FirebaseDatabase.getInstance().getReference();

        fabNOnewItem=findViewById(R.id.fabNOnewItem);
        fabNOnewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnPlaceOrder=findViewById(R.id.btnPlaceOrder);

        actvP1=findViewById(R.id.etp1);

        etNOquantity=findViewById(R.id.etNOquantity);

        tvNOtotal=findViewById(R.id.tvNOtotal);

        tvNOrate=findViewById(R.id.tvNOrate);

        tvNOstock=findViewById(R.id.tvNOstock);

        tvNOname=findViewById(R.id.tvNOname);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        date=day+"/"+(month+1)+"/"+year;
        tvNOname.setText(name+", "+date);

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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        com.google.firebase.database.Query q=dbRef.child("product");
        q.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
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

        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbase f=new fbase();
                f.setDate(date);
                f.setName(name);
                f.setOrdertotal(""+total);
                String orderKey=firebase.push().getKey();
                firebase.child("orders").child(custkey).child(orderKey).setValue(f);
                onBackPressed();
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
              //  Toast.makeText(NewOrder.this, "selected", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < sP.length ; i++) {
                    if(sP[i].equals(parent.getItemAtPosition(position).toString())){
                        tvNOrate.setText(sR[i]);
                        tvNOstock.setText(sQ[i]);
                        //disable setquantity and set quantity as 0
                        if (sQ[i].equals("0")){
                            etNOquantity.setText("0");
                            etNOquantity.setEnabled(false);
                        }
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

            //   Toast.makeText(NewOrder.this, "editor", Toast.LENGTH_SHORT).show();
               if(s.toString().length()>0){
                   total=Double.parseDouble(tvNOrate.getText().toString())*Double.parseDouble(etNOquantity.getText().toString());
                   tvNOtotal.setText(""+total);
                   fbase f=new fbase();

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

    public static void setDate(int x,int y,int z){
        date=x+"/"+y+"/"+z;
        tvNOname.setText(name+", "+date);
    }

    public void showDatePickerDialog(View v) {
       // Toast.makeText(this, "datepicker touched", Toast.LENGTH_SHORT).show();
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "test");

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {



        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            NewOrder.setDate(day,(month+1),year);
        }
    }
}