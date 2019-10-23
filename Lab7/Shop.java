import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import java.util.Optional;
import java.util.function.Predicate;
/**
 * A shop object maintains the list of servers and support queries
 * for server.
 *
 * @author weitsang
 * @author atharvjoshi
 * @version CS2030 AY19/20 Sem 1 Lab 7
 */
class Shop {
  /** List of servers. */
  private final List<Server> servers;

  /**
   * Create a new shop with a given number of servers.
   * @param numOfServers The number of servers.
   */
  Shop(int numOfServers) {
    this.servers = Stream.iterate(1,x->x+1).limit(numOfServers).map(x-> new Server(x)).collect(Collectors.toList()); 
  }
  Shop(List<Server> s) {
    this.servers = s;
  }
  public Shop copy(){
    return new Shop(this.servers);
  }
  /**
   * Return the first idle server in the list.
   *
   * @return An idle server, or {@code null} if every server is busy.
   */
  public Optional<Server> findIdleServer() {
    return servers.stream().filter(x->x.isIdle()).findFirst();
  }
  public Optional<Server> find(Predicate<Server> t){
    return servers.stream().filter(t).findFirst();
  }
  public Shop replace(Server s) {
    return new Shop(copy().servers.stream().map(x-> x.equals(s)? s :x).collect(Collectors.toList()));
  }
  /**
   * Return the first server with no waiting customer.
   * @return A server with no waiting customer, or {@code null} is every
   *     server already has a waiting customer.
   */
  public Optional<Server> findServerWithNoWaitingCustomer() {
    return servers.stream().filter(x->!x.hasWaitingCustomer()).findFirst();
  }

  /**
   * Return a string representation of this shop.
   * @return A string reprensetation of this shop.
   */
  public String toString() {
    return servers.toString();
  }
}
