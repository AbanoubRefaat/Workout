package com.example.android.workout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        WorkoutDetailFragment frag = (WorkoutDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
//        frag.setWorkoutId(1);
//        //make w reference to the 2nd fragment
//        WorkoutListFragment frag2 = (WorkoutListFragment)getSupportFragmentManager().findFragmentById(R.id.list_frag);


    }

    @Override
    public void itemClicked(long id) {

        View view = (View)findViewById(R.id.fragment_container);
        if (view != null){

            WorkoutDetailFragment detailFragment = new WorkoutDetailFragment();

            //Start the fragment transaction.
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            detailFragment.setWorkoutId(id);

            //Replace the fragment and add it to the back stack.
            ft.replace(R.id.fragment_container,detailFragment);
            ft.addToBackStack(null);

            //Get the new and old fragments to fade in and out
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }else {
            Intent intent = new Intent(this,DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORK_ID,(int) id);
            startActivity(intent);
        }
    }
}
