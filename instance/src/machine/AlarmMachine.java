package machine;

import context.rooms.Bedroom;
import context.rooms.Room;
import context.rooms.ServiceRoom;
import context.singleton.RoomSingleton;
import machine.variable.StateDoor;

import java.util.ArrayList;

public class AlarmMachine extends  CleanerMachine {
    boolean isAlarmOn;

    public AlarmMachine(){
        isAlarmOn = false;
    }

    /**
     * Set the alarm to on.
     * @return 0 on success, -1 on error
     */
    public int setAlarmOn(){
        if (isAlarmOn)
            return -1;
        ArrayList<Bedroom> albr = RoomSingleton.getInstance().getBedrooms();
        ArrayList<ServiceRoom> alsr = RoomSingleton.getInstance().getServiceRooms();
        for (Room r : albr){
            r.getDoor().setState(StateDoor.alwaysUnlocked);
        }
        for (Room r : alsr){
            r.getDoor().setState(StateDoor.alwaysUnlocked);
        }
        this.isAlarmOn = true;
        return 0;
    }

    /**
     * set the alarm to off
     * @return  on success, -1 on error
     */
    public int setAlarmOff(){
        if (!isAlarmOn)
            return -1;
        ArrayList<Bedroom> albr = RoomSingleton.getInstance().getBedrooms();
        ArrayList<ServiceRoom> alsr = RoomSingleton.getInstance().getServiceRooms();
        for (Room r : albr){
            r.getDoor().setState(StateDoor.locked);
        }
        for (Room r : alsr){
            r.getDoor().setState(StateDoor.locked);
        }
        this.isAlarmOn = false;
        return 0;
    }

    /**
     * Return if alarm is on or not
     */
    public boolean isAlarmOn(){
        return isAlarmOn;
    }
}
