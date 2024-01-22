import java.util.ArrayList;

public class Memory {
    
    private ArrayList<String> tasks;
    private ArrayList<Boolean> completed;

    Memory() {
        this.tasks = new ArrayList<>();
        this.completed = new ArrayList<>();
    }

    public boolean add(String sentence) {
        this.tasks.add(sentence);
        this.completed.add(false);
        return true;
    }

    public boolean mark(int index) {
        this.completed.set(index, true);
        return true;
    }

    public boolean unmark(int index) {
        this.completed.set(index, false);
        return true;
    }

    public ArrayList<String> getTasks() {
        ArrayList<String> list = new ArrayList<>();
        for (String sentence : this.tasks) {
            list.add(sentence);
        }
        return list;
    }

    public ArrayList<Boolean> getCompleted() {
        ArrayList<Boolean> list = new ArrayList<>();
        for (Boolean bool : this.completed) {
            list.add(bool);
        }
        return list;
    }

}
