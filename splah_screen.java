package thebetadeveloper.app.first_avenue_customer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splah_screen extends AppCompatActivity {
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah_screen);
        myDb=new DatabaseHelper(this);
        //boolean addStaff=myDb.insertData("waiter","12345");
        //boolean addStaff=myDb.insertData("staff","12345");






        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent splash_intent = new Intent( getApplicationContext() , homepage.class);
                                          startActivity(splash_intent);
                                          finish();
                                      }
                                  },3700
        );

    }
}
