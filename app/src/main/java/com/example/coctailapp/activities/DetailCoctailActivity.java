package com.example.coctailapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;


import com.example.coctailapp.R;
import com.example.coctailapp.databinding.ActivityDetailCoctailBinding;
import com.example.coctailapp.viewmodels.AlcoholicCoctailsViewModel;
import com.example.coctailapp.viewmodels.DetailsCoctailViewModel;

public class DetailCoctailActivity extends AppCompatActivity {

    private ActivityDetailCoctailBinding activityDetailCoctailBinding;
    private DetailsCoctailViewModel detailsCoctailViewModel;
    public static final String ID_DETAIL_COCkTAIL_ACTIVITY="com.example.coctailapp.activities.ID_DETAIL_COCkTAIL_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailCoctailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_coctail);
        doInitializeation();
    }

    private void doInitializeation(){
        detailsCoctailViewModel = new ViewModelProvider(this).get(DetailsCoctailViewModel.class);
        getDetailsCoctail();
    }


    private void getDetailsCoctail(){
        activityDetailCoctailBinding.setIsLoading(true);
        String coctailId = String.valueOf(getIntent().getIntExtra(ID_DETAIL_COCkTAIL_ACTIVITY, -1));
        detailsCoctailViewModel.getDetailsCoctail(coctailId).observe(
                this, detailCoctailResponse -> {
                    activityDetailCoctailBinding.setIsLoading(false);
                    Toast.makeText(getApplicationContext(), detailCoctailResponse.getDetailsCoctail().getDate(), Toast.LENGTH_SHORT).show();
                }
        );
    }
}