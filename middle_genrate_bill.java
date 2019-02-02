package thebetadeveloper.app.first_avenue_customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class middle_genrate_bill extends AppCompatActivity {
  static  int input_bill_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_middle_genrate_bill);


    }
    public void gotogenerate(View v)
    {
        EditText input = (EditText)findViewById(R.id.input);
         input_bill_number = Integer.parseInt(input.getText().toString());



        Intent i = new Intent(middle_genrate_bill.this, generate_bill.class);

        startActivity(i);
    }
}
