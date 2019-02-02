package thebetadeveloper.app.first_avenue_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class del_food extends AppCompatActivity {
DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_food);
        myDb=new DatabaseHelper(this);
    }
    public void del_food_item(View view)
    {
        EditText foodid_ref = (EditText) findViewById(R.id.food_id_field);
        String foodid = foodid_ref.getText().toString();
        boolean res=myDb.deletefood(foodid);
        if(res==true)
        {
            Toast.makeText(del_food.this,"Item deleted successfully",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(del_food.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
        }
        foodid_ref.getText().clear();

    }
}
