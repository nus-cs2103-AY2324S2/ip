import java.util.ArrayList;
public class BartenderBob {
    public static final String NAME = "BartenderBob";
    private final ArrayList<String> STORAGE = new ArrayList<>();

    public void greet() {
        System.out.println("Welcome back! I'm " + NAME + "\nHow's it going out there?");
    }

    public void leave() {
        System.out.println("Bye! Another round next time!");
    }

    public void echo(String userInput) {
        System.out.println(userInput);
    }

    public void store(String task) {
        STORAGE.add(task);
        System.out.println("Added: " + task);
    }

    public void list() {
        for (int i = 0; i < STORAGE.size(); i++) {
            int number = i + 1;
            System.out.println(number + ". " + STORAGE.get(i));
        }
    }
}
