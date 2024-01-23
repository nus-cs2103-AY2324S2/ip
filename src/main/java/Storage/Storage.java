package Storage;

import java.util.ArrayList;

public class Storage {
    private static final String lines = "    ____________________________________________________________";
    private final ArrayList<Task> storage;

    public Storage() {
        this.storage = new ArrayList<>();
    }
    public void add(String input) {
        Task task = new Task(input);
        this.storage.add(task);
    }

    public void markTask(int number) {
       this.storage.get(number).mark();
       System.out.println(String.format("      [%s] %s", this.storage.get(number).getStatusIcon(),
               this.storage.get(number).toString()));
    }
    public void unMarkTask(int number) {
       this.storage.get(number).unMark();
       System.out.println(String.format("      [%s] %s", this.storage.get(number).getStatusIcon(),
                this.storage.get(number).toString()));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(lines).append("\n");
        for(int i=1;i<=storage.size();i++) {
           result.append(String.format("    %d.",i)).append(String.format("[%s] %s\n", this.storage.get(i-1).getStatusIcon(),
                   this.storage.get(i-1).toString()));
        }
        result.append(lines);
        return result.toString();
    }


}
