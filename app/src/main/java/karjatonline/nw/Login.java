package karjatonline.nw;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String pin = "pinKey";

    SharedPreferences sharedpreferences;
    SharedPreferences.Editor ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences(Prefs.StoreKey, Context.MODE_PRIVATE);
        Toast.makeText(this, ""+sharedpreferences.getString(), Toast.LENGTH_SHORT).show();
    }
}
