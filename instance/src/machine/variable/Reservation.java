package machine.variable;

import context.entities.Customer;
import context.rooms.Bedroom;

/**
 * A reservation which start at begining and finish at ending
 * Link a Bedroom and a Customer.
 */
public class Reservation {
    private long begining;
    private long ending;
    private Bedroom room;
    private Customer customer;

    public Reservation(Bedroom bed, long begining, long end, Customer customer) {
        this.room = bed;
        this.begining = begining;
        this.ending = end;
        this.customer = customer;
    }

    public long getBegining() {
        return begining;
    }

    public long getEnding() {
        return ending;
    }

    public Bedroom getRoom() {
        return room;
    }

    public void setRoom(Bedroom room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }
}
