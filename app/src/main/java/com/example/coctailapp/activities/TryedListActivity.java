package com.example.coctailapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;

import android.view.View;


import com.example.coctailapp.R;
import com.example.coctailapp.adapters.TryedListAdapter;
import com.example.coctailapp.databinding.ActivityTryedListBinding;

import com.example.coctailapp.listeners.TryedListListener;
import com.example.coctailapp.models.Coctail;
import com.example.coctailapp.viewmodels.TryedCocktailViewModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class TryedListActivity extends AppCompatActivity implements TryedListListener {

    private ActivityTryedListBinding activityTryedListBinding;
    private TryedCocktailViewModel viewModel;
    private TryedListAdapter tryedListAdapter;
    private List<Coctail> tryedList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTryedListBinding = DataBindingUtil.setContentView(this, R.layout.activity_tryed_list);
        doInitializeation();

        // To swipe for remove cocktail's items
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        recyclerView = findViewById(R.id.tryedListRecyclerView);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    Coctail deletedCocktail = null;

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();

            deletedCocktail = tryedList.get(position);
            removeCocktailFromTryedList(deletedCocktail, position);
            Snackbar.make(recyclerView, deletedCocktail.getName()+" deleted", Snackbar.LENGTH_SHORT).show();

        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(TryedListActivity.this, c, recyclerView, viewHolder, dX,dY,actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(TryedListActivity.this,R.color.colorThemeExtra))
                    .addSwipeLeftActionIcon(R.drawable.ic_deleted)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(TryedListActivity.this,R.color.colorThemeExtra))
                    .addSwipeRightActionIcon(R.drawable.ic_deleted)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };


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