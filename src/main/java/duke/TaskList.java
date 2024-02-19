package duke;

import java.util.ArrayList;
/**
 * Represents a task list.
 */
public class TaskList {
    private static ArrayList<Task> list;
    private static Storage store;
    private final String BYE_MESSAGE = "Bye. Bingus hopes to see you again soon!\n";
    private final String MEOW_MESSAGE = "Mrow. :3";



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
    public String addTask() throws DukeException{
        //System.out.println("Got it. I've added this task:\n");

        //System.out.println(list.get(list.size()-1).ToString());

       //System.out.println("Now you have " + list.size() + " tasks in the list.");
        if(store != null) {
            store.save();
        }
        assert this.list.size() > 0 : "taskList should not be empty";
        return "Got it. I've added this task:\n" + list.get(list.size()-1).ToString() + "\n" + "Now you have " + list.size() + " tasks in the list.\n";
    }

    /**
     * Creates an event in the tasklist.
     * @param str The string representation of the event.
     */
    public String eventCase(String str) throws DukeException {
        String parsedString = str.replace("event", "");
        //str = str.replace("from", "");
        //str = str.replace("to", "");
        String[] eventtokens = str.split(" ((/from)|(/to)) ");

        if (eventtokens.length < 1) {
            return "OOPS!!! The description of a event cannot be empty." + "Please give this instruction in the following format: event [description] / [event starting date] / [event ending date]\n";
           // throw new DukeException("OOPS!!! The description of a event cannot be empty." +
                  //  "Please give this instruction in the following format: event [description] / [event starting date] / [event ending date]");
        } else if (eventtokens.length < 2) {
            return "OOPS!!! The description of a event cannot be empty." + "Please give this instruction in the following format: event [description] / [event starting date] / [event ending date]\n";
            //throw new DukeException("OOPS!!! The beginning date of a event cannot be empty." +
                    //"Please give this instruction in the following format: event [description] / [event starting date] / [event ending date]");
        } else if (eventtokens.length < 3) {
            return "OOPS!!! The description of a event cannot be empty." + "Please give this instruction in the following format: event [description] / [event starting date] / [event ending date]\n";
            //throw new DukeException("OOPS!!! The ending date of a event cannot be empty." +
                    //"Please give this instruction in the following format: event [description] / [event starting date] / [event ending date]");
        }
        String subject = eventtokens[0];

        String to = eventtokens[1];
        assert to != null : "to clause should not be null";
        String from = eventtokens[2];
        assert from != null : "from clause should not be null";
        list.add(new Event(subject, to, from));
        return addTask();



    }
    /**
     * Creates an deadline in the tasklist.
     * @param str The string representation of the deadline.
     */
    public String deadlineCase(String str) throws DukeException {
        str = str.replace("deadline", "");
        //str = str.replace("by", "");

        String[] deadlinetokens = str.split("(/by)");
        if(deadlinetokens.length < 1) {

            return "OOPS!!! The description of a deadline cannot be empty." +
                    "Please give this instruction in the following format: deadline [description] / [deadline date]\n";
            //throw new DukeException("OOPS!!! The description of a deadline cannot be empty." +
                    //"Please give this instruction in the following format: deadline [description] / [deadline date]");
        }
        else if (deadlinetokens.length < 2) {
            return "OOPS!!! The description of a deadline cannot be empty." +
                    "Please give this instruction in the following format: deadline [description] / [deadline date]\n";
            //throw new DukeException("OOPS!!! You must provide a deadline for this task." +
                    //"Please give this instruction in the following format: deadline [description] / [deadline date]");
        }
        String subject = deadlinetokens[0];
        String deadline = deadlinetokens[1];
        assert deadline != null : "deadline should not be null";
        list.add(new Deadline(subject, deadline));
        return addTask();

    }



