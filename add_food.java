package thebetadeveloper.app.first_avenue_customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class add_food extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseHelper myDb;
    String item;
    EditText food_id_ref,food_name_ref,food_price_ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        myDb = new DatabaseHelper(this);
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.food_category_field);

        // Spinner click listener
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Beverages");
        categories.add("Salads");
        categories.add("Soups");
        categories.add("Deserts");
        categories.add("Main Course");
        categories.add("Appetizers");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
         item = adapterView.getItemAtPosition(i).toString();
    }


    public void add_food(View view)
    {

         food_id_ref = (EditText) findViewById(R.id.food_id_field);
        String food_id = food_id_ref.getText().toString();
         food_name_ref = (EditText) findViewById(R.id.food_name_field);
        String name = food_name_ref.getText().toString();
         food_price_ref = (EditText) findViewById(R.id.food_price_field);
        String price = food_price_ref.getText().toString();
        if((food_id.equals(""))||(name.equals(""))||(price.equals("")))
        {
            Toast.makeText(this,"Enter All Data to Continue",Toast.LENGTH_LONG).show();
            return;
        }
        boolean result=myDb.insertFoodItem(food_id,item,name,price);

        if(result==true)
        {
            Toast.makeText(add_food.this,"Data Inserted",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(add_food.this,"Something went Wrong",Toast.LENGTH_SHORT).show();
        }

        food_id_ref.getText().clear();
        food_name_ref.getText().clear();
        food_price_ref.getText().clear();

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
