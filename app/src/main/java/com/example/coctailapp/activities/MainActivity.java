package com.example.coctailapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.example.coctailapp.R;
import com.example.coctailapp.adapters.CoctailAdapter;
import com.example.coctailapp.databinding.ActivityMainBinding;
import com.example.coctailapp.listeners.CoctailListener;
import com.example.coctailapp.models.Coctail;
import com.example.coctailapp.viewmodels.AlcoholicCoctailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CoctailListener {

    private ActivityMainBinding activityMainBinding;
    private AlcoholicCoctailsViewModel alCocViewModel;
    private List<Coctail> coctails = new ArrayList<>();
    private CoctailAdapter coctailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        doInitialization();

    }

    private void doInitialization(){
        activityMainBinding.coctailsRecyclerView.setHasFixedSize(true);
        alCocViewModel = new ViewModelProvider(this).get(AlcoholicCoctailsViewModel.class);
        coctailAdapter = new CoctailAdapter(coctails, this);
        activityMainBinding.coctailsRecyclerView.setAdapter(coctailAdapter);
        getAlcohlicCoctails();
    }

    private void getAlcohlicCoctails(){

        activityMainBinding.setIsLoading(true);
        alCocViewModel.getAlcoholicCoctails("Alcoholic").observe(this, alcoholicCoctailsResponse ->{
            activityMainBinding.setIsLoading(false);
            if (alcoholicCoctailsResponse.getCoctails() != null){
                if (alcoholicCoctailsResponse.getCoctails() != null){
                    List<Coctail> coctails=alcoholicCoctailsResponse.getCoctails();
                    for (int i=0;i<coctails.size();i++){
                        coctailAdapter.bindViewModel(coctails.get(i));
                    }

                }

            }
        });

        alCocViewModel.getAlcoholicCoctails("Non_Alcoholic").observe(this, alcoholicCoctailsResponse ->{
            activityMainBinding.setIsLoading(false);
            if (alcoholicCoctailsResponse.getCoctails() != null){
                if (alcoholicCoctailsResponse.getCoctails() != null){
                    List<Coctail> NAlCoctails=alcoholicCoctailsResponse.getCoctails();
                    for (int i=0;i< NAlCoctails.size();i++){
                        coctailAdapter.bindViewModel(NAlCoctails.get(i));
                    }

                }

            }
        });


        /*
        alCocViewModel.getAlcoholicCoctails("margarita").observe(this, coctailResponse ->
                Toast.makeText(
                        getApplicationContext(),
                        "Drinks: "+coctailResponse.getCoctails().get(0).getId()+coctailResponse.getCoctails().get(0).getName(),
                        Toast.LENGTH_SHORT
                ).show()
        );

         */
    }

    @Override
    public void onCoctailClicked(Coctail coctail) {
        Intent intent = new Intent(getApplicationContext(), DetailCoctailActivity.class);
        intent.putExtra("id", coctail.getId());
        intent.putExtra("name", coctail.getName());
        startActivity(intent);
    }
}