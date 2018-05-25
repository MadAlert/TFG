package a.madalert.madalert;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtil {

    public static RetrofitInterface getRetrofit(){

        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());

        return new Retrofit.Builder()
                .baseUrl("http://192.168.1.36:8080/api/v1/")
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitInterface.class);

    }

}


