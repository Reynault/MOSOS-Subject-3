package context.singleton;

/**
 * Singleton which help us give an ID to customers.
 * Start at 3.
 */
public class CounterIdSingleton {
    private static CounterIdSingleton instance = new CounterIdSingleton();
    private int count;

    private CounterIdSingleton(){
        count = 3; // Because 1 is for officer, 2 for cleaners
    }

    public static CounterIdSingleton getInstance() {
        return instance;
    }

    public int getCount() {
        count = count + 1;
        return count;
    }
}
