import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Commands are a family of classes that represent commands with a defining execute method.
 */
public abstract class Command {
    /**
     * Executes actions to complete the command.
     *
     * @param list a TaskList object containing current tasks.
     * @param ui a Ui object. Receives instructions on how to update the user interface.
     * @param storage Storage object. For saving changes to memory.
     */
    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException.IllegalParamException;

    public static class ByeCommand extends Command{
        @Override
        public void execute(TaskList list, Ui ui, Storage storage) {
            ui.showBye();
        }
    }

    public static class ListCommand extends Command{
        @Override
        public void execute(TaskList list, Ui ui, Storage storage) {
            ui.showList(list);
        }
    }

    public static class MarkCommand extends Command{
        private int id;
        public MarkCommand(int id) {
            this.id = id;
        }
        @Override
        public void execute(TaskList list, Ui ui, Storage storage) throws DukeException.IllegalParamException{
            try {
                Task done = list.getTask(id);
                done.setDone();
                ui.showMessage("Thats sick! Great work, marked as done!\n" + done);
            } catch (DukeException.IllegalParamException e) {
                throw new DukeException.IllegalParamException(e.getMessage() + " Unable to mark task!");
            }
        }
    }

    public static class UnmarkCommand extends Command {
        private int id;

        public UnmarkCommand(int id) {
            this.id = id;
        }

        public void execute(TaskList list, Ui ui, Storage storage) throws DukeException.IllegalParamException {
            try {
                Task notDone = list.getTask(this.id);
                notDone.setNotDone();
                ui.showMessage("Awh why uncheck me :( Its ok, it is what it is!\n" + notDone);
            } catch (DukeException.IllegalParamException e) {
                throw new DukeException.IllegalParamException(e.getMessage() + " Unable to unmark task!");
            }
        }
    }

    public static class DeleteCommand extends Command {
        private int id;
        public DeleteCommand(int id) {
            this.id = id;
        }

        @Override
        public void execute(TaskList list, Ui ui, Storage storage) throws DukeException.IllegalParamException {
            list.deleteTask(this.id);
            ui.showMessage("Looks like you have " + list.countTasks() + " things left to do!");

        }
    }

    public static class ToDoCommand extends Command {
        private String description;

        public ToDoCommand(String name) {
            this.description = name;
        }

        public void execute(TaskList list, Ui ui, Storage storage) {
            Task.ToDo newTask = new Task.ToDo(this.description);
            list.add(newTask);
            ui.showMessage("added new ToDo: " + newTask);
            ui.showMessage("Looks like you have " + list.countTasks() + " things left to do!");
        }
    }
    public static class DeadlineCommand extends Command {
        private String description;
        private LocalDate by;

        public DeadlineCommand(String description, LocalDate by) {
            this.description = description;
            this.by = by;
        }

        @Override
        public void execute(TaskList list, Ui ui, Storage storage) {
            Task.Deadline newDeadline = new Task.Deadline(description, by);
            list.add(newDeadline);
            ui.showMessage("added new deadline: " + newDeadline);
            ui.showMessage("Looks like you have " + list.countTasks() + " things left to do!");
        }
    }

    public static class EventCommand extends Command {
        private String task;
        private LocalDate start;
        private LocalDate end;

        public EventCommand(String task, LocalDate start, LocalDate end) {
            this.task = task;
            this.start = start;
            this.end = end;
        }

        @Override
        public void execute(TaskList list, Ui ui, Storage storage) {
            Task newEvent = new Task.Event(task, start, end);
            list.add(newEvent);
            ui.showMessage("added new event: " + newEvent);
            ui.showMessage("Looks like you have " + list.countTasks() + " things left to do!");
        }
    }
}
