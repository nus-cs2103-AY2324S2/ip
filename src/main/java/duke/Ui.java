package duke;


import duke.task.Task;

import java.util.Scanner;

public class Ui {

    private Scanner user;
    private Task task;
    private TaskList tasklist;


    public Ui(Scanner user, TaskList tasklist) {
        this.user = user;
        this.tasklist = tasklist;
    }



    public String showWelcome() {
        return "Hi babyyy! It's your EUEU!! \n"
                + "What are you doing today??";
    }

    public String exit() {
        return "byeee love uu ttyl ok!";
    }

    public void readCommand() {
        Parser parse = new Parser(tasklist);
        Task task = new Task(user.nextLine());
        while (!task.getTask().equals("bye")) {
            parse.parsing(task.getTask());
            task = new Task(user.nextLine());
        }
    }



}
//            try {
//                task = new Task(user.nextLine());
//            } catch (NoSuchElementException e) {
//                System.out.println("Say something I'm giving up on you ");
//            }