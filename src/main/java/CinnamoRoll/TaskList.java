package CinnamoRoll;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

/**
 * TaskList class represents a collection of tasks and provides methods
 * for managing tasks, including adding, deleting, marking, and listing tasks.
 * It also handles reading and writing tasks to a file, searching for tasks,
 * and identifying duplicate tasks within the list.
 */
class TaskList {
    private final ArrayList<Task> tasks;
    private final String path = "./task/Cinnamo.txt";
    private final Parser parser = new Parser();

    /**
     * Creates a new empty TaskList.
     */
    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Creates a new TaskList with the given list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the task at the specified index in the TaskList.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range (index < 0 || index >= size()).
     */
    public Task getUser(int index) {
        return this.tasks.get(index);
    }

    /**
     * Writes the tasks stored in the current task list to the file specified by the PATH constant.
     * The tasks are formatted and written according to the list() method's output.
     */

    // Solution below, very specifically only on how to write into the file using filewriter object with
    // write/close methods, and how writeinto method allows other methods to
    // directly write into the file were adapted from two sources: chatgpt code and previous
    // batch's level 8 submission here:
    // 1. https://chat.openai.com/c/c34f9461-e0dc-4946-b2f2-eeec0e39aded
    // 2. https://github.com/david-eom/CS2103T-IP/releases/tag/Level-8
    // 3. Solution was also inspired by chatgpt generated result here:
    // https://chat.openai.com/c/c34f9461-e0dc-4946-b2f2-eeec0e39aded
    public void writeInto() {
        try {
            FileWriter fileWriter = new FileWriter(this.path);
            String writeInto = this.storeTask();
            fileWriter.write(writeInto);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("No input provided!");
        }
    }

    /**
     * Executes a task instruction, adds the task to the task list, and writes the updated list to the file.
     *
     * @param instruction An array representing the task instruction.
     * @return A string message indicating the success of adding the task.
     * @throws CinnamoException If there is an error parsing the task instruction
     */
    public String executeTask(String[] instruction) throws CinnamoException {
        try {
            int arraySize = this.tasks.size();
            Task task = this.parser.parseTasks(instruction);
            this.tasks.add(task);
            this.writeInto();
            assert this.tasks.size() == arraySize + 1 : "length of array must have increased by 1";
            return task.addTask(this.tasks.size());
        } catch (ArrayIndexOutOfBoundsException exception) {
            if (instruction[0].equals("TODO")) {
                throw new CinnamoTodoException();
            } else if (instruction[0].equals("DEADLINE")) {
                throw new CinnamoDeadlineException();
            } else {
                throw new CinnamoEventException();
            }
        }
    }

    /**
     * Recognizes duplicate tasks with more than one occurrence then return the list of
     * duplicate tasks to the users from the list. As long as task names are exactly the same
     * tasks will be regarded as duplicates even if task type is different
     *
     * @return A sublist that contains every duplicate tasks recorded in the list
     */
    public String listDuplicates() {
        Hashtable<String, Integer> storeDuplicate = new Hashtable<>();
        boolean isDuplicate = false;

        for (int i = 0; i < this.tasks.size(); i++) {
            String taskName = this.tasks.get(i).getTaskName();
            if (storeDuplicate.containsKey(taskName)) {
                int occurrence = storeDuplicate.get(taskName) + 1;
                storeDuplicate.put(taskName, occurrence);
                isDuplicate = true;
            } else {
                storeDuplicate.put(taskName, 1);
            }
        }

        if (!isDuplicate) {
            return "No duplicate tasks are found :) Cinnamo is happy!";
        }

        return printDuplicate(storeDuplicate);
    }

    /**
     * Print duplicate tasks in the required list format
     * @param storeDuplicate Hashtable containing pair of duplicate task name and occurrence.
     */
    public String printDuplicate(Hashtable<String, Integer> storeDuplicate) {
        String output = "Here are duplicate tasks in your list >.<:\n";
        Set<String> taskList = storeDuplicate.keySet();
        int counter = 1;

        for (String taskDetail : taskList) {
            int repeats = storeDuplicate.get(taskDetail);
            if (repeats > 1) {
                output += String.format("%d. %s (occurrence: %d times)%n", counter,
                        taskDetail, repeats);
            }
            counter += 1;
        }
        return output;
    }

