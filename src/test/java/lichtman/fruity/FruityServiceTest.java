package lichtman.fruity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FruityServiceTest {

    @Test
    void getFruit() {
        //given
        FruityService service = new FruityServiceFactory().create();

        //when
        Fruit fruit = service.getFruit("strawberry").blockingGet();

        //then
        assertEquals("Strawberry", fruit.name());
        assertEquals(3, fruit.id());
        assertEquals("Rosaceae", fruit.family());
        assertEquals("Rosales", fruit.order());
        assertEquals("Fragaria", fruit.genus());

        Nutritions nutritions = fruit.nutritions();
        assertEquals(29, nutritions.calories());
        assertEquals(0.4, nutritions.fat());
        assertEquals(5.4, nutritions.sugar());
        assertEquals(5.5, nutritions.carbohydrates());
        assertEquals(0.8, nutritions.protein());
    }
}