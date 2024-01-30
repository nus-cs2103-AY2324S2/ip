package Commands;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import Utilities.Storage;
import Utilities.TaskList;
import Utilities.Ui;
import Exceptions.YpxmmException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public enum Command {
    LIST {
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            ui.printList(tasklist.tasks);
        }
    },
    MARK {
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) throws YpxmmException {
            int index = Integer.parseInt(parsed.get(1));
            try {
                Task task = tasklist.tasks.get(index - 1);
                task.markTask();
                storage.reWrite(tasklist);
                ui.markMessage(task);
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasklist.tasks.isEmpty() ? "no tasks to mark." : tasklist.tasks.size() +
                                " tasks, enter any number from 1 to " + tasklist.tasks.size()));
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            }
        }
    },
    UNMARK {
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) throws YpxmmException {
            int index = Integer.parseInt(parsed.get(1));
            try {
                Task task = tasklist.tasks.get(index - 1);
                task.unmarkTask();
                storage.reWrite(tasklist);
                ui.unmarkMessage(task);
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasklist.tasks.isEmpty() ? "no tasks to unmark." : tasklist.tasks.size() +
                                " tasks, enter any number from 1 to " + tasklist.tasks.size()));
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            }
        }
    },
    TODO {
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            try {
                Task task = new ToDo(parsed.get(1));
                tasklist.addTask(parsed, task);
                storage.appendToFile(task.toWrite());
                ui.addTaskMessage(task, tasklist);
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            }
        }
    },
    DEADLINE {
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            try {
                Task task = new Deadline(parsed.get(1).trim(), parsed.get(2).trim());
                tasklist.addTask(parsed, task);
                storage.appendToFile(task.toWrite());
                ui.addTaskMessage(task, tasklist);
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Brother, follow format can or not? Enter dates in dd-mm-yyyy HHmm (24-08-2024 1800)");
            }
        }
    },
    EVENT {
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            try {
                Task task = new Event(parsed.get(1).trim(), parsed.get(2).trim(), parsed.get(3).trim());
                tasklist.addTask(parsed, task);
                storage.appendToFile(task.toWrite());
                ui.addTaskMessage(task, tasklist);
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Brother, follow format can or not? Enter dates in dd-mm-yyyy HHmm (24-08-2024 1800)");
            }
        }
    },
    GETCOMMANDS {
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            ui.getCommands();
        }
    },
    DELETE {
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) throws YpxmmException {
            int index = Integer.parseInt(parsed.get(1));
            try {
                Task task = tasklist.tasks.get(index - 1);
                tasklist.deleteTask(index);
                storage.reWrite(tasklist);
                ui.deleteTaskMessage(task, tasklist);
            } catch (IndexOutOfBoundsException e) {
                throw new YpxmmException("Eh u seh isit? Now your list got " +
                        (tasklist.tasks.isEmpty() ? "no tasks to delete." : tasklist.tasks.size() +
                                " tasks, enter any number from 1 to " + tasklist.tasks.size()));
            } catch (YpxmmException y) {
                System.out.println(y.getMessage());
            }
        }
    },
    BYE {
        @Override
        public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) {
            ui.sayGoodbye();
        }
    };

    public void execute(TaskList tasklist, Ui ui, Storage storage, ArrayList<String> parsed) throws YpxmmException {
        //should never reach here as all invalid inputs will be handled
        //in parse method in Parser class
    }
}
