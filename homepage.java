package thebetadeveloper.app.first_avenue_customer;

import android.app.ActionBar;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;

public class homepage extends AppCompatActivity {
    DatabaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        myDB = new DatabaseHelper(this);



    }


    public void cust_dash(View view)
    {
        Intent cust_dash_intent = new Intent(this,MainActivity.class);
        startActivity(cust_dash_intent);
    }

    public void mng_dash(View view)
    {
        Intent mng_dash_intent = new Intent(this,mng_dashboard.class);
        startActivity(mng_dash_intent);
    }
    public void cook_dash(View view)
    {
        Intent cook_dash_intent = new Intent(this,cook_login.class);
        startActivity(cook_dash_intent);
    }
    public void staff_dash(View view)
    {
        Intent staff_dash_intent = new Intent(this,staff_log_in.class);
        startActivity(staff_dash_intent);


    }
}
