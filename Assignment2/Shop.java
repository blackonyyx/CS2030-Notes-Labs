package cs2030.simulator;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Optional;

/**
 * A shop object maintains the list of servers and support queries
 * for server.
 *
 * @author Stephen Tan
 */
class Shop {

    /** List of servers. */
    private final List<HumanServer> servers;

    /** List of self-checkouts.*/
    private final List<SelfCheckout> checkouts;

    /**
     * Create a new shop with a given number of servers.
     * @param numOfServers The number of servers.
     * @param capacity the allowed queue length.
     * @param numOfCheckouts the number of selfcheckouts in the shop.
     */
    public Shop(int numOfServers,int capacity,int numOfCheckouts) {
        this.servers = Stream.iterate(1, i -> i + 1)
            .map(i -> new HumanServer(i,capacity))
            .limit(numOfServers)
            .collect(Collectors.toList());
        this.checkouts = Stream.iterate(numOfServers + 1,i -> i + 1)
            .map(i -> new SelfCheckout(i,capacity))
            .limit(numOfCheckouts)
            .collect(Collectors.toList());
    }

    /**
     * Constructor for updated shop.
     */
    public Shop(List<HumanServer> servers,List<SelfCheckout> checkouts) {
        this.servers = servers;
        this.checkouts = checkouts;
    }

    /**
     * Find a server matching the predicate.
     *
     * @param predicate A predicate to match the server with.
     * @return Optional.empty if no server matches the predicate, or the
     *     optional of the server if a matching server is found.
     */
    public Optional<HumanServer> find(Predicate<HumanServer> predicate) {
        return this.servers.stream()
            .filter(predicate)
            .findFirst();
    }
    /**
     * Find a selfcheckout matching the predicate.
     *
     * @param predicate A predicate to match the server with.
     * @return Optional.empty if no server matches the predicate, or the
     *     optional of the server if a matching server is found.
     */

    public Optional<SelfCheckout> findself(Predicate<SelfCheckout> pred) {
        return this.checkouts.stream().filter(pred).findFirst();
    }

    /**
     * Replaces the server in the shop with the new server with a new state.
     * @param Server a server to be replaced in either the server list or self checkout queue
     * @return  a new shop when one of the server changes its state.
     */
    public Shop replace(Server server) {
        if (server instanceof HumanServer) {
            HumanServer ser = (HumanServer) server;
            return new Shop(
                    servers.stream()
                    .map(s -> (s.equals(ser) ? ser : s))
                    .collect(Collectors.toList()),checkouts);
        } else {
            SelfCheckout ser = (SelfCheckout)server;
            return new Shop(servers,checkouts.stream()
                    .map(s -> (s.equals(ser) ? ser : s))
                    .collect(Collectors.toList()));
        }
    }

    /**
     * Finds the Human Server with the shortest queue. 
     * Then conpares it to the queue of self checkout, if self checkout queue is smaller
     * , returns the selfcheckout queue
     * @return a Optional containing a server which could be null.
     */
    public Optional<? extends Server> findShortest() {
        Optional<Server> server = Optional.empty();
        int size = 99999;
        for (HumanServer s: servers) {
            if (s.queueFull()) {
                continue;
            } else if (s.queueSize() == 0) {
                return Optional.of(s);
            } else if (s.queueSize() < size) {
                server = Optional.of(s);
                size = s.queueSize();
            }
        }
        if (checkouts.size() > 0) {
            if (!checkouts.get(0).queueFull()) {
                Optional<? extends Server> x = Optional.of((checkouts.get(0).queueSize() < size) 
                        ? checkouts.get(0) : server.get());
                return x;
            }
            return server;
        }
        return server;
    }

    /**
     * Return a string representation of this shop.
     * @return A string reprensetation of this shop.
     */
    public String toString() {
        return servers.toString();
    }
}
