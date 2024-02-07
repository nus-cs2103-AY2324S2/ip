package duke;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Handles the list of tasks and related methods.
 */
public class TaskList {

    /**
     * The ArrayList that stores the task objects.
     */
    private ArrayList<Task> tasks;

    /**
     * Creates the task list as an ArrayList of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Gets the ArrayList itself
     *
     * @return the ArrayList of tasks that the tasks are stored in
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Returns the task with the specified index from the list
     *
     * @param index the index of the list to be retrieved
     * @return the task with that index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the list
     *
     * @return the size of the list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Creates a string representation of the task list.
     * If empty, returns an empty message.
     *
     * @return the string representation of the task list.
     */
    public String list() {
        StringBuilder str = new StringBuilder();
        if (tasks.size() == 0) {
            System.out.println("Your task list is empty! Congratulations!");
        } else {
            for(int i = 0; i < tasks.size(); i++) {
                str.append(String.format("%s: %s \n", i + 1, tasks.get(i)));
            }
        }
        return str.toString();
    }

    /**
     * Adds a ToDo task to the taskList.
     *
     * @param words the input broken down into individual words
     * @param announce whether to announce the addition of the task
     * @param isDone whether to set the task as done
     * @return true if the addition was successful, false otherwise
     */
    public boolean addToDo(String[] words, boolean announce, boolean isDone) {
        boolean successful = false;
        int length = words.length;
        int wordsIndex = 1;

        if (length < 2) {
            System.out.println("The name cannot be empty!");
        } else {
            StringBuilder nameBuilder = new StringBuilder();
            while(wordsIndex < length) {
                nameBuilder.append(" ").append(words[wordsIndex]);
                wordsIndex++;
            }
            String name = nameBuilder.substring(1);
            successful = true;
            Task task = new ToDo(name, isDone);
            tasks.add(task);

            if(announce) {
                announceAddition(task);
            }
        }
        return successful;
    }

    /**
     * Adds a deadline task to the taskList.
     *
     * @param words the input broken down into individual words
     * @param announce whether to announce the addition of the task
     * @param isDone whether to set the task as done
     * @return true if the addition was successful, false otherwise
     */
    public boolean addDeadline(String[] words, boolean announce, boolean isDone) {
        boolean successful = false;
        int length = words.length;
        int wordsIndex = 1;

        if (length < 2) {
            System.out.println("The name cannot be empty!");
        } else {
            StringBuilder nameBuilder = new StringBuilder();
            while(wordsIndex < length) {
                if (words[wordsIndex].equals("/by")) {
                    wordsIndex++;
                    break;
                } else {
                    nameBuilder.append(" ").append(words[wordsIndex]);
                    wordsIndex++;
                }
            }
            String name = nameBuilder.substring(1);

            if (wordsIndex >= length) {
                System.out.println("The deadline cannot be empty!");
            } else {
                StringBuilder deadlineBuilder = new StringBuilder();
                while (wordsIndex < length) {
                    deadlineBuilder.append(" ").append(words[wordsIndex]);
                    wordsIndex++;
                }
                String deadline = deadlineBuilder.substring(1);
                successful = true;
                Task task = new Deadline(name, LocalDateTime.parse(deadline), isDone);
                tasks.add(task);

                if(announce) {
                    announceAddition(task);
                }
            }
        }
        return successful;
    }

    /**
     * Adds an event task to the taskList.
     *
     * @param words the input broken down into individual words
     * @param announce whether to announce the addition of the task
     * @param isDone whether to set the task as done
     * @return true if the addition was successful, false otherwise
     */
    public boolean addEvent(String[] words, boolean announce, boolean isDone) {
        boolean successful = false;
        int length = words.length;
        int wordsIndex = 1;

        if (length < 2) {
            System.out.println("The name cannot be empty!");
        } else {
            StringBuilder nameBuilder = new StringBuilder();
            while(wordsIndex < length) {
                if (words[wordsIndex].equals("/from")) {
                    wordsIndex++;
                    break;
                } else {
                    nameBuilder.append(" ").append(words[wordsIndex]);
                    wordsIndex++;
                }
            }
            String name = nameBuilder.substring(1);

            if (wordsIndex >= length) {
                System.out.println("The start date cannot be empty!");
            } else {
                StringBuilder startDateBuilder = new StringBuilder();
                while (wordsIndex < length) {
                    if (words[wordsIndex].equals("/to")) {
                        wordsIndex++;
                        break;
                    } else {
                        startDateBuilder.append(" ").append(words[wordsIndex]);
                        wordsIndex++;
                    }
                }
                String startDate = startDateBuilder.substring(1);

                if (wordsIndex >= length) {
                    System.out.println("The end date cannot be empty!");
                } else {
                    StringBuilder endDateBuilder = new StringBuilder();
                    while (wordsIndex < length) {
                        endDateBuilder.append(" ").append(words[wordsIndex]);
                        wordsIndex++;
                    }
                    String endDate = endDateBuilder.substring(1);
                    successful = true;

                    Task task = new Event(name, LocalDateTime.parse(startDate), LocalDateTime.parse(endDate), isDone);
                    tasks.add(task);

                    if(announce) {
                        announceAddition(task);
                    }
                }
            }
        }
        return successful;
    }

    /**
     * Announces the addition of a task
     *
     * @param task the task being added
     */
    public void announceAddition(Task task) {
        System.out.println("Alright. Adding this task:");
        System.out.println(task);
        String str = "";
        str = String.format("You now have %s tasks", tasks.size());
        System.out.println(str);
    }

    /**
     * Deletes the task with the specified index from the task list
     *
     * @param index the index of the task to be deleted
     */
    public void delete(int index) {
        if(index > tasks.size() - 1 || index < 0) {
            System.out.println("Invalid index!");
        } else {
            Task task = tasks.get(index);
            System.out.println("Alright, removing this task");
            System.out.println(task.toString());
            tasks.remove(index);
            System.out.println(String.format("You now have %s tasks left", tasks.size()));
        }
    }
}
