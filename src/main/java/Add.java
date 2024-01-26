import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class for adding a task into task list
 */
public class Add implements Command{
    private Task task;
    private TaskList tasks;
    /**
     * Constructor
     * add task to task list.
     * Identify which type of task is it.
     * @param text the description of a task
     * @throws DukeException wrong inputs might happens
     */
    public Add(String text, TaskList taskList) throws DukeException{
        if (text.startsWith("todo")) {
            if (text.length()<=5) {
                throw new EmptyTextException("description","todo");
            }
            String descrip=text.substring(5);
            this.task=new Todo(descrip);
            taskList.addTask(this.task);
        } else if (text.startsWith("deadline")) {
            String[] token = text.split("/");
            if (token.length != 2) {
                throw new WrongUsageException("deadline xxx /by xxx");
            }
            String by = token[1];
            if (token[0].length()<=9) {
                throw new EmptyTextException("description","deadline");
            }
            String descrip = token[0].substring(9);
            if (!by.startsWith("by ")) {
                throw new WrongUsageException("deadline xxx /by xxx");
            }
            if (by.length()<=3) {
                throw new EmptyTextException("due","deadline");
            }
            String byText = by.substring(3).trim();
            if (!timeFormCheck(byText)) {
                throw new TimeFormatException();
            }
            this.task=new Deadline(descrip, LocalDate.parse(byText));
            taskList.addTask(this.task);
        } else if (text.startsWith("event")) {
            String[] token = text.split("/");
            if (token.length != 3) {
                throw new WrongUsageException("event xxx /from xxx /to xxx");
            }
            String from = token[1];
            String to = token[2];
            if (token[0].length()<=6) {
                throw new EmptyTextException("description","event");
            }
            String descrip = token[0].substring(6);
            if (!from.startsWith("from ")) {
                throw new WrongUsageException("event xxx /from yyyy-mm-dd /to yyyy-mm-dd");
            }
            if (!to.startsWith("to ")) {
                throw new WrongUsageException("event xxx /from yyyy-mm-dd /to yyyy-mm-dd");
            }
            if (from.length()<=5) {
                throw new EmptyTextException("start","event");
            }
            if (to.length()<=3) {
                throw new EmptyTextException("end","event");
            }
            String fromText = from.substring(5).trim();
            String toText = to.substring(3).trim();
            if (!timeFormCheck(fromText) || !timeFormCheck(toText)) {
                throw new TimeFormatException();
            }
            LocalDate fromDay = LocalDate.parse(fromText);
            LocalDate toDay = LocalDate.parse(toText);
            if (fromDay.isAfter(toDay)) {
                throw new TimeInconsistException();
            }
            this.task=new Event(descrip, fromDay, toDay);
            taskList.addTask(this.task);
        } else {
            throw new CommandNotDefinedException();
        }
        this.tasks=taskList;
    }

    /**
     * Override the interface method.
     */
    @Override
    public void reply() {
        System.out.printf(
                "    Got it. I've added this task:\n      %s\n    Now you have %s tasks in the list.\n"
                ,this.task,this.tasks.getListLength());
    }

    private boolean timeFormCheck(String time) {
        try {
            LocalDate.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
