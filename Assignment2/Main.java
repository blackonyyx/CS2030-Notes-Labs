import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.IntStream;
import cs2030.simulator.Simulation;
import cs2030.simulator.SimState;

/**
 * The LabOFourB class is the entry point into Lab 4b.
 *
 * @author atharvjoshi
 * @version CS2030 AY19/20 Sem 1 Lab 7
 */
class Main {
    /**
     * The main method for Lab 4b. Reads data from file and
     * then run a simulation based on the input data.
     *
     * @param args two arguments, first an integer specifying number of servers
     *      in the shop. Second a file containing a sequence of double values, each
     *      being the arrival time of a customer (in any order).
     */
    public static void main(String[] args) {
        createScanner(args)
            .map(scanner -> initSimState(scanner).run())
            .ifPresent(System.out::println);
    }

    /**
     * Read from inputs,  generates the seed parameters for the simulation events,
     * then passes the parameters along to a SimState instance that will run the simulation.
     * @param scanner The scanner to read inputs from.
     * @return SimState that will be printed out as the output.
     */
    public static SimState initSimState(Scanner sc) {
        int seed = sc.nextInt();
        int servers = sc.nextInt();
        int selfchecks = sc.nextInt();
        int capacity = sc.nextInt();
        int n = sc.nextInt();
        double lambda = sc.nextDouble();
        double miu = sc.nextDouble();
        double rho = sc.nextDouble();
        double restProb = sc.nextDouble();
        double greedProb = sc.nextDouble();
        Simulation rng = new Simulation(seed,lambda,miu,rho,restProb,greedProb);
        return (new SimState(servers,selfchecks,capacity,rng)).simulation(n);
    }

    /**
     * Create and return a scanner. If a command line argument is given,
     * treat the argument as a file and open a scanner on the file. Else,
     * create a scanner that reads from standard input.
     *
     * @param args The arguments provided for simulation.
     * @return A scanner or {@code null} if a filename is provided but the file
     *     cannot be open.
     */
    private static Optional<Scanner> createScanner(String[] args) {
        try {
            // Read from stdin if no filename is given, otherwise read from the
            // given file.
            if (args.length == 0) {
                // If there is no argument, read from standard input.
                return Optional.of(new Scanner(System.in));
            } else {
                // Else read from file
                FileReader fileReader = new FileReader(args[0]);
                return Optional.of(new Scanner(fileReader));
            }
        } catch (FileNotFoundException exception) {
            System.err.println("Unable to open file " + args[0] + " "
                    + exception);
        }
        return Optional.empty();
    }
}
