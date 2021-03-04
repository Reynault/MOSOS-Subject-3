package machine;

import context.entities.Cleaner;
import context.entities.Customer;
import context.entities.Officer;
import context.rooms.Room;
import machine.variable.Door;
import machine.variable.Reservation;
import machine.variable.StateDoor;

import java.util.Date;

public class CheckInOut extends PayementBilling{

    public CheckInOut(){
        super();
    }

    /**
     * Check if the reserved room is accessible today
     * @param beginning the beginning of the reservation
     * @param ending the ending of the reservation
     * @return true if yes, false otherwise
     */
    public boolean checkCurrentDate(long beginning, long ending) {
        Date todayDate = new Date();
        long today = todayDate.getTime();
        return (beginning <= today && today < ending && beginning < ending);
    }

    /**
     * Check if a customer get in the room
     * @param r the room
     * @param c the customer
     * @return 0 on success, -1 on failure, 1 if anybody can.
     */
    public int checkIn(Room r, Customer c){
        if (r.getDoor().getState() == StateDoor.alwaysUnlocked){
            System.out.println("CheckIn: Alarm is ON, no check needed");
            return 1;
        }
        Reservation reservation = getCustomerReservation(c);
        if (reservation == null)
            return -1;
        Room tmp = reservation.getRoom();
        boolean dateOk = checkCurrentDate(reservation.getBegining(), reservation.getEnding());
        if (tmp.equals(r)
            && dateOk
            && r.getDoor().getState() == StateDoor.locked
        ){
            r.getDoor().setState(StateDoor.unlocked);
            System.out.println(r.getDoor().getState());
            return 0;
        }
        return -1;
    }

    /**
     *  Open a door to get out of the room
     * @param d a door
     * @return 0 on success, -1 on error, 1 if door is always unlocked so check
     */
    public int checkOut(Door d){
        if (d.getState() == StateDoor.alwaysUnlocked){
            System.out.println("CheckOut: Alarm is ON, no check needed");
            return 1;
        }
        if (d.getState() == StateDoor.locked) {
            d.setState(StateDoor.unlocked);
            return 0;
        }
        return -1;
    }

    /**
     * Allow an officer to go in a room
     * @param room the room
     * @param officer the offcier
     * @return 0 on success, -1 on failure, 1 if room's alwaysUnlocked
     */
    public int checkInOfficer(Room room, Officer officer){
        if (room.getDoor().getState() == StateDoor.alwaysUnlocked){
            System.out.println("CheckInOfficer: Alarm is ON, no check needed");
            return 1;
        }
        Door d = room.getDoor();
        if (d.getState() == StateDoor.locked) {
            d.setState(StateDoor.unlocked);
            return 0;
        }
        return -1;
    }

    /**
     * To close/lock a door
     * @param d the door to lock
     * @return 0 on success, -1 on error
     */
    public int close(Door d){
        if (d.getState() == StateDoor.unlocked) {
            d.setState(StateDoor.locked);
            return 0;
        }
        return -1;
    }
}
