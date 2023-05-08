package edu.uaslp.objetos.shoppingcart;
import edu.uaslp.objetos.shoppingcart.exceptions.ItemNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCart {

    private final ShoppingItemCatalog shoppingItemCatalog;
    private final Map<String, Integer> itemsCount;
    private final List<ShoppingItem> items;

    public ShoppingCart(ShoppingItemCatalog shoppingItemCatalog) {
        this.shoppingItemCatalog = shoppingItemCatalog;
        this.itemsCount = new HashMap<>();
        this.items = new ArrayList<>();
    }

    public void add(String itemCode) throws ItemNotFoundException {
        ShoppingItem item = shoppingItemCatalog.getItem(itemCode);
        if (item == null) {
            throw new ItemNotFoundException("Item with code " + itemCode + " not found");
        } else {
            items.add(item);
            itemsCount.put(itemCode, itemsCount.getOrDefault(itemCode, 0) + 1);
        }
    }

    public void remove(String itemCode) {
        ShoppingItem item = shoppingItemCatalog.getItem(itemCode);
        if (item != null) {
            if (itemsCount.containsKey(itemCode)) {
                items.remove(item);
                int count = itemsCount.get(itemCode);
                if (count > 1) {
                    itemsCount.put(itemCode, count - 1);
                } else {
                    itemsCount.remove(itemCode);
                }
            }
        }
    }

    public List<ShoppingItem> getItems() {
        return items;
    }

    public Collection<ShoppingItem> getDistinctItems() {
        return itemsCount.keySet().stream()
                .map(shoppingItemCatalog::getItem)
                .collect(Collectors.toList());
    }

    public int getTotalCostInCents() {
        int totalCost = 0;
        for (Map.Entry<String, Integer> entry : itemsCount.entrySet()) {
            String itemId = entry.getKey();
            int count = entry.getValue();
            ShoppingItem item = shoppingItemCatalog.getItem(itemId);
            if (item != null) {
                totalCost += count * item.getUnitCostInCents();
            }
        }
        return totalCost;
    }

    public int getDistinctItemsCount() {
        return itemsCount.size();
    }

    public int getTotalItemsCount() {
        int totalCount = 0;
        for (int count : itemsCount.values()) {
            totalCount += count;
        }
        return totalCount;
    }
}

