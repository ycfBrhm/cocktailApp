package com.example.coctailapp.activities;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.coctailapp.R;
import com.example.coctailapp.fragments.MainGridFragment;
import com.example.coctailapp.fragments.MainListFragment;

public class MainActivity extends AppCompatActivity  {

    private ImageButton btnTypeGrid,btnTypeList;
    ImageView btnTryedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTypeGrid=findViewById(R.id.btnImageTypeGrid);
        btnTypeList=findViewById(R.id.btnImageTypeList);
        btnTryedList=findViewById(R.id.imageTryedList); ///---------------
        btnTypeGrid.setVisibility(View.VISIBLE);
        btnTypeList.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, MainListFragment.class, null)
                .commit();


        btnTypeList.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, MainListFragment.class, null)
                    .commit();
            btnTypeList.setVisibility(View.GONE);
            btnTypeGrid.setVisibility(View.VISIBLE);
        });


        btnTypeGrid.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, MainGridFragment.class, null)
                    .commit();
            btnTypeList.setVisibility(View.VISIBLE);
            btnTypeGrid.setVisibility(View.GONE);
        });

        btnTryedList.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), TryedListActivity.class)));

    }



}