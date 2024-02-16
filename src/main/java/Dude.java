import Exceptions.*;
import Exceptions.TaskListFullException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.TaskList;
import Tasks.Todo;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class  Dude {

    static TaskList taskList = new TaskList();

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

            String command = "";
            try {
                command = get_command(msg);
            }catch (InvalidCommandException e){
                System.out.println(echo(e.getMessage()));
                continue;
            }

            switch (command){
                case "bye":
                    System.out.println(bye());
                    return;
                case "list":
                    System.out.println(list());
                    break;
                case "mark":
                    System.out.println(mark_as_done(msg));
                    break;
                case "unmark":
                    System.out.println(mark_as_undone(msg));
                    break;
                case "todo":
                    System.out.println(handle_todo_command(msg));
                    break;
                case "event":
                    System.out.println(handle_event_command(msg));
                    break;
                case "deadline":
                    System.out.println(handle_deadline_command(msg));
                    break;
                case "delete":
                    System.out.println(handle_delete_command(msg));
                    break;
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

    private static String get_command(String msg) throws InvalidCommandException {
        String cmd = msg.split(" ")[0];

        for (String supported_command : supported_commands) {
            if (cmd.equals(supported_command)) {
                return cmd;
            }
        }
        throw new InvalidCommandException("I'm sorry, but I don't know what\n\tthat means :-(");
    }

}
