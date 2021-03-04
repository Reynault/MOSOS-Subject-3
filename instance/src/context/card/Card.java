package context.card;

/**
 * Card to access a Room
 * id = 1 when it's an officer
 * id = 2 when it's a cleaner
 * id >= 3 when it's a customer
 */
public class Card {
    long id;

    public Card(int id){
        this.id = id;
    }

    public long getId(){
        return id;
    }
}
