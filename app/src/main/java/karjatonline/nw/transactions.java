package karjatonline.nw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class transactions extends AppCompatActivity {
    String name,custkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        Bundle extras=getIntent().getExtras();
        name=extras.getString("name");
        custkey=extras.getString("custkey");

        Toast.makeText(this, ""+name+"\n"+custkey, Toast.LENGTH_SHORT).show();
    }
}
