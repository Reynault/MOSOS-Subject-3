package context.rooms;

import machine.variable.Door;

public abstract class Room {
    private String name; // name of the room, like "bedroom n°2" or "restaurant"
    private Door door;

    protected Room(String name) {
        this.name = name;
        this.door = new Door();
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return getPrice();
    }

    public Door getDoor() {
        return door;
    }

    public boolean equals(Room room){
        if (room.getName().equals(name)
            && room.getDoor().getState() == door.getState()){
            return true;
        }
        return false;
    }
}
