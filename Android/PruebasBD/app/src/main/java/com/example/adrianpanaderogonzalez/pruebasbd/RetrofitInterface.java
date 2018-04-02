package com.example.adrianpanaderogonzalez.pruebasbd;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import android.database.Observable;

/**
 * Created by adrianpanaderogonzalez on 26/3/18.
 */

public interface RetrofitInterface {

    @GET("users/{distrito}")
    Observable<Alertas> getAlertasDistrito(@Path("distrito") String distrito);
}
