package a.madalert.madalert;

import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtil {

    public static RetrofitInterface getRetrofit(){

        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());

        OkHttpClient client =new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS) .connectTimeout(60, TimeUnit.SECONDS).build();

        return new Retrofit.Builder()
                .baseUrl("http://192.168.1.198:8080/api/v1/")
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build().create(RetrofitInterface.class);

    }

}


