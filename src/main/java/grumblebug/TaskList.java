package grumblebug;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private ArrayList<Task> list;

    TaskList() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public int size() {
        return this.list.size();
    }

    public String getTasks() {
        StringBuilder builder = new StringBuilder("Okay, here... \n");
        for (int i = 1; i <= this.size(); i++) {
            builder.append(i + this.get(i).getFullStatus() + "\n");
        }
        return builder.toString();
    }

    public Task get(int i) {
        try {
            return this.list.get(i - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("List Index out of bounds!");
        }
        return null;
    }

    public void mark(int i) {
        try {
            this.get(i).setDone(true);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("List Index out of bounds!");
        }
    }

    public void unmark(int i) {
        try {
            this.get(i).setDone(false);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("List Index out of bounds!");
        }
    }

    public void delete(int i) {
        try {
            this.getList().remove(i - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("List Index out of bounds!");
        }
    }

    public String findMatches(String query) {
        StringBuilder matchesBuilder = new StringBuilder("Okay, here... \n");
        String[] queryTerms = query.split(" ");
        for (int i = 1; i <= this.size(); i++) {
            boolean anyMatches = Arrays.stream(queryTerms)
                    .anyMatch(this.get(i).description::contains);
            if (anyMatches) {
                matchesBuilder.append(i + this.get(i).getFullStatus() + "\n");
            }
        }
        return matchesBuilder.toString();
    }

    public void addToDo(String desc) {
        Task t = new Task(false, desc);
        this.add(t);
    }

    public void addDeadline(String desc, LocalDate deadline) {
        Task t = new Task(false, desc, deadline);
        this.add(t);
    }

    public void addEvent(String desc, LocalDate start, LocalDate end) {
        Task t = new Task(false, desc, start, end);
        this.add(t);
    }
}
