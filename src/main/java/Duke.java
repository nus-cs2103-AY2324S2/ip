import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    private static String name = "GanAnWo";

    private static ArrayList<Task> task = new ArrayList<>();

    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        System.out.println("Hello! I'm " + name + "\n"
                + "What can I do for you?");
        String input;
        Boolean running = true;

        while (running){
            input = inp.nextLine();
            String[] inputs = input.split(" ");
            boolean commV = valid(input);
            try {
                if (commV == true) {
                    if (input.equals("bye")) { //if the user use bye command
                        running = false;
                        break;
                    } else if (inputs[0].equals("mark") || inputs[0].equals("unmark")) {
                        //if the user use mark or unmark command
                        if (inputs[0].equals("mark")) {
                            mark(input);
                        } else if (inputs[0].equals("unmark")) {
                            unMark(input);
                        }
                    } else if (input.equals("list")) { //if the user use list command
                        showTask();
                    } else if (inputs[0].equals("delete")) { //if the user use delete command
                        delete(input);
                    } else { //if the user want to add task(todo, deadline, and event)
                        addTask(input);
                    }
                } else {
                    throw new CommandInvalidException();
                }
            } catch (CommandInvalidException e){
                System.out.println("Invalid command -_-, please use the available commands!!");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");

    }

    public static void addTask(String item){ //to add task to the arraylist
        String[] inputs = item.split(" ");
        String[] name;
        try {
            switch (inputs[0]) {
                case "event": // when the task added is event
                    if(inputs.length == 1){
                        throw new DescriptionFormatException(); //when the description format is wrong (e.g: no desc)
                    } else {
                        name = item.split("event ");
                        String[] desFromTo = name[1].split(" /from ");
                        String[] fromTo = desFromTo[1].split(" /to ");

                        task.add(new Event(desFromTo[0], fromTo[0], fromTo[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.get(task.size()-1).toString());
                        System.out.println("Now you have " + task.size() + " tasks in the list.");
                    }
                    break;
                case "todo": // when the task added is todo
                    if(inputs.length == 1){
                        throw new DescriptionFormatException(); //when the description format is wrong (e.g: no desc)
                    } else {
                        name = item.split("todo ");
                        task.add(new ToDos(name[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.get(task.size()-1).toString());
                        System.out.println("Now you have " + task.size() + " tasks in the list.");

                    }
                    break;
                case "deadline": // when the task added is deadline
                    if(inputs.length == 1){
                        throw new DescriptionFormatException(); //when the description format is wrong (e.g: no desc)
                    } else {
                        name = item.split("deadline ");
                        String[] desBy = name[1].split(" /by ");
                        task.add(new Deadline(desBy[0], desBy[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.get(task.size()-1).toString());
                        System.out.println("Now you have " + task.size() + " tasks in the list.");
                    }
                    break;
                default:
                    throw new CommandInvalidException();
                    // when none of task type matched the available task type which means the command is invalid
            }
        } catch (DescriptionFormatException e){ // the description format exception handling
            System.out.println("Please add description for the " + inputs[0] + " command");
        } catch (CommandInvalidException e){ // the invalid command exception handling
            System.out.println("Invalid command -_-, please use the available commands!!");
        }
    }

    public static void showTask(){ // method to show available task (list command)
        for (int i = 0 ; i < task.size(); i++){
            System.out.println(i+1 + "." + task.get(i).toString());
        }
    }

    public static void mark(String n){ // method to mark task (mark command)
        int noArr;
        try {
            String[] inputs = n.split(" ");
            if(!(inputs.length == 2)){
                throw new CommandFormatException();
            }
            noArr = Integer.parseInt(inputs[1])-1;
            task.get(noArr).mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.get(noArr).toString());
        } catch (IndexOutOfBoundsException e){ //when the given number is out of bounds (exception handling)
            System.out.println("No task number " + n);
        } catch (NumberFormatException e){ //when the given number is not a number (exception handling)
            System.out.println("The task number given is not a number");
        } catch (CommandFormatException e){
            System.out.println("The command format for mark is mark number (e.g.: mark 1)");
        }
    }

    public static void unMark(String n){ //method to unmark task (unmark command)
        int noArr;
        try {
            String[] inputs = n.split(" ");
            if(!(inputs.length == 2)){
                throw new CommandFormatException();
            }
            noArr = Integer.parseInt(inputs[1])-1;
            task.get(noArr).unMark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task.get(noArr).toString());
        } catch (IndexOutOfBoundsException e){  //when the given number is out of bounds (exception handling)
            System.out.println("No task number " + n);
        } catch (NumberFormatException e){ //when the given number is not a number (exception handling)
            System.out.println("The task number given is not a number");
        } catch (CommandFormatException e){
            System.out.println("The command format for unmark is unmark number (e.g.: unmark 1)");
        }
    }

    public static void delete(String n){ //method to delete task (delete command)
        int noArr;
        try {
            String[] inputs = n.split(" ");
            if(!(inputs.length == 2)){
                throw new CommandFormatException();
            }
            noArr = Integer.parseInt(inputs[1])-1;
            Task delT = task.get(noArr);
            task.remove(noArr);
            System.out.println("Noted. I've removed this task:");
            System.out.println(delT.toString());
            System.out.println("Now you have " + task.size() + " tasks in the list. ");
        } catch (IndexOutOfBoundsException e){ //when the given number is out of bounds (exception handling)
            System.out.println("No task number " + n);
        } catch (NumberFormatException e){ //when the given number is not a number (exception handling)
            System.out.println("The task number given is not a number");
        } catch (CommandFormatException e){
            System.out.println("The command format for delete is delete number (e.g.: delete 1)");
        }
    }

    public static boolean valid(String n){ //check if the given command is valid
        String[] inputs = n.split(" ");
        for (Command com : Command.values()) {
            if (com.name().equalsIgnoreCase(inputs[0])) {
                return true;
            }
        }
        return false;
    }
}
