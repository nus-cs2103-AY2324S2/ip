package duke;


import duke.task.Task;

import java.util.Scanner;

/**
 * Represents the User Interaction that deals with interactions with the user.
 * Contains Scanner user to read user input, Task task to create new Tasks
 * with user's String input, and TaskList tasklist to pass to Parser.
 */
public class Ui {

    private Scanner user;
    private Task task;
    private TaskList tasklist;


    public Ui(Scanner user, TaskList tasklist) {
        this.user = user;
        this.tasklist = tasklist;
    }


    /**
    * Returns welcome message at the start of the program.
    *
    * @return Welcome message.
    */
    public String showWelcome() {
        return "Hi babyyy! It's your EUEU!! \n"
                + "What are you doing today??";
    }

    /**
     * Returns exit message at the end of the program.
     *
     * @return Exit message.
     */
    public String exit() {
        return "byeee love uu ttyl ok!";
    }

    /**
     * Reads command of user's next line and passes String command to Parser.
     */
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