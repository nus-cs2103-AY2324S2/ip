package Storage;

import java.util.ArrayList;

public class Storage {
    private static final String lines = "    ____________________________________________________________";
    private final ArrayList<Task> storage;

    public Storage() {
        this.storage = new ArrayList<>();
    }
    public void add(Task task) {
        this.storage.add(task);
    }

    public void markTask(int number) {
       this.storage.get(number).mark();
       System.out.println("      " + this.storage.get(number).toString());
    }
    public void unMarkTask(int number) {
       this.storage.get(number).unMark();
       System.out.println("      " + this.storage.get(number).toString());

    }
    public int taskLength() {
        return this.storage.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(lines).append("\n");
        for(int i=1;i<=storage.size();i++) {
           result.append(String.format("    %d.",i)).append(this.storage.get(i-1).toString()).append("\n");
        }
        result.append(lines);
        return result.toString();
    }


}
