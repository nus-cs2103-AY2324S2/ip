package UI;

import java.util.List;
import java.util.Scanner;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke \nWhat can I do for you?\n");
        System.out.println("____________________________________________________________");
    }

    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printError(String message) {
        System.out.println(message);
    }

    public String[] readCommand() {
        return scanner.nextLine().split(" ", 2);
    }

    public void printList(List<Task> tasks){
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        if(tasks.isEmpty()){
            System.out.println("No tasks found!");
        }
    }

    public void printMark(Task task){
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    public void printUnmark(Task task){
        System.out.println("OK, I've marked this task as not done yet:\n  " + task);
    }

    public void printTodo(Todo todo, List<Task> tasks){
        System.out.println("Got it. I've added this task:\n  " + todo + "\nNow you have " + tasks.size() + " tasks in the list");
    }

    public void printDeadline(Deadline deadline, List<Task> tasks){
        System.out.println("Got it. I've added this task:\n  " + deadline + "\nNow you have " + tasks.size() + " tasks in the list");
    }

    public void printEvent(Event event, List<Task> tasks){
        System.out.println("Got it. I've added this task:\n  " + event + "\nNow you have " + tasks.size() + " tasks in the list");
    }

    public void printDelete(Task task, List<Task> tasks){
        System.out.println("Noted. I've removed this task:\n " + task + "\nNow you have " + tasks.size() + " tasks in the list");
    }



}
