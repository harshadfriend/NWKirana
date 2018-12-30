package karjatonline.nw;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CustDetails extends AppCompatActivity {
    TextView tvNameorder;
    FloatingActionButton fab;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_details);

        setTitle("Orders");

        Bundle extras=getIntent().getExtras();
        name=extras.getString("name");
        //custkey=extras.getString("custkey");

        fab=findViewById(R.id.fabNewOrder);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustDetails.this,NewOrder.class));
            }
        });

        tvNameorder=findViewById(R.id.tvnameorder);
        tvNameorder.setText(name);
    }
}
