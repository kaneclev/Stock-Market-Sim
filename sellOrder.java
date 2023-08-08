public class sellOrder extends Order {
    public sellOrder(long id, long ts, int tId, int sId, int p, int q) {
        super(id, ts, tId, sId, p, q);
    }

    @Override
    public boolean isSell() {
        return true;
    }


    //todo: note that compareTo just tells us whether or not this.order is a higher priority than other Order;
    @Override
    public int compareTo(Order o) {

        // return a negative int when this order is higher priority than the comparison order
        if (this.getPrice() == o.getPrice()) {
            // then the order than came first gets priority
            // note: smaller timestamp means the order came earlier
            return (int) (this.getId() - o.getId());
        }

        // fixme price comparison will have to be different for a buy order.
        return this.getPrice() - o.getPrice();
    }
}
