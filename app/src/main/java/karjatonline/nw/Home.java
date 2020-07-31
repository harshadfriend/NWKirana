package karjatonline.nw;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {
    Button btnCustomers,btnAddProduct,btnAddCustomer,btnProducts,btnEditCust,btnEditProduct,btnChangePin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("Seema Provision Stores");

        btnCustomers=findViewById(R.id.btnCustomer);
        btnProducts=findViewById(R.id.btnProducts);
        btnAddCustomer=findViewById(R.id.btnAddcust);
        btnAddProduct=findViewById(R.id.btnAddProduct);
        btnEditCust=findViewById(R.id.btnEditCust);
        btnEditProduct=findViewById(R.id.btnEditProduct);
        btnChangePin=findViewById(R.id.btnChangePin);

        btnChangePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,ChangePin.class));
            }
        });
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
                startActivity(new Intent(Home.this,EditCust.class));
            }
        });
        btnEditProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,EditProduct.class));
            }
        });
        btnProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,ProductList.class));
            }
        });
    }
}
