package chatbot;
import java.io.*;
import java.util.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class EmptyTodoException extends Exception {
    public EmptyTodoException() {
        super("Todo cannot have empty description .");
    }
}

class EmptyDeadlineException extends Exception {
    public EmptyDeadlineException() {
        super("Deadline cannot have empty description.");
    }
}

class EmptyEventException extends Exception {
    public EmptyEventException() {
        super("Event cannot have empty description.");
    }
}

class InvalidException extends Exception {
    public InvalidException() {
        super("Sorry, invalid input.");
    }
}

 class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (this.getStatusIcon() + this.description);
    }
}

class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}

 class Deadline extends Task {

    protected String by;

    public Deadline(String description, String dateString) {
        super(description);
        LocalDate date = LocalDate.parse(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        this.by = date.format(formatter);

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

 class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + ") " + "to: " + to + ")";
    }
}


public class Duke {
    private TaskList myList;
    private Storage st;
    private Ui ui;
    private Parser p;


    public Duke(String filePath) {
        this.st = new Storage(filePath);
        this.myList = new TaskList(new ArrayList<Task>());
        this.p = new Parser();
        this.ui = new Ui(this.myList);
    }


     public void run() throws IOException{
        ui.start();
        boolean continueChat = true;
        while (continueChat) {
            continueChat = ui.reply(this.st); // This will be false when user types "bye"
        }
        
    }

    public static void main(String[] args) throws IOException {
            new Duke("./data/tasks.txt").run();
            }
        }
