import java.util.ArrayList;
public class BartenderBob {
    private static final String NAME = "BartenderBob";
    private final ArrayList<Task> STORAGE = new ArrayList<>();
    public BartenderBob() {
        System.out.println("Welcome back! I'm " + NAME + "\nHow's it going out there?");
    }

    public void leave() {
        System.out.println("Bye! Another round next time!");
    }

    public void echo(String userInput) {
        System.out.println(userInput);
    }

    public void store(Task task) {
        STORAGE.add(task);
        int totalTasks = STORAGE.size();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.show());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
    }
    public void list() {
        System.out.println("Here are the tasks in your list!");
        for (int i = 0; i < STORAGE.size(); i++) {
            int number = i + 1;
            System.out.println(number + "." + STORAGE.get(i).show());
        }
    }

    public void markDone(String index) throws BartenderBobException{
        try {
            int integerIndex = Integer.parseInt(index);
            Task task = STORAGE.get(integerIndex - 1);
            task.mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.show());
        } catch (IndexOutOfBoundsException e) {
            throw new BartenderBobException();
        }
    }

    public void unmarkDone(String index) throws BartenderBobException{
        try {
            int integerIndex = Integer.parseInt(index);
            Task task = STORAGE.get(integerIndex - 1);
            task.unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task.show());
        } catch (IndexOutOfBoundsException e) {
            throw new BartenderBobException();
        }

    }
    public void delete(String index) throws BartenderBobException{
        try {
            int integerIndex = Integer.parseInt(index);
            String display = STORAGE.get(integerIndex - 1).show();
            STORAGE.remove(integerIndex - 1);
            int totalTasks = STORAGE.size();
            System.out.println("Noted. I've removed this task:");
            System.out.println(display);
            System.out.println("Now you have " + totalTasks + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new BartenderBobException();
        }

    }
}
