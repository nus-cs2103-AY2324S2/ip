package duke.main;
import duke.exception.DateFormatException;
import duke.exception.InvalidIndexException;
import duke.exception.UnknownInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskType;
import duke.task.ToDo;


/**
 * Represents a parser that interprets user inputs and carries out the interpreted commands
 */
public class Parser {
    private final TaskList taskList;
    private final Ui ui;
    private final NotesList notesList;

    /**
     * Constructor for a new Parser object
     * @param taskList  container for new Tasks created by add commands
     * @param ui        interface for printing messages to user
     */
    public Parser(TaskList taskList, Ui ui, NotesList notesList) {
        this.taskList = taskList;
        this.ui = ui;
        this.notesList = notesList;
    }

    public boolean isExit(String input) {
        return input.equals("exit");
    }

    /**
     * Method for taking in user input and executing specified commands based on a pre-defined list
     *
     * @param input                   Takes in user input as a String
     */
    public String parse(String input) {
        int taskEnd = input.indexOf(" ");
        String commandType = taskEnd == -1 ? input : input.substring(0, taskEnd);
        try {
            TaskType type = TaskType.valueOf(commandType);
            String details = input.substring(taskEnd + 1);

            switch (type) {
            case bye:
                return this.ui.printExitMessage();
            case todo:
                this.taskList.add(new ToDo(details));
                return this.ui.printOnAdd();
            case deadline:
                String[] d = details.split("/by ");
                try {
                    this.taskList.add(new Deadline(d[0], d[1]));
                } catch (DateFormatException dFE) {
                    return Ui.printException(dFE);
                }
                return this.ui.printOnAdd();
            case event:
                String[] v1 = details.split("/from ");
                String[] v2 = v1[1].split("/to ");
                try {
                    this.taskList.add(new Event(v1[0], v2[0], v2[1]));
                } catch (ArrayIndexOutOfBoundsException | DateFormatException formatException) {
                    return Ui.printException(formatException);
                }
                return this.ui.printOnAdd();
            case delete:
                int deleteIndex = Integer.parseInt(details) - 1;
                try {
                    String out = this.ui.printOnDelete(deleteIndex);
                    this.taskList.remove(deleteIndex);
                    out = out + "\n" + this.ui.printTotal();
                    return out;
                } catch (ArrayIndexOutOfBoundsException arrIndexEx) {
                    throw new InvalidIndexException();
                }
            case mark:
                int markIndex = Integer.parseInt(details) - 1;
                assert markIndex > 0;
                try {
                    this.taskList.mark(markIndex);
                    return this.ui.printOnMark(markIndex);
                } catch (ArrayIndexOutOfBoundsException arrEx) {
                    throw new InvalidIndexException();
                }
            case unmark:
                int unmarkIndex = Integer.parseInt(details) - 1;
                assert unmarkIndex > 0;
                try {
                    this.taskList.unmark(unmarkIndex);
                    return this.ui.printOnUnmark(unmarkIndex);
                } catch (ArrayIndexOutOfBoundsException arrException) {
                    throw new InvalidIndexException();
                }
            case find:
                return this.ui.printOnFind(this.taskList.find(details));
            case list:
                return this.ui.printList(taskList);
            case write:
                this.notesList.add(details);
                return this.ui.printOnAddNote(details);
            case notes:
                return this.ui.printAllNotes();
            case remove:
                this.notesList.remove(Integer.parseInt(details) - 1);
                return this.ui.printOnDeleteNote(Integer.parseInt(details));
            default:
                throw new UnknownInputException();
            }
        } catch (IllegalArgumentException | InvalidIndexException | UnknownInputException e) {
            return this.ui.printException(e);
        }
    }
}