    /**
     * Recognizes unique tasks with exactly one occurrence then uniquify the tasklist
     * by removing duplicate tasks from the tasklist
     *
     * @return A sublist that contains every duplicate tasks recorded in the list
     */
    public String uniquifyTasks() {
        ArrayList<String> uniqueTasks = new ArrayList<>();
        ArrayList<Integer> duplicateIndex = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); i++) {
            String taskName = this.tasks.get(i).getTaskName();
            if (uniqueTasks.contains(taskName)) {
                duplicateIndex.add(i);
            } else {
                uniqueTasks.add(taskName);
            }
        }

        for (int j = duplicateIndex.size() - 1; j >= 0; j--) {
            int toRemove = duplicateIndex.get(j);
            this.tasks.remove(toRemove);
        }
        this.writeInto();

        return "Cinnamo has uniquified the tasks for you >.<\n";
    }

    /**
     * Generates a formatted string listing all tasks in the current task list.
     *
     * @return A string representation of the tasks in the format "1. [Task1] \n 2. [Task2] \n ..."
     */
    public String listTask() {
        if (this.tasks.isEmpty()) {
            return "There are no tasks in the list now! Let Cinnamo know which tasks"
                    + " to store for you >.<";
        }
        String output = "Here are the tasks in your list >.<:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            output += String.valueOf(i + 1) + "." + this.tasks.get(i).toString();
            if (i < this.tasks.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }

    /**
     * Returns a string of the list for the entire list of the tasks
     * so that it can be stored into the database with a correct format
     * to load data in future
     */
    public String storeTask() {
        String output = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            output += this.tasks.get(i).storeInFile();
            if (i < this.tasks.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }

    /**
     * Searches for tasks containing a specified string and generates a formatted string listing the matching tasks.
     *
     * @param str The string to search for within task descriptions.
     * @return A formatted string listing the tasks that contain the specified string.
     */
    public String findTask(String str) {
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).containString(str)) {
                output += String.valueOf(i + 1) + "." + this.tasks.get(i).toString();
                if (i < this.tasks.size() - 1) {
                    output += "\n";
                }
            }
        }
        return output;
    }

    /**
     * Deletes a task from the task list based on the provided index and updates the file accordingly.
     *
     * @param str An array containing the command and task index for deletion.
     * @return A formatted string indicating the success of the deletion and the updated task list size.
     * @throws CinnamoIndexException If the provided index is out of bounds.
     * @throws Exception If there is an error parsing the index or writing to the file.
     */
    public String deleteTask(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]);
            Task temp = this.tasks.get(index - 1);
            this.tasks.remove(index - 1);
            this.writeInto();
            return String.format("Noted. I've removed the following task:%n   %s%n"
                    + "Now, you have %d tasks in the list", temp.toString(), this.tasks.size());
        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    /**
     * Marks a task as done in the task list based on the provided index and updates the file accordingly.
     *
     * @param str An array containing the command and task index for marking the task as done.
     * @return A formatted string indicating the success of marking the task as done.
     * @throws CinnamoIndexException If the provided index is out of bounds.
     * @throws Exception If there is an error parsing the index or writing to the file.
     */
    public String markTask(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]) - 1;
            this.tasks.get(index).markTask();
            this.writeInto();
            assert this.tasks.get(index).getMarked() == true : "the marking status must be true";
            return String.format("Nice! I've marked this task for you:%n   %s",
                    this.tasks.get(index).toString());

        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    /**
     * Marks a task as not done in the task list based on the provided index and updates the file accordingly.
     *
     * @param str An array containing the command and task index for marking the task as not done.
     * @return A formatted string indicating the success of marking the task as not done.
     * @throws CinnamoIndexException If the provided index is out of bounds.
     * @throws Exception If there is an error parsing the index or writing to the file.
     */
    public String unmarkTask(String[] str) throws Exception, CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]) - 1;
            this.tasks.get(index).unmarkTask();
            this.writeInto();
            assert this.tasks.get(index).getMarked() == false : "the marking status must be false";
            return String.format("Ok! I've unmarked this task for you:%n      %s",
                    this.tasks.get(index).toString());

        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    /**
     * Processes and responds to the user's input by delegating tasks to corresponding methods.
     *
     * @param str The user's input to be processed.
     * @throws CinnamoException If an error related to task execution or command parsing occurs.
     * @throws Exception If there is an error during the response process.
     */
    // Solution below on how to convert string into enum value for switch-case was inspired
    // by the following stackoverflow forum:
    // https://stackoverflow.com/questions/10387329/using-string-representations-of-enum-values-in-switch-case
    void respondUser(String str) throws Exception {
        try {
            Parser parser = new Parser();
            String[] arr = parser.parseInput(str);

            switch (User.valueOf(arr[0])) {
            case MARK:
                System.out.println(this.markTask(arr));
                break;

            case UNMARK:
                System.out.println(this.unmarkTask(arr));
                break;

            case LIST:
                System.out.println(this.listTask());
                break;

            case DELETE:
                System.out.println(this.deleteTask(arr));
                break;

            case TODO:
                System.out.println(this.executeTask(arr));
                break;

            case DEADLINE:
                System.out.println(this.executeTask(arr));
                break;

            case EVENT:
                System.out.println(this.executeTask(arr));
                break;

            case FIND:
                System.out.println(this.findTask(arr[1]));
                break;

            case DUPLICATE:
                System.out.println(this.listDuplicates());
                break;

            case UNIQUIFY:
                System.out.println(this.uniquifyTasks());
                break;

            default:
                throw new CinnamoException();
            }
        } catch (CinnamoException cin) {
            System.out.println(cin.toString());
        } catch (IllegalArgumentException e) {
            CinnamoException cin = new CinnamoException();
            System.out.println(cin.toString());
        }
    }
}
