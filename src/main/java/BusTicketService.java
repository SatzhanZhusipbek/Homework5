import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BusTicketService {

    /**
     * ticketsStorage is a variable that serves as an in-memory storage.
     */

    private static final List<BusTicket> ticketsStorage = new ArrayList<>();

    /**
     * Creates a bus ticket with specified parameters.
     * @param id    the id of a ticket being created
     * @param date  the date of an event on a ticket
     * @param type  user chooses day, week, month or year as a value
     * @param price price of a ticket
     */

    public static void createTicket(String id, String date, String type, String price) {
        BusTicket ticket = new BusTicket(id, null, date, type, price);
        ticketsStorage.add(ticket);
    }

    /**
     * Removes a bus ticket with a specified parameter.
     * @param id    the id of a ticket that needs to be removed
     * @throws RuntimeException  if there is no ticket under the given id
     */

    public static void removeTicket(String id) {
        for (BusTicket ticket: ticketsStorage) {
            if (Objects.equals(ticket.getId(), id)) {
                ticketsStorage.remove(ticket);
            }
            else {
                throw new RuntimeException("There is no ticket under that id!");
            }
        }
    }

    /**
     * Obtains a bus ticket with a specified parameter.
     * @param id    the id of a ticket that needs to be found
     * @throws RuntimeException  if there is no ticket under the given id
     * @return      the bus ticket that has a particular id
     */

    public static BusTicket getTicket(String id) {
        BusTicket searchedTicket;
        for (BusTicket ticket: ticketsStorage) {
            if (Objects.equals(ticket.getId(), id)) {
                searchedTicket = ticket;
                return searchedTicket;
            }
        }
        throw new RuntimeException("There is no ticket under that id!");
    }

    /**
     * Presents a list of bus tickets that are of a particular type.
     * @param type    user chooses day, week, month or year as a value
     * @throws RuntimeException  if there are no tickets under the given type
     * @return        list of bus tickets of a particular type
     */

    public static List<BusTicket> getTicketsByType(String type) {
        List<BusTicket> searchedTickets = new ArrayList<>();
        for (BusTicket ticket: ticketsStorage) {
            if (Objects.equals(ticket.getTicketType(), type) ) {
                searchedTickets.add(ticket);
            }
        }
        if (searchedTickets.isEmpty()) {
            throw new RuntimeException("There are no tickets of the given type!");
        }
        return searchedTickets;
    }

    /**
     * Presents a list of bus tickets that are in a given price range.
     * @param startPrice    user inputs the lower limit of the range
     * @param endPrice      user inputs the higher limit of the range
     * @throws RuntimeException  if there are no tickets in the given range
     * @return              list of bus tickets that have their prices in a given range
     */

    public static List<BusTicket> getTicketsByPrice(int startPrice, int endPrice) {
        List<BusTicket> searchedTickets = new ArrayList<>();
        for (BusTicket ticket: ticketsStorage) {
            if ( Integer.parseInt(ticket.getPrice()) > startPrice &&
                    Integer.parseInt(ticket.getPrice()) < endPrice ) {
                searchedTickets.add(ticket);
            }
        }
        if (searchedTickets.isEmpty()) {
            throw new RuntimeException("There are no tickets in the given price range!");
        }
        return searchedTickets;
    }
}
