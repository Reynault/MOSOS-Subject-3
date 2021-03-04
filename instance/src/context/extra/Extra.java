package context.extra;

/**
 * Service room offers some Extras
 */
public class Extra {
    private String name;
    private double price;

    public Extra(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean equals(Extra e){
        if (e.getName().equals(name)
            && e.getPrice() == price
        ){
            return true;
        }
        return false;
    }
}
