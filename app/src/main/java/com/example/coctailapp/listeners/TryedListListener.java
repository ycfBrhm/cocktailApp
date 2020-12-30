package com.example.coctailapp.listeners;

import com.example.coctailapp.models.Coctail;

public interface TryedListListener {


    void onCocktailClicked(Coctail coctail);

    void removeCocktailFromTryedList(Coctail coctail, int position);
}
