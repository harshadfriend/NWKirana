package karjatonline.nw;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    SharedPreferences sharedpreferences;
    SharedPreferences.Editor ed;

    Button btnSubmit,btnCreatePin;

    EditText etPin,etCreatePin;
    LinearLayout llSubmitPin,llCreatePin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        setTitle("Chaitanya Kanifnath Kirana Stores");
        setTitle(getResources().getString(R.string.app_name));

        llSubmitPin=findViewById(R.id.llSubmitPin);
        llCreatePin=findViewById(R.id.llCreatePin);

        btnSubmit=findViewById(R.id.btnSubmit);
        btnCreatePin=findViewById(R.id.btnCreatePin);

        etPin=findViewById(R.id.etPin);
        etCreatePin=findViewById(R.id.etCreatePin);


        sharedpreferences = getSharedPreferences(Prefs.StoreKey, Context.MODE_PRIVATE);
        //Toast.makeText(this, ""+sharedpreferences.getString(Prefs.PinKey,"no value"), Toast.LENGTH_SHORT).show();

        if(sharedpreferences.getString(Prefs.PinKey,"no value").equals("no value")){
            llCreatePin.setVisibility(View.VISIBLE);
            llSubmitPin.setVisibility(View.GONE);

            btnCreatePin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //sharedpreferences = getSharedPreferences(Prefs.StoreKey, Context.MODE_PRIVATE);
                    if(etCreatePin.getText().toString().isEmpty()){
                        Toast.makeText(Login.this, "Pin cannot be empty !", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        ed = sharedpreferences.edit();
                        ed.putString(Prefs.PinKey, etCreatePin.getText().toString());
                        ed.apply();

                        Toast.makeText(Login.this, "Pin created successfully !", Toast.LENGTH_SHORT).show();
                        llCreatePin.setVisibility(View.GONE);
                        llSubmitPin.setVisibility(View.VISIBLE);
                    }

                }
            });

        }
        else {
            llCreatePin.setVisibility(View.GONE);
            llSubmitPin.setVisibility(View.VISIBLE);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedpreferences = getSharedPreferences(Prefs.StoreKey, Context.MODE_PRIVATE);
//                    Toast.makeText(Login.this, ""+sharedpreferences.getString(Prefs.PinKey,"no value"), Toast.LENGTH_SHORT).show();
                if(etPin.getText().toString().equals(sharedpreferences.getString(Prefs.PinKey,""))){
                    Toast.makeText(Login.this, "Login Successful !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,Home.class));
                    finish();
                }
                else {
                    Toast.makeText(Login.this, "Incorrect Pin !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
