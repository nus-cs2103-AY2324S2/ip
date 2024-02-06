package duke;

import java.util.ArrayList;

public class Ui {

    public void message() {
        // Displaying Duke logo and initial message
        String logo = " KASSIM ";
        System.out.println("YOO I AM " + logo);
        System.out.println("What can I do for you?");
    }

    public void errorEncounter(DukeException e) {
        System.out.println("------------------------------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("------------------------------------------------------------");
    }

    public String emptyErrorMessage() {
        return "yes u have your task, but what your task plan to do? please indicate it.";
    }

    public String errorMessage() {
        return "what? please check your input.";
    }

    public void commandError() {
        System.out.println("SORRY! but are you sure you enter the correct command? please check!");
    }

    public void markInfo(Task task) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
        System.out.println("------------------------------------------------------------");
    }

    public void unmarkInfo(Task task) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Ok, I've marked this task as not done yet: ");
        System.out.println(task);
        System.out.println("------------------------------------------------------------");
    }

    public void invalidNum() {
        System.out.println("Invalid task number.");
    }

    public void listDetails() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Here are the tasks in your list: ");
    }

    public void findListDetails() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Here are the matching tasks in your list: ");
    }

    public void removeTop(Task task) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
    }

    public void removeBottom(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("------------------------------------------------------------");
    }

    public void todoInfo(Todo newTodo, int size) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTodo);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("------------------------------------------------------------");
    }

    public void deadlineInfo(Deadline newDeadline, int size) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(newDeadline);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("------------------------------------------------------------");
    }

    public void eventInfo(Event newEvent, int size) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(newEvent);
        System.out.println("Now you have " + size + " tasks in the list.");
       System.out.println("------------------------------------------------------------");
    }

    public void finalMessage() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Bye! Hope to see you again!!");
    }

    public void blank() {
        System.out.print(" ");
    }

    public void separationLiine() {
        System.out.println("------------------------------------------------------------");
    }
}
