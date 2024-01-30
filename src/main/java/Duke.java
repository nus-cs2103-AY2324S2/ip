
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Duke {
    private static ArrayList<Task> list;
    private static Storage storage;
    private static final String FILE_PATH = "data/duke.txt";


    public static void intro(String name, String logo) {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm \n" + name);
        System.out.println("What can I do for you?\n");// initial introductory message

    }

    public static void bye() throws DukeException{
        System.out.println("Bye. Hope to see you again soon!\n");
        storage.save();
        System.exit(1);// if keyword is bye, exit the program
    }

    public static void addtask(ArrayList<Task> list) {
        System.out.println("Got it. I've added this task:\n");

        System.out.println(list.get(list.size()-1).ToString());

        System.out.println("Now you have " + list.size() + " tasks in the list.");

    }

    public static ArrayList<Task> eventcase(String str, ArrayList<Task> list) throws DukeException {
        str = str.replace("event", "");
        //str = str.replace("from", "");
        //str = str.replace("to", "");
        String[] eventtokens = str.split(" ((/from)|(/to)) ");

        if(eventtokens.length < 1) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty." +
                    "Please give this instruction in the following format: event [description] / [event starting date] / [event ending date]");
        }
        else if(eventtokens.length < 2) {
            throw new DukeException("OOPS!!! The beginning date of a event cannot be empty." +
                    "Please give this instruction in the following format: event [description] / [event starting date] / [event ending date]");
        }
        else if(eventtokens.length < 3) {
            throw new DukeException("OOPS!!! The ending date of a event cannot be empty." +
                    "Please give this instruction in the following format: event [description] / [event starting date] / [event ending date]");
        }
        String subject = eventtokens[0];

        String to = eventtokens[1];
        String from = eventtokens[2];
        list.add(new Event(subject, to, from));
        return list;


    }

    public static ArrayList<Task> deadlinecase(String str, ArrayList<Task> list) throws DukeException {
        str = str.replace("deadline", "");
        //str = str.replace("by", "");
        String[] deadlinetokens = str.split("/by");
        if(deadlinetokens.length < 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty." +
                    "Please give this instruction in the following format: deadline [description] / [deadline date]");
        }
        else if(deadlinetokens.length < 2) {
            throw new DukeException("OOPS!!! You must provide a deadline for this task." +
                    "Please give this instruction in the following format: deadline [description] / [deadline date]");
        }
        String subject = deadlinetokens[0];
        String deadline = deadlinetokens[1];
        list.add(new Deadline(subject, deadline));
        return list;
    }

    public static ArrayList<Task> todocase(String str, ArrayList<Task> list) throws DukeException {
        str = str.replace("todo", "");
        int strcount = str.split("\\s").length;
        
        if(strcount == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty. " +
                    " Please give this instruction in the following format: todo [description]");
        }
        list.add(new Task(str));
        return list;
    }

    public static void markcase(String[] tokens, ArrayList<Task> list) throws DukeException{
        if(tokens.length != 2) {
            throw new DukeException("please give this instruction in the following format: mark [task number]");
        }
        int no = Integer.parseInt(tokens[1]) - 1;
        list.get(no).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(list.get(no).ToString());
    }

    public static void unmarkcase(String[] tokens, ArrayList<Task> list) throws DukeException{
        if(tokens.length != 2) {
            throw new DukeException("please give this instruction in the following format: unmark [task number]");
        }
        int no = Integer.parseInt(tokens[1]) - 1;
        list.get(no).unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(no).ToString());
    }

    public static ArrayList<Task> removecase(String[] tokens, ArrayList<Task> list) throws DukeException{
        if(tokens.length != 2) {
            throw new DukeException("please give this instruction in the following format: delete [task number]");
        }
        System.out.println("Noted. I've removed this task:\n");
        int no = Integer.parseInt(tokens[1])-1;
        System.out.println(list.get(no).ToString());
        list.remove(no);



        System.out.println("Now you have " + list.size() + " tasks in the list.");

        return list;



    }

    public static void printlist(){
        System.out.println("Here are the tasks in your list:\n");
        for (int a = 0; a < list.size(); a++) {
            System.out.println(a + 1 + ". " + list.get(a).ToString());
        }
    }

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Bingus";
        intro(name, logo);
        storage = new Storage(FILE_PATH);
        list = storage.load();// array to store tasks given


        //int n = list.size();
        Scanner bfn = new Scanner(
                new InputStreamReader(System.in));// scanner to read user input

        String str = bfn.nextLine();




            while (true) {
                try {
                String[] tokens = str.split("\\s+");// split read string into individual components to read keywords
                String identifier = tokens[0];// store keywords


                if (str.equals("list")) {

                    printlist();
                }// if keyword is list, open list

                else if (str.equals("bye")) {
                    bye();
                } else if (identifier.equals("mark")) {
                    markcase(tokens, list);
                } else if (identifier.equals("unmark")) {
                    int no = Integer.parseInt(tokens[1]) - 1;
                    unmarkcase(tokens, list);
                } else if (identifier.equals("event")) {
                    list = eventcase(str, list);
                    addtask(list);

                } else if (identifier.equals("deadline")) {
                    list = deadlinecase(str, list);
                    addtask(list);
                } else if (identifier.equals("todo")) {
                    list = todocase(str, list);
                    addtask(list);
                } else if (identifier.equals("delete")) {
                    list = removecase(tokens, list);

                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }


                str = bfn.nextLine();
            }catch(DukeException ex){
                    System.out.println("Exception occured: " + ex);
                    str = bfn.nextLine();


                }
        }

    }
}

