package chatbot;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;




public class TaskList {

    protected ArrayList<Task> al;

    public TaskList(ArrayList<Task> al) {
        this.al = al;
    }

    public void add(Task t) {
        al.add(t);
    }

    public void remove(int c ) {
        al.remove(c);
    }

    public void asDone(int c) {
        al.get(c-1).markAsDone();
    }

    public void asNotDone(int c) {
        al.get(c-1).markAsUndone();
    }

    public Task get(int c) {
        return al.get(c);
    }

    public ArrayList<Task> mine() {
        return this.al;
    }

    public int len() {
        return this.al.size();
    }

}

