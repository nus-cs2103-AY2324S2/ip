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
    protected ArrayList<Task> instrList = new ArrayList<>();

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
     *
     * @return A string that represents the results of instruction.
     */
    public String listOut() {
        String res = "Here are the tasks in your list:";
        for (int i = 0; i < this.instrList.size(); i++) {
            System.out.println(i + 1 + "." + this.instrList.get(i).toString());
            String temp = i + 1 + "." + this.instrList.get(i).toString();
            res += "\n" + temp;
        }
        return res;
    }


    /**
     * Adds tasks to the list based of what tasks they are.
     *
     * @param cmd Represents the type of the task.
     * @param instr The string with the task information.
     * @param thisStorage Local file access management.
     *
     * @return A string that represents the results of instruction.
     *
     * @throws DukeException When there is inappropriate input.
     */
    public String addTask(TaskCommand cmd, String instr, Storage thisStorage) throws DukeException {
        String res = "";
        switch (cmd) {
        case TODO:
            res = processTodo(instr, thisStorage);
            System.out.println(res);
            break;
        case DEADLINE:
            res = processDeadline(instr, thisStorage);
            System.out.println(res);
            break;
        case EVENT:
            res = processEvent(instr, thisStorage);
            System.out.println(res);
            break;
        default:
            throw new DukeException("OOPS!!! What is that? I'm sorry,"
                + " but I don't recognise this command :-( \nTry another command!");
        }
        res = res + "\n" + "Now you have " + this.instrList.size() + " tasks in the list.";
        System.out.println(res);
        return res;
    }

    /**
     * Marks the task off the list if the user commands it to.
     *
     * @param instr The string with the task information.
     * @param thisStorage Local file access management.
     * @return A string that represents the results of instruction.
     * @throws DukeException When there is inappropriate input.
     */
    public String mark(String instr, Storage thisStorage) throws DukeException {
        String res = "";
        try {
            int instrNum = Integer.valueOf(instr.split(" ")[1]) - 1;
            res = this.instrList.get(instrNum).markAsDone();
            thisStorage.saveTaskList(this.instrList);
            res = "Nice! I've marked this task as done:" + "\n" + res;
            System.out.println(res);
        } catch (NullPointerException e) {
            throw new DukeException("OOPS!! You have inputted an invalid task number."
                + " \nTry again with a different task number!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task number.");
        }
        return res;
    }

    /**
     * Unmark a task if the user commands it to.
     *
     * @param instr The string with the task information.
     * @param thisStorage Local file access management.
     * @return A string that represents the results of instruction.
     * @throws DukeException When there is inappropriate input.
     */
    public String unmark(String instr, Storage thisStorage) throws DukeException {
        String res = "";
        try {
            int instrNum = Integer.valueOf(instr.split(" ")[1]) - 1;
            res = this.instrList.get(instrNum).markAsUndone();
            thisStorage.saveTaskList(this.instrList);
            res = "OK, I've marked this task as not done yet:" + "\n" + res;
            System.out.println(res);
        } catch (NullPointerException e) {
            throw new DukeException("OOPS!! You have inputted an invalid task number."
                + " \nTry again with a different task number!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task number.");
        }
        return res;
    }

    /**
     * Deletes a task if the user commands it to.
     *
     * @param instr The string with the task information.
     * @param thisStorage Local file access management.
     * @return A string that represents the results of instruction.
     * @throws DukeException When there is inappropriate input.
     */
    public String delete(String instr, Storage thisStorage) throws DukeException {
        String res = "";
        try {
            int ptr = Integer.valueOf(instr.split(" ")[1]) - 1;
            Task str = this.instrList.get(ptr);
            this.instrList.remove(ptr);
            thisStorage.saveTaskList(this.instrList);
            res = "Noted. I've removed this task: \n" + str.toString()
                + "\nNow you have " + this.instrList.size() + " tasks in the list.";
            System.out.println(res);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!! You have inputted an invalid task number."
                + " \nTry again with a different task number!");
        }
        return res;
    }

    /**
     * Edits the details for the different tasks.
     *
     * @param cmdWord Command word informing what detail to be edited.
     * @param newDetails Representing the new details to be added.
     * @param ptr Representing the task number to be edited.
     *
     * @return A string representing the work that has been done.
     */
    public String edit(String cmdWord, String newDetails, int ptr) {
        Task tsk = this.instrList.get(ptr - 1);
        try {
            switch (cmdWord) {
            case("description"):
                return tsk.updateDescription(newDetails);
            case("start"):
                if (tsk instanceof Event) {
                    return ((Event) tsk).updateStart(newDetails);
                } else {
                    throw new DukeException("OOPSIE! Task you are trying to edit is NOT AN EVENT!!!");
                }
            case("end"):
                if (tsk instanceof Event) {
                    return ((Event) tsk).updateStart(newDetails);
                } else {
                    throw new DukeException("OOPSIE! Task you are trying to edit is NOT AN EVENT!!!");
                }
            case("by"):
                if (tsk instanceof Deadline) {
                    return ((Deadline) tsk).updateBy(newDetails);
                } else {
                    throw new DukeException("OOPSIE! Task you are trying to edit is NOT A DEADLINE!!!");
                }
            default:
                throw new DukeException("Invalid details to be edited");
            }
        } catch (DukeException e) {
            return e.toString();
        }
    }

    /**
     * Finds the list of task that matches the key words.
     *
     * @param instr The string with the task information.
     * @return A string that represents the results of instruction.
     * @throws DukeException When there is inappropriate input.
     */
    public String find(String instr) throws DukeException {
        String res = "";
        try {
            String taskKeyword = instr.split(" ")[1];
            res = "Here are the matching tasks in your list:";
            System.out.println(res);
            int ctr = 1;
            for (int i = 0; i < this.instrList.size(); i++) {
                if (this.instrList.get(i).description.contains(taskKeyword)) {
                    System.out.println(ctr + 1 + "." + this.instrList.get(i).toString());
                    res += ctr + 1 + "." + this.instrList.get(i).toString();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOOPS!!! You missed out the task to search for.");
        }
        return res;
    }

    /**
     * Process todo task.
     *
     * @param instr The string with the task information.
     * @param thisStorage Task List manager.
     * @return A string representing the result.
     * @throws DukeException
     */
    public String processTodo(String instr, Storage thisStorage) throws DukeException {
        try {
            Todo taskTodo = new Todo(instr.split("todo ")[1]);
            this.instrList.add(taskTodo);
            thisStorage.saveTaskList(this.instrList);
            String res = "Got it. I've added this task: \n "
                + taskTodo.toString();
            return res;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty. \nTry again!");
        }
    }

    /**
     * Process deadline task.
     *
     * @param instr The string with the task information.
     * @param thisStorage Task List manager.
     * @return A string representing the result.
     * @throws DukeException
     */
    public String processDeadline(String instr, Storage thisStorage) throws DukeException {
        try {
            String deadlineDescription = instr.substring(9);
            String[] tskNames = deadlineDescription.split(" /by ");
            Deadline taskDeadline = new Deadline(tskNames[0], tskNames[1]);
            this.instrList.add(taskDeadline);
            thisStorage.saveTaskList(this.instrList);
            String res = "Got it. I've added this task: \n "
                + taskDeadline.toString();
            return res;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! You cannot leave the description"
                + " of a deadline to be empty. \nTry again!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! You missed out the deadline time of this task. \nTry again!");
        }
    }

    /**
     * Process event task.
     *
     * @param instr The string with the task information.
     * @param thisStorage Task List manager.
     * @return A string representing the result.
     * @throws DukeException
     */
    public String processEvent(String instr, Storage thisStorage) throws DukeException {
        try {
            String eventDescription = instr.substring(6);
            String[] instrSubString = eventDescription.split(" /from ");
            String name = instrSubString[0];
            String[] startAndEnd = instrSubString[1].split(" /to ");
            Event taskEvent = new Event(name, startAndEnd[0], startAndEnd[1]);
            this.instrList.add(taskEvent);
            thisStorage.saveTaskList(this.instrList);
            String res = "Got it. I've added this task: \n "
                + taskEvent.toString();
            return res;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! You cannot leave the"
                + " description of an event to be empty. \nTry again!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! You missed out the date of this task. \nTry again!");
        }
    }
}
