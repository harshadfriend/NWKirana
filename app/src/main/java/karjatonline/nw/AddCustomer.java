package karjatonline.nw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class AddCustomer extends AppCompatActivity {
    EditText etname,etcity,etmobile;
    Firebase firebase;
    String dburl="https://nwkirana-3eb2e.firebaseio.com/";
//    String dburl=this.getString(R.string.url);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);


        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);

        etname=findViewById(R.id.etname);
        etcity=findViewById(R.id.etcity);
        etmobile=findViewById(R.id.etmobile);

        Button btn=findViewById(R.id.btnSubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbase fb=new fbase();
                fbase fb2=new fbase();
                if(etname.getText().toString().isEmpty()) etname.setError("Enter Name");
                if(etcity.getText().toString().isEmpty()) etcity.setError("Enter City");
                if(etmobile.getText().toString().isEmpty()) etmobile.setError("Enter Mobile");

                if(!etname.getText().toString().isEmpty() && !etcity.getText().toString().isEmpty() && !etmobile.getText().toString().isEmpty()) {
                    fb.setcity(etcity.getText().toString());
                    fb.setName(etname.getText().toString().toUpperCase());
                    fb.setmobile(etmobile.getText().toString());

                    fb2.setName(etname.getText().toString());
                    Log.d("logg", "inside if");
                    firebase.child("cust").push().setValue(fb);
                    //firebase.child("orders").child(etname.getText().toString());
//                    firebase.child("transactions").child(etname.getText().toString());


                    Toast.makeText(AddCustomer.this, "Success", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });


    }
}
