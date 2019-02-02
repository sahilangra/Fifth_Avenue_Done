package thebetadeveloper.app.first_avenue_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class delete_offer extends AppCompatActivity {
DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_offer);
        myDb=new DatabaseHelper(this);
    }
    public void delete_it(View v)
    {
        EditText editText = (EditText) findViewById(R.id.input_min_value);
        int intake_delete = Integer.parseInt(editText.getText().toString());
        boolean d_flag = myDb.deleteOffer(intake_delete);

        if(d_flag)
        {
            Toast.makeText(this,"Offer Deleted successfully",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Could not delete successfully",Toast.LENGTH_SHORT).show();
        }

    }
}
