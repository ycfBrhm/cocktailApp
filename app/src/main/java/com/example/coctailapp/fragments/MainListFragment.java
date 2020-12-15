package com.example.coctailapp.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coctailapp.R;
import com.example.coctailapp.databinding.FragmentMainListBinding;


public class MainListFragment extends MainBaseFragment {
    private FragmentMainListBinding fragmentMainListBinding;
    public MainListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMainListBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_main_list,container,false);
        recyclerView=fragmentMainListBinding.coctailsRecyclerView;
        return fragmentMainListBinding.getRoot();
    }

    @Override
    public void setLayoutManager() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    @Override
    public boolean isListFragment() {
        return true;
    }

    @Override
    public void setIsLoading(boolean isLoading) {
        fragmentMainListBinding.setIsLoading(isLoading);
    }

    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}