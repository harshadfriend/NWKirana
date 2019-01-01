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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
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
    AutoCompleteTextView actvP1,actvP2,actvP3,actvP4,actvP5,actvP6,actvP7,actvP8,actvP9,actvP10,
            actvP11,actvP12,actvP13,actvP14,actvP15;
    EditText etQ1,etQ2,etQ3,etQ4,etQ5,etQ6,etQ7,etQ8,etQ9,etQ10,etQ11,etQ12,etQ13,etQ14,etQ15;
    TextView tvR1,tvR2,tvR3,tvR4,tvR5,tvR6,tvR7,tvR8,tvR9,tvR10,tvR11,tvR12,tvR13,tvR14,tvR15;
    TextView tvS1,tvS2,tvS3,tvS4,tvS5,tvS6,tvS7,tvS8,tvS9,tvS10,tvS11,tvS12,tvS13,tvS14,tvS15;
    TextView tvT1,tvT2,tvT3,tvT4,tvT5,tvT6,tvT7,tvT8,tvT9,tvT10,tvT11,tvT12,tvT13,tvT14,tvT15;

    //TextView tvNOrate,tvNOtotal,tvNOstock;
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
        setContentView(R.layout.activity_new_order_old);

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
        actvP2=findViewById(R.id.etp2);
        actvP3=findViewById(R.id.etp3);
        actvP4=findViewById(R.id.etp4);
        actvP5=findViewById(R.id.etp5);
        actvP6=findViewById(R.id.etp6);
        actvP7=findViewById(R.id.etp7);
        actvP8=findViewById(R.id.etp8);
        actvP9=findViewById(R.id.etp9);
        actvP10=findViewById(R.id.etp10);
        actvP11=findViewById(R.id.etp11);
        actvP12=findViewById(R.id.etp12);
        actvP13=findViewById(R.id.etp13);
        actvP14=findViewById(R.id.etp14);
        actvP15=findViewById(R.id.etp15);

        etQ1=findViewById(R.id.etQ1);
        etQ2=findViewById(R.id.etQ2);
        etQ3=findViewById(R.id.etQ3);
        etQ4=findViewById(R.id.etQ4);
        etQ5=findViewById(R.id.etQ5);
        etQ6=findViewById(R.id.etQ6);
        etQ7=findViewById(R.id.etQ7);
        etQ8=findViewById(R.id.etQ8);
        etQ9=findViewById(R.id.etQ9);
        etQ10=findViewById(R.id.etQ10);
        etQ11=findViewById(R.id.etQ11);
        etQ12=findViewById(R.id.etQ12);
        etQ13=findViewById(R.id.etQ13);
        etQ14=findViewById(R.id.etQ14);
        etQ15=findViewById(R.id.etQ15);

        tvT1=findViewById(R.id.tvT1);
        tvT2=findViewById(R.id.tvT2);
        tvT3=findViewById(R.id.tvT3);
        tvT4=findViewById(R.id.tvT4);
        tvT5=findViewById(R.id.tvT5);
        tvT6=findViewById(R.id.tvT6);
        tvT7=findViewById(R.id.tvT7);
        tvT8=findViewById(R.id.tvT8);
        tvT9=findViewById(R.id.tvT9);
        tvT10=findViewById(R.id.tvT10);
        tvT11=findViewById(R.id.tvT11);
        tvT12=findViewById(R.id.tvT12);
        tvT13=findViewById(R.id.tvT13);
        tvT14=findViewById(R.id.tvT14);
        tvT15=findViewById(R.id.tvT15);

        tvR1=findViewById(R.id.tvR1);
        tvR2=findViewById(R.id.tvR2);
        tvR3=findViewById(R.id.tvR3);
        tvR4=findViewById(R.id.tvR4);
        tvR5=findViewById(R.id.tvR5);
        tvR6=findViewById(R.id.tvR6);
        tvR7=findViewById(R.id.tvR7);
        tvR8=findViewById(R.id.tvR8);
        tvR9=findViewById(R.id.tvR9);
        tvR10=findViewById(R.id.tvR10);
        tvR11=findViewById(R.id.tvR11);
        tvR12=findViewById(R.id.tvR12);
        tvR13=findViewById(R.id.tvR13);
        tvR14=findViewById(R.id.tvR14);
        tvR15=findViewById(R.id.tvR15);

        tvS1=findViewById(R.id.tvS1);
        tvS2=findViewById(R.id.tvS2);
        tvS3=findViewById(R.id.tvS3);
        tvS4=findViewById(R.id.tvS4);
        tvS5=findViewById(R.id.tvS5);
        tvS6=findViewById(R.id.tvS6);
        tvS7=findViewById(R.id.tvS7);
        tvS8=findViewById(R.id.tvS8);
        tvS9=findViewById(R.id.tvS9);
        tvS10=findViewById(R.id.tvS10);
        tvS11=findViewById(R.id.tvS11);
        tvS12=findViewById(R.id.tvS12);
        tvS13=findViewById(R.id.tvS13);
        tvS14=findViewById(R.id.tvS14);
        tvS15=findViewById(R.id.tvS15);


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
        actvP1.setAdapter(adp);
        actvP2.setAdapter(adp);
        actvP3.setAdapter(adp);
        actvP4.setAdapter(adp);
        actvP5.setAdapter(adp);
        actvP6.setAdapter(adp);
        actvP7.setAdapter(adp);
        actvP8.setAdapter(adp);
        actvP9.setAdapter(adp);
        actvP10.setAdapter(adp);
        actvP11.setAdapter(adp);
        actvP12.setAdapter(adp);
        actvP13.setAdapter(adp);
        actvP14.setAdapter(adp);
        actvP15.setAdapter(adp);


        actvP1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(NewOrder.this, "selected", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < sP.length ; i++) {
                    if(sP[i].equals(parent.getItemAtPosition(position).toString())){
                        tvR1.setText(sR[i]);
                        tvS1.setText(sQ[i]);
                        //disable setquantity and set quantity as 0
                        if (sQ[i].equals("0")){
                            etQ1.setText("0");
                            etQ1.setEnabled(false);
                        }
                    }
                }
                total=Double.parseDouble(tvR1.getText().toString())*Double.parseDouble(etQ1.getText().toString());
                tvT1.setText(""+total);
            }
        });


       etQ1.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

            //   Toast.makeText(NewOrder.this, "editor", Toast.LENGTH_SHORT).show();
               if(s.toString().length()>0){
                   total=Double.parseDouble(tvR1.getText().toString())*Double.parseDouble(etQ1.getText().toString());
                   tvT1.setText(""+total);
                   fbase f=new fbase();

               }
               else {
                   tvT1.setText("0");
               }

           }

           @Override
           public void afterTextChanged(Editable s) {

           }
       });

        actvP2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(NewOrder.this, "selected", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < sP.length ; i++) {
                    if(sP[i].equals(parent.getItemAtPosition(position).toString())){
                        tvR2.setText(sR[i]);
                        tvS2.setText(sQ[i]);
                        //disable setquantity and set quantity as 0
                        if (sQ[i].equals("0")){
                            etQ2.setText("0");
                            etQ2.setEnabled(false);
                        }
                    }
                }
                total=Double.parseDouble(tvR2.getText().toString())*Double.parseDouble(etQ2.getText().toString());
                tvT2.setText(""+total);
            }
        });


        etQ2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //   Toast.makeText(NewOrder.this, "editor", Toast.LENGTH_SHORT).show();
                if(s.toString().length()>0){
                    total=Double.parseDouble(tvR2.getText().toString())*Double.parseDouble(etQ2.getText().toString());
                    tvT2.setText(""+total);
                    fbase f=new fbase();

                }
                else {
                    tvT2.setText("0");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        actvP3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(NewOrder.this, "selected", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < sP.length ; i++) {
                    if(sP[i].equals(parent.getItemAtPosition(position).toString())){
                        tvR3.setText(sR[i]);
                        tvS3.setText(sQ[i]);
                        //disable setquantity and set quantity as 0
                        if (sQ[i].equals("0")){
                            etQ3.setText("0");
                            etQ3.setEnabled(false);
                        }
                    }
                }
                total=Double.parseDouble(tvR3.getText().toString())*Double.parseDouble(etQ3.getText().toString());
                tvT3.setText(""+total);
            }
        });


        etQ3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //   Toast.makeText(NewOrder.this, "editor", Toast.LENGTH_SHORT).show();
                if(s.toString().length()>0){
                    total=Double.parseDouble(tvR3.getText().toString())*Double.parseDouble(etQ3.getText().toString());
                    tvT3.setText(""+total);
                    fbase f=new fbase();

                }
                else {
                    tvT3.setText("0");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        actvP4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(NewOrder.this, "selected", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < sP.length ; i++) {
                    if(sP[i].equals(parent.getItemAtPosition(position).toString())){
                        tvR4.setText(sR[i]);
                        tvS4.setText(sQ[i]);
                        //disable setquantity and set quantity as 0
                        if (sQ[i].equals("0")){
                            etQ4.setText("0");
                            etQ4.setEnabled(false);
                        }
                    }
                }
                total=Double.parseDouble(tvR4.getText().toString())*Double.parseDouble(etQ4.getText().toString());
                tvT4.setText(""+total);
            }
        });


        etQ4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                //   Toast.makeText(NewOrder.this, "editor", Toast.LENGTH_SHORT).show();
                if(s.toString().length()>0){
                    total=Double.parseDouble(tvR4.getText().toString())*Double.parseDouble(etQ4.getText().toString());
                    tvT4.setText(""+total);
                    fbase f=new fbase();

                }
                else {
                    tvT4.setText("0");
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