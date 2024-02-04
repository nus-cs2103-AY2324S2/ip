import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime;
/*
 * Deals with making sense of the user command
 */


public class Parser {
    
    private String userInput;
    private ArrayList<Task> myList;
    private Ui ui;

    public Parser(String userInput, ArrayList<Task> myList) {
        this.userInput = userInput;
        this.myList = myList;
        this.ui = new Ui();
    }

    public boolean parseCommand() {

        // Split input into "command" and "parameters"
        String[] parts = userInput.split(" ", 2);
        String command = parts[0];
        String restOfInputs = parts.length > 1 ? parts[1] : "";


        if(command.equals("list")) {
            /*
            * Displays the list of tasks in the MyList
            */
            TaskList taskList = new TaskList(myList);
            taskList.list();
            return true;

        } else if(command.equals("bye")) {
            /*
            * Save the new task in MyList before exitting the Duke Program
            */
            return false;

        } else if (parts.length == 1) {

            ui.commandError();
            return true;

        } else if(command.equals("unmark")) {
            /**
            * Marks a specified task as not done
            * 
            * @param task number the index of the task to be marked as not done
            * @throws IndexOutOfBoundsException if the task number is out of bounds
            */
            TaskList taskList = new TaskList(myList);
            taskList.unmarkList(parts);
            return true;

        } else if (command.equals("mark")) {
            /**
            * Marks a specified task as done
            * 
            * @param taskNUmver the index of the task to be marked as done
            * @throws INdexOutOfBoundsException if the task numr is out of bounds
            */
            TaskList taskList = new TaskList(myList);
            taskList.markList(parts);
            return true;

        } else if(command.equals("delete")){
            /**
            * Deletes a specified task from the list
            * 
            * @param task numer the index of the task to be deleted
            * @throws INdexOutOfBoundsException if the task number is out of bounds
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

 