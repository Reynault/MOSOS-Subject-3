package context.entities;

import context.card.Card;
import context.extra.Extra;
import java.util.ArrayList;

public class Customer extends Entity{
    private Card cardId;
    private ArrayList<Extra> extras;

    public Customer(String lastname, String firstname){
        super(-1, lastname, firstname);
        this.extras = new ArrayList<>();
    }

    public ArrayList<Extra> getExtras() {
        return extras;
    }

    public void setExtras(ArrayList<Extra> extras) {
        this.extras = extras;
    }

    public void addExtra(Extra e){
        if (this.extras == null)
            this.extras = new ArrayList<>();
        this.extras.add(e);
    }
}
