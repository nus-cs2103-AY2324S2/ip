import java.io.*;
import java.util.ArrayList;

public class History implements Serializable {

    private ArrayList<Task> history;

    public History() {
        history = new ArrayList<>();
    }

    public void addTask(Task task) {
        if (task != null) {
            history.add(task);
            System.out.println("I helped you add task '" + task.fullStatus() + "'. But do it yourself next time! Hmmph!"  + "\n");
        }
    }

    //Marks a particular task as done. Takes in a string command.
    public void markTask(String input) {
        int idx;
        try {
            idx = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("I can't help you out if you don't tell me what to mark! ");
            System.out.println("[Missing input parameter for mark]\n");
            return;
        }
        try {
            history.get(idx).complete();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Jeez, you really ought to give me a number I can work with... got that?");
            System.out.println("[Item index exceeds history count]\n");
            return;
        }
        System.out.println("Good work, I guess.");
        System.out.println((idx + 1) + "." + history.get(idx).fullStatus());
        System.out.println();
    }

    public void listHistory() {
        int num = 1;
        if (history.size() == 0) {
            System.out.println("Looks like you have way too much free time on your hands, huh.");
            System.out.println("[No items in list]");
        }
        for (Task s : history) {
            if (s.isDone()) {
                System.out.println(num + "." + s.fullStatus());
            } else {
                System.out.println(num + "." + s.fullStatus());
            }
            num += 1;
        }
        System.out.println();
    }

    //Deletes a specified task. Takes in a string input.
    public void deleteTask(String input) {
        String fullStatus;
        int index = Integer.parseInt(input.split(" ")[1].strip()) - 1;
        try {
            fullStatus = history.get(index).fullStatus();
            history.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There's nothing there, dummy...");
            System.out.println("[Tried to remove non-existent event]\n");
            return;
        }
        System.out.println("Fine! If that's what you really want...");
        System.out.println("[Removed " + fullStatus + "]\n");
    }

    //Attempts to save history (reference: https://www.baeldung.com/java-serialization)
    //For now we will only do this once when exiting the program normally. (i.e., by using "bye" command).
    public void saveHistory(File file) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(this);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("oops!!!");
        }
    }
}
