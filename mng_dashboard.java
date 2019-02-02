package thebetadeveloper.app.first_avenue_customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class mng_dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mng_dashboard);

    }

    public void mng_add_food(View view)
    {
        Intent mng_add_food_intent = new Intent(this, add_food.class);
        startActivity(mng_add_food_intent);
    }
    public void mng_add_staff(View view)
    {
        Intent mng_add_staff_intent = new Intent(this, add_staff.class);
        startActivity(mng_add_staff_intent);
    }
    public void mng_del_staff(View view)
    {
        Intent mng_del_staff_intent = new Intent(this, del_staff.class);
        startActivity(mng_del_staff_intent);
    }
    public void mng_del_food(View view)
    {
        Intent mng_del_food_intent = new Intent(this, del_food.class);
        startActivity(mng_del_food_intent);
    }
    public void mng_view_all_staff(View view)
    {
        Intent mng_view_staff_intent = new Intent(this, view_staff.class);
        startActivity(mng_view_staff_intent);
    }
    public void view_all_food(View view)
    {
        Intent mng_view_staff_intent = new Intent(this, view_food.class);
        startActivity(mng_view_staff_intent);
    }
    public void view_all_orders(View view)
    {
        Intent mng_view_order_intent = new Intent(this, mng_view_all_orders.class);
        startActivity(mng_view_order_intent);
    }
    public void view_all_feedback(View view)
    {
        Intent mng_view_feedback_intent = new Intent(this, mng_view_feedback.class);
        startActivity(mng_view_feedback_intent);
    }
    public void add_offers(View view)
    {
        Intent mng_view_feedback_intent = new Intent(this, add_offers.class);
        startActivity(mng_view_feedback_intent);
    }
    public void delete_offers(View view)
    {
        Intent mng_view_feedback_intent = new Intent(this, delete_offer.class);
        startActivity(mng_view_feedback_intent);
    }
}
