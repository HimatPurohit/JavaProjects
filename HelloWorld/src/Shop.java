public class Shop {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {
        StockItem tempItem = new StockItem("bread", 0.86, 100);
        stockList.addStock(tempItem);

        tempItem = new StockItem("cake", 1.10, 7);
        stockList.addStock(tempItem);

        tempItem = new StockItem("car", 12.50, 2);
        stockList.addStock(tempItem);
        tempItem = new StockItem("chair", 52.0, 8);
        stockList.addStock(tempItem);
        tempItem = new StockItem("cup", 0.86, 20);
        stockList.addStock(tempItem);
        tempItem = new StockItem("bread", 0.86, 35);
        stockList.addStock(tempItem);
        tempItem = new StockItem("juice", 0.86, 26);
        stockList.addStock(tempItem);
        tempItem = new StockItem("towel", 0.86, 38);
        stockList.addStock(tempItem);
        tempItem = new StockItem("vase", 0.86, 46);
        stockList.addStock(tempItem);

        System.out.println(stockList);
    }
}
