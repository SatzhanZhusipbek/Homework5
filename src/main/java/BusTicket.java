public class BusTicket {

    String ticketClass;

    String ticketType;

    String startDate;

    String price;

    public BusTicket(String ticketClass, String ticketType, String startDate,
                     String price) {
        this.ticketClass = ticketClass;
        this.ticketType = ticketType;
        this.startDate = startDate;
        this.price = price;
    }

    public BusTicket() {

    }

    public String getTicketClass() {
        return ticketClass;
    }

    public String getTicketType() {
        return ticketType;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getPrice() {
        return price;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "{ticket class = "+ticketClass+", ticket type = "+ticketType
                +", start date = "+startDate+", price = "+price+"}";
    }
}
