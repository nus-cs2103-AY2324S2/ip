package Klee;

import Klee.task.Task;

public class Ui {
    protected static String divider = "____________________________________________________________________________";
    public Ui () {

    }

    public void showWelcome () {
        //Greet user
        System.out.println(divider);
        System.out.println("Hello! My name is Klee.");
        System.out.println("Are you here to break Klee out of solitary confinement?");
        System.out.println(divider);
    }

    public void showError (String errorMessage) {
        System.out.println(divider);
        System.out.println(errorMessage);
        System.out.println(divider);
    }

    public void showTasks (TaskList tasks) {
        System.out.println(divider);
        System.out.println("These are all the things that we have to do today:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i).getStatus());
        }
        System.out.println(divider);
    }

    public void showFilteredTasks (TaskList tasks) {
        System.out.println(divider);
        System.out.println("These are all the things that are similar to what you are looking for:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i).getStatus());
        }
        System.out.println(divider);
    }

    public void showCreation (Task task, int size) {
        System.out.println(divider);
        System.out.println("Klee will help you write that down! : ");
        System.out.println(task.getStatus());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(divider);
    }

    public void showMarked (Task task) {
        System.out.println(divider);
        System.out.println("Great! Klee will put a big cross on this box:");
        System.out.println(task.getStatus());
        System.out.println(divider);
    }

    public void showUnMarked(Task task) {
        System.out.println(divider);
        System.out.println("Oh no! Klee will burn the cross away...:");
        System.out.println(task.getStatus());
        System.out.println(divider);
    }

    public void showDeletion (Task task, int size) {
        System.out.println(divider);
        System.out.println("Okay, Klee will wipe this task away!");
        System.out.println(task.getStatus());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(divider);
    }

    public void showBye () {
        System.out.println(divider);
        System.out.println("Goodbye. Klee will go back to solitary confinement now...");
        System.out.println(divider);
    }
}
