import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new HashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            // check whether the item is already present in stock
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // if item is already in stock, adjust quantity
            if (inStock != item) {
                item.adjustStock(inStock.getQuantityStock());
            }
            list.put(item.getName(), item);
            return item.getQuantityStock();
        }
        return 0;
    }

    public int sellStock(StockItem item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);
        // if item is already in stock, adjust quantity
        if ((inStock != null) && (inStock.getQuantityStock() >= quantity) && (quantity > 0)) {
            inStock.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }

    public Map<String, StockItem> getList() {
        return list;
    }
    public StockItem get(String key){
        return list.get(key);
    }
    public Map<String,StockItem> items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s="\nStock List:\n";
        double totalCost=0.0;
        for (Map.Entry<String,StockItem> item: list.entrySet()){
            StockItem stockItem=item.getValue();
            double itemValue=stockItem.getPrice()*stockItem.getQuantityStock();
            s=s + stockItem+". There are "+stockItem.getQuantityStock()+" in Stocks. Value of items: "+String.format("%.2f",itemValue)+"\n";
            totalCost+=itemValue;
        }
        return s+" Total stock "+totalCost;
    }
}

