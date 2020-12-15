package com.example.coctailapp.fragments;

import androidx.recyclerview.widget.RecyclerView;

public interface ContractMainFragment {
    void setIsLoading(boolean isLoading);
    RecyclerView getRecyclerView();

}
