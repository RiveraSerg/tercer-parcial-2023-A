package edu.uaslp.objetos.shoppingcart;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

/*Shopping cart = carrito de compras*/
public class Exercise1ShoppingCartTest {

    private final ShoppingItemCatalog shoppingItemCatalog = new ShoppingItemCatalog();

    @Test
    public void givenAnEmptyShoppingCart_whenAnItemIsAdded_thenSizeIsOneAndTotalCostIsCorrect() {
        // Given:
        ShoppingCart shoppingCart = new ShoppingCart(shoppingItemCatalog);
        ShoppingItem item = shoppingItemCatalog.getItem("ABC1000");

        // When:
        shoppingCart.add("ABC1000");
        int totalCost = shoppingCart.getTotalCostInCents();
        int distinctItemsCount = shoppingCart.getDistinctItemsCount();
        int totalItemsCount = shoppingCart.getTotalItemsCount();

        // Then:
        List<ShoppingItem> shoppingItems = shoppingCart.getItems();

        assertThat(shoppingItems).containsExactly(item);
        assertThat(totalCost).isEqualTo(12000);
        assertThat(distinctItemsCount).isEqualTo(1);
        assertThat(totalItemsCount).isEqualTo(1);

    }

    @Test
    public void givenShoppingCartWithItems_whenAnItemIsAdded_thenSizeIncreases() {
        // Given:
        ShoppingCart shoppingCart = new ShoppingCart(shoppingItemCatalog);
        ShoppingItem item1 = shoppingItemCatalog.getItem("ABC4000");
        ShoppingItem item2 = shoppingItemCatalog.getItem("ABC4001");
        ShoppingItem item3 = shoppingItemCatalog.getItem("ABC4002");

        shoppingCart.add("ABC4000");
        shoppingCart.add("ABC4001");

        // When:
        shoppingCart.add("ABC4002");
        int totalCost = shoppingCart.getTotalCostInCents();
        int distinctItemsCount = shoppingCart.getDistinctItemsCount();
        int totalItemsCount = shoppingCart.getTotalItemsCount();

        // Then:
        List<ShoppingItem> shoppingItems = shoppingCart.getItems();

        assertThat(shoppingItems).containsExactly(item1, item2, item3);
        assertThat(totalCost).isEqualTo(3650000);
        assertThat(distinctItemsCount).isEqualTo(3);
        assertThat(totalItemsCount).isEqualTo(3);
    }

    @Test
    public void givenShoppingCartWithItems_whenAnItemIsAddedMultipleTimes_thenSizeIncreases() {
        // Given:
        ShoppingCart shoppingCart = new ShoppingCart(shoppingItemCatalog);
        ShoppingItem item1 = shoppingItemCatalog.getItem("ABC3000");
        ShoppingItem item2 = shoppingItemCatalog.getItem("ABC3000");
        ShoppingItem item3 = shoppingItemCatalog.getItem("ABC3000");

        shoppingCart.add("ABC3000");
        shoppingCart.add("ABC3000");

        // When:
        shoppingCart.add("ABC3000");
        int totalCost = shoppingCart.getTotalCostInCents();
        int distinctItemsCount = shoppingCart.getDistinctItemsCount();
        int totalItemsCount = shoppingCart.getTotalItemsCount();

        // Then:
        List<ShoppingItem> shoppingItems = shoppingCart.getItems();

        assertThat(shoppingItems).containsExactly(item1, item1, item1);
        assertThat(totalCost).isEqualTo(103800);
        assertThat(distinctItemsCount).isEqualTo(1);
        assertThat(totalItemsCount).isEqualTo(3);
    }

    @Test
    public void givenShoppingCartWithItems_whenItemsAddedMultipleTimes_thenSizeIncreases() {
        // Given:
        ShoppingCart shoppingCart = new ShoppingCart(shoppingItemCatalog);
        ShoppingItem item1 = shoppingItemCatalog.getItem("ABC3000");
        ShoppingItem item2 = shoppingItemCatalog.getItem("ABC4000");

        shoppingCart.add("ABC3000");
        shoppingCart.add("ABC3000");
        shoppingCart.add("ABC3000");

        // When:
        shoppingCart.add("ABC4000");
        shoppingCart.add("ABC4000");
        int totalCost = shoppingCart.getTotalCostInCents();
        int distinctItemsCount = shoppingCart.getDistinctItemsCount();
        int totalItemsCount = shoppingCart.getTotalItemsCount();

        // Then:
        Collection<ShoppingItem> shoppingItems = shoppingCart.getItems();
        Collection<ShoppingItem> shoppingDistinctItems = shoppingCart.getDistinctItems();

        assertThat(shoppingItems).containsExactly(item1, item1, item1, item2, item2);
        assertThat(shoppingDistinctItems).containsExactly(item1, item2);
        assertThat(totalCost).isEqualTo(3460200);
        assertThat(distinctItemsCount).isEqualTo(2);
        assertThat(totalItemsCount).isEqualTo(5);
    }
}
