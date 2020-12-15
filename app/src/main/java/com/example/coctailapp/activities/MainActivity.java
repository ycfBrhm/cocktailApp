package com.example.coctailapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.coctailapp.R;
import com.example.coctailapp.fragments.MainGridFragment;
import com.example.coctailapp.fragments.MainListFragment;

public class MainActivity extends AppCompatActivity  {

    private ImageButton btnTypeGrid,btnTypeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTypeGrid=findViewById(R.id.btnImageTypeGrid);
        btnTypeList=findViewById(R.id.btnImageTypeList);
        btnTypeGrid.setVisibility(View.VISIBLE);
        btnTypeList.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, MainListFragment.class, null)
                .commit();
        btnTypeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, MainListFragment.class, null)
                        .commit();
                btnTypeList.setVisibility(View.GONE);
                btnTypeGrid.setVisibility(View.VISIBLE);
            }
        });
        btnTypeGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragment_container_view, MainGridFragment.class, null)
                        .commit();
                btnTypeList.setVisibility(View.VISIBLE);
                btnTypeGrid.setVisibility(View.GONE);
            }
        });

    }



}