package machine;

import context.entities.Customer;
import context.extra.Extra;
import context.rooms.ServiceRoom;
import context.singleton.RoomSingleton;
import machine.variable.Reservation;

import java.util.ArrayList;

public class AddExtra extends CheckInOut {
    public AddExtra(){}

    /**
     * Check if extra exists
     * @param e
     * @return true or false
     */
    public boolean checkExtra(Extra e){
        ArrayList<ServiceRoom> serviceRooms = RoomSingleton.getInstance().getServiceRooms();
        for (ServiceRoom sr : serviceRooms){
            if (sr.getExtras().contains(e)){
                return true;
            }
        }
        return false;
    }

    /**
     * Add an extra to a customer
     * @param c customer
     * @param e extra to add
     * @return 0 on success, -2 on reservation error, -1 for everything else.
     */
    public int addExtra(Customer c, Extra e){
        Reservation reservation = getCustomerReservation(c);
        if (reservation == null)
            return -2;
        boolean dateOk = checkCurrentDate(reservation.getBegining(), reservation.getEnding());
        if (dateOk && checkExtra(e)) {
            c.addExtra(e);
            return 0;
        }
        return -1;
    }
}
