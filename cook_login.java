package thebetadeveloper.app.first_avenue_customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class cook_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_login);
    }
    public void chef_dash(View view) {

        EditText username_ref = (EditText) findViewById(R.id.chef_user_field);
        String username = username_ref.getText().toString();
        EditText password_ref = (EditText) findViewById(R.id.chef_password_field);
        String paswd = password_ref.getText().toString();

        String expctd_username = "chef";
        String expctd_pswd = "12345" ;

        if(username.equals(expctd_username)&&paswd.equals(expctd_pswd)) {
            Intent chef_dash_intent = new Intent(this, view_order.class);
            startActivity(chef_dash_intent);
        }
        else if (username.equals(expctd_username))
        {
            password_ref.setError("Error");
        }

        else if (paswd.equals(expctd_pswd))
        {
            username_ref.setError("Error");
        }
        else
        {
            Toast.makeText(this,"Invalid Credential",Toast.LENGTH_LONG).show();
        }
        username_ref.getText().clear();
        password_ref.getText().clear();

    }
}
