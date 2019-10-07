import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
/**
 * Main class used to take inputs for Simulator. 
 */
class Main {
  /**
   * Creates a Events Manager to simulate the execution of events.
   * Scanner object takes in inputs of doubles indicating arrival events of customers.
   * Arguments are then passed on to the Simulation
   * @parm args String[]
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int numServers = sc.nextInt();
    List<Double> arrivals = new ArrayList<>();
    double temp;
    while (sc.hasNext()) {
      temp = sc.nextDouble();
      arrivals.add(temp);
    }
    Simulation s = new Simulation(numServers, arrivals);
    s.processEvents();
  }
}
