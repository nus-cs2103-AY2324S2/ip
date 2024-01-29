import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {

    private static String name = "GanAnWo";
    private static String currentWorkingDirectory = System.getProperty("user.dir");
    private static String path = "/list.txt";
    private static DateTimeFormatter dFormatInp = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");
    private static DateTimeFormatter dFormatOut = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static ArrayList<Task> task = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException, IOException{
        Scanner inp = new Scanner(System.in);
        System.out.println("Hello! I'm " + name + "\n"
                + "What can I do for you?");
        start();
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
                        throw new DescriptionFormatException("Wrong format!, please use this format: "
                                + Event.getFormat()); //when the description format is wrong (e.g: no desc)
                    } else {
                        name = item.split("event ");
                        String[] desFromTo = name[1].split(" /from ");
                        String[] fromTo = desFromTo[1].split(" /to ");
                        if (!(desFromTo.length != 2 || fromTo.length != 2)){
                            throw new DescriptionFormatException("Wrong format!, please use this format: "
                                    + Event.getFormat());
                        }
                        LocalDateTime ldtf = LocalDateTime.parse(fromTo[0], dFormatInp);
                        LocalDateTime ldtt = LocalDateTime.parse(fromTo[1], dFormatInp);
                        task.add(new Event(desFromTo[0], ldtf, ldtt));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.get(task.size()-1).toString());
                        System.out.println("Now you have " + task.size() + " tasks in the list.");
                        try {
                            write();
                        } catch (IOException e) {
                            System.out.println("Save failed ");
                        }
                    }
                    break;
                case "todo": // when the task added is todo
                    if((inputs.length == 1)){
                        throw new DescriptionFormatException("Wrong format!, please use this format: "
                                + ToDos.getFormat()); //when the description format is wrong (e.g: no desc)
                    } else {
                        name = item.split("todo ");
                        if(name.length != 2){
                            throw new DescriptionFormatException("Wrong format!, please use this format: "
                                    + ToDos.getFormat());
                        }
                        task.add(new ToDos(name[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.get(task.size()-1).toString());
                        System.out.println("Now you have " + task.size() + " tasks in the list.");
                        try {
                            write();
                        } catch (IOException e) {
                            System.out.println("Save failed");
                        }
                    }
                    break;
                case "deadline": // when the task added is deadline
                    if(inputs.length == 1){
                        throw new DescriptionFormatException("Wrong format!, please use this format: "
                                + Deadline.getFormat()); //when the description format is wrong (e.g: no desc)
                    } else {
                        name = item.split("deadline ");
                        String[] desBy = name[1].split(" /by ");
                        if(desBy.length != 2) {
                            throw new DescriptionFormatException("Wrong format!, please use this format: "
                                    + Deadline.getFormat());
                        }
                        LocalDateTime ldt = LocalDateTime.parse(desBy[1], dFormatInp);
                        task.add(new Deadline(desBy[0], ldt));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(task.get(task.size()-1).toString());
                        System.out.println("Now you have " + task.size() + " tasks in the list.");
                        try {
                            write();
                        } catch (IOException e) {
                            System.out.println("Save failed");
                        }
                    }
                    break;
                default:
                    throw new CommandInvalidException();
                    // when none of task type matched the available task type which means the command is invalid
            }
        } catch (DescriptionFormatException e){ // the description format exception handling
            System.out.println(e.getMessage());
        } catch (CommandInvalidException e){ // the invalid command exception handling
            System.out.println("Invalid command -_-, please use the available commands!!");
        } catch (DateTimeParseException e){
            System.out.println("Please use the date format: yyyy-MM-dd HH:mm (e.g.: 2023-02-01 12:30)");
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
            try {
                write();
            } catch (IOException e) {
                System.out.println("Save failed");
            }
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
            try {
                write();
            } catch (IOException e) {
                System.out.println("Save failed");
            }
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
            try {
                write();
            } catch (IOException e) {
                System.out.println("Save failed");
            }
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

    public static void start() throws FileNotFoundException{
        File data = new File(currentWorkingDirectory + path);
        if(data.exists()){
            Scanner sc = new Scanner(data);
            while (sc.hasNext()){
                String dt = sc.nextLine();
                String[] dtl = dt.split("/");
                switch (dtl[0]) {
                    case "T":
                        task.add(new ToDos(dtl[1], dtl[2]));
                        break;
                    case "D":
                        task.add(new Deadline(dtl[1], dtl[2], LocalDateTime.parse(dtl[3], dFormatInp)));
                        break;
                    case "E":
                        task.add(new Event(dtl[1], dtl[2], LocalDateTime.parse(dtl[3],dFormatInp),
                                LocalDateTime.parse(dtl[4],dFormatInp)));
                }
            }
        }
    }

    public static void write() throws IOException {
        FileWriter rf;
        try {
            rf = new FileWriter(currentWorkingDirectory + path);
            for(int i = 0; i < task.size(); i++){
                rf.write(task.get(i).toWrite());
                rf.write("\n");
            }
            rf.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: Cannot load your saved tasks");
        }

    }
}
