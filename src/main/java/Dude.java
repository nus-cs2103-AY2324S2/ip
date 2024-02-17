import Exceptions.*;
import Exceptions.TaskListFullException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.TaskList;
import Tasks.Todo;
import Utils.Command;
import Utils.Storage;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class  Dude {

    private static TaskList taskList = null;
    private static Storage storage = new Storage("data/tasklist.ser");

    static final String[] supported_commands = {"bye", "list", "mark", "unmark", "todo", "event", "deadline", "delete"};

    public static void main(String[] args) {

//        String logo =   "888888ba                 dP          \n" +
//                        "88    `8b                88          \n" +
//                        "88     88 dP    dP .d888b88 .d8888b. \n" +
//                        "88     88 88    88 88'  `88 88ooood8 \n" +
//                        "88    .8P 88.  .88 88.  .88 88.  ... \n" +
//                        "8888888P  `88888P' `88888P8 `88888P'";
//
//        System.out.println("--------------------------------------\n");
//        System.out.println(logo + "\n");
//        System.out.println("--------------------------------------");
//        System.out.println("Dude v1.0 by Tahsin Hasem.\n");


        try {
            taskList = storage.loadTasks();
        }
        catch (Exception e){
            System.out.println(echo("An error occurred while loading the tasks. Deleting the storage and starting with an empty task list."));
            storage.deleteStorage();
            taskList = new TaskList();
        }

        System.out.println(greet());

        // Initialise Scanner to reach command line input
        Scanner sc = new Scanner(System.in);
        while(true){

            String msg = "";
            try {
                msg = sc.nextLine();
            }catch(NoSuchElementException e){
                //this will not be handled.
                break;
            }

            Command command = getCommand(msg);

            switch (command) {
            case BYE:
                System.out.println(bye());
                return;
            case LIST:
                System.out.println(list());
                break;
            case MARK:
                System.out.println(mark_as_done(msg));
                break;
            case UNMARK:
                System.out.println(mark_as_undone(msg));
                break;
            case TODO:
                System.out.println(handle_todo_command(msg));
                break;
            case EVENT:
                System.out.println(handle_event_command(msg));
                break;
            case DEADLINE:
                System.out.println(handle_deadline_command(msg));
                break;
            case DELETE:
                System.out.println(handle_delete_command(msg));
                break;
            case INVALID:
                System.out.println(echo("I'm sorry, but I don't know what that means :-("));
                break;
            }

            if (isCommandChangingState(command)) {
                try {
                    storage.saveTasks(taskList);
                } catch (Exception e) {
                    System.out.println(echo("An error occurred while saving the tasks."));
                }
            }

        }

    }

    private static String greet(){
        return "\t-----------------------------------\n" +
                "\tHello! I'm Dude\n" +
                "\tWhat can I do for you?\n" +
                "\t-----------------------------------\n";
    }

    private static String list(){
        return taskList.toString();
    }


    private static String bye(){
        String bye_msg = "\t-----------------------------------";
        bye_msg += "\n\tBye. Hope to see you again soon!";
        bye_msg +=  "\n\t-----------------------------------";

        return bye_msg;
    }

    private static String mark_as_done(String msg){
        //TODO: Write logic for commands with multiple parameters. Eg: mark 1 2 3
        //Try to retrieve the index
        int index = 0;
        try{
            index = Integer.parseInt(msg.split(" ")[1]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "\t-----------------------------------\n" +
                    "\tPlease provide a valid task ID. Has to be an integer.\n" +
                    "\t-----------------------------------";
        }

        //This will run only when the index is valid, as catch block will return the error message
        try {
            return taskList.mark_as_done(index);
        } catch (IndexOutOfBoundsException e) {
            return "\t-----------------------------------\n" +
                    "\t" + e.getMessage() +"\n" +
                    "\t-----------------------------------";
        }
    }

    private static String mark_as_undone(String msg){
        //TODO: Write logic for commands with multiple parameters. Eg: unmark 1 2 3
        //Try to retrieve the index
        int index = 0;
        try{
            index = Integer.parseInt(msg.split(" ")[1]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "\t-----------------------------------\n" +
                    "\tPlease provide a valid task ID. Has to be an integer.\n" +
                    "\t-----------------------------------";
        }

        //This will run only when the index is valid, as catch block will return the error message
        try {
            return taskList.mark_as_undone(index);
        } catch (IndexOutOfBoundsException e) {
            return "\t-----------------------------------\n" +
                    "\t" + e.getMessage() +"\n" +
                    "\t-----------------------------------";
        }
    }

    private static String echo(String msg){
        return "\t-----------------------------------\n" +
                "\t" + msg + "\n" +
                "\t-----------------------------------";
    }


    private static String handle_todo_command(String msg){
        try{
            Todo task = Todo.from(msg);
            return taskList.add_task(task);
        }catch (InvalidDescriptionException | TaskListFullException e) {
            return echo(e.getMessage());
        }
    }

    private static String handle_event_command(String msg){
        try{
            Event task = Event.from(msg);
            return taskList.add_task(task);
        }catch (InvalidDescriptionException | InvalidFormatException | InvalidArgumentException | TaskListFullException e) {
            return echo(e.getMessage());
        }
    }

    private static String handle_deadline_command(String msg){
        try{
            Deadline task = Deadline.from(msg);
            return taskList.add_task(task);
        }catch (InvalidFormatException | InvalidArgumentException | InvalidDescriptionException | TaskListFullException e) {
            return echo(e.getMessage());
        }
    }

    private static String handle_delete_command(String msg){
        int index = 0;
        try{
            index = Integer.parseInt(msg.split(" ")[1]);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "\t-----------------------------------\n" +
                    "\tPlease provide a valid task ID. Has to be an integer.\n" +
                    "\t-----------------------------------";
        }

        try {
            return taskList.remove_task(index);
        } catch (IndexOutOfBoundsException e) {
            return "\t-----------------------------------\n" +
                    "\t" + e.getMessage() +"\n" +
                    "\t-----------------------------------";
        }
    }

    private static boolean isCommandChangingState(Command command){
        return command == Command.TODO || command == Command.EVENT || command == Command.DEADLINE
                || command == Command.DELETE || command == Command.MARK || command == Command.UNMARK;
    }

    private static Command getCommand(String msg) {
        String cmd = msg.split(" ")[0];

        switch(cmd) {
            case "bye":
                return Command.BYE;
            case "list":
                return Command.LIST;
            case "mark":
                return Command.MARK;
            case "unmark":
                return Command.UNMARK;
            case "todo":
                return Command.TODO;
            case "event":
                return Command.EVENT;
            case "deadline":
                return Command.DEADLINE;
            case "delete":
                return Command.DELETE;
            default:
                return Command.INVALID;
        }

    }

}
