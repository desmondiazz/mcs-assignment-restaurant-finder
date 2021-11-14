import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    String defaultRestaurantName = "Amelie's cafe";
    String defaultRestaurantLocation = "Chennai";
    LocalTime defaultRestaurantOpeningTime = LocalTime.parse("10:30:00");
    LocalTime defaultRestaurantClosingTime = LocalTime.parse("22:00:00");

    @BeforeEach
    public void create_mock_restaurant() {
        this.restaurant = service.addRestaurant(this.defaultRestaurantName, this.defaultRestaurantLocation,
                this.defaultRestaurantOpeningTime, this.defaultRestaurantClosingTime);
    }

    @AfterEach
    public void remove_mock_restaurant() {
        try {
            service.removeRestaurant(this.defaultRestaurantName);
            this.restaurant = null;
        } catch (restaurantNotFoundException e) {
            this.restaurant = null;
        }
    }


    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        Restaurant restaurantFound = service.findRestaurantByName("Amelie's cafe");
        assertEquals(this.restaurant, restaurantFound);
    }

    //You may watch the video by Muthukumaran on how to write exceptions in Course 3: Testing and Version control: Optional content
    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        assertThrows(restaurantNotFoundException.class, () -> service.findRestaurantByName("Pantry d'or"));
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        this.restaurant.addToMenu("Sweet corn soup", 119);
        this.restaurant.addToMenu("Vegetable lasagne", 269);
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant(this.defaultRestaurantName);
        assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        this.restaurant.addToMenu("Sweet corn soup", 119);
        this.restaurant.addToMenu("Vegetable lasagne", 269);
        assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1(){
        this.restaurant.addToMenu("Sweet corn soup", 119);
        this.restaurant.addToMenu("Vegetable lasagne", 269);
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}