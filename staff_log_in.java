package thebetadeveloper.app.first_avenue_customer;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class staff_log_in extends AppCompatActivity {
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_log_in);
    }
    public void staff_dash(View view)
    {
        myDb = new DatabaseHelper(this);
       EditText username_ref = (EditText) findViewById(R.id.staff_user_field);
        String username = username_ref.getText().toString();
        EditText password_ref = (EditText) findViewById(R.id.staff_password_field);
        String paswd = password_ref.getText().toString();

        Cursor res = myDb.getStaffData(username,paswd);
        if(res.getCount()>0)
        {
            //Toast.makeText(staff_login.this,"Data Retrieved",Toast.LENGTH_LONG).show();
            Intent staff_dash_intent = new Intent(this, staff_dashboard.class);
            startActivity(staff_dash_intent);
        }

        else
        {

            Toast.makeText(this,"Invalid Credential",Toast.LENGTH_LONG).show();
        }
        username_ref.getText().clear();
        password_ref.getText().clear();

    }
}
