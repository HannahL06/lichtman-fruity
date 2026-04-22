package lichtman.fruity.unsplash;

import lichtman.fruity.FruityService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnsplashServiceFactory {

    public UnsplashService create() {
        // configure Retrofit for the dummyjson website
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                // Configure Retrofit to use Gson to turn the JSON into Objects
                .addConverterFactory(GsonConverterFactory.create())
                // Configure Retrofit to use Rx
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        return retrofit.create(UnsplashService.class);
    }
}
