package com.example.coctailapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.RecyclerView;


import com.example.coctailapp.activities.DetailCoctailActivity;
import com.example.coctailapp.adapters.CoctailAdapter;

import com.example.coctailapp.listeners.CoctailListener;
import com.example.coctailapp.models.Coctail;
import com.example.coctailapp.responses.CoctailResponse;
import com.example.coctailapp.viewmodels.AlcoholicCoctailsViewModel;

import java.util.ArrayList;
import java.util.List;



public abstract class MainBaseFragment extends Fragment implements CoctailListener,ContractMainFragment{

    private AlcoholicCoctailsViewModel alCocViewModel;

    private List<Coctail> coctails = new ArrayList<>();
    private CoctailAdapter coctailAdapter;
    protected RecyclerView recyclerView;
    public MainBaseFragment() {
    }


    public abstract void setLayoutManager();
    public abstract boolean isListFragment();



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView=getRecyclerView();
        recyclerView.setHasFixedSize(true);
        setLayoutManager();
        alCocViewModel = new ViewModelProvider(this).get(AlcoholicCoctailsViewModel.class);
        boolean isListFragment=isListFragment();
        coctailAdapter = new CoctailAdapter(coctails, this,isListFragment);
        recyclerView.setAdapter(coctailAdapter);
        getAlcohlicCoctails();
    }

    public void getAlcohlicCoctails(){
        setIsLoading(true);
        alCocViewModel.getAlcoholicCoctails("Alcoholic").observe(this, alcoholicCoctailsResponse ->{
            setIsLoading(false);
            setDataToAdapter(alcoholicCoctailsResponse);
        });
        alCocViewModel.getAlcoholicCoctails("Non_Alcoholic").observe(this, this::setDataToAdapter);
    }

    public void setDataToAdapter(CoctailResponse cocktailResponse){
        List<Coctail> NAlCoctails=cocktailResponse.getCoctails();
        if (NAlCoctails!= null){
                for (int i=0;i<NAlCoctails.size();i++){
                    coctailAdapter.bindViewModel(NAlCoctails.get(i));
                }
        }
    }
    @Override
    public void onCoctailClicked(Coctail coctail) {
        Intent intent = new Intent(getContext(), DetailCoctailActivity.class);
        intent.putExtra("coctail", coctail);
        startActivity(intent);
    }

}