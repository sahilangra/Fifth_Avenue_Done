package thebetadeveloper.app.first_avenue_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class add_staff extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText username_ref,password_ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
        myDb = new DatabaseHelper(this);

    }
    public void add_staff_member(View view)
    {
         username_ref = (EditText) findViewById(R.id.staff_user_field);
        String username = username_ref.getText().toString();
         password_ref = (EditText) findViewById(R.id.staff_password_field);
        String paswd = password_ref.getText().toString();

        if((username.equals(""))||(paswd.equals("")))
        {
            Toast.makeText(this,"Enter All Data to Continue",Toast.LENGTH_LONG).show();
            return;
        }

        boolean result=myDb.addStaff(username,paswd);
        if(result==true)
        {
            Toast.makeText(add_staff.this,"Data Inserted",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(add_staff.this,"Something went Wrong",Toast.LENGTH_LONG).show();
        }

        username_ref.getText().clear();
        username_ref.getText().clear();

    }
}
