package com.something;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.Arrays;
import java.util.List;

public class ObservableMain {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Hello", "Streams", "Not");

        String[] arrayOfStrings = {"a", "b", "c"};

        // note how we no longer use a generic Observable.from()
        Disposable d = Observable.fromIterable(list)
                .filter(s -> !s.contains("H"))
                .toList()
                .subscribe(resultList -> {
                    for (String element : resultList) {
                        System.out.println(element);
                    }
                }, Throwable::printStackTrace);

        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(d);


    }
}
