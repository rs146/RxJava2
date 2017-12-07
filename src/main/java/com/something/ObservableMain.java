package com.something;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class ObservableMain {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hello", "Streams", "Not");

        String[] arrayOfStrings = {"a", "b", "c"};

        // note how we no longer use a generic Observable.from()
        Observable.fromArray(arrayOfStrings)
                .filter(s -> !s.contains("N"))
                .toList()
                .subscribe(resultList -> {
                    for (String element : resultList) {
                        System.out.println(element);
                    }
                }, Throwable::printStackTrace);
    }
}
