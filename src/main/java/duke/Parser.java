package duke;

import java.util.ArrayList;

/**
 * Deals with parsing and interpreting user commands for task management.
 * Parses the user input and executes corresponding actions on the task list.
 */


public class Parser {
    
    private String userInput;
    private ArrayList<Task> myList;
    private Ui ui;

    /**
     * Constructs a Parser with the specified user input and task list.
     *
     * @param userInput The user input to be parsed.
     * @param myList    The list of tasks to be managed.
     */

    public Parser(String userInput, ArrayList<Task> myList) {
        this.userInput = userInput;
        this.myList = myList;
        this.ui = new Ui();
    }


    /**
     * Parses and executes the user command based on the provided input.
     *
     * @return True if the program should continue processing commands, false if the program should exit.
     */
    public boolean parseCommand() {

        // Split input into "command" and "parameters"
        String[] parts = userInput.split(" ", 2);
        String command = parts[0];
        String restOfInputs = parts.length > 1 ? parts[1] : "";


        if (command.equals("list")) {
            /*
             * Displays the list of tasks in the MyList
             */
            TaskList taskList = new TaskList(myList);
            taskList.list();
            return true;

        } else if (command.equals("bye")) {
            /*
<<<<<<< HEAD
            * Save the new task in MyList before exiting the Duke Program
            */
=======
             * Save the new task in MyList before exiting the Duke Program
             */
>>>>>>> A-Jar
            return false;

        } else if (command.equals("find")){

            TaskList taskList = new TaskList(myList);
            taskList.find(parts);
            return true;

        } else if (parts.length == 1) {

            ui.commandError();
            return true;

<<<<<<< HEAD

        }  else if (command.equals("unmark")) {
            /**
            * Marks a specified task as not done
            * 
            * @param task number the index of the task to be marked as not done
            * @throws IndexOutOfBoundsException if the task number is out of bounds
            */
=======
        } else if (command.equals("unmark")) {
>>>>>>> A-Jar

            TaskList taskList = new TaskList(myList);
            taskList.unmarkList(parts);
            return true;

        } else if (command.equals("mark")) {
<<<<<<< HEAD
=======
            /*
            * Marks a specified task as done
            *
            * @param task number the index of the task to be marked as done
            * @throws IndexOutOfBoundsException if the task number is out of bounds
            */
>>>>>>> A-Jar
            TaskList taskList = new TaskList(myList);
            taskList.markList(parts);
            return true;

        } else if (command.equals("delete")){
<<<<<<< HEAD
            /**
=======
            /*
>>>>>>> A-Jar
            * Deletes a specified task from the list
            * 
            * @param task number the index of the task to be deleted
            * @throws IndexOutOfBoundsException if the task number is out of bounds
            */
            TaskList taskList = new TaskList(myList);
            taskList.remove(parts);
            return true;

        } else {
            TaskList taskList = new TaskList(myList);
            taskList.add(command, restOfInputs);
            return true;
            
        }
    }
 }

 