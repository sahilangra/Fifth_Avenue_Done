package thebetadeveloper.app.first_avenue_customer;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class staff_new_order extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int table_no;
    DatabaseHelper myDb;
    String order = null, item_quantity = null, add_order = null, add_quant = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_new_order);
        myDb = new DatabaseHelper(this);

        Spinner spinner = (Spinner) findViewById(R.id.table_number_spinner);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        List<String> categories = new ArrayList<String>();
        categories.add("1");
        categories.add("2");
        categories.add("3");
        categories.add("4");
        categories.add("5");
        categories.add("6");
        categories.add("7");
        categories.add("8");
        categories.add("9");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        //++++++++++++++++++++++++++++++++++++
        Spinner menuspinner = (Spinner) findViewById(R.id.food_show);
        List<String> menu = new ArrayList<String>();
        Cursor res = myDb.getAllFoodData();

        if (res.getCount() == 0) {
            Toast.makeText(staff_new_order.this, "No Data Found", Toast.LENGTH_SHORT).show();
            return;
        }
        while (res.moveToNext()) {
            menu.add(res.getString(2));
        }
        ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, menu);
        menuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuspinner.setAdapter(menuAdapter);


        menuspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

                add_order = parent.getItemAtPosition(i).toString();

                Log.v("qqqq", " " + item_quantity);
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //+++++++++++++++++++++++++++++++++++++
        Spinner quantspinner = (Spinner) findViewById(R.id.quantity_show);
        quantspinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        List<String> quantity = new ArrayList<String>();
        quantity.add("1");
        quantity.add("2");
        quantity.add("3");
        quantity.add("4");
        quantity.add("5");
        quantity.add("6");
        quantity.add("7");
        quantity.add("8");
        quantity.add("9");
        ArrayAdapter<String> quantAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, quantity);
        quantAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantspinner.setAdapter(quantAdapter);

        quantspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                add_quant = parent.getItemAtPosition(i).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        table_no = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
        Log.v("qqqq", " " + table_no);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void add_cart(View view) {
        if (order != null) {
            order = order + add_order + "\n";
        } else {
            order = add_order + "\n";
        }

        if (item_quantity != null) {
            item_quantity = item_quantity + add_quant + "\n";
        } else {
            item_quantity = add_quant + "\n";
        }

        Toast.makeText(this , "Dish Added to Cart",Toast.LENGTH_LONG).show();


    }
    public void place_order(View view)
    {
        myDb.insertOrder(table_no,order,item_quantity);
        Toast.makeText(staff_new_order.this,"Order Placed Successfully",Toast.LENGTH_SHORT).show();
        finish();

        }

    public void view_order(View view) {
        Cursor res = myDb.getAllOrderData();
        if (res.getCount() == 0) {
            // show message
            Log.v("hhhh", " " + res.getCount());
        }
        Log.v("qqqq", " Im here also");
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            buffer.append(res.getString(0) + "\n");
            buffer.append(res.getString(1) + "\n");
            buffer.append(res.getString(2) + "\n");
            buffer.append(res.getString(3) + "\n");
            buffer.append(res.getString(4) + "\n\n");
            Log.v("hhhh", " " + res.getString(0));
        }


//    public void showMessage(String title,String Message){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(Message);
//        builder.show();
    }
}
