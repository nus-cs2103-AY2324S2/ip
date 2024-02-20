package ui;

import java.util.List;
import java.util.Scanner;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String printWelcome() {
        return "____________________________________________________________\n" +
                "Hello! I'm Duke \nWhat can I do for you?\n" +
                "____________________________________________________________";
    }

    public String printGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    public String printError(String message) {
        return message;
    }

    public String[] readCommand() {
        return scanner.nextLine().split(" ", 2);
    }

    public String printList(List<Task> tasks){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        if (tasks.isEmpty()) {
            stringBuilder.append("No tasks found!\n");
        }
        return stringBuilder.toString();
    }

    public String findTasks(String keyword, List<Task> tasks){
        StringBuilder stringBuilder = new StringBuilder("Here are the matching tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                stringBuilder.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        return stringBuilder.toString();

    }

    public String printMark(Task task){
        return "Nice! I've marked this task as done:\n  " + task;
    }

    public String printUnmark(Task task){
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    public String printTodo(Todo todo, List<Task> tasks){
        return "Got it. I've added this task:\n  " + todo + "\nNow you have " + tasks.size() + " tasks in the list";
    }

    public String printDeadline(Deadline deadline, List<Task> tasks){
        return "Got it. I've added this task:\n  " + deadline + "\nNow you have " + tasks.size() + " tasks in the list";
    }

    public String printEvent(Event event, List<Task> tasks){
        return "Got it. I've added this task:\n  " + event + "\nNow you have " + tasks.size() + " tasks in the list";
    }

    public String printDelete(Task task, List<Task> tasks){
        return "Noted. I've removed this task:\n " + task + "\nNow you have " + tasks.size() + " tasks in the list";
    }



}
