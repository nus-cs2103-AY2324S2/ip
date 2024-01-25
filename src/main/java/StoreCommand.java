import java.util.ArrayList;

public class StoreCommand {
    ArrayList<String> stored;
    int counter;
    public StoreCommand() {
        this.stored = new ArrayList<>();
        this.counter = 0;
    }

    public void addCommand(String c) {
        this.stored.add(c);
    }

    public ArrayList<String> returnCommands() {
        return this.stored;
    }
}