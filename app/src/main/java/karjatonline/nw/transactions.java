package karjatonline.nw;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class transactions extends AppCompatActivity {
    FloatingActionButton fabNewTrans;
    String name,custkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        Bundle extras=getIntent().getExtras();
        name=extras.getString("name");
        custkey=extras.getString("custkey");

        Toast.makeText(this, ""+name+"\n"+custkey, Toast.LENGTH_SHORT).show();

        fabNewTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
