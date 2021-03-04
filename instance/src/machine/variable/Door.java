package machine.variable;

/**
 * The door of a room
 */
public class Door {
    StateDoor state;

    public Door(){
        state = StateDoor.locked;
    }

    public StateDoor getState(){
        return this.state;
    }

    public void setState(StateDoor state){
        this.state = state;
    }
}
