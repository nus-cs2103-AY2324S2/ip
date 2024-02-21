package duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import javafx.util.Pair;

/**
 * Handles the list of tasks and related methods.
 */
public class TaskList {

    /**
     * The ArrayList that stores the task objects.
     */
    private final ArrayList<Task> tasks;
    private final Duke duke;

    /**
     * Creates the task list as an ArrayList of tasks.
     */
    public TaskList(Duke duke) {
        this.tasks = new ArrayList<Task>();
        this.duke = duke;
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
            return "Your task list is empty! Congratulations!";
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
        boolean successful = true;
        int length = words.length;
        int wordsIndex = 1;
        String name = null;

        try {
            Pair<StringBuilder, Integer> namePair = parameterBuilder(words, wordsIndex, "");
            StringBuilder nameBuilder = namePair.getKey();
            name = nameBuilder.substring(1);
        } catch (InputMismatchException e) {
            successful = false;
            duke.output(Help.getTodoHelp());
        }

        if (successful) {
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
        boolean successful = true;
        int length = words.length;
        int wordsIndex = 1;
        String name = null;
        LocalDateTime deadline = null;

        try {
            Pair<StringBuilder, Integer> namePair = parameterBuilder(words, wordsIndex, "/by");
            StringBuilder nameBuilder = namePair.getKey();
            wordsIndex = namePair.getValue();
            name = nameBuilder.substring(1);
        } catch (InputMismatchException e) {
            successful = false;
            duke.output("The name cannot be empty!");
            duke.output(Help.getDeadlineHelp());
        }

        if (successful) {
            try {
                Pair<StringBuilder, Integer> deadlinePair = parameterBuilder(words, wordsIndex, "");
                StringBuilder deadlineBuilder = deadlinePair.getKey();
                deadline = dateBuilder(announce, deadlineBuilder);
            } catch (InputMismatchException e) {
                successful = false;
                duke.output("The deadline cannot be empty!");
            } catch (DateTimeParseException d) {
                successful = false;
                duke.output("Invalid Deadline format!");
            }
        }

        if (successful) {
            Task task = new Deadline(name, deadline, isDone);
            tasks.add(task);
            if (announce) {
                announceAddition(task);
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
        boolean successful = true;
        int wordsIndex = 1;
        String name = null;
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;

        try {
            Pair<StringBuilder, Integer> namePair = parameterBuilder(words, wordsIndex, "/from");
            StringBuilder nameBuilder = namePair.getKey();
            wordsIndex = namePair.getValue();
            name = nameBuilder.substring(1);
        } catch (InputMismatchException e) {
            successful = false;
            duke.output("The name cannot be empty!");
            duke.output(Help.getEventHelp());
        }

        if (successful) {
            try {
                Pair<StringBuilder, Integer> startDatePair = parameterBuilder(words, wordsIndex, "/to");
                StringBuilder startDateBuilder = startDatePair.getKey();
                wordsIndex = startDatePair.getValue();
                startDate = dateBuilder(announce, startDateBuilder);
            } catch (InputMismatchException e) {
                successful = false;
                duke.output("The start date cannot be empty!");
            } catch (DateTimeParseException d) {
                successful = false;
                duke.output("Invalid Start Date format!");
            }
        }

        if (successful) {
            try {
                Pair<StringBuilder, Integer> endDatePair = parameterBuilder(words, wordsIndex, "");
                StringBuilder endDateBuilder = endDatePair.getKey();
                endDate = dateBuilder(announce, endDateBuilder);
            } catch (InputMismatchException e) {
                successful = false;
                duke.output("The end date cannot be empty!");
            } catch (DateTimeParseException d) {
                successful = false;
                duke.output("Invalid End Date format!");
            }
        }

        if (successful) {
            Task task = new Event(name, startDate, endDate, isDone);
            tasks.add(task);
            if(announce) {
                announceAddition(task);
            }
        }
        return successful;
    }

    /**
     * Creates a string of words for each parameter, given the request
     *
     * @param words the request sent by the user
     * @param wordsIndex where the request has been read up to
     * @param delimiter the delimiter to stop adding new words
     * @return a pair containing the StringBuilder representation of the parameter,
     * and the wordIndex.
     * @throws InputMismatchException if the output contains no words
     */
    private Pair<StringBuilder, Integer> parameterBuilder(String[] words,
                                                          int wordsIndex,
                                                          String delimiter) throws InputMismatchException {
        int no_of_words = 0;
        StringBuilder parameterBuild = null;

        if (wordsIndex < words.length) {
            parameterBuild = new StringBuilder();
            while (wordsIndex < words.length) {
                if (words[wordsIndex].equals(delimiter) && !delimiter.equals("")) {
                    wordsIndex++;
                    break;
                } else {
                    parameterBuild.append(" ").append(words[wordsIndex]);
                    wordsIndex++;
                    no_of_words++;
                }
            }
        }
        if (no_of_words < 1) {
            throw new InputMismatchException();
        }
        return new Pair<>(parameterBuild, wordsIndex);
    }

    /**
     * Creates a LocalDateTime object from a StringBuilder
     *
     * @param isFromChatting Whether the request is from the chat
     * @param dateBuilder the StringBuilder representation of the date
     * @return the LocalDateTime representation of the date
     */
    private LocalDateTime dateBuilder(boolean isFromChatting, StringBuilder dateBuilder) {
        LocalDateTime date = null;
        if(isFromChatting) {
            date = LocalDateTime.parse(dateBuilder.substring(1),
                        DateTimeFormatter.ofPattern("d/M/yy H:mm"));
        } else {
            date = LocalDateTime.parse(dateBuilder.substring(1));
        }
        return date;
    }

    /**
     * Announces the addition of a task
     *
     * @param task the task being added
     */
    public void announceAddition(Task task) {
        StringBuilder announce = new StringBuilder();
        announce.append("Alright. Adding this task:\n");
        announce.append(task.toString()).append("\n");
        String str = "";
        str = String.format("You now have %s task(s)", tasks.size());
        announce.append(str);
        duke.output(announce.toString());
    }

    /**
     * Deletes the task with the specified index from the task list
     *
     * @param index the index of the task to be deleted
     */
    public void delete(int index) {
        if(index > tasks.size() - 1 || index < 0) {
            duke.output("Invalid index!");
        } else {
            Task task = tasks.get(index);
            StringBuilder toPrint = new StringBuilder();
            toPrint.append("Alright, removing this task\n");
            toPrint.append(task.toString());
            tasks.remove(index);
            toPrint.append(String.format("\nYou now have %s task(s) left", tasks.size()));
            duke.output(toPrint.toString());
        }
    }

    /**
     * Prints all matching tasks that have the searchTerm.
     *
     * @param searchTerm the search term to look for.
     */
    public void find(String searchTerm) {
        boolean foundSomething = false;
        StringBuilder listOfTasks = new StringBuilder();
        for (Task task : tasks) {
            String taskName = task.getName();
            if (taskName.contains(searchTerm)) {
                listOfTasks.append(task).append("\n");
                foundSomething = true;
            }
        }

        if(!foundSomething) {
            duke.output("No tasks found :(");
        } else {
            duke.output(listOfTasks.toString());
        }
    }

    /**
     * Clears the list of tasks
     */
    public void clear() {
        tasks.clear();
        duke.output("List Cleared!");
    }

    /**
     * Searches for events or deadlines occurring on the date specified.
     * @param date the date to search for events and deadlines
     * @return the list of events and deadlines occurring on that date
     */
    public String searchDate(LocalDate date) {
        StringBuilder str = new StringBuilder(String.format("Searching for tasks on %s:\n",date));
        int i = 0;
        for(Task task : tasks) {
            if (task instanceof Event) {
                LocalDate taskStartDate = ((Event) task).getStartDate().toLocalDate();
                LocalDate taskEndDate = ((Event) task).getEndDate().toLocalDate();
                if (!(date.isBefore(taskStartDate)) && !(date.isAfter(taskEndDate)) && !(task.isDone)) {
                    str.append(String.format("%s: %s \n", i + 1, task));
                    i++;
                }
            }
            if (task instanceof Deadline) {
                LocalDate taskDeadline = ((Deadline) task).getDeadline().toLocalDate();
                if (date.equals(taskDeadline) && !(task.isDone)) {
                    str.append(String.format("%s: %s \n", i + 1, task));
                    i++;
                }
            }
        }
        if (i == 0) {
            return str.append("No tasks found!").toString();
        } else {
            return str.toString();
        }
    }

    /**
     * Searches for the deadline tasks and events occurring today
     *
     * @return the list of deadline tasks and events occurring today
     */
    public String searchDate() {
        return searchDate(LocalDate.now());
    }
}
