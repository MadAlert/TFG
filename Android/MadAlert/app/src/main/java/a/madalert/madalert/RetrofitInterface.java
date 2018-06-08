package a.madalert.madalert;

import com.google.gson.JsonArray;


import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import io.reactivex.Observable;


public interface RetrofitInterface {

    //@Headers("Content-Type: application/json")

    @GET("alertas/{distrito}/{count}/{categorias}")
    Observable<JsonArray> getCountAlertasDistrito(@Path("distrito") String distrito, @Path("count") boolean count, @Path("categorias") String categorias);

    @GET("alertas/{distrito}")
    Observable<List<Alertas>> getAlertasDistrito(@Path("distrito") String distrito);

    @GET("alertas/{distrito}/{categorias}")
    Observable<List<Alertas>> getAlertasDistritoCategoria(@Path("distrito") String distrito, @Path("categorias") String categorias);

    @POST("alertas/{alerta}/{distrito}/{fuente}/{categoria}")
    Observable<Alertas> postAlerta(@Path("alerta") String alerta, @Path("distrito") String distrito, @Path("fuente") String fuente, @Path("categoria") String categoria);
}
