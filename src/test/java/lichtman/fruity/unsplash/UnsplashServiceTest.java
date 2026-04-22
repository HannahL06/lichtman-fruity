package lichtman.fruity.unsplash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnsplashServiceTest {

    @Test
    public void search() {

        //given
        UnsplashService service = new UnsplashServiceFactory().create();

        //when
        Photos photos = service.search("strawberry").blockingGet();

        //then
        assertEquals(10000, photos.total());
        assertEquals(1000, photos.total_pages());

        assertNotNull(photos.results()[0].urls().small());
    }
}