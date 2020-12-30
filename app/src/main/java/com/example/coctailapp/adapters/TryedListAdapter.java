 package com.example.coctailapp.adapters;

 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;

 import androidx.annotation.NonNull;
 import androidx.databinding.DataBindingUtil;
 import androidx.databinding.ViewDataBinding;
 import androidx.recyclerview.widget.RecyclerView;

 import com.example.coctailapp.R;
 import com.example.coctailapp.databinding.ItemContainerCoctailBinding;
 import com.example.coctailapp.databinding.ItemContainerCoctailGridBinding;
 import com.example.coctailapp.listeners.TryedListListener;
 import com.example.coctailapp.models.Coctail;

 import java.util.List;

 public class TryedListAdapter extends RecyclerView.Adapter<TryedListAdapter.CoctailViewHolder>{

     private List<Coctail> coctails;
     private TryedListListener tryedListListener;
     //private boolean isList;

     public TryedListAdapter(List<Coctail> coctails, TryedListListener tryedListListener) {

         this.coctails = coctails;
         this.tryedListListener = tryedListListener;
     }

     @NonNull
     @Override
     public CoctailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         ViewDataBinding employeeListItemBinding =null;

         employeeListItemBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                 R.layout.item_container_coctail, parent, false);
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

             ItemContainerCoctailBinding itemContainerCoctailBinding=(ItemContainerCoctailBinding)itemBinding;
             itemContainerCoctailBinding.setCoctail(coctail);
             itemBinding.getRoot().setOnClickListener(view -> tryedListListener.onCocktailClicked(coctail));
             itemContainerCoctailBinding.imageDelete.setOnClickListener(v -> tryedListListener.removeCocktailFromTryedList(coctail,getAdapterPosition()));
             itemContainerCoctailBinding.imageDelete.setVisibility(View.VISIBLE);





         }
     }
 }
