package thebetadeveloper.app.first_avenue_customer;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class mng_view_all_orders extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mng_view_all_orders);
        ListView listView = (ListView) findViewById(R.id.listview_view_order);

        myDb = new DatabaseHelper(this);
        Cursor res = myDb.getAllOrderData();

        if (res.getCount() == 0) {
            Toast.makeText(mng_view_all_orders.this, "No Data Found", Toast.LENGTH_SHORT).show();
            return;
        }

        order_id_buffer = new StringBuffer();
        food_items_buffer = new StringBuffer();
        food_quant_buffer = new StringBuffer();
        order_status_buffer = new StringBuffer();
        while (res.moveToNext()) {
            order_id_buffer.append(res.getString(0) + "_");

            food_items_buffer.append(res.getString(2) + "_");
            food_quant_buffer.append(res.getString(3) + "_");
            order_status_buffer.append(res.getString(4) + "_");


        }
        order_id_arr = toStringArr(order_id_buffer);

        food_items_arr = toStringArr(food_items_buffer);
        food_quant_arr = toStringArr(food_quant_buffer);

        order_status_arr = toStringArr(order_status_buffer);
        mng_view_all_orders.CustomAdapter customAdapter = new mng_view_all_orders.CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    public static String[] toStringArr(StringBuffer sb) {
        String s = sb.toString();
        String[] arr = s.split("_");
        return arr;
    }

    class CustomAdapter extends BaseAdapter {
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

            view = getLayoutInflater().inflate(R.layout.custom_view_order_layout, null);
            TextView order = (TextView) view.findViewById(R.id.order_id);
            TextView food = (TextView) view.findViewById(R.id.food);
            TextView quantity = (TextView) view.findViewById(R.id.quantity);
            TextView status = (TextView) view.findViewById(R.id.status);

            order.setText(order_id_arr[i]);
            food.setText(food_items_arr[i]);

            quantity.setText(food_quant_arr[i]);
            status.setText(order_status_arr[i]);

            String status_code = "";
            status_code = order_status_arr[i];

            if (status_code.equals("0")) {
                status.setText("Received");
                status.setTextColor(Color.parseColor("#FF0000"));
            }
            if (status_code.equals("1")) {
                status.setText("In " + "Kitchen");
                status.setTextColor(Color.parseColor("#0000ff"));
            }
            if (status_code.equals("2")) {
                status.setText("Served");
                status.setTextColor(Color.parseColor("#32CD32"));
            }

            return view;
        }
    }
}
