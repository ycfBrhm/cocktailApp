package com.example.coctailapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;


import com.example.coctailapp.R;
import com.example.coctailapp.adapters.TryedListAdapter;
import com.example.coctailapp.databinding.ActivityTryedListBinding;

import com.example.coctailapp.listeners.TryedListListener;
import com.example.coctailapp.models.Coctail;
import com.example.coctailapp.viewmodels.TryedCocktailViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class TryedListActivity extends AppCompatActivity implements TryedListListener {

    private ActivityTryedListBinding activityTryedListBinding;
    private TryedCocktailViewModel viewModel;
    private TryedListAdapter tryedListAdapter;
    private List<Coctail> tryedList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTryedListBinding = DataBindingUtil.setContentView(this, R.layout.activity_tryed_list);
        doInitializeation();

    }


    private void doInitializeation() {

        viewModel = new ViewModelProvider(this).get(TryedCocktailViewModel.class);
        activityTryedListBinding.imageBack.setOnClickListener(v -> onBackPressed());
        tryedList = new ArrayList<>();
    }

    private void loadTryedList() {
        activityTryedListBinding.setIsLoading(true);
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(viewModel.loadTryedList().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(coctails -> {
                    activityTryedListBinding.setIsLoading(false);
                    if (tryedList.size() > 0) {
                        tryedList.clear();
                    }
                    tryedList.addAll(coctails);
                    tryedListAdapter = new TryedListAdapter(tryedList, this);
                    activityTryedListBinding.tryedListRecyclerView.setAdapter(tryedListAdapter);
                    activityTryedListBinding.tryedListRecyclerView.setVisibility(View.VISIBLE);
                    compositeDisposable.dispose();
                }));

    }


    @Override
    protected void onResume(){
        super.onResume();
        loadTryedList();
    }

    @Override
    public void onCocktailClicked(Coctail coctail) {
        Intent intent = new Intent(getApplicationContext(), DetailCoctailActivity.class);
        intent.putExtra("coctail", coctail);
        startActivity(intent);
    }

    @Override
    public void removeCocktailFromTryedList(Coctail coctail, int position) {
        CompositeDisposable compositeDisposableForDelete = new CompositeDisposable();
        compositeDisposableForDelete.add(viewModel.removeCocktailFromTryedList(coctail)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    tryedList.remove(position);
                    tryedListAdapter.notifyItemRemoved(position);
                    tryedListAdapter.notifyItemRangeChanged(position, tryedListAdapter.getItemCount());
                    compositeDisposableForDelete.dispose();
                }));
    }
}