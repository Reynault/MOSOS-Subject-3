package context.rooms;

import context.extra.Extra;
import java.util.ArrayList;

/**
 * A room that offers a service, like a swimming pool, restaurant.
 * These rooms have some Extra, like only massaging the head, the back, or the foot.
 */

public abstract class ServiceRoom extends Room {
    protected ArrayList<Extra> extras;

    protected ServiceRoom(String name) {
        super(name);
        this.extras = new ArrayList<>();
    }

    public ArrayList<Extra> getExtras(){
        return this.extras;
    }
}
