import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Reacher {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Reacher(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        tasks = new TaskList(storage.loadList());
    }
    public static void main(String[] args) {
        new Reacher("./storage.txt").run();
    }
    public void run() {
        ui.printWelcome();
        while (true) {
            try {
                String input = ui.readString();
                if (input.equalsIgnoreCase("bye")) { //check for end request
                    System.out.println("bye");
                    break;
                } else if (input.equalsIgnoreCase("list")) {// check for list request
                    ui.printList(tasks.getTasks());
                } else if (input.equalsIgnoreCase("edit")) {
                    System.out.println("Which task number would u like to edit?");
                    try {
                        int num = ui.readInt();
                        if (num > tasks.noOfTasks() || num < 1) {
                            throw new ReacherException("No such task number");
                        }
                        Task task = tasks.getTask(num - 1);
                        System.out.println("Mark Done or Undone or Delete?");
                        String change = ui.readString();
                        if (change.equalsIgnoreCase("done")) {
                            task.markDone();
                            System.out.println("Task " + num + " marked done");
                        } else if (change.equalsIgnoreCase("undone")) {
                            task.markNotDone();
                            System.out.println("Task " + num + " marked Undone");
                        } else if (change.equalsIgnoreCase("delete")) {
                            tasks.delete(num - 1);
                            System.out.println("Task " + num + " deleted");
                        }else {
                            throw new ReacherException("u did not write done, undone or delete.");
                        }
                        storage.storeList(tasks.getTasks());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("What type of task is this?(Deadline, Event, Todo)");
                    try {
                        String type = ui.readString();
                        if (type.equalsIgnoreCase("todo")) {
                            Todos t = new Todos(input);
                            tasks.addTask(t);
                            System.out.println("I've added " + t.toString());
                        } else if (type.equalsIgnoreCase("deadline")) {
                            System.out.println("When is the deadline?");
                            LocalDate deadline = LocalDate.parse(ui.readString());
                            Deadline t = new Deadline(input, deadline);
                            tasks.addTask(t);
                            System.out.println("I've added " + t.toString());
                        } else if (type.equalsIgnoreCase("event")) {
                            System.out.println("When is the start?");
                            LocalDate start = LocalDate.parse(ui.readString());
                            System.out.println("When is the end?");
                            LocalDate end = LocalDate.parse(ui.readString());
                            if (start.isAfter(end)){
                                throw new ReacherException("End cannot be before start.");
                            }
                            Events t = new Events(input, start, end);
                            tasks.addTask(t);
                            System.out.println("I've added " + t.toString());
                        } else {
                            throw new ReacherException("That is not a type of task.");
                        }
                        storage.storeList(tasks.getTasks());
                    } catch (ReacherException e) {
                        System.out.println(e.getMessage());
                    } catch (DateTimeParseException e) {
                        System.out.println("Pls enter a valid date format, yyyy-mm-dd");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
