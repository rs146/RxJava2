package com.something;

import io.reactivex.Flowable;
import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

public class FlowableExample {

	public static void main(String[] args) {
		Flowable<Integer> flowable = Flowable.just(1, 2, 3, 4, 5);

		// remove the seed (50) argument, reduce returns a Maybe instead of a
		// Single - both do not have onNext() terminal operations
		flowable.reduce(new BiFunction<Integer, Integer, Integer>() {
			@Override
			public Integer apply(Integer t1, Integer t2) {
				return t1 + t2;
			}
		}).subscribe(getMaybeObserver());
	}

	private static SingleObserver<Integer> getSingleObserver() {
		return new SingleObserver<Integer>() {
			@Override
			public void onSubscribe(Disposable d) {
				// dispose method similar to .unsubscribe() in Subscription
				System.out.println("onSubscribe : " + d.isDisposed());
			}

			@Override
			public void onSuccess(Integer value) {
				System.out.println("onSuccess : value : " + value);
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e);
			}
		};
	}

	private static MaybeObserver<Integer> getMaybeObserver() {
		return new MaybeObserver<Integer>() {

			@Override
			public void onSubscribe(Disposable d) {
				System.out.println("onSubscribe : " + d.isDisposed());
			}

			@Override
			public void onSuccess(Integer t) {
				System.out.println("Total " + t);
			}

			@Override
			public void onError(Throwable e) {
				System.out.println(e);
			}

			@Override
			public void onComplete() {
				// why do you think this doesn't happen???????
				System.out.println("Finished");
			}
		};
	}
}
