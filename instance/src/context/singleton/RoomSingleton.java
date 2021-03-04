package context.singleton;

import context.extra.Extra;
import context.rooms.Bedroom;
import context.rooms.Room;
import context.rooms.ServiceRoom;
import context.rooms.services.MassageService;
import context.rooms.services.RestaurantService;
import machine.variable.StateDoor;

import java.util.ArrayList;
import java.util.Random;

/**
 * Singleton which get us all the things that come to rooms.
 * Like giving us a random room, a random bedroom or a randomServiceRoom.
 */
public class RoomSingleton {
    private static RoomSingleton instance = new RoomSingleton();
    ArrayList<Bedroom> bedrooms;
    ArrayList<ServiceRoom> serviceRooms;
    Random rand;
    public final static int NUMBER_OF_ROOM = 4;

    private RoomSingleton(){
        bedrooms = new ArrayList<>(NUMBER_OF_ROOM);
        for (int number = 0; number != NUMBER_OF_ROOM; number++){
            bedrooms.add(new Bedroom(number));
        }
        makeServiceRooms();
        rand = new Random();
    }

    private void makeServiceRooms() {
        this.serviceRooms = new ArrayList<>();
        this.serviceRooms.add(new RestaurantService());
        this.serviceRooms.add(new MassageService());
    }

    /**
     * To get a random bedroom from the not reserved ones
     * @return a bedroom
     */
    public Bedroom getUnreservedBedroom(){
        ArrayList<Bedroom> tmp = new ArrayList<>();
        for (Bedroom bed : bedrooms){
            if (!bed.isReserved()){
                tmp.add(bed);
            }
        }
        if (tmp.size() == 0)
            return null;
        else
            return tmp.get(rand.nextInt(tmp.size()));
    }

    /**
     * Get a random room where's the door is unlocked.
     * @return a Room
     */
    public Room getUnlockedDoor(){
        ArrayList<Room> ar = new ArrayList<>();
        for (Bedroom bed : bedrooms){
            if (bed.getDoor().getState() == StateDoor.unlocked)
                ar.add(bed);
        }
        for (ServiceRoom sr : serviceRooms){
            if (sr.getDoor().getState() == StateDoor.unlocked)
                ar.add(sr);
        }
        if (ar.size() == 0)
            return null;
        else {
            return ar.get(rand.nextInt(ar.size()));
        }
    }

    /**
     * Get a random room where's the door is locked.
     * @return a Room
     */
    public Room getLockedDoor(){
        ArrayList<Room> ar = new ArrayList<>();
        for (Bedroom bed : bedrooms){
            if (bed.getDoor().getState() == StateDoor.locked)
                ar.add(bed);
        }
        for (ServiceRoom sr : serviceRooms){
            if (sr.getDoor().getState() == StateDoor.locked)
                ar.add(sr);
        }
        if (ar.size() == 0)
            return null;
        else {
            Random rand = new Random();
            int r = rand.nextInt(ar.size());
            return ar.get(r);
        }
    }

    public ArrayList<Bedroom> getBedrooms(){
        return this.bedrooms;
    }

    public ArrayList<ServiceRoom> getServiceRooms(){
        return this.serviceRooms;
    }

    public static RoomSingleton getInstance(){
        return instance;
    }

    /**
     * Get all the rooms (ServiceRoom + Bedroom)
     * @return
     */
    public ArrayList<Room> getRooms(){
        ArrayList<Room> ar = new ArrayList<>();
        for (Bedroom bed : bedrooms)
            ar.add(bed);
        for (ServiceRoom sr : serviceRooms)
            ar.add(sr);
        return ar;
    }

    /**
     * Get a random room from all the rooms
     */
    public Room getRandomRoom() {
        ArrayList<Room> ar = getRooms();
        return ar.get(rand.nextInt(ar.size()));
    }

    /**
     * Get a random extra from aa random ServiceRoom
     * @return
     */
    public Extra getRandomExtra(){
        ServiceRoom sr = serviceRooms.get(rand.nextInt(serviceRooms.size()));
        int extraSize = sr.getExtras().size();
        return sr.getExtras().get(rand.nextInt(extraSize));
    }
}
