package karjatonline.nw;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class AddProduct extends AppCompatActivity {
    EditText etPname,etPquantity,etPrate;
    Firebase firebase;
//    String dburl="https://nwkirana-3eb2e.firebaseio.com/";
//    String dburl="https://kanifnathstore.firebaseio.com/";
    String dburl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        dburl=getResources().getString(R.string.url);

        setTitle("Add Product");

        Firebase.setAndroidContext(this);
        firebase=new Firebase(dburl);

        etPname=findViewById(R.id.etPname);
        etPquantity=findViewById(R.id.etPquantity);
        etPrate=findViewById(R.id.etPrate);

        Button btn=findViewById(R.id.btnSubmit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbase fb=new fbase();
                fbase fb2=new fbase();
                if(etPname.getText().toString().isEmpty()) etPname.setError("Enter Name");
                if(etPquantity.getText().toString().isEmpty()) etPquantity.setError("Enter City");
                if(etPrate.getText().toString().isEmpty()) etPrate.setError("Enter Mobile");

                if(!etPname.getText().toString().isEmpty() && !etPquantity.getText().toString().isEmpty() && !etPrate.getText().toString().isEmpty()) {
                    fb.setPquantity(etPquantity.getText().toString());
//                    fb.setPname(etPname.getText().toString().toUpperCase());
                    fb.setPname(etPname.getText().toString());
                    fb.setPrate(etPrate.getText().toString());

                    fb2.setPquantity(etPquantity.getText().toString());
                    Log.d("logg", "inside if");
                    firebase.child("product").push().setValue(fb);
                   // firebase.child("stock").child(etPname.getText().toString()).setValue(fb2);
//                    firebase.child("transactions").child(etPname.getText().toString());


                    Toast.makeText(AddProduct.this, "Success", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });


    }
}
