package com.example.android.workout;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends Fragment implements View.OnClickListener {

    Button start , pause, reset;

    //Number of seconds displayed on the stopwatch.
    private int seconds = 0;
    //Is the stopwatch running?
    private boolean running;
    private boolean wasRunning;

    // how onCreate is applied in A fragment class
    // maybe because we need the onSavedInstanceState
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
            if (wasRunning) {
                running = true;
            }
        }
    }
    //Set the fragment’s layout, and start the
    //runTimer() method passing in the layout.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_stopwatch, container, false);

        start = (Button)layout.findViewById(R.id.bt_start);
        start.setOnClickListener(this);
        pause = (Button)layout.findViewById(R.id.bt_pause);
        pause.setOnClickListener(this);
        reset = (Button)layout.findViewById(R.id.bt_reset);
        reset.setOnClickListener(this);


        runTimer(layout);
        return layout;
    }


    //If the fragment’s paused,
    //record whether the stopwatch
    //was running and stop it.
    @Override
    public void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    //If the stopwatch was running before it
    //was paused, set it running again.
    @Override
    public void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }


    //Put the values of the
    //variables in the Bundle
    //before the activity is
    //destroyed. These are
    //used when the user
    //turns the device.

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }
    //setting the buttons

    // we cant use the onclick method inside a fragment so we are going to use onClickListener instead.
    // onCLick method are only applied in activities
    // 1)implement onCLickListener  2)setting buttons to this  3)creating onclick method

    public void onClickStart(View view) {
        running = true;
    }
    public void onClickStop(View view) {
        running = false;
    }
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    @Override
    public void onClick(View v) {

    switch (v.getId()){
        case R.id.bt_start:
           onClickStart(v);
           break;

        case R.id.bt_pause:
            onClickStop(v);
            break;

        case R.id.bt_reset:
            onClickReset(v);
            break;


    }
    }


    private void runTimer(View view) {
        final TextView timeView = (TextView) view.findViewById(R.id.tv_timer_frag);
        //Putting the code in a Handler means it
        //can run in the background thread.
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d",
                        hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


}
