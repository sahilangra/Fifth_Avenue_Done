package thebetadeveloper.app.first_avenue_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class add_offers extends AppCompatActivity {
DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offers);
        myDb=new DatabaseHelper(this);
    }

    public void add_offer(View view)
    {
        EditText editText1 = (EditText) findViewById(R.id.min_order_intake);
        String min_order_check = editText1.getText().toString();
        int min_order_value = Integer.parseInt(editText1.getText().toString());

        EditText editText2 = (EditText) findViewById(R.id.offer_off);
        String off_value_check = editText2.getText().toString();
        int off_value = Integer.parseInt(editText2.getText().toString());

        if((min_order_check.equals(""))||(off_value_check.equals("")))
        {
            Toast.makeText(this,"Enter All Data to Continue",Toast.LENGTH_LONG).show();
            return;
        }

        myDb.addOffer(min_order_value,off_value);
        Toast.makeText(this,"Offer Added",Toast.LENGTH_LONG);
    }
}
