package lichtman.fruity;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FruityService {

    @GET("api/fruit/{name}")
    Single<Fruit> getFruit(@Path("name") String name);


}
