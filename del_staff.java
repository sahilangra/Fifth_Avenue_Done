package thebetadeveloper.app.first_avenue_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class del_staff extends AppCompatActivity {
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_staff);
        myDb = new DatabaseHelper(this);
    }
    public void del_staff_member(View view)
    {
        EditText username_ref = (EditText) findViewById(R.id.staff_user_field);
        String username = username_ref.getText().toString();
        boolean res=myDb.deleteStaff(username);
        if(res==true)
        {
            Toast.makeText(del_staff.this,"Data deleted successfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(del_staff.this,"Something went Wrong",Toast.LENGTH_LONG).show();
        }
username_ref.getText().clear();
    }
}
