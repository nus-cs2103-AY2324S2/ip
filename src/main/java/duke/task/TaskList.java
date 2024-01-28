package duke.task;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;

/**
 * Represents the management of the task list. 
 */
public class TaskList {
    /**
     * Tracks the list of task inputteed by the user. 
     */
    public ArrayList<Task> instrList = new ArrayList<>();

    /**
     * Constructor that creates an instance of task list. 
     * 
     * @param arr The current instruction list. 
     */
    public TaskList(ArrayList<Task> arr) {
        this.instrList = arr; 
    }

    /**
     * Enums for tasks. 
     */
    public enum TaskCommand {
        TODO, DEADLINE, EVENT
    }

    /**
     * Prints out the tasks in the instruction list. 
     */
    public void listOut() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.instrList.size() ; i++) {
            System.out.println(i + 1 + "." + this.instrList.get(i).toString());
        }
    }


    /**
     * Adds tasks to the list based of what tasks they are. 
     * 
     * @param cmd Represents the type of the task.
     * @param instr The string with the task information. 
     * @param thisStorage Local file access management. 
     * 
     * @throws DukeException When there is inappropriate input. 
     */
    public void addTask(TaskCommand cmd, String instr, Storage thisStorage) throws DukeException {
        switch (cmd) {
        case TODO: 
            try {
                Todo taskTodo = new Todo(instr.split("todo ")[1]);
                this.instrList.add(taskTodo); 
                thisStorage.saveTaskList(this.instrList);
                System.out.println("Got it. I've added this task:");
                System.out.println(taskTodo.toString());
            } catch (ArrayIndexOutOfBoundsException e ){
                throw new DukeException("OOPS!!! The description of a todo cannot be empty. \nTry again!"); 
            }
            break;
        case DEADLINE: 
            try {
                String deadlineDescription = instr.substring(9);
                String[] tskNames = deadlineDescription.split(" /by ");
                Deadline taskDeadline = new Deadline(tskNames[0], tskNames[1]); 
                this.instrList.add(taskDeadline); 
                thisStorage.saveTaskList(this.instrList);
                System.out.println("Got it. I've added this task:");
                System.out.println(taskDeadline.toString());
            } catch(StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! You cannot leave the description of a deadline to be empty. \nTry again!");
            } catch(ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! You missed out the deadline time of this task. \nTry again!");
            }
            break;
        case EVENT: 
            try {
                String eventDescription = instr.substring(6);
                String[] instrsubString = eventDescription.split(" /from ");
                String name = instrsubString[0]; 
                String[] startAndEnd = instrsubString[1].split(" /to "); 
                Event taskEvent = new Event(name, startAndEnd[0], startAndEnd[1]);
                this.instrList.add(taskEvent); 
                thisStorage.saveTaskList(this.instrList);
                System.out.println("Got it. I've added this task:");
                System.out.println(taskEvent.toString()); 
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! You cannot leave the description of an event to be empty. \nTry again!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! You missed out the date of this task. \nTry again!");
            }
            break;
        
        default:
        throw new DukeException("OOPS!!! What is that? I'm sorry, but I don't recognise this command :-( \nTry another command!");  
        }              
        System.out.println("Now you have " + this.instrList.size() + " tasks in the list."); 
    }

    /**
     * Marks the task off the list if the user commands it to. 
     * 
     * @param instr The string with the task information. 
     * @param thisStorage Local file access management. 
     * @throws DukeException When there is inappropriate input. 
     */
    public void mark(String instr, Storage thisStorage) throws DukeException {
        try {
            int instrNum = Integer.valueOf(instr.split(" ")[1]) - 1;
            String res = this.instrList.get(instrNum).markAsDone(); 
            thisStorage.saveTaskList(this.instrList);
            System.out.println("Nice! I've marked this task as done:" + "\n" + res); 
        } catch (NullPointerException e) {
            throw new DukeException("OOPS!! You have inputted an invalid task number. \nTry again with a different task number!"); 
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task number."); 
        }
    }

    /**
     * Unmark a task if the user commands it to. 
     * 
     * @param instr The string with the task information. 
     * @param thidStorage Local file access management. 
     * @throws DukeException When there is inappropriate input. 
     */
    public void unmark(String instr, Storage thisStorage) throws DukeException {
        try {
            int instrNum = Integer.valueOf(instr.split(" ")[1]) - 1;
            String res = this.instrList.get(instrNum).markAsUndone(); 
            thisStorage.saveTaskList(this.instrList);
            System.out.println("OK, I've marked this task as not done yet:" + "\n" + res); 
        } catch (NullPointerException e) {
            throw new DukeException("OOPS!! You have inputted an invalid task number. \nTry again with a different task number!"); 
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task number."); 
        }
    } 

    /**
     * Deletes a task if the user commands it to. 
     * 
     * @param instr The string with the task information. 
     * @param thisStorage Local file access management. 
     * @throws DukeException When there is inappropriate input. 
     */
    public void delete(String instr, Storage thisStorage) throws DukeException { 
        try {
            int ptr = Integer.valueOf(instr.split(" ")[1]) - 1; 
            Task str = this.instrList.get(ptr); 
            this.instrList.remove(ptr); 
            thisStorage.saveTaskList(this.instrList);
            System.out.println("Noted. I've removed this task: \n" + str.toString() + "\nNow you have " + this.instrList.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task number."); 
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!! You have inputted an invalid task number. \nTry again with a different task number!"); 
        } 
    }

    public void find(String instr) throws DukeException {
        try {
            String taskKeyword = instr.split(" ")[1]; 
            System.out.println("Here are the matching tasks in your list:");
            int ctr = 1; 
            for (int i = 0; i < this.instrList.size() ; i++) {
                if (this.instrList.get(i).description.contains(taskKeyword)) {
                    System.out.println(ctr + 1 + "." + this.instrList.get(i).toString());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task to search for."); 
        }
    }
}
