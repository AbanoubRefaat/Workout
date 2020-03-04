package com.example.android.workout;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class WorkoutListFragment extends ListFragment {



    static interface WorkoutListListener{
        void itemClicked(long id);
    }

    private  WorkoutListListener listener;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        String[] names = new String[Workout.workouts.length];
        for (int i = 0 ; i <names.length ; i++){

            //  very tricky move to populate the array with the correct name
            names[i] = Workout.workouts[i].getName();

        }

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1,names);

        setListAdapter(arrayAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //This is called when the
    //fragment gets attached
    //to the activity
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.listener= (WorkoutListListener)activity;
    }

    //Tell the listener when an item
    //in the ListView is clicked.
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {

        if (listener != null){
            listener.itemClicked(id);
        }
    }
}

