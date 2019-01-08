package karjatonline.nw;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePin extends AppCompatActivity {
    EditText etNewPin,etOldPin;
    Button btnChangePin;

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pin);

        etNewPin=findViewById(R.id.etNewPin);
        etOldPin=findViewById(R.id.etOldPin);

        btnChangePin=findViewById(R.id.btnChangePin);

        sharedpreferences = getSharedPreferences(Prefs.StoreKey, Context.MODE_PRIVATE);

        btnChangePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etOldPin.getText().toString().isEmpty() || etNewPin.getText().toString().isEmpty()){
                    Toast.makeText(ChangePin.this, "Fields cannot be empty !", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(etOldPin.getText().toString().equals(sharedpreferences.getString(Prefs.PinKey,"no value"))){

                        ed = sharedpreferences.edit();
                        ed.putString(Prefs.PinKey, etNewPin.getText().toString());
                        ed.apply();
                        Toast.makeText(ChangePin.this, "Pin changed successfully !", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                    else{
                        Toast.makeText(ChangePin.this, "Incorrect old pin !", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }
}
