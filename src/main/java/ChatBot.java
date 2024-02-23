import java.io.FileNotFoundException;
import java.io.IOException;

public class ChatBot {
    private static final String INDENT = "    ";
    private static final String NEW_LINE = INDENT + "____________________________________________________________ \n";
    private static final String GREETING = INDENT + "Good day good sir! I am Chatimous Maximous, here to help you with your every need!\n";
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
    private static final String TASK_REMOVED = NEW_LINE + INDENT + "I have removed the task from the list Sir! \n" + NEW_LINE;
    private static final String FILE_PATH = "./data/saved_tasks.txt";

    Storage storage = new Storage(FILE_PATH);
    TaskList taskList;
    Boolean isFinished = false;
    int idx;
    Task task;

    public ChatBot() throws FileNotFoundException, IOException {
        System.out.println(NEW_LINE + GREETING);
        taskList = storage.load();
    }

    public Boolean hasFinished() {
        return isFinished;
    }

    public String interact(String input) throws UnrecognizedException, MissingInputException, IOException {
        if (input.contains(LIST)) {
            return NEW_LINE + taskList.showList() + NEW_LINE;

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
        } else if (input.contains(REMOVE)) {
            idx = Integer.valueOf(input.substring(SEVEN));
            taskList.removeTask(idx - 1);
            this.storage.save(taskList);
            return TASK_REMOVED;
        } else {
            if (input.contains(TODO)) {
                try {
                    task = new Todo(input.substring(TODO.length() + 1));
                } catch (StringIndexOutOfBoundsException e) {
                     throw new MissingInputException("Life is liddat");
                }
            } else if (input.contains(DEADLINE)) {
                try {
                    task = new Deadline(input.substring(DEADLINE.length() + 1));
                } catch (StringIndexOutOfBoundsException e) {
                    throw new MissingInputException("Bruh");
                }
            } else if (input.contains(EVENT)) {
                try {
                    task = new Event(input.substring(EVENT.length() + 1));
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
