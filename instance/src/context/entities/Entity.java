package context.entities;

import context.card.Card;

/**
 * An entity is a human, with a lastname, a firstname and a CardId
 */
public abstract class Entity {

    protected Card cardId;
    private String lastname;
    private String firstname;

    protected Entity(int id, String lastname, String firstname){
        cardId = null;
        if (id != - 1)
            this.cardId = new Card(id);
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public Card getCardId() {
        return cardId;
    }

    public void setCardId(Card cardId) {
        this.cardId = cardId;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }
}
