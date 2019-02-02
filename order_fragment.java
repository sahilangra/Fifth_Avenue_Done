package thebetadeveloper.app.first_avenue_customer;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class order_fragment extends Fragment {
    DatabaseHelper myDb;

    StringBuffer food_name_buffer;
    StringBuffer food_price_buffer;

    String[] food_name_arr;
    String[] food_price_arr;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myDb=new DatabaseHelper(getContext());
        return inflater.inflate(R.layout.layout_order_fragment,null);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myDb=new DatabaseHelper(getContext());
        Cursor res = myDb.getAllFoodData();

        if(res.getCount() == 0) {
            Toast.makeText(getContext(),"No Data Found",Toast.LENGTH_SHORT).show();
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


        ListView listView = (ListView) view.findViewById(R.id.list_view);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);



    }
    class CustomAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return food_name_arr.length;
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
//            TextView order = (TextView) view.findViewById(R.id.cust_dish);
//            TextView food = (TextView) view.findViewById(R.id.cust_price);

//            order.setText(food_name_arr[i]);
//            food.setText(food_price_arr[i]);


            return view;
        }
    }
    public static String[] toStringArr(StringBuffer sb)
    {
        String s=sb.toString();
        String[] arr=s.split("\n");
        return arr;
    }
}
