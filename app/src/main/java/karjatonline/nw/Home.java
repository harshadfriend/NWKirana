package karjatonline.nw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button btnCustomers,btnAddProduct,btnAddCustomer,btnProducts,btnEditCust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnCustomers=findViewById(R.id.btnCustomer);
        btnProducts=findViewById(R.id.btnProducts);
        btnAddCustomer=findViewById(R.id.btnAddcust);
        btnAddProduct=findViewById(R.id.btnAddProcuct);
        btnEditCust=findViewById(R.id.btnEditCust);

        btnAddCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,AddCustomer.class));
            }
        });
        btnCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,CustList.class));
            }
        });
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,AddProduct.class));
            }
        });
        btnEditCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Edit.class));
            }
        });
    }
}
