package thebetadeveloper.app.first_avenue_customer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseHelper myDb;
    StringBuffer minvalue_buffer;
    StringBuffer discount_buffer;
    String[] minvalue_arr;
    String[] discounts_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView)findViewById(R.id.list_view);
        myDb=new DatabaseHelper(this);
        Cursor res = myDb.getAllOfferData();

        if(res.getCount() == 0) {
            Toast.makeText(this,"No Data Found",Toast.LENGTH_SHORT).show();
            return;
        }

        minvalue_buffer = new StringBuffer();
        discount_buffer = new StringBuffer();

        while (res.moveToNext()) {
            minvalue_buffer.append(res.getString(0)+"\n");

            discount_buffer.append( res.getString(1)+"\n");


        }
        minvalue_arr=toStringArr(minvalue_buffer);

        discounts_arr=toStringArr(discount_buffer);

        CustomAdapter adap = new CustomAdapter();
        listView.setAdapter(adap);






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public static String[] toStringArr(StringBuffer sb)
    {
        String s=sb.toString();
        String[] arr=s.split("\n");
        return arr;
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.nav_order) {
            Intent in = new Intent (this,view_food.class);
            startActivity(in);


        }  else if (id == R.id.nav_feedback) {
            fragment = new feedback_fragment();
        }


        if (fragment!=null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.screen_area , fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    class CustomAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return minvalue_arr.length;
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
        public View getView(int i, View v, ViewGroup viewGroup) {

            v = getLayoutInflater().inflate(R.layout.custom_view_offers,null);

            TextView first = (TextView)v.findViewById(R.id.buy_for);
            TextView second = (TextView)v.findViewById(R.id.off_worth);
            Log.v("wwww",":"+minvalue_arr[i]);
            Log.v("wwww",":"+discounts_arr[i]);

            first.setText(minvalue_arr[i]);
            second.setText(discounts_arr[i]);
            return v;
        }
    }
}
