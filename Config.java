import gnu.getopt.LongOpt;
import gnu.getopt.Getopt;
import java.util.Objects;

public class Config {
    // mode initializations
    private boolean verbose = false;
    private boolean median = false;
    private boolean traderInfo = false;
    private boolean testing = false;
    public Config(String[] args) {
        // todo implement getopt processing for the command line args
        LongOpt[] longOptions = {
                new LongOpt("verbose", LongOpt.NO_ARGUMENT, null, 'v'),
                new LongOpt("median", LongOpt.NO_ARGUMENT, null, 'm'),
                new LongOpt("trader-info", LongOpt.NO_ARGUMENT, null, 'i'),
                new LongOpt("testing", LongOpt.NO_ARGUMENT, null, 't')
        };

        Getopt g = new Getopt("Project2", args, "vmit", longOptions);

        int choice;

        while ((choice = g.getopt()) != -1) {
            switch (choice) {
                case 'v':
                    verbose = true;
                    break;
                case 'm':
                    median = true;
                    break;
                case 'i':
                    traderInfo = true;
                    break;
                case 't':
                    testing = true;
                    break;
                default:
                    System.err.println("Unknown command line argument option: " + choice);
                    System.exit(1);
            }

        }
        // call configHandler to do work
        configHandler();
    } // end of constructor


    private void configHandler() {
        if (!verbose && !median && !traderInfo && !testing) {
            System.err.println("There must be an output mode specified. Your options are as follows: ");
            System.out.println("verbose, median, trader-info, testing.");
            System.exit(1);
        }

        // config testing
        if (verbose) {
            System.out.print("verbose is set");
            System.out.println("");
        }
        if (median) {
            System.out.print("median is set");
            System.out.println("");
        }
        if (traderInfo) {
            System.out.print("trader-info is set");
            System.out.println("");
        }
        if (testing) {
            System.out.print("testing mode is on");
            System.out.println("");
        }
    }

    // getters for other classes
    public boolean isVerbose() {
        return verbose;
    }
    public boolean isMedian() {
        return median;
    }

    public boolean isTraderInfo() {
        return traderInfo;
    }
    public boolean isTesting() {
        return testing;
    }
}
