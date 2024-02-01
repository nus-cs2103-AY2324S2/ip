import java.io.*;
import java.util.ArrayList;

public class History implements Serializable {

    private ArrayList<Task> history;

    public History() {
        history = new ArrayList<>();
    }

    //Attempts to save history (reference: https://www.baeldung.com/java-serialization)
    //For now we will only do this once when exiting the program normally. (i.e., by using "bye" command).
    public void saveHistory(File file, ArrayList<Task> tasks) {
        updateHistory(tasks);
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(this);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("oops!!!");
        }
    }

    public void updateHistory(ArrayList<Task> tasks) {
        this.history = tasks;
    }

    public ArrayList<Task> getHistory() {
        return history;
    }

    //TODO: is loading history done by history class itself?
}
