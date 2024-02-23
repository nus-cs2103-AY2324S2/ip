package ally;

import ally.exception.AllyException;
import ally.parser.Parser;
import ally.task.Deadline;
import ally.task.Event;
import ally.task.Todo;
import ally.ui.MainWindow;
import ally.utils.Finder;
import ally.utils.Storage;
import ally.utils.TaskList;


/**
 * Chatbot to help users manage their tasks.
 * Supports Todos, Deadline and Event tasks.
 */
public class Ally {

    public static TaskList lst = new TaskList();
    protected static Storage storage = new Storage("./data/ally.txt");
    private MainWindow mainWindow;

    public Ally() {
        lst = storage.loadTasks();
    }

    /**
     * Links Main Window to Ally for Ally to print messages
     * @param mw
     */
    public void linkMainWindow(MainWindow mw) {
        this.mainWindow = mw;
        mw.addMessage("Hello from Ally!\nHow can I help you today?");
    }

    public String getResponse(String s) {
        try {
            Parser.Command command = Parser.parseCommand(s);
            String taskDetail = Parser.parseTaskDetail(s);
            switch (command) {
            case LIST:
                return lst.getTasks(taskDetail);
            case BYE:
                storage.saveTasks();
                mainWindow.addMessage("Bye. Hope to see you again soon!");
                System.exit(0);
                // will not fall through
            case MARK:
                return lst.markComplete(Integer.parseInt(taskDetail.trim()));
            case UNMARK:
                return lst.unmarkComplete(Integer.parseInt(taskDetail.trim()));
            case TODO:
                return lst.addToList(new Todo(taskDetail));
            case DEADLINE:
                return lst.addToList(new Deadline(taskDetail));

            case EVENT:
                return lst.addToList(new Event(taskDetail));

            case DELETE:
                return lst.delete(Integer.parseInt(taskDetail.trim()));

            case FIND:
                assert lst != null;
                Finder finder = new Finder(lst);
                return finder.find(taskDetail);

            case ARCHIVE:
                return lst.archive(Integer.parseInt(taskDetail.trim()));

            case UNKNOWN:
                throw new AllyException();

            default:
                throw new AllyException();
            }
        } catch (AllyException e) {
            return e.getMessage();
        }
    }
}
