package com.something.repository;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class NamesRepository {
    
    public static Observable<List<String>> provideListNames() {
        return Observable.just(Arrays.asList("Max", "Bob", "Rex", "Luther", "Ted", "Red John"));
    }
}
