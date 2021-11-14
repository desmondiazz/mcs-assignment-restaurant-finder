import org.junit.jupiter.api.*;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class OrderServiceTest {
  RestaurantService service = new RestaurantService();
  OrderService order = new OrderService();
  Restaurant restaurant;
  
  @BeforeAll
  public void create_mock_restaurant() {
    this.restaurant = service.addRestaurant("Amelie's cafe", "Chennai", LocalTime.parse("10:30:00"),
        LocalTime.parse("22:00:00"));
    this.restaurant.addToMenu("Sweet corn soup", 119);
    this.restaurant.addToMenu("Vegetable lasagne", 269);
    this.restaurant.addToMenu("Pizza", 400);
  }

  @Test
  public void should_return_0_if_no_items_passed() {
    List<Item> itemList = new ArrayList<Item>();
    int price = this.order.getItemsTotalPrice(itemList);
    assertEquals(0, price);
  }

  @Test
  public void should_return_proper_price_if_items_found_in_the_menu() {
    List<Item> itemList = new ArrayList<Item>();
    Item item1 = this.restaurant.findItemByName("Sweet corn soup");
    Item item2 = this.restaurant.findItemByName("Vegetable lasagne");
    itemList.add(item1);
    itemList.add(item2);
    int price = this.order.getItemsTotalPrice(itemList);
    assertEquals(388, price);
  }
}
