package com.example.coctailapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import android.widget.Toast;

import com.example.coctailapp.R;
import com.example.coctailapp.databinding.ActivityTryedListBinding;

import com.example.coctailapp.viewmodels.TryedCocktailViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TryedListActivity extends AppCompatActivity {

    private ActivityTryedListBinding activityTryedListBinding;
    private TryedCocktailViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTryedListBinding = DataBindingUtil.setContentView(this, R.layout.activity_tryed_list);
        doInitializeation();
    }


    private void doInitializeation(){

        viewModel = new ViewModelProvider(this).get(TryedCocktailViewModel.class);
        activityTryedListBinding.imageBack.setOnClickListener(v -> onBackPressed());
    }

    private void loadTryedList(){
        activityTryedListBinding.setIsLoading(true);
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(viewModel.loadTryedList().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(coctails -> {
                    activityTryedListBinding.setIsLoading(false);
                    Toast.makeText(getApplicationContext(), "Tryed cocktails: " + coctails.size(), Toast.LENGTH_SHORT).show();
                }));

    }


    @Override
    protected void onResume(){
        super.onResume();
        loadTryedList();
    }
}