package thebetadeveloper.app.first_avenue_customer;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class mng_view_feedback extends AppCompatActivity {
DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mng_view_feedback);
        myDb=new DatabaseHelper(this);
        List<Integer> service_list=new ArrayList<Integer>();
        List<Integer> food_list=new ArrayList<Integer>();
        List<Integer> hygiene_list=new ArrayList<Integer>();
        Cursor res = myDb.getAllFeedback();
        if(res.getCount() == 0) {
            Toast.makeText(mng_view_feedback.this,"No Data Found",Toast.LENGTH_LONG).show();
            return;
        }
        while (res.moveToNext()) {
            service_list.add(Integer.parseInt(res.getString(0)));
            food_list.add(Integer.parseInt(res.getString(1)));
            hygiene_list.add(Integer.parseInt(res.getString(2)));

        }
        int service_mean=0;
        int sum = 0;
        for (int i : service_list) {
            sum += i;
        }
        service_mean = sum / service_list.size();
        TextView service = (TextView) findViewById(R.id.service_value);
        service.setText(""+service_mean);

        int food_mean=0;
        int sum_food = 0;
        for (int i : food_list) {
            sum_food += i;
        }
        food_mean = sum_food / service_list.size();
        TextView food = (TextView) findViewById(R.id.food_value);
        food.setText(""+food_mean);

        int hygiene_mean=0;
        int sum_hygiene = 0;
        for (int i : food_list) {
            sum_hygiene += i;
        }
        hygiene_mean = sum_hygiene / service_list.size();
        TextView hygiene = (TextView) findViewById(R.id.hygeine_value);
        hygiene.setText(""+hygiene_mean);


    }

}
