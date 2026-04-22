package lichtman.fruity.unsplash;

import com.andrewoid.apikeys.ApiKey;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnsplashServiceTest {

    @Test
    public void search() {

        //given
        UnsplashService service = new UnsplashServiceFactory().create();
        ApiKey apiKey = new ApiKey();
        String keyString = apiKey.get();

        //when
        Photos photos = service.search(keyString, "strawberry").blockingGet();

        //then
        assertNotNull(photos.results()[0].urls().small());
    }
}