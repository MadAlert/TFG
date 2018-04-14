package com.example.adrianpanaderogonzalez.pruebasbd;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import io.reactivex.Observable;

/**
 * Created by adrianpanaderogonzalez on 26/3/18.
 */

public interface RetrofitInterface {

    //@Headers("Content-Type: application/json")
    @GET("alertas")
    Observable<Response> getDistrito();

    @GET("alertas/{distrito}")
    Observable<Alertas> getAlertasDistrito(@Path("distrito") String distrito);
}
