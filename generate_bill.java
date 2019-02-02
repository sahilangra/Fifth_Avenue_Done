package thebetadeveloper.app.first_avenue_customer;


import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class generate_bill extends AppCompatActivity {
    DatabaseHelper myDb;
    StringBuffer food_items_buffer;
    StringBuffer food_quant_buffer;
    String[] food_items_arr,final_food_items,final_food_quant,price_arr;
    String[] food_quant_arr;
    private ListView mlistView;
    List<String> food_items=new ArrayList<String>();
    List<String> food_quant=new ArrayList<String>();
    List<String> food_price=new ArrayList<String>();
    String[] fi,fq;
    int price[],total;
    Cursor c;
    int tab_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_bill);

      middle_genrate_bill obj = new middle_genrate_bill();
      tab_no = obj.input_bill_number;
//      boolean yes=myDb.checkTableNumber(tab_no);
//      if(yes)
//      {
//          Toast.makeText(generate_bill.this,"No order taken for this table",Toast.LENGTH_SHORT).show();
//
//      }



          TextView order_text_view = (TextView) findViewById(R.id.table_number_view);
          order_text_view.setText("Table Number : " + tab_no);

          generate_bills();

          Button btn = (Button) findViewById(R.id.total_bill_cost);
          btn.setText("Total Bill : " + total);

          ListView listView = (ListView) findViewById(R.id.list_view);
          Customadapter customadapter = new Customadapter();
          listView.setAdapter(customadapter);




    }

    class Customadapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return final_food_items.length;
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
            view = getLayoutInflater().inflate(R.layout.custom_bill_layout,null);

            TextView first = (TextView)view.findViewById(R.id.name_dish);
            TextView second = (TextView)view.findViewById(R.id.quan_dish);
            TextView third = (TextView)view.findViewById(R.id.price_per_dish);
            TextView fourth = (TextView)view.findViewById(R.id.pricee_of_dish);


            first.setText(final_food_items[i]);
            second.setText(final_food_quant[i]);
            third.setText(price_arr[i]);
            fourth.setText(""+price[i]);



            return view;
        }

    }
    public void generate_bills(){



        myDb=new DatabaseHelper(this);
        Cursor res = myDb.getOrder(tab_no);
        if(res.getCount() == 0) {
            Toast.makeText(generate_bill.this,"No Data Found",Toast.LENGTH_SHORT).show();
            return;
        }
        food_items_buffer = new StringBuffer();
        food_quant_buffer = new StringBuffer();

        while (res.moveToNext()) {
            food_items_buffer.append( res.getString(2)+"_");
            food_quant_buffer.append( res.getString(3)+"_");
        }
        food_items_arr=toStringArr(food_items_buffer);
        food_quant_arr=toStringArr(food_quant_buffer);
        for(int i=0;i<food_items_arr.length;i++)
        {
            fi=food_items_arr[i].split("\n");
            fq=food_quant_arr[i].split("\n");
            for(int j=0;j<fi.length;j++)
            {
                c=myDb.foodPrice(fi[j]);
                c.moveToFirst();
                if (c != null) {
                    do {
                        for (int p = 0; p < c.getColumnCount(); p++) {
                            food_price.add(c.getString(p));
                        }
                    }while (c.moveToNext());
                }
                food_items.add(fi[j]);
                food_quant.add(fq[j]);

            }
            Log.v("uuuu",":"+food_items);
        }

        final_food_items = new String[food_items.size()];
        final_food_quant=new String[food_quant.size()];
        price_arr=new String[food_price.size()];
        price=new int[food_price.size()];

        for (int i =0; i < food_items.size(); i++){
            final_food_items[i] = food_items.get(i);
            }
        for (int i =0; i < food_quant.size(); i++)
            final_food_quant[i] = food_quant.get(i);
        for (int i =0; i < food_price.size(); i++)
            price_arr[i] = food_price.get(i);
        for (int i =0; i < food_price.size(); i++)
        {
            price[i]=Integer.parseInt(final_food_quant[i])*Integer.parseInt(price_arr[i]);

        }
        total=0;
        for(int i=0;i<price.length;i++)
        { total=total+price[i];}

//                TextView total_cost=(TextView)this.findViewById(R.id.total_bill_cost);
//        total_cost.setText(total);
        Log.v("pop","comn" + total);


    }
    public static String[] toStringArr(StringBuffer sb)
    {
        String s=sb.toString();
        String[] arr=s.split("_");
        return arr;
    }


}
