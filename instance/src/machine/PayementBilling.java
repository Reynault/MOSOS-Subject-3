package machine;

import context.singleton.CounterIdSingleton;
import machine.variable.Reservation;
import context.card.Card;
import context.entities.Customer;
import context.rooms.Bedroom;
import java.util.ArrayList;
import java.util.Date;

public class PayementBilling {
    //List of all reservations
    protected ArrayList<Reservation> reservations;

    public PayementBilling(){
        reservations = new ArrayList<>();
    }

    /**
     * Find a reservation from a customer
     * @param c
     * @return the reservation if found, null otherwise
     */
    public Reservation getCustomerReservation(Customer c){
        if (c.getCardId() == null)
            return null;
        long toFind = c.getCardId().getId();
        for (Reservation r : reservations){
            Customer tmp = r.getCustomer();
            if (tmp.getCardId().getId() == toFind){
                return r;
            }
        }
        return null;
    }

    /**
     * Check if the date reservation is valid for the payment function
     * @param begin the beginning of the reservation
     * @param ending the ending of the reservation
     * @return true or false
     */
    public boolean checkPaymentDate(long begin, long ending){
        Date todayDate = new Date();
        long today = todayDate.getTime();
        return (begin < ending
                && begin >= today
                && today < ending);
    }

    /**
     * When a customer is paying a reservation
     * @param bed the bedRoom
     * @param begin the beginning of the reservation
     * @param ending the ending of the reservation
     * @param customer the customer who must pay
     * @return 0 on success, -1 on failure
     */
    public int payment(Bedroom bed, long begin, long ending, Customer customer){
        if (!bed.isReserved()
            && checkPaymentDate(begin, ending)
            && getCustomerReservation(customer) == null
            ){
            bed.setReserved(true);
            customer.setCardId(new Card(CounterIdSingleton.getInstance().getCount()));
            reservations.add(new Reservation(bed, begin, ending, customer));
            return 0;
        }
            return -1;
    }

    /**
     * Billing for the extra services that has taken a customer who have a reservation.
     * @param customer the customer
     * @return 0 on success, -1 on failure
     */
    public int billing(Customer customer){
        Reservation r = getCustomerReservation(customer);
        if (r == null)
            return -1;
        Bedroom bed = r.getRoom();
        if (r != null
            && bed.isReserved()
            && r.getRoom().getNumber() == bed.getNumber()){
            reservations.remove(r);
            bed.setReserved(false);
            customer.setExtras(null);
            customer.setCardId(null);
            return 0;
        }
        return -1;
    }
}
