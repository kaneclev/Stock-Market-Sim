public class Main {
    //todo: necessary for checkpoint 1:
    // summary ou


    public static void main(String[] args) {
        //  create config object for command line argument use
        Config c = new Config(args);

        // create object for stock market simulation
        StockMarketSimulation s = new StockMarketSimulation(c);
        s.simulate();
    }
}