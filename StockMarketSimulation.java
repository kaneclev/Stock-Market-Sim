import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class StockMarketSimulation {
    private Config config;
    private ArrayList<Stock> stocks;
    private ArrayList<Trader> traders;
    private Scanner in;
    // in order to identify order ids
    private long currId = 0;
    public StockMarketSimulation(Config c) {
        config = c;
        // todo program startup output
        System.out.println("Processing orders...");

        in = new Scanner(System.in);
        // COMMENT: <COMMENT>
        // MODE: <INPUT_MODE>
        // NUM_TRADERS: <NUM_TRADERS>
        // NUM_STOCKS: <NUM_STOCKS>

        // SKIP OVER THE COMMENT
        in.nextLine();

        // throw away the header
        in.next();
        String mode = in.next();
        System.out.println("mode: " + mode);

        in.next();
        int numTraders = in.nextInt();

        in.next();
        int numStocks = in.nextInt();

        // construct arraylists for traders and stocks using the capacity we now know
        traders = new ArrayList<>(numTraders);
        stocks = new ArrayList<>(numStocks);

        // populate the stock arraylist
        for (int i = 0; i < numStocks; i++) {
            stocks.add(new Stock());
        }

        // populate the trader arraylist
        for (int i = 0; i < numTraders; i++) {
            traders.add(new Trader());
        }

        // now we need to check for pseudorandom mode
        if (mode.equals("PR")) {
            // RANDOM_SEED: <RANDOM_SEED>
            // NUMBER_OF_ORDERS: <NUMBER_OF_ORDERS>
            // ARRIVAL_RATE: <ARRIVAL_RATE>

            in.next();
            int seed = in.nextInt();

            in.next();
            int numOrders = in.nextInt();

            in.next();
            int arrivalRate = in.nextInt();

            // use the given java PR class to change our scanner
            in = P2Random.PRInit(seed, numTraders, numStocks, numOrders, arrivalRate);

        }

    }

    public void simulate() {
        // todo implement the main processing loop for the simulation
        long currentTime = 0;

        // fixme: this while condition may not always work
        while (in.hasNextLine()) {
            Order nextOrder = getNextOrder();


            if (nextOrder.getTimestamp() > currentTime) {
                currentTime = nextOrder.getTimestamp();
            }


            // the stock id is the index of the stock
            Stock s = stocks.get(nextOrder.getStockId());

            // add the order to the stock
            s.addOrder(nextOrder);
            s.performMatches();



        }
        System.exit(0);


    }

    /**
     * read and return the next order from "in"
     * this is where we effectively read the data
     * and as we read each piece of data (IT SEEMS) that we process it and perform matches right then and there
     * @return Order object with the next order to process.
     */

    private Order getNextOrder() {
        // <TIMESTAMP> <BUY/SELL> T<TRADER_ID> S<STOCK_NUM> $<PRICE> #<QUANTITY>

        long ts = in.nextLong();
        String intent = in.next();
        int traderId = Integer.parseInt(in.next().substring(1));
        int stockId = Integer.parseInt(in.next().substring(1));
        int price = Integer.parseInt(in.next().substring(1));
        int qty = Integer.parseInt(in.next().substring(1));

        if (intent.equals("SELL")) {
            return new sellOrder(currId++, ts, traderId, stockId, price, qty);
        }
        else {
            return new buyOrder(currId++, ts, traderId, stockId, price, qty);
        }

    }


}
