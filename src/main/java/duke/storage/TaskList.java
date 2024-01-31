package duke.storage;

import java.util.ArrayList;

public class TaskList {
    private static final String lines = "    ____________________________________________________________";
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public void add(Task task) {
        this.tasks.add(task);
        saveToFile();
    }

    public  void loadFromSave(Task task) {
       this.tasks.add(task);
    }

    public void markTask(int number) {
        this.tasks.get(number).mark();
        System.out.println("      " + this.tasks.get(number).toString());
        saveToFile();
    }
    public void unMarkTask(int number) {
        this.tasks.get(number).unMark();
        System.out.println("      " + this.tasks.get(number).toString());
        saveToFile();
    }

    public void remove(int number) {
        System.out.println("      " + this.tasks.get(number).toString());
        this.tasks.remove(number);
        saveToFile();
    }

    public int taskLength() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(lines).append("\n");
        for (int i=1;i<=tasks.size();i++) {
           result.append(String.format("    %d.",i)).append(this.tasks.get(i-1).toString()).append("\n");
        }
        result.append(lines);
        return result.toString();
    }

    private void saveToFile() {
        StringBuilder temporary = new StringBuilder();
        for (Task task : tasks) {
           temporary.append("save ").append(task.isDone?"1 ":"0 ").append(task.getOriginalCommand() + "\n");
        }
        Storage.save(temporary.toString());
    }



}
