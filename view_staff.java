package thebetadeveloper.app.first_avenue_customer;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class view_staff extends AppCompatActivity {
    DatabaseHelper myDb;
    private ListView mlistView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_staff);
        myDb = new DatabaseHelper(this);
        mlistView = (ListView)findViewById(R.id.list);

        Cursor res = myDb.getAllStaffData();
        if(res.getCount() == 0) {
            Toast.makeText(view_staff.this,"No Data Found",Toast.LENGTH_LONG).show();
            return;
        }

        StringBuffer user_buffer = new StringBuffer();

        while (res.moveToNext()) {
            user_buffer.append("Username:"+ res.getString(0)+"\n"+res.getString(1));


        }

        populate_staff_view();

    }


        public void populate_staff_view()
        {
            Cursor data = myDb.getAllStaffData();


            ArrayList<String> username_list = new ArrayList<>();



            while(data.moveToNext())
            {
                username_list.add("Username : "+data.getString(0)+"\n"+"Password : "+data.getString(1));
            }

            ListAdapter adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,username_list);
            mlistView.setAdapter(adapter);


        }

}
