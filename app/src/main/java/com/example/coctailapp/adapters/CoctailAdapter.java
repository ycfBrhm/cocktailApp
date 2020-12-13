 package com.example.coctailapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.coctailapp.R;
import com.example.coctailapp.databinding.ItemContainerCoctailBinding;
import com.example.coctailapp.listeners.CoctailListener;
import com.example.coctailapp.models.Coctail;
import com.example.coctailapp.utilities.BindingAdapters;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class CoctailAdapter extends RecyclerView.Adapter<CoctailAdapter.CoctailViewHolder>{

    private List<Coctail> coctails;
    private LayoutInflater layoutInflater;
    private CoctailListener coctailListener;

    public CoctailAdapter(List<Coctail> coctails, CoctailListener coctailListener) {

        this.coctails = coctails;
        this.coctailListener = coctailListener;
    }

    @NonNull
    @Override
    public CoctailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null){
            layoutInflater= LayoutInflater.from(parent.getContext());
        }

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_container_coctail, parent, false);
        return new CoctailViewHolder(v);
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

        private RoundedImageView imageCoctail;
        private TextView nameCoctail;
        private TextView ingredientsCoctail;

        private ItemContainerCoctailBinding itemContainerCoctailBinding;

        public CoctailViewHolder (View view){
          super(view);
            imageCoctail=view.findViewById(R.id.imageCoctail);
            nameCoctail=view.findViewById(R.id.nameCoctail);
            ingredientsCoctail=view.findViewById(R.id.ingredientsCoctail);

        }

        public  void bindCoctail(Coctail coctail) {

            BindingAdapters.setImageURL(imageCoctail, coctail.getImage());
            nameCoctail.setText(coctail.getName());
            ingredientsCoctail.setText(coctail.getName());
            itemContainerCoctailBinding.getRoot().setOnClickListener(view -> coctailListener.onCoctailClicked(coctail));
        }
    }
}
