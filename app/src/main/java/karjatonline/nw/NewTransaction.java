package karjatonline.nw;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class NewTransaction extends AppCompatActivity {
//    String dburl="https://nwkirana-3eb2e.firebaseio.com/";
//    String dburl="https://kanifnathstore.firebaseio.com/";
String dburl;
    Firebase firebase;
    DatabaseReference dbRef;

    String name,custkey;
    static String date;

    TextView tvName;
    static TextView tvDate;
    EditText etAmount;
    Button btnCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        dburl=getResources().getString(R.string.url);

        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);
        dbRef = FirebaseDatabase.getInstance().getReference();

        setTitle("New Transaction");

        Bundle extras=getIntent().getExtras();
        if (extras != null) {
            name=extras.getString("name");
            custkey=extras.getString("custkey");
        }

        // Toast.makeText(this, ""+name+"\n"+custkey, Toast.LENGTH_SHORT).show();

        tvName=findViewById(R.id.tvnameNewTrans);
        tvName.setText(name);

        tvDate=findViewById(R.id.tvDate);

        etAmount=findViewById(R.id.etAmount);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        date=day+"/"+(month+1)+"/"+year;
        tvDate.setText(date);

        btnCredit=findViewById(R.id.btnCredit);
        btnCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etAmount.getText().toString().isEmpty()){
                    fbase f=new fbase();
                    f.setAmount(etAmount.getText().toString());
                    f.setDate(tvDate.getText().toString());
                    firebase.child("transactions").child(custkey).push().setValue(f);
                    Toast.makeText(NewTransaction.this, "Success !", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
                else
                    Toast.makeText(NewTransaction.this, "Enter valid amount !", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static void setDate(int x,int y,int z){
        date=x+"/"+y+"/"+z;
        tvDate.setText(date);
    }

    public void showDatePickerDialog(View v) {
        // Toast.makeText(this, "datepicker touched", Toast.LENGTH_SHORT).show();
        DialogFragment newFragment = new NewTransaction.DatePickerFragment();
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
            NewTransaction.setDate(day,(month+1),year);
        }
    }
}