package com.example.coctailapp.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coctailapp.R;
import com.example.coctailapp.databinding.FragmentMainCardBinding;


public class MainGridFragment extends MainBaseFragment  {

    private FragmentMainCardBinding fragmentMainGridBinding;
    public MainGridFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMainGridBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_card,container,false);
        recyclerView=fragmentMainGridBinding.coctailsRecyclerView;
        return fragmentMainGridBinding.getRoot();
    }

    @Override
    public void setLayoutManager() {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
    }

    @Override
    public boolean isListFragment() {
        return false;
    }

    @Override
    public void setIsLoading(boolean isLoading) {
        fragmentMainGridBinding.setIsLoading(isLoading);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}