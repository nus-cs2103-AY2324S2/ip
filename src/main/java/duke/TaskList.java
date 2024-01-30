package duke;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> list;

    public TaskList(ArrayList<Task> list){
        this.list = list;
    }
    public static void addtask() {
        System.out.println("Got it. I've added this task:\n");

        System.out.println(list.get(list.size()-1).ToString());

        System.out.println("Now you have " + list.size() + " tasks in the list.");

    }

    public static void eventcase(String str) throws DukeException {
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



    }

    public static void deadlinecase(String str) throws DukeException {
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

    }

    public static void todocase(String str) throws DukeException {
        str = str.replace("todo", "");
        int strcount = str.split("\\s").length;

        if(strcount == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty. " +
                    " Please give this instruction in the following format: todo [description]");
        }
        list.add(new Task(str));

    }

    public static void markcase(String[] tokens) throws DukeException{
        if(tokens.length != 2) {
            throw new DukeException("please give this instruction in the following format: mark [task number]");
        }
        int no = Integer.parseInt(tokens[1]) - 1;
        list.get(no).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(list.get(no).ToString());
    }

    public static void unmarkcase(String[] tokens) throws DukeException{
        if(tokens.length != 2) {
            throw new DukeException("please give this instruction in the following format: unmark [task number]");
        }
        int no = Integer.parseInt(tokens[1]) - 1;
        list.get(no).unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(no).ToString());
    }

    public static ArrayList<Task> removecase(String[] tokens) throws DukeException{
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


}
