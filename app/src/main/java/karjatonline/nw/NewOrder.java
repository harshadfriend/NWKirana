package karjatonline.nw;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class NewOrder extends AppCompatActivity {

    String dburl="https://nwkirana-3eb2e.firebaseio.com/";
    Firebase firebase;
    DatabaseReference dbRef;

    double total=0;

    String[] sP,sR,sQ;
    AutoCompleteTextView actvP1;
    EditText etQ1;
    TextView tvR1;
    TextView tvS1;
    TextView tvT1;

    //TextView tvNOrate,tvNOtotal,tvNOstock;
    static TextView tvNOname;
    ArrayAdapter<String> adp;
    static String name,date;
    String custkey,orderKey;
    Button btnPlaceOrder,btnAddItem;

    int fabnewitemcount=0;

    ListView lvorderitemlist;


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
        orderKey=firebase.push().getKey();

        lvorderitemlist=findViewById(R.id.lvorderitemlist);

        btnPlaceOrder=findViewById(R.id.btnPlaceOrder);
        btnAddItem=findViewById(R.id.btnAddItem);

        actvP1=findViewById(R.id.etp1);

        etQ1=findViewById(R.id.etQ1);

        tvT1=findViewById(R.id.tvT1);

        tvR1=findViewById(R.id.tvR1);

        tvS1=findViewById(R.id.tvS1);

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
                  //  Toast.makeText(NewOrder_old.this, ""+custkey, Toast.LENGTH_SHORT).show();
                    Log.d("logd",""+custkey);
                }

                setlist();
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
//                String orderKey=firebase.push().getKey();
                firebase.child("orders").child(custkey).child(orderKey).setValue(f);
                onBackPressed();
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbase f=new fbase();
                f.setDate(date);
                f.setName(name);
                f.setItem(actvP1.getText().toString());
                f.setPquantity(etQ1.getText().toString());
                orderKey=firebase.push().getKey();
                firebase.child("orders").child(custkey).child(orderKey).setValue(f);

                etQ1.setText("");tvR1.setText("");tvS1.setText("");actvP1.setText("");tvT1.setText("0");
//                onBackPressed();
            }
        });




    }

    public void setlist(){
        Query getitems=dbRef.child("orders").child(custkey).child(orderKey);
        getitems.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String[] str=new String[(int)dataSnapshot.getChildrenCount()];
                int i=0;
                for (DataSnapshot data:dataSnapshot.getChildren()){
                    fbase f=data.getValue(fbase.class);
                    str[i]=f.getItem();
                    i++;

                }
                ArrayAdapter<String> a=new ArrayAdapter<String>(NewOrder.this,android.R.layout.simple_list_item_1,str);
                a.setNotifyOnChange(true);
                lvorderitemlist.setAdapter(a);

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
        actvP1.setAdapter(adp);

        actvP1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  Toast.makeText(NewOrder_old.this, "selected", Toast.LENGTH_SHORT).show();
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

            //   Toast.makeText(NewOrder_old.this, "editor", Toast.LENGTH_SHORT).show();
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