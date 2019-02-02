package thebetadeveloper.app.first_avenue_customer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;


public class feedback_fragment extends Fragment {

    RatingBar ratingbar_service,ratingbar_food,ratingbar_hygiene;
    Button button;
    DatabaseHelper myDb;
    int rating_service,rating_food,rating_hygiene;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myDb = new DatabaseHelper(getContext());
        return inflater.inflate(R.layout.layout_feedback_fragment,null);

    }




    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btn = (Button)view.findViewById(R.id.submit_btn);
        List<String> list=new ArrayList<String>();


        SeekBar seek=(SeekBar)view.findViewById(R.id.seek_service);
        seek.setProgress(0);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                // To convert it as discrete value
                 rating_service=progress;
                 TextView t = (TextView)view.findViewById(R.id.seek_service_view);
                 t.setText(""+rating_service);



            }
        });


        SeekBar seek2=(SeekBar)view.findViewById(R.id.seek_food);
        seek2.setProgress(0);
        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                // To convert it as discrete value
                rating_food=progress;
                TextView t = (TextView)view.findViewById(R.id.seek_food_view);
                t.setText(""+rating_food);



            }
        });


        SeekBar seek3=(SeekBar)view.findViewById(R.id.seek_hygeine);
        seek3.setProgress(0);
        seek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                // To convert it as discrete value
                rating_hygiene=progress;
                TextView t = (TextView)view.findViewById(R.id.seek_hygeine_view);
                t.setText(""+rating_hygiene);



            }
        });



        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addListenerOnButtonClick(view);
            }
        });

    }
    public void addListenerOnButtonClick(View v){

        boolean result=myDb.addFeedback(rating_service,rating_food,rating_hygiene);
        if(result==true)
        {
            Toast.makeText(getContext(),"Feedback submitted",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getContext(),"Something went Wrong",Toast.LENGTH_LONG).show();
        }
    }
    public int string_to_int(String a)
    {
        Log.v("ratee","in " +a );
       switch (a) {
           case "1.0":
               return 1;
           case "2.0":
               return 2;
           case "3.0":
               return 3;
           case "4.0":
               return 4;
           case "5.0":
               return 5;



       }
        return 0;
    }
}
