package duke;

import java.util.ArrayList;
public class Storage {
    public ArrayList<Task> storage;
    public Storage() {
        this.storage = new ArrayList<Task>();
    }
    public void add(Task t) {
        this.storage.add(t);
    }
    public int size() {
        return this.storage.size();
    }
    public Task get(int i) {
        return this.storage.get(i);
    }
    public int indexOf(String s) {
        return this.storage.indexOf(s);
    }

    public Task pop(int index) throws DukeExceptions {
        Task t = this.storage.get(index);
        this.storage.remove(index);
        return t;
    }

    public String find(String s) throws DukeExceptions {
        String output = "";
        try {
            for (int i=0; i<this.storage.size(); i++) {
                if (this.storage.get(i).description.contains(s)) {
                    output += String.format("%d. ", i+1);
                    output += this.storage.get(i).toString() + "\n";
                }
            }
            if (output.isEmpty()) throw new DukeExceptions();

        } catch (DukeExceptions d) {
            System.out.println("Nothing was found");
        }
        return output;
    }

    public String printList() {
        String output = "";
        for (int i=0; i<this.storage.size(); i++) {
            output += String.format("%d. ", i+1);
            output += this.storage.get(i).toString() + "\n";
        }
        return output;
    }

    public String addToListOutput(Task t) {
        String output = "Got it. I've added this task:\n" +
                String.format("  %s\nNow you have %d tasks in the list.", t.toString(), this.size());
        return output;
    }



}