    /**
     * Creates an todo task in the tasklist.
     * @param str The string representation of the task.
     */
    public String todoCase(String str) throws DukeException {
        str = str.replace("todo", "");
        int strcount = str.split("\\s").length;

        if (strcount == 1) {
            return "OOPS!!! The description of a todo cannot be empty. " +
                    " Please give this instruction in the following format: todo [description]\n";
            //throw new DukeException("OOPS!!! The description of a todo cannot be empty. " +
                   // " Please give this instruction in the following format: todo [description]");
        }
        list.add(new Task(str));
        return addTask();

    }
    /**
     * marks a task as done.
     * @param  tokens The command to mark task as done.
     */
    public String markCase(String[] tokens) throws DukeException{
        if (tokens.length != 2) {
            return "please give this instruction in the following format: mark [task number]";
           // throw new DukeException("please give this instruction in the following format: mark [task number]");

        }
        int no = Integer.parseInt(tokens[1]) - 1;
        list.get(no).markAsDone();
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(list.get(no).ToString());
        store.save();
        return "Nice! I've marked this task as done:\n" + list.get(no).ToString();
    }

    /**
     * marks a task as undone.
     * @param  tokens The command to mark task as undone.
     */
    public String unmarkCase(String[] tokens) throws DukeException{
        if(tokens.length != 2) {
            return "please give this instruction in the following format: unmark [task number]";
            //throw new DukeException("please give this instruction in the following format: unmark [task number]");
        }
        int no = Integer.parseInt(tokens[1]) - 1;
        list.get(no).unmarkAsDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.get(no).ToString());
        store.save();
        return "OK, I've marked this task as not done yet:\n" + list.get(no).ToString();
    }

    public String priorityCase(String[] tokens) throws DukeException{
        if(tokens.length != 3) {
            return "please give this instruction in the following format: priority [task number] [priority(HIGH/MEDIUM/LOW)]";
            //throw new DukeException("please give this instruction in the following format: unmark [task number]");
        }
        int no = Integer.parseInt(tokens[1]) - 1;
        String priority = tokens[2];
        list.get(no).setPriority(priority);
        store.save();
        return "OK, I've changed the priority of this task:\n" + list.get(no).ToString();
    }

    /**
     * removes a task.
     * @param  tokens The command to remove a task.
     */
    public String removeCase(String[] tokens) throws DukeException{
        if(tokens.length != 2) {
            return "please give this instruction in the following format: delete [task number]";
            //throw new DukeException("please give this instruction in the following format: delete [task number]");
        }
        int no = Integer.parseInt(tokens[1])-1;
        String result = "Noted. I've removed this task:\n";
        result += list.get(no).ToString() + "\n";
        list.remove(no);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        result += "Now you have " + list.size() + " tasks in the list.";
        store.save();

        return result;



    }

    public String findCase(String str) throws DukeException{
        if(str.equals("find")) {
            return "please give this instruction in the following format: find [keyword]";
            //throw new DukeException("please give this instruction in the following format: find [keyword]");
        }
        String keyword = str.replace("find", "");
        String result = "Here are the matching tasks in your list:\n";
        //System.out.println("Here are the matching tasks in your list:");
        int findCounter = 0;
        for (int i = 0; i < list.size(); i++) {

            Task task = list.get(i);
            if (task.description.contains(keyword)) {
                findCounter++;
                System.out.println( findCounter + "." + task.ToString());
                result += findCounter + "." + task.ToString() + "\n";

            }
        }
        return result;

    }


    /**
     * prints the task list.
     */
    public String printList(){
        System.out.println("Here are the tasks in your list:\n");
        String result = "Here are the tasks in your list:\n";
        for (int a = 0; a < list.size(); a++) {
            System.out.println(a + 1 + ". " + list.get(a).ToString());
            result += a + 1 + ". " + list.get(a).ToString() + "\n";
        }
        return result;
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

/**
 * Prints outro.
 */
    public String bye() throws DukeException{

        return BYE_MESSAGE;

        // if keyword is bye, exit the program
    }

/**
 * Prints outro.
 */
    public String meow() throws DukeException{

        return MEOW_MESSAGE;

        // if keyword is meow, meow
    }


}
