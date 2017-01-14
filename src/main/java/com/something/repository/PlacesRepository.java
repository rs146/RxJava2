package com.something.repository;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class PlacesRepository {

    public static Observable<List<String>> provideListPlaces() {
        return Observable.just(Arrays.asList("London", "Paris", "Berlin", "Hong Kong", "New York", "Madrid"));
    }
}
