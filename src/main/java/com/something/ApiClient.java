package com.something;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface ApiClient {

    @GET("posts/1")
    Single<Post> getPost();

    @PUT("posts/1")
    Completable updatePost();
}
