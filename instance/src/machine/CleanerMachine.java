package machine;

import context.entities.Cleaner;
import context.entities.Customer;
import context.entities.Officer;
import context.rooms.Room;
import machine.variable.Door;
import machine.variable.Reservation;
import machine.variable.StateDoor;

import java.util.ArrayList;
import java.util.HashMap;

public class CleanerMachine extends AddExtra {
    ArrayList<Room> toclean; // the room to clean
    private HashMap<Room, Cleaner> isBeingCleaned; // which room is being cleaned by whom

    public CleanerMachine(){
        isBeingCleaned = new HashMap<>();
        toclean = new ArrayList<>();
    }

    /**
     * A cleaner enter a room which need to be cleaned
     * @param room the room
     * @param cleaner the cleaner
     * @return 0 on success, -1 on error, 1 if alarm is on.
     */
    public int checkInCleaner(Room room, Cleaner cleaner){
        if (room.getDoor().getState() == StateDoor.alwaysUnlocked){
            System.out.println("CheckIn: Alarm is ON, no check needed");
            return 1;
        }
        Door d = room.getDoor();
        if (toclean.contains(room)
            && d.getState() == StateDoor.locked
            && !isBeingCleaned.containsValue(cleaner)) {
            d.setState(StateDoor.unlocked);
            toclean.remove(room);
            isBeingCleaned.put(room, cleaner);
            return 0;
        }
        return -1;
    }

    /**
     * When a cleaner has finishing cleaning a room
     * @param room the room that has been cleaned
     * @return 0 on success, -1 on failure
     */
    public int finishToClean(Room room){
        if (isBeingCleaned.containsKey(room)){
            toclean.remove(room);
            isBeingCleaned.remove(room);
            return 0;
        }
        return -1;
    }

    /**
     * An officer request the cleaning of a room
     * @param room the room
     * @param officer the officer
     * @return 0 on success, -1 on failure
     */
    public int requestCleaning(Room room, Officer officer){
        if (!isBeingCleaned.containsKey(room)
             && !toclean.contains(room)
        ){
            toclean.add(room);
            return 0;
        }
        return -1;
    }

    /**
     * When a customer request a cleaning for his bedroom or a serviceRoom
     * @param room the room to clean
     * @param customer the customer
     * @return 0 on success, -2 if no reservation have been made, -1 for any others errors.
     */
    public int requestCleaningClient(Room room, Customer customer){
        Reservation r = getCustomerReservation(customer);
        if (r == null)
            return -2;
        Room tmp = r.getRoom();
        boolean dateOk = checkCurrentDate(r.getBegining(), r.getEnding());
        if (!isBeingCleaned.containsKey(room)
            && !toclean.contains(room)
            && room.equals(tmp)
            && dateOk
        ){
            toclean.add(room);
            return 0;
        }
        return -1;
    }

    @Override
    /**
     * Overriding checkIn for customer because we need to know if the room is being cleaned or not.
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
                && !isBeingCleaned.containsKey(r)
        ){
            r.getDoor().setState(StateDoor.unlocked);
            System.out.println(r.getDoor().getState());
            return 0;
        }
        return -1;
    }

    /**
     * Return all the room that need cleaning
     * @return
     */
    public ArrayList<Room> getToclean(){
        return toclean;
    }

    /**
     * Return the assignment of cleaners
     * @return
     */
    public HashMap<Room, Cleaner> getIsBeingCleaned(){
        return isBeingCleaned;
    }
}
