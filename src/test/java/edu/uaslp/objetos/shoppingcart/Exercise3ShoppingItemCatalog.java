package edu.uaslp.objetos.shoppingcart;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
public class Exercise3ShoppingItemCatalog {


    @Test
    public void givenAValidCode_whenGetItem_thenShoppingItemIsReturned() {
        // Given:
        ShoppingItemCatalog shoppingItemCatalog = new ShoppingItemCatalog();
        String itemCode = "ABC1000";

        // When:
        ShoppingItem item = shoppingItemCatalog.getItem(itemCode);

        // Then:
        assertThat(item).isNotNull();
        assertThat(item.getName()).isEqualTo("Escoba");
        assertThat(item.getDescription()).isEqualTo("Articulo de limpieza para barrer");
        assertThat(item.getCode()).isEqualTo(itemCode);
        assertThat(item.getUnitCostInCents()).isEqualTo(12000);
        assertThat(item.getUnitCostInPesos()).isEqualTo(120.0);
    }


    @Test
    public void givenANotValidCode_whenGetItem_thenNullIsReturned() {
        // Given:
        ShoppingItemCatalog shoppingItemCatalog = new ShoppingItemCatalog();
        String notValidCode = "1234";

        // When:
        ShoppingItem item = shoppingItemCatalog.getItem(notValidCode);

        // Then:
        Assertions.assertNull(item);
    }

}
