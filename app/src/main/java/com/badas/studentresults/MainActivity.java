package com.badas.studentresults;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    StudentResultFragment resultFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //below from https://developer.android.com/training/basics/fragments/fragment-ui#AddAtRuntime
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }
            resultFragment = new StudentResultFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, resultFragment).commit();
        }

        ArrayList<StudentResult> resultsData = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        ArrayList<StudentResult.ResultData> data;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j<50;j++){
                data = new ArrayList<>();
                calendar.add(Calendar.DAY_OF_YEAR,j);
                data.add(new StudentResult.ResultData("Date:", new SimpleDateFormat("dd MMM YYYY", Locale.getDefault()).format(calendar.getTime())));
                for (int k = 1; k < i+1; k++)
                    data.add(new StudentResult.ResultData("Text " + k +":","Value " + k));
                resultsData.add(
                        new StudentResult()
                                .setStudent("Student "+i)
                                .setHeader("Item: " + j)
                                .setResultsData(data)
                );
            }
        }

        resultFragment.attachDataSet(resultsData);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}