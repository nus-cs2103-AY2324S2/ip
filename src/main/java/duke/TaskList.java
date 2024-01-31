package duke;

import java.util.ArrayList;
/**
 * Represents a task list.
 */
public class TaskList {
    private static ArrayList<Task> list;
    private static Storage store;

    /**
     * Creates a task list based on the stored array.
     * @param store The storage used.
     */
    public TaskList(Storage store) throws DukeException{
        this.store = store;
        this.list = store.load();
    }

    /**
     * Creates an empty task list.
     */
    public TaskList() throws DukeException{

        this.list = new ArrayList<Task>(100);
    }
    /**
     * Creates the message when task is added to tasklist.
     */
    public static void addTask() throws DukeException{
        System.out.println("Got it. I've added this task:\n");

        System.out.println(list.get(list.size()-1).ToString());

        System.out.println("Now you have " + list.size() + " tasks in the list.");
        store.save();
    }

    /**
     * Creates an event in the tasklist.
     * @param str The string representation of the event.
     */
    public static void eventCase(String str) throws DukeException {
        str = str.replace("event", "");
        //str = str.replace("from", "");
        //str = str.replace("to", "");
        String[] eventtokens = str.split(" ((/from)|(/to)) ");

        if(eventtokens.length < 1) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty." +
                    "Please give this instruction in the following format: event [description] / [event starting date] / [event ending date]");
        } else if(eventtokens.length < 2) {
            throw new DukeException("OOPS!!! The beginning date of a event cannot be empty." +
                    "Please give this instruction in the following format: event [description] / [event starting date] / [event ending date]");
        } else if(eventtokens.length < 3) {
            throw new DukeException("OOPS!!! The ending date of a event cannot be empty." +
                    "Please give this instruction in the following format: event [description] / [event starting date] / [event ending date]");
        }
        String subject = eventtokens[0];

        String to = eventtokens[1];
        String from = eventtokens[2];
        list.add(new Event(subject, to, from));



    }
    /**
     * Creates an deadline in the tasklist.
     * @param str The string representation of the deadline.
     */
    public static void deadlineCase(String str) throws DukeException {
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
    /**
     * Creates an todo task in the tasklist.
     * @param str The string representation of the task.
     */
    public static void todoCase(String str) throws DukeException {
        str = str.replace("todo", "");
        int strcount = str.split("\\s").length;

        if(strcount == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty. " +
                    " Please give this instruction in the following format: todo [description]");
        }
        list.add(new Task(str));

    }
    /**
     * marks a task as done.
     * @param  tokens The command to mark task as done.
     */
    public static void markCase(String[] tokens) throws DukeException{
        if(tokens.length != 2) {
            throw new DukeException("please give this instruction in the following format: mark [task number]");
        }
        int no = Integer.parseInt(tokens[1]) - 1;
        list.get(no).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(list.get(no).ToString());
        store.save();
    }

    /**
     * marks a task as undone.
     * @param  tokens The command to mark task as undone.
     */
    public static void unmarkCase(String[] tokens) throws DukeException{
        if(tokens.length != 2) {
            throw new DukeException("please give this instruction in the following format: unmark [task number]");
        }
        int no = Integer.parseInt(tokens[1]) - 1;
        list.get(no).unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(no).ToString());
        store.save();
    }

    /**
     * removes a task.
     * @param  tokens The command to remove a task.
     */
    public static ArrayList<Task> removeCase(String[] tokens) throws DukeException{
        if(tokens.length != 2) {
            throw new DukeException("please give this instruction in the following format: delete [task number]");
        }
        System.out.println("Noted. I've removed this task:\n");
        int no = Integer.parseInt(tokens[1])-1;
        System.out.println(list.get(no).ToString());
        list.remove(no);



        System.out.println("Now you have " + list.size() + " tasks in the list.");
        store.save();

        return list;



    }

    public static void findCase(String str) throws DukeException{
        if(str.equals("find")) {
            throw new DukeException("please give this instruction in the following format: find [keyword]");
        }
        String keyword = str.replace("find", "");
        System.out.println("Here are the matching tasks in your list:");
        int n = 0;
        for (int i = 0; i < list.size(); i++) {

            Task task = list.get(i);
            if (task.description.contains(keyword)) {
                n++;
                System.out.println( n + "." + task.ToString());

            }
        }

    }
    /**
     * prints the task list.
     */
    public static void printList(){
        System.out.println("Here are the tasks in your list:\n");
        for (int a = 0; a < list.size(); a++) {
            System.out.println(a + 1 + ". " + list.get(a).ToString());
        }
    }


    /**
     * gets the size of the task list.
     */
    public int getSize(){
        return list.size();
    }
    /**
     * gets a specific task in the task list.
     * @param  n The number of the specified task in the task list.
     */
    public Task getTask(int n){
        return list.get(n);
    }


}
