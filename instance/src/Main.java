import context.entities.Cleaner;
import context.entities.Customer;
import context.entities.Officer;
import context.extra.Extra;
import context.rooms.Room;
import context.singleton.EntitySingleton;
import context.singleton.RoomSingleton;
import machine.AlarmMachine;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class Main {
    private AlarmMachine machine;
    private Random rand;
    ArrayList<Integer> action;
    public final static int NUMBER_OF_ACTION = 13;

    /**
     * Every action will be made at least once every 13 actions.
     */
    private void makeActionList(){
        if (action.size() != 0)
            return ;
        for (int i = 0; i != NUMBER_OF_ACTION; i++)
            action.add(i);
    }

    /**
     * Doing an action in the goal of testing the program
     */
    public Main() {
        machine = new AlarmMachine();
        action = new ArrayList<>(NUMBER_OF_ACTION);
        rand = new Random();
        while (true) {
            makeActionList();
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int n = rand.nextInt(action.size());
            int choice = action.get(n);
            action.remove(n);
            switch (choice) {
                case 0:
                    payment();
                    break;
                case 1:
                    billing();
                    break;
                case 2:
                    checkInCustomer();
                    break;
                case 3:
                    checkOut();
                    break;
                case 4:
                    close();
                    break;
                case 5:
                    addExtra();
                    break;
                case 6:
                    finishToClean();
                    break;
                case 7:
                    requestCleaning();
                    break;
                case 8:
                    requestCleaningClient();
                    break;
                case 9:
                    checkInCleaner();
                    break;
                case 10:
                    checkInOfficer();
                    break;
                case 11:
                    setAlarmOff();
                    break;
                case 12:
                    setAlarmOn();
                    break;
            }
        }
    }

    /**
     * The payment of the machine.
     * Take a random unreserved room, a random customers
     * and try to make a reservation with them
     */
    private void payment() {
        RoomSingleton roomSingleton = RoomSingleton.getInstance();
        Date todayDate = new Date();
        long now = todayDate.getTime();
        Room room = roomSingleton.getUnreservedBedroom();
        Customer c = EntitySingleton.getInstance().getRandomCustomer();
        if (room == null)
            return;
        int ret = machine.payment(roomSingleton.getUnreservedBedroom(), now, now + 100000, c);
        if (ret == 0)
            System.out.println("Payment:" + c.getFirstname() + " has reserved " + room.getName());
        if (ret == -1)
            System.out.println("Payment: Reservation impossible for " + c.getFirstname() + " for " + room.getName());
    }

    /**
     * Select a random Customer and try to make a bill for him.
     * even if he has no reservation (testing error)
     */
    private void billing() {
        Customer c = EntitySingleton.getInstance().getRandomCustomer();
        int ret = machine.billing(c);
        if (ret == 0)
            System.out.println("Billing: Billing done for " + c.getFirstname());
        if (ret == -1)
            System.out.println("Billing: " + c.getFirstname() + " has no reservation.");
    }

    /**
     * Take a random customer and try to make him enter a random room
     */
    private void checkInCustomer() {
        Customer c = EntitySingleton.getInstance().getRandomCustomer();
        Room r = RoomSingleton.getInstance().getRandomRoom();
        int ret = machine.checkIn(r, c);
        if (ret == 0)
            System.out.println("CheckIn: Customer " + c.getFirstname() + " is now in " + r.getName());
        else
            System.out.println("CheckIn: Customer " + c.getFirstname() + " has not access to " + r.getName());
    }

    /**
     * Take a random officer and try to make him enter a random room
     */
    private void checkInOfficer() {
        Officer off = EntitySingleton.getInstance().getRandomOfficer();
        Room r = RoomSingleton.getInstance().getRandomRoom();
        int ret = machine.checkInOfficer(r, off);
        if (ret == 0)
            System.out.println("CheckInOfficer: Officer " + off.getFirstname() + " is now in " + r.getName());
        else if (ret == -1) {
            System.out.println("CheckInOfficer: Not allowed");
        }
    }

    /**
     * Take a random cleaner and make him clean a random room which need cleaning
     */
    private void checkInCleaner() {
        Cleaner c = EntitySingleton.getInstance().getRandomCleaner();
        int numberToClean = machine.getToclean().size();
        if (numberToClean == 0)
            return;
        Room r = machine.getToclean().get(rand.nextInt(numberToClean));
        int ret = machine.checkInCleaner(r, c);
        if (ret == 0)
            System.out.println("CheckInCleaner: Cleaner " + c.getFirstname() + " is now in " + r.getName());
        else if (ret == -1)
            System.out.println("CheckInCleaner: " + r.getName() + " doesn't need to be cleaned ");
    }

    /**
     * Getting out of a room
     */
    private void checkOut() {
        if (machine.isAlarmOn()) {
            System.out.println("CheckOut: Alarm is on, not check required");

        }
        Room room = RoomSingleton.getInstance().getLockedDoor();
        if (room == null)
            return;
        int ret = machine.checkOut(room.getDoor());
        if (ret == 0)
            System.out.println("Checkout: " + room.getName() + " is now empty");
    }

    /**
     * Closing a random opened room
     */
    private void close() {
        Room room = RoomSingleton.getInstance().getUnlockedDoor();
        if (room == null)
            return;
        int ret = machine.close(room.getDoor());
        if (ret == 0)
            System.out.println("Close: Door of " + room.getName() + " is now closed");
        else
            System.out.println("Close: Door of " + room.getName() + " is already closed !");
    }

    /**
     * Adding a random extra to a random customer
     */
    private void addExtra() {
        Extra extra = RoomSingleton.getInstance().getRandomExtra();
        if (extra == null)
            return;
        Customer c = EntitySingleton.getInstance().getRandomCustomer();
        int ret = machine.addExtra(c, extra);
        if (ret == 0)
            System.out.println("addExtra: Customer " + c.getFirstname() + " has commanded " + extra.getName());
        else if (ret == -1)
            System.out.println("addExtra: Customer " + c.getFirstname() + " can't command " + extra.getName());
        else if (ret == -2)
            System.out.println("addExtra: Customer " + c.getFirstname() + " has no reservation at all !");
    }

    /**
     * Finishing to clean a random room which is being cleaned, if any.
     */
    private void finishToClean() {
        ArrayList<Room> tmp = new ArrayList<>();
        HashMap<Room, Cleaner> isBeingCleaned = machine.getIsBeingCleaned();
        for (Room r : isBeingCleaned.keySet()) {
            tmp.add(r);
        }
        if (tmp.size() == 0) {
            System.out.println("FinishToClean: No room being cleaned");
            return ;
        }
        Room r = tmp.get(rand.nextInt(tmp.size()));
        Cleaner cleaner =isBeingCleaned.get(r);
        machine.finishToClean(r);
        System.out.println("finishToClean: " + cleaner.getFirstname() + " has cleaned " + r.getName());

    }

    /**
     * When a random officer request the cleaning of a random room
     */
    private void requestCleaning() {
        Officer off = EntitySingleton.getInstance().getRandomOfficer();
        Room r = RoomSingleton.getInstance().getRandomRoom();
        int ret = machine.requestCleaning(r, off);
        System.out.println("RequestCleaning: Officer " + off.getFirstname() + " has requested cleaning for " + r.getName());
        if (ret == -1) {
            System.out.println("--> But the request has already been made");
        }
    }

    /**
     * When a random client is requesting the cleaning of his room
     */
    private void requestCleaningClient() {
        Room r = RoomSingleton.getInstance().getRandomRoom();
        Customer c = EntitySingleton.getInstance().getRandomCustomer();
        int ret = machine.requestCleaningClient(r, c);
        if (ret == 0)
            System.out.println("RequestCleaningClient: " + c.getFirstname() + " has requested cleaning for " + r.getName());
    }

    /**
     * Set the alarm to on
     */
    private void setAlarmOn() {
        int ret = machine.setAlarmOn();
        if (ret == 0)
            System.out.println("setAlarmOn: Alarm is now On, all door will be unlocked");
    }

    /**
     * Set the alarm to off
     */
    private void setAlarmOff() {
        int ret = machine.setAlarmOff();
        if (ret == 0)
            System.out.println("setAlarmOff: Alarm is now Off, all doors will be closed");
    }

    public static void main(String[] args) {
        new Main();
    }
}
