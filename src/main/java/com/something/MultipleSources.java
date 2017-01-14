package com.something;

import util.Pair;

import com.something.repository.NamesRepository;
import com.something.repository.PlacesRepository;

public class MultipleSources {

    public static void main(String[] args) {
        NamesRepository.provideListNames()
            .flatMap(names -> PlacesRepository.provideListPlaces()
                .map(placesList -> new Pair<>(names, placesList)))
            .subscribe(pair -> {
                Pair<?, ?> aPair = (Pair<?, ?>) pair;
                System.out.println(aPair.first);
                System.out.println(aPair.second);
            }, Throwable::printStackTrace);
    }
}
