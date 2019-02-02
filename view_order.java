package thebetadeveloper.app.first_avenue_customer;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class view_order extends AppCompatActivity {
    DatabaseHelper myDb;
    StringBuffer order_id_buffer;
    StringBuffer food_items_buffer;
    StringBuffer food_quant_buffer;
    StringBuffer order_status_buffer;
    String[] order_id_arr;
    String[] food_items_arr;
    String[] food_quant_arr;
    String[] order_status_arr;
    private ListView mlistView;
    int input_status;
    private RadioGroup radio_Group;
    private RadioButton radio_Button;
    private Button btnDisplay;
    String order_update_method = "";
    String ab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        ListView listView = (ListView)findViewById(R.id.listview_view_order);

        myDb=new DatabaseHelper(this);
        Cursor res = myDb.getAllOrderData();

        if(res.getCount() == 0) {
            Toast.makeText(view_order.this,"No Data Found",Toast.LENGTH_SHORT).show();
            return;
        }

        order_id_buffer = new StringBuffer();
        food_items_buffer = new StringBuffer();
        food_quant_buffer = new StringBuffer();
        order_status_buffer = new StringBuffer();
        while (res.moveToNext()) {
            order_id_buffer.append(res.getString(0)+"_");

            food_items_buffer.append( res.getString(2)+"_");
            food_quant_buffer.append( res.getString(3)+"_");
            order_status_buffer.append(res.getString(4)+"_");


        }
        order_id_arr=toStringArr(order_id_buffer);

        food_items_arr=toStringArr(food_items_buffer);
        food_quant_arr=toStringArr(food_quant_buffer);

        order_status_arr=toStringArr(order_status_buffer);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);


        addListenerOnButton();

    }

    public void get_status (View V)
    {
        EditText a = (EditText)findViewById(R.id.input_status_code);
         ab = a.getText().toString();
        if(ab.equals(""))
        {
            Toast.makeText(this , "Enter A Valid Order ID",Toast.LENGTH_LONG).show();
        }
        else
        { input_status = Integer.parseInt(a.getText().toString());
            LinearLayout main = (LinearLayout)findViewById(R.id.main_update);
            main.setVisibility(View.VISIBLE);

        }


    }

    public void addListenerOnButton() {

        radio_Group = (RadioGroup) findViewById(R.id.radio_grps);
        btnDisplay = (Button) findViewById(R.id.update);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radio_Group.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radio_Button = (RadioButton) findViewById(selectedId);


                order_update_method = radio_Button.getText().toString();
                Toast.makeText(view_order.this,
                       order_update_method, Toast.LENGTH_SHORT).show();
                if(order_update_method.equals("order processing"))
                {
                    Log.v("oooo","entered");
                    myDb.updateOrderStatus(Integer.parseInt(ab),1);

                }
                else if(order_update_method.equals("order delivered"))
                {
                    myDb.updateOrderStatus(Integer.parseInt(ab),2);

                }
                //ListView myListView = (ListView)findViewById(R.id.listview_view_order);
                //myListView.invalidateViews();
                finish();
                startActivity(getIntent());


            }

        });

    }

    public static String[] toStringArr(StringBuffer sb)
    {
        String s=sb.toString();
        String[] arr=s.split("_");
        return arr;
    }

    class CustomAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return order_id_arr.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.custom_view_order_layout,null);
            TextView order = (TextView)view.findViewById(R.id.order_id);
            TextView food = (TextView)view.findViewById(R.id.food);
            TextView quantity = (TextView)view.findViewById(R.id.quantity);
            TextView status = (TextView)view.findViewById(R.id.status);

            order.setText(order_id_arr[i]);
            food.setText(food_items_arr[i]);

            quantity.setText(food_quant_arr[i]);
            status.setText(order_status_arr[i]);

            String status_code = "";
            status_code = order_status_arr[i];

            if(status_code.equals("0"))
            {
                status.setText("Received");
                status.setTextColor(Color.parseColor("#FF0000"));
            }
            if(status_code.equals("1"))
            {
                status.setText("In " + "Kitchen");
                status.setTextColor(Color.parseColor("#0000ff"));
            }
            if(status_code.equals("2"))
            {
                status.setText("Served");
                status.setTextColor(Color.parseColor("#32CD32"));
            }

            return view;
        }
    }
}