import java.util.List;

public class OrderService {
  public int getItemsTotalPrice(List<Item> items) {
    int totalPrice = 0;
    for (Item item : items) {
      totalPrice += item.getPrice();
    }
    return totalPrice;
  }  
}
