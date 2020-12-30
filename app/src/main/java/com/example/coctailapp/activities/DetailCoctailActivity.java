package com.example.coctailapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;


import com.example.coctailapp.R;
import com.example.coctailapp.adapters.ImageSliderAdapter;
import com.example.coctailapp.databinding.ActivityDetailCoctailBinding;
import com.example.coctailapp.models.Coctail;
import com.example.coctailapp.responses.DetailCoctailResponse;
import com.example.coctailapp.viewmodels.AlcoholicCoctailsViewModel;
import com.example.coctailapp.viewmodels.DetailsCoctailViewModel;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DetailCoctailActivity extends AppCompatActivity {

    private ActivityDetailCoctailBinding activityDetailCoctailBinding;
    private DetailsCoctailViewModel detailsCoctailViewModel;
    //public static final Coctail COCTAIL="com.example.coctailapp.activities.COCTAIL";
    private Coctail coctail;
    private Boolean isCocktailAvailableInTryedList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailCoctailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_coctail);
        doInitializeation();
    }

    private void doInitializeation(){
        detailsCoctailViewModel = new ViewModelProvider(this).get(DetailsCoctailViewModel.class);
        activityDetailCoctailBinding.imageBack.setOnClickListener(v -> onBackPressed());
        coctail = (Coctail) getIntent().getSerializableExtra("coctail");
        checkCocktailinTryedList();
        getDetailsCoctail();
    }

    private void checkCocktailinTryedList() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(detailsCoctailViewModel.getCocktailFromTryedList(String.valueOf(coctail.getId()))
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(coctail -> {
                    isCocktailAvailableInTryedList = true;
                    activityDetailCoctailBinding.imageTryedList.setImageResource(R.drawable.ic_tryed);
                    compositeDisposable.dispose();
                }));
    }


    private void getDetailsCoctail(){
        activityDetailCoctailBinding.setIsLoading(true);
        String[] sliderImages = new String[1];
        String coctailId = String.valueOf(coctail.getId());
        detailsCoctailViewModel.getDetailsCoctail(coctailId).observe(
                this, detailCoctailResponse -> {
                    activityDetailCoctailBinding.setIsLoading(false);
                    if (detailCoctailResponse.getDetailsCoctail()!= null){
                        if (detailCoctailResponse.getDetailsCoctail().getImage() != null){
                            sliderImages[0] = detailCoctailResponse.getDetailsCoctail().getImage();
                            loadImageSlider(sliderImages);
                        }

                        activityDetailCoctailBinding.setCocktailImageURL(
                                detailCoctailResponse.getDetailsCoctail().getImage()
                        );
                        activityDetailCoctailBinding.imageCoctail.setVisibility(View.VISIBLE);

                        activityDetailCoctailBinding.setInstructions(
                                String.valueOf(
                                        HtmlCompat.fromHtml(
                                                detailCoctailResponse.getDetailsCoctail().getInstruction(),
                                                HtmlCompat.FROM_HTML_MODE_LEGACY
                                        )
                                )
                        );
                        activityDetailCoctailBinding.textInstructions.setVisibility(View.VISIBLE);
                        activityDetailCoctailBinding.textReadMore.setVisibility(View.VISIBLE);
                        activityDetailCoctailBinding.textReadMore.setOnClickListener(v -> {
                            if(activityDetailCoctailBinding.textReadMore.getText().toString().equals("Read More")) {
                                activityDetailCoctailBinding.textInstructions.setMaxLines(Integer.MAX_VALUE);
                                activityDetailCoctailBinding.textInstructions.setEllipsize(null);
                                activityDetailCoctailBinding.textReadMore.setText(R.string.read_less);
                            }else{
                                activityDetailCoctailBinding.textInstructions.setMaxLines(2);
                                activityDetailCoctailBinding.textInstructions.setEllipsize(TextUtils.TruncateAt.END);
                                activityDetailCoctailBinding.textReadMore.setText(R.string.read_more);
                            }
                        });




                        // sous titre
                        if(detailCoctailResponse.getDetailsCoctail().getDate() != null){
                            activityDetailCoctailBinding.setCocktailDateModified(
                                    detailCoctailResponse.getDetailsCoctail().getDate()
                            );
                            activityDetailCoctailBinding.textCocktailDateModified.setVisibility(View.VISIBLE);
                        }


                        if(detailCoctailResponse.getDetailsCoctail().getName() != null){
                            activityDetailCoctailBinding.setCocktailName(
                                    detailCoctailResponse.getDetailsCoctail().getName()
                            );
                            activityDetailCoctailBinding.textName.setVisibility(View.VISIBLE);
                        }



                        if(detailCoctailResponse.getDetailsCoctail().getAlcoholic() != null){
                            activityDetailCoctailBinding.setCocktailAlcoholic(
                                    detailCoctailResponse.getDetailsCoctail().getAlcoholic()
                            );
                            activityDetailCoctailBinding.textCocktailAlcoholic.setVisibility(View.VISIBLE);
                        }

                        if(detailCoctailResponse.getDetailsCoctail().getCategory() != null){
                            activityDetailCoctailBinding.setCocktailCategory(
                                    detailCoctailResponse.getDetailsCoctail().getCategory()
                            );
                            activityDetailCoctailBinding.textCocktailCategory.setVisibility(View.VISIBLE);
                        }

                        if(detailCoctailResponse.getDetailsCoctail().getGlass() != null){
                            activityDetailCoctailBinding.setCocktailGlass(
                                    detailCoctailResponse.getDetailsCoctail().getGlass()
                            );
                            activityDetailCoctailBinding.textCocktailGlass.setVisibility(View.VISIBLE);
                        }

                        // Ingredients
                        if(detailCoctailResponse.getDetailsCoctail().getIngredient1() != null){
                            activityDetailCoctailBinding.setIng1(
                                    detailCoctailResponse.getDetailsCoctail().getIngredient1()
                            );
                            activityDetailCoctailBinding.setOz1(
                                    detailCoctailResponse.getDetailsCoctail().getMesure1()
                            );
                            activityDetailCoctailBinding.textIng1.setVisibility(View.VISIBLE);
                        }

                        if(detailCoctailResponse.getDetailsCoctail().getIngredient2() != null){
                            activityDetailCoctailBinding.setIng2(
                                    detailCoctailResponse.getDetailsCoctail().getIngredient2()
                            );

                            activityDetailCoctailBinding.setOz2(
                                    detailCoctailResponse.getDetailsCoctail().getMesure2()
                            );

                            activityDetailCoctailBinding.textIng2.setVisibility(View.VISIBLE);
                        }

                        if(detailCoctailResponse.getDetailsCoctail().getIngredient3() != null ){
                            activityDetailCoctailBinding.setIng3(
                                    detailCoctailResponse.getDetailsCoctail().getIngredient3()
                            );
                            activityDetailCoctailBinding.setOz3(
                                    detailCoctailResponse.getDetailsCoctail().getMesure3()
                            );
                            activityDetailCoctailBinding.textIng3.setVisibility(View.VISIBLE);
                        }


                        if(detailCoctailResponse.getDetailsCoctail().getIngredient4() != null ){
                            activityDetailCoctailBinding.setIng4(
                                    detailCoctailResponse.getDetailsCoctail().getIngredient4()
                            );
                            activityDetailCoctailBinding.setOz4(
                                    detailCoctailResponse.getDetailsCoctail().getMesure4()
                            );
                            activityDetailCoctailBinding.textIng4.setVisibility(View.VISIBLE);
                        }

                        if(detailCoctailResponse.getDetailsCoctail().getIngredient5() != null){
                            activityDetailCoctailBinding.setIng5(
                                    detailCoctailResponse.getDetailsCoctail().getIngredient5()
                            );
                            activityDetailCoctailBinding.setOz5(
                                    detailCoctailResponse.getDetailsCoctail().getMesure5()
                            );
                            activityDetailCoctailBinding.textIng5.setVisibility(View.VISIBLE);
                        }

                        if(detailCoctailResponse.getDetailsCoctail().getIngredient6() != null ){
                            activityDetailCoctailBinding.setIng6(
                                    detailCoctailResponse.getDetailsCoctail().getIngredient6()
                            );
                            activityDetailCoctailBinding.setOz6(
                                    detailCoctailResponse.getDetailsCoctail().getMesure6()
                            );
                            activityDetailCoctailBinding.textIng6.setVisibility(View.VISIBLE);
                        }


                        if(detailCoctailResponse.getDetailsCoctail().getIngredient7() != null ){
                            activityDetailCoctailBinding.setIng7(
                                    detailCoctailResponse.getDetailsCoctail().getIngredient7()
                            );
                            activityDetailCoctailBinding.setOz7(
                                    detailCoctailResponse.getDetailsCoctail().getMesure7()
                            );
                            activityDetailCoctailBinding.textIng7.setVisibility(View.VISIBLE);
                        }

                        if(detailCoctailResponse.getDetailsCoctail().getIngredient8() != null  ){
                            activityDetailCoctailBinding.setIng8(
                                    detailCoctailResponse.getDetailsCoctail().getIngredient8()
                            );
                            activityDetailCoctailBinding.setOz8(
                                    detailCoctailResponse.getDetailsCoctail().getMesure8()
                            );
                            activityDetailCoctailBinding.textIng8.setVisibility(View.VISIBLE);
                        }

                        // fin ingredients bind
                        activityDetailCoctailBinding.imageTryedList.setOnClickListener(v -> {
                            CompositeDisposable compositeDisposable = new CompositeDisposable();
                            if (isCocktailAvailableInTryedList) {
                                compositeDisposable.add(detailsCoctailViewModel.removeCocktailFromTryedList(coctail)
                                        .subscribeOn(Schedulers.computation())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(() -> {
                                            isCocktailAvailableInTryedList = false;
                                            activityDetailCoctailBinding.imageTryedList.setImageResource(R.drawable.ic_delete);
                                            Toast.makeText(getApplicationContext(), "Removed from tryed list", Toast.LENGTH_SHORT).show();
                                        }));
                            } else {
                                compositeDisposable.add(detailsCoctailViewModel.addToTryedList(coctail)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(() -> {
                                            activityDetailCoctailBinding.imageTryedList.setImageResource(R.drawable.ic_tryed);
                                            Toast.makeText(getApplicationContext(), "added to cocktails tryed", Toast.LENGTH_SHORT).show();
                                            compositeDisposable.dispose();
                                        })
                                );
                            }

                        });
                        activityDetailCoctailBinding.imageTryedList.setVisibility(View.VISIBLE);

                    }
                }
        );
    }

    private void loadImageSlider(String[] sliderImages){
        activityDetailCoctailBinding.sliderViewPager.setOffscreenPageLimit(1);
        activityDetailCoctailBinding.sliderViewPager.setAdapter(new ImageSliderAdapter(sliderImages));
        activityDetailCoctailBinding.sliderViewPager.setVisibility(View.VISIBLE);
        activityDetailCoctailBinding.viewFadingEdge.setVisibility(View.VISIBLE);

    }


}