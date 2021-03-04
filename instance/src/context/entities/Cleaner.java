package context.entities;

import context.card.Card;

public class Cleaner extends Entity{
    private Card cardId;

    public Cleaner(String lastname, String firstname){
        super(2, lastname, firstname);
    }
}
