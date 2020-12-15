 package com.example.coctailapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coctailapp.R;
import com.example.coctailapp.databinding.ItemContainerCoctailBinding;
import com.example.coctailapp.databinding.ItemContainerCoctailGridBinding;
import com.example.coctailapp.listeners.CoctailListener;
import com.example.coctailapp.models.Coctail;
import com.example.coctailapp.utilities.BindingAdapters;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class CoctailAdapter extends RecyclerView.Adapter<CoctailAdapter.CoctailViewHolder>{

    private List<Coctail> coctails;
    private CoctailListener coctailListener;
    private boolean isList;

    public CoctailAdapter(List<Coctail> coctails, CoctailListener coctailListener,boolean isList) {
        this.isList=isList;
        this.coctails = coctails;
        this.coctailListener = coctailListener;
    }

    @NonNull
    @Override
    public CoctailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding employeeListItemBinding =null;
        if(isList){
            employeeListItemBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_container_coctail, parent, false);
        }
        else{
            employeeListItemBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.item_container_coctail_grid, parent, false);
        }

        return new CoctailViewHolder(employeeListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CoctailViewHolder holder, int position) {
        holder.bindCoctail(coctails.get(position));
    }

    @Override
    public int getItemCount() {
        return coctails.size();
    }

    public void bindViewModel(Coctail coctail){
        coctails.add(coctail);
        notifyDataSetChanged();
    }

    class CoctailViewHolder extends RecyclerView.ViewHolder{
        private ViewDataBinding itemBinding;
        public CoctailViewHolder (ViewDataBinding itemBinding){
          super(itemBinding.getRoot());
          this.itemBinding=itemBinding;
        }

        public  void bindCoctail(Coctail coctail) {
            if (isList){
                ItemContainerCoctailBinding itemContainerCoctailBinding=(ItemContainerCoctailBinding)itemBinding;
                itemContainerCoctailBinding.setCoctail(coctail);
            }
            else {
                ItemContainerCoctailGridBinding itemContainerCoctailGridBinding=(ItemContainerCoctailGridBinding)itemBinding;
                itemContainerCoctailGridBinding.setCoctail(coctail);
            }

            itemBinding.getRoot().setOnClickListener(view -> coctailListener.onCoctailClicked(coctail));
        }
    }
}
