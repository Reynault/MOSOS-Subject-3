package context.rooms;

/**
 * Room to sleep, can be reserved
 */
public class Bedroom extends Room {
    private boolean isReserved;
    private int number;

    public Bedroom(int number){
        super("Bedroom n°"+number);
        isReserved = false;
        this.number = number;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public int getNumber() {
        return number;
    }

    public boolean equals(Bedroom room) {
        return (room.getNumber() == number);
    }
}
