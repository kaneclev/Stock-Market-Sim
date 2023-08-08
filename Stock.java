import java.util.PriorityQueue;

public class Stock {
    // need priority queues to help manage orders
    private PriorityQueue<Order> buyOrders;
    private PriorityQueue<Order> sellOrders;
    // for summary output
    private long ordersProcessed;

    public Stock() {
        sellOrders = new PriorityQueue<>();
        buyOrders = new PriorityQueue<>();

    }

    public void addOrder(Order o) {
        //todo: implement adding in order
        if (o.isSell()) {
            sellOrders.add(o);
        } else {
            buyOrders.add(o);
        }

    }
    public void performMatches() {
        ordersProcessed = 0;

        // loop until we can no longer do transactions
        while (canMatch()) {
            Order buy = buyOrders.peek();
            Order sell = sellOrders.peek();
            ordersProcessed++;

            int price;
            if (buy.getId() < sell.getId()) { // then the buy order came first
                price = buy.getPrice();
            } else {
                price = sell.getPrice();
            }


            // tells us how many shares we are working with
            int shares = Math.min(buy.getQuantity(), sell.getQuantity());

            System.out.println("Trader " + buy.getTraderId() + " purchased " + shares + " shares of Stock "
                    + buy.getStockId() + " from Trader " + sell.getTraderId() + " for $" +
                    price + "/share");

            buy.removeShares(shares);
            sell.removeShares(shares);



            // if the quantity of shares that a trader is trying to buy is zero;
            if (buy.getQuantity() == 0) {
                buyOrders.remove();
            }
            if (sell.getQuantity() == 0) {
                sellOrders.remove();
            }

        }

    }

    private boolean canMatch() {
        //todo: check if two orders can be matched at the head of the priority queues
        if (buyOrders.isEmpty() || sellOrders.isEmpty()) {
            // we cannot match, there are no orders
            return false;
        }

        // the rule is that the asking price of the seller must be less than or equal to the buyer's price
        return sellOrders.peek().getPrice() <= buyOrders.peek().getPrice();
    }

    // for trader-info
    public long ordersProcessed() {
        return ordersProcessed;
    }

    private class makeReadable {
        String order;
        makeReadable(String order) {
            this.order = order;

        }

    }

    private void verboseOutput() {

    }

}

