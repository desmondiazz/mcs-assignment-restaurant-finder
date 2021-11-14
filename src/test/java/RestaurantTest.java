import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(Lifecycle.PER_CLASS)
class RestaurantTest {
    Restaurant restaurant;
    String defaultRestaurantName = "Amelie's cafe";
    String defaultRestaurantLocation = "Chennai";
    LocalTime defaultRestaurantOpeningTime = LocalTime.parse("10:30:00");
    LocalTime defaultRestaurantClosingTime = LocalTime.parse("22:00:00");

    @BeforeAll
    public void create_mock_restaurant() {
        restaurant = new Restaurant(
            "Amelie's cafe", 
            "Chennai", 
            defaultRestaurantOpeningTime,
            defaultRestaurantClosingTime
        );
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        LocalTime mockCurrentTime = LocalTime.parse("18:30:00");
        Restaurant spiedRestaurant = spy(this.restaurant);
        when(spiedRestaurant.getCurrentTime()).thenReturn(mockCurrentTime);
        assertTrue(spiedRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        LocalTime mockCurrentTime = LocalTime.parse("22:30:00");
        Restaurant spiedRestaurant = spy(this.restaurant);
        when(spiedRestaurant.getCurrentTime()).thenReturn(mockCurrentTime);
        assertFalse(spiedRestaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        this.restaurant.addToMenu("Sweet corn soup", 119);
        this.restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = this.restaurant.getMenu().size();
        this.restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, this.restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        this.restaurant.addToMenu("Sweet corn soup", 119);
        this.restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = this.restaurant.getMenu().size();
        this.restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, this.restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        this.restaurant.addToMenu("Sweet corn soup", 119);
        this.restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class, () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}