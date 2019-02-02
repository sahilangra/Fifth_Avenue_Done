package thebetadeveloper.app.first_avenue_customer;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class view_food extends AppCompatActivity {
DatabaseHelper myDb;
    //StringBuffer food_id_buffer;
    StringBuffer food_name_buffer;
    StringBuffer food_price_buffer;
    //String[] food_id_arr;
    String[] food_name_arr;
    String[] food_price_arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_food);

        ListView listView = (ListView)findViewById(R.id.ListView);







        //=======DB
        myDb=new DatabaseHelper(this);
        Cursor res = myDb.getAllFoodData();

        if(res.getCount() == 0) {
            Toast.makeText(view_food.this,"No Data Found",Toast.LENGTH_SHORT).show();
            return;
        }


        food_name_buffer = new StringBuffer();
        food_price_buffer = new StringBuffer();
        while (res.moveToNext()) {


            food_name_buffer.append( res.getString(2)+"\n");
            food_price_buffer.append( res.getString(3)+"\n");

        }


        food_name_arr=toStringArr(food_name_buffer);
        food_price_arr=toStringArr(food_price_buffer);


        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
    }

    public static String[] toStringArr(StringBuffer sb)
    {
        String s=sb.toString();
        String[] arr=s.split("\n");
        return arr;
    }

    class CustomAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return food_price_arr.length;
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


            view = getLayoutInflater().inflate(R.layout.custom_food_view_layout,null);
            TextView food_price = (TextView) view.findViewById(R.id.food_price);
            TextView food_name = (TextView) view.findViewById(R.id.food_name);


            food_price.setText(food_price_arr[i]);
            food_name.setText(food_name_arr[i]);

            return view;
        }
    }





}
