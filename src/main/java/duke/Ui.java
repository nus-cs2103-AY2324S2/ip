package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

import duke.exceptions.UnrecognizedException;
import duke.exceptions.MissingInputException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Class that handles inputs given to the chat bot.
 */
public class Ui {
    private static final String INDENT = "    ";
    private static final String NEW_LINE = INDENT + "________________________________________________________ \n";
    private static final String GREETING = INDENT
            + "Good day good sir! I am Chatimous Maximous, here to help you with your every need!\n";
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String UNMARK = "unmark";
    private static final String MARK = "mark";
    private static final String EXIT = INDENT + "It has been a pleasure! I do hope to see you again! \n";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String PRAISE = NEW_LINE + "A job well done Sir! \n" + NEW_LINE;
    private static final int SEVEN = 7;
    private static final String ENCOURAGEMENT = NEW_LINE + "Don't fret Sir! You'll get it soon \n" + NEW_LINE;
    private static final int FIVE = 5;
    private static final String EVENT = "event";
    private static final String ADD_TASK = " has been accounted for! \n" + NEW_LINE;
    private static final String REMOVE = "remove";
    private static final String TASK_REMOVED = NEW_LINE + INDENT
            + "I have removed the task from the list Sir! \n" + NEW_LINE;
    private static final String FILE_PATH = "./src/main/java/data/saved_tasks.txt";
    private static final String FIND = "find";
    private static final String FOUND_MESSAGE = NEW_LINE + INDENT + "Here are the tasks I have found!\n";
    private static final String CHECK_DUPLICATE = "dupe";

    private Storage storage = new Storage(FILE_PATH);
    private TaskList taskList;
    private Boolean isFinished = false;
    private int idx;
    private Task task;

    /**
     * Constructor for Ui
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Ui() throws FileNotFoundException, IOException {
        System.out.println(NEW_LINE + GREETING);
        taskList = storage.load();
    }

    /**
     * Checks if chatbot is no longer in use.
     * @return false if still in use.
     */
    public Boolean hasFinished() {
        return isFinished;
    }

    /**
     * Returns the appropriate reply string based on the input.
     * @param input Message given to chatbot by user.
     * @return Reply message based on input.
     * @throws UnrecognizedException If input contains an invalid command.
     * @throws MissingInputException If input is missing required inputs.
     * @throws IOException
     */
    public String interact(String input) throws UnrecognizedException, MissingInputException, IOException {
        if (input.contains(LIST)) {
            return NEW_LINE + taskList.showList() + NEW_LINE;

        } else if (input.contains(FIND)) {
            String[] parts = input.split(" ");
            return FOUND_MESSAGE + taskList.findTask(parts[1]) + NEW_LINE;

        } else if (input.contains(UNMARK)) {
            idx = Integer.valueOf(input.substring(SEVEN));
            taskList.unmarkTask(idx);
            this.storage.save(taskList);
            return ENCOURAGEMENT;

        } else if (input.contains(MARK)) {
            idx = Integer.valueOf(input.substring(FIVE));
            taskList.markTask(idx);
            this.storage.save(taskList);
            return PRAISE;

        } else if (input.contains(BYE)) {
            this.isFinished = true;
            return EXIT;

        } else if (input.contains(CHECK_DUPLICATE)) {
            taskList.deleteDuplicate();
            this.storage.save(taskList);
            return NEW_LINE + taskList.showList() + NEW_LINE;
            
        } else if (input.contains(REMOVE)) {
            idx = Integer.valueOf(input.substring(SEVEN));
            taskList.removeTask(idx - 1);
            this.storage.save(taskList);
            return TASK_REMOVED;

        } else {
            if (input.contains(TODO)) {
                try {
                    //todo takes in the name of the task

                    task = new Todo(input.substring(TODO.length() + 1));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new MissingInputException("Life is liddat");
                }
            } else if (input.contains(DEADLINE)) {
                try {
                    // deadline takes in the name and the deadline
                    // Need to split the input into name and deadline split by "by"

                    String[] parts = input.split(" by "); // part 0 is todo [name], part 1 is [date]
                    LocalDate date = LocalDate.parse(parts[1].trim());
                    task = new Deadline(parts[0].substring(DEADLINE.length() + 1), date);

                } catch (StringIndexOutOfBoundsException e) {
                    throw new MissingInputException("Bruh");
                }
            } else if (input.contains(EVENT)) {
                try {
                    // event takes in the name and the deadline
                    String[] parts = input.split(" by "); // part 0 is todo [name], part 1 is [date]
                    LocalDate date = LocalDate.parse(parts[1].trim());
                    task = new Event(parts[0].substring(DEADLINE.length() + 1), date);

                } catch (StringIndexOutOfBoundsException e) {
                    throw new MissingInputException("Haiz");
                }
            } else {
                // task = new Task(input, NORMAL);
                throw new UnrecognizedException("Yoyoyo");
            }
            taskList.addTask(task);
            this.storage.save(taskList);
            // System.out.println("What");
            return NEW_LINE + INDENT + task.getName() + ADD_TASK;
        }
    }

}
