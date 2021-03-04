package context.singleton;

import context.entities.Cleaner;
import context.entities.Customer;
import context.entities.Officer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Singleton that help in generating some customers, cleaners and officers
 * Used for tests.
 */
public class EntitySingleton {
    private static EntitySingleton instance = new EntitySingleton();
    private ArrayList<Customer> customers;
    private ArrayList<Officer> officers;
    private ArrayList<Cleaner> cleaners;
    private Random rand;
    public final static int NUMBER_OF_CUSTOMER = 6;
    public final static int NUMBER_OF_OFFICER = 1;
    public final static int NUMBER_OF_CLEANER = 2;

    private EntitySingleton(){
        makeCustomer();
        makeCleaners();
        makeOfficers();
        rand = new Random();
    }

    private void makeOfficers() {
        officers = new ArrayList<>();
        officers.add(new Officer("Officer1", "Marta"));
    }

    private void makeCleaners() {
        cleaners = new ArrayList<>();
        cleaners.add(new Cleaner("Diallo", "Nafissatou"));
        cleaners.add(new Cleaner("Cleaner2", "Bob"));
    }

    private void makeCustomer() {
        customers = new ArrayList<>(NUMBER_OF_CUSTOMER);
        customers.add(new Customer("Strauss-Kahn", "Dominique"));
        customers.add(new Customer("Customer2", "louise"));
        customers.add(new Customer("Customer3", "Roger"));
        customers.add(new Customer("Customer4", "Maxime"));
        customers.add(new Customer("Customer5", "Alexis"));
        customers.add(new Customer("Customer6", "Reynault"));
    }

    public Customer getRandomCustomer(){
        return customers.get(rand.nextInt(NUMBER_OF_CUSTOMER));
    }


    public Officer getRandomOfficer(){
        return officers.get(rand.nextInt(NUMBER_OF_OFFICER));
    }


    public Cleaner getRandomCleaner(){
        return cleaners.get(rand.nextInt(NUMBER_OF_CLEANER));
    }

    public static EntitySingleton getInstance(){
        return instance;
    }
}
