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

public class track_order extends AppCompatActivity {
    DatabaseHelper myDb;
    StringBuffer table_id_buffer;
    StringBuffer order_status_buffer;
    String[] table_id_arr;
    String[] order_status_arr;
    private ListView mlistView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_order);
        ListView listView = (ListView) findViewById(R.id.listview_view_order);

        myDb = new DatabaseHelper(this);
        Cursor res = myDb.getAllOrderData();

        if (res.getCount() == 0) {
            Toast.makeText(track_order.this, "No Data Found", Toast.LENGTH_SHORT).show();
            return;
        }

        table_id_buffer = new StringBuffer();
        order_status_buffer = new StringBuffer();
        while (res.moveToNext()) {
            table_id_buffer.append(res.getString(1) + "_");
            order_status_buffer.append(res.getString(4) + "_");


        }
        table_id_arr = toStringArr(table_id_buffer);
        order_status_arr = toStringArr(order_status_buffer);
        track_order.CustomAdapter customAdapter = new track_order.CustomAdapter();
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
            return table_id_arr.length;
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

            view = getLayoutInflater().inflate(R.layout.custom_track_order_layout, null);
            TextView table = (TextView) view.findViewById(R.id.table_id);

            TextView status = (TextView) view.findViewById(R.id.status);

            table.setText(table_id_arr[i]);
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
