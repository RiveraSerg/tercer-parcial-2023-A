package edu.uaslp.objetos.shoppingcart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import edu.uaslp.objetos.shoppingcart.exceptions.ItemNotFoundException;
import edu.uaslp.objetos.shoppingcart.exceptions.ShoppingCartException;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/*Shopping cart = carrito de compras*/
public class Exercise2ShoppingCartWithMocksTest {


    @Test
    public void givenAnEmptyShoppingCart_whenAnItemIsAdded_thenSizeIsOneAndTotalCostIsCorrect() {
        // Given:
        ShoppingItemCatalog shoppingItemCatalog = Mockito.mock(ShoppingItemCatalog.class);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingItemCatalog);
        String itemCode = "9785";

        when(shoppingItemCatalog.getItem(itemCode)).thenReturn(new ShoppingItem("Prueba", "Item de prueba", itemCode, 74150));

        // When:
        shoppingCart.add(itemCode);
        int totalCost = shoppingCart.getTotalCostInCents();
        int distinctItemsCount = shoppingCart.getDistinctItemsCount();
        int totalItemsCount = shoppingCart.getTotalItemsCount();

        // Then:
        List<ShoppingItem> shoppingItems = shoppingCart.getItems();
        ShoppingItem insertedItem = shoppingItems.get(0);

        assertThat(insertedItem.getCode()).isEqualTo(itemCode);
        assertThat(insertedItem.getName()).isEqualTo("Prueba");
        assertThat(insertedItem.getDescription()).isEqualTo("Item de prueba");
        assertThat(insertedItem.getUnitCostInCents()).isEqualTo(74150);

        assertThat(totalCost).isEqualTo(74150);
        assertThat(distinctItemsCount).isEqualTo(1);
        assertThat(totalItemsCount).isEqualTo(1);

    }

    @Test
    public void givenAShoppingCart_whenItemNotFound_thenExceptionIsThrown() {
        // Given:
        ShoppingItemCatalog shoppingItemCatalog = Mockito.mock(ShoppingItemCatalog.class);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingItemCatalog);
        String itemCode = "9785";

        when(shoppingItemCatalog.getItem(itemCode)).thenThrow(new ItemNotFoundException("Item with code 9785 not found"));

        // When:
        assertThatThrownBy(() ->shoppingCart.add(itemCode))
                .isInstanceOf(ItemNotFoundException.class)
                .isInstanceOf(ShoppingCartException.class)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Item with code 9785 not found");

        int totalCost = shoppingCart.getTotalCostInCents();
        int distinctItemsCount = shoppingCart.getDistinctItemsCount();
        int totalItemsCount = shoppingCart.getTotalItemsCount();

        // Then:
        List<ShoppingItem> shoppingItems = shoppingCart.getItems();

        assertThat(shoppingItems).isEmpty();

        assertThat(totalCost).isEqualTo(0);
        assertThat(distinctItemsCount).isEqualTo(0);
        assertThat(totalItemsCount).isEqualTo(0);

    }

    @Test
    public void testObjectsComparison() {
        // Given:
        ShoppingItemCatalog shoppingItemCatalog = Mockito.mock(ShoppingItemCatalog.class);
        ShoppingCart shoppingCart = new ShoppingCart(shoppingItemCatalog);
        String itemCode = "9785";
        ShoppingItem itemFromCatalog = new ShoppingItem("Prueba", "Item de prueba", itemCode, 74150);
        ShoppingItem itemFromShoppingCart = new ShoppingItem("Prueba", "Item de prueba", itemCode, 74150);

        when(shoppingItemCatalog.getItem(itemCode)).thenReturn(itemFromCatalog);

        // When:
        shoppingCart.add(itemCode);

        // Then:
        List<ShoppingItem> shoppingItems = shoppingCart.getItems();

        assertThat(shoppingItems).containsExactly(itemFromShoppingCart);

    }
}
