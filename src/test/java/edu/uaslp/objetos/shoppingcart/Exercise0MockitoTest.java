package edu.uaslp.objetos.shoppingcart;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class Exercise0MockitoTest {

    @Test
    public void exampleMockitoTest1(){
        ShoppingItemCatalog shoppingItem = Mockito.mock(ShoppingItemCatalog.class);

        Mockito.when(shoppingItem.getItem("ABC")).thenReturn(null);

        ShoppingItem item = shoppingItem.getItem("ABC");

        assertThat(item).isNull();
    }

    @Test
    public void exampleMockitoTest2(){
        ShoppingItemCatalog shoppingItem = Mockito.mock(ShoppingItemCatalog.class);

        Mockito.when(shoppingItem.getItem("ABC")).thenReturn(new ShoppingItem("Test", "Test description", "ABC", 100));

        ShoppingItem item = shoppingItem.getItem("ABC");

        assertThat(item).isNotNull();
        assertThat(item.getName()).isEqualTo("Test");
        assertThat(item.getDescription()).isEqualTo("Test description");
        assertThat(item.getCode()).isEqualTo("ABC");
        assertThat(item.getUnitCostInCents()).isEqualTo(100);
    }
}
