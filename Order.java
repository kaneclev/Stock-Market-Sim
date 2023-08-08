public abstract class Order implements Comparable<Order> {
    private long timestamp;
    private int traderId;
    private int stockId;
    private int price;
    private int quantity;
    private long id;

    public Order(long id, long ts, int tId, int sId, int p, int q) {
        this.timestamp = ts;
        this.traderId = tId;
        this.stockId = sId;
        this.price = p;
        this.quantity = q;
        this.id = id;

    }

    public abstract boolean isSell();

    @Override
    public abstract int compareTo(Order o);
    public long getId() {return id;}
    public long getTimestamp() {return timestamp;}
    public int getTraderId() {return traderId;}
    public int getPrice() {return price;}
    public int getStockId() {return stockId;}
    public int getQuantity() {return quantity;}

    /**
     * reduce the quantity of shares in this order.
     * @param shares
     */
    public void removeShares(int shares) {
        quantity -= shares;
    }
}
