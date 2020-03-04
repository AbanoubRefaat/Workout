package com.example.android.workout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WorkoutDetailFragment extends Fragment {

    private long workoutId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // checking if there  saved data to update the view before we inflate it
        if (savedInstanceState != null){
            workoutId = savedInstanceState.getLong("workoutId", workoutId);

        }else {

            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            StopwatchFragment stopwatchFragment = new StopwatchFragment();
            transaction.replace(R.id.stopWatch_container,stopwatchFragment);
            transaction.addToBackStack(null);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }

        //add a fragment transaction to the onCreateView() method to display StopwatchFragment




        return inflater.inflate(R.layout.fragment_workout_detail, container, false);



    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("workoutId",workoutId);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null){

            TextView title = (TextView) view.findViewById(R.id.textTitle);
            // i dont really get what this whole line means
            Workout workout = Workout.workouts[(int) workoutId];
            title.setText(workout.getName());

            TextView description = (TextView)view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }

    public void setWorkoutId(long workoutId) {
        this.workoutId = workoutId;
    }


}