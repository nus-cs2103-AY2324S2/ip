package knight;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks in the Knight program.
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Execute a command on the task list.
     *
     * @param commandType The type of command to execute.
     * @param message     The full command message.
     */
    void executeCommand(Command commandType, String message) {
        if (commandType == Command.LIST) {
            if (tasks.isEmpty()) {
                Ui.speak("Your Excellency, thy list remaineth free of tasks at this present moment.");
            } else {
                Ui.speak("Behold, the duties thou hast assigned:\n" + this.toString());
            }
        } else if (commandType == Command.SAVE) {
            Storage.writeToFile(this);
            Ui.speak("Thy list hath been saved to thy scrolls of history.");
        } else if (commandType == Command.MARK) {
            int index = Integer.parseInt(message.substring(5));
            Task task;

            try {
                task = tasks.get(index - 1);
            } catch (IndexOutOfBoundsException e) {
                Ui.speak("I regret to inform thee, Your Excellency, "
                        + "that thou lackest a task bearing this index in thy list.");
                return;
            }

            task.mark();
            Ui.speak("Thy task hath been marked as done.\n" + task);
        } else if (commandType == Command.UNMARK) {
            int index = Integer.parseInt(message.substring(7));
            Task task;

            try {
                task = tasks.get(index - 1);
            } catch (IndexOutOfBoundsException e) {
                Ui.speak("I regret to inform thee, Your Excellency, "
                        + "that thou lackest a task bearing this index in thy list.");
                return;
            }

            task.unmark();
            Ui.speak("Thy task hath been unmarked.\n" + task);
        } else if (commandType == Command.DELETE) {
            int index = Integer.parseInt(message.substring(7));
            Task task;

            try {
                task = tasks.get(index - 1);
            } catch (IndexOutOfBoundsException e) {
                Ui.speak("I regret to inform thee, Your Excellency,"
                        + "that thou lackest a task bearing this index in thy list.");
                return;
            }

            tasks.remove(index - 1);
            Ui.speak("Thy task hath been removed from thy list.\n" + task);
        } else if (commandType == Command.TODO) {
            Task task = new ToDo(message.substring(5));
            tasks.add(task);
            Ui.speak("Understood. This task hath been added to thy list:\n" + task);
        } else if (commandType == Command.DEADLINE) {
            String[] params = message.split(" /");
            Task task;
            try {
                task = new Deadline(params[0].substring(9), params[1].substring(3));
            } catch (DateTimeParseException e) {
                Ui.speak("Your Excellency, I struggle to understand thee. To specify a date, use format\n"
                        + "yyyy-mm-dd");
                return;
            }
            tasks.add(task);
            Ui.speak("Understood. This task hath been added to thy list:\n" + task);
        } else if (commandType == Command.EVENT) {
            String[] params = message.split(" /");
            Task task;
            try {
                task = new Event(params[0].substring(6),
                        params[1].substring(5),
                        params[2].substring(3));
            } catch (DateTimeParseException e) {
                Ui.speak("Your Excellency, I struggle to understand thee. To specify a date, use format\n"
                        + "yyyy-mm-dd");
                return;
            }
            tasks.add(task);
            Ui.speak("Understood. This task hath been added to thy list:\n" + task);
        } else if (commandType == Command.FIND) {
            String keyword = message.substring(5);
            String output = "";
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).matches(keyword)) {
                    output += "\n" + (i + 1) + ". " + tasks.get(i);
                }
            }
            if (output.equals("")) {
                Ui.speak("I regret to inform thee, Your Excellency, "
                        + "that no tasks bearing this keyword exist in thy list.");
            } else {
                Ui.speak("Behold, the tasks that match thy keyword:\n" + output);
            }
        }
    }

    String executeCommandReturnString(Command commandType, String message) {
        if (commandType == Command.LIST) {
            if (tasks.isEmpty()) {
                return "Your Excellency, thy list remaineth free of tasks at this present moment.";
            } else {
                return "Behold, the duties thou hast assigned:\n" + this.toString();
            }
        } else if (commandType == Command.SAVE) {
            Storage.writeToFile(this);
            return "Thy list hath been saved to thy scrolls of history.";
        } else if (commandType == Command.MARK) {
            int index = Integer.parseInt(message.substring(5));
            Task task;

            try {
                task = tasks.get(index - 1);
            } catch (IndexOutOfBoundsException e) {
                return "I regret to inform thee, Your Excellency, "
                        + "that thou lackest a task bearing this index in thy list.";
            }

            task.mark();
            return "Thy task hath been marked as done.\n" + task;
        } else if (commandType == Command.UNMARK) {
            int index = Integer.parseInt(message.substring(7));
            Task task;

            try {
                task = tasks.get(index - 1);
            } catch (IndexOutOfBoundsException e) {
                return "I regret to inform thee, Your Excellency, "
                        + "that thou lackest a task bearing this index in thy list.";
            }

            task.unmark();
            return "Thy task hath been unmarked.\n" + task;
        } else if (commandType == Command.DELETE) {
            int index = Integer.parseInt(message.substring(7));
            Task task;

            try {
                task = tasks.get(index - 1);
            } catch (IndexOutOfBoundsException e) {
                return "I regret to inform thee, Your Excellency,"
                        + "that thou lackest a task bearing this index in thy list.";
            }

            tasks.remove(index - 1);
            return "Thy task hath been removed from thy list.\n" + task;
        } else if (commandType == Command.TODO) {
            Task task = new ToDo(message.substring(5));
            tasks.add(task);
            return "Understood. This task hath been added to thy list:\n" + task;
        } else if (commandType == Command.DEADLINE) {
            String[] params = message.split(" /");
            Task task;
            try {
                task = new Deadline(params[0].substring(9), params[1].substring(3));
            } catch (DateTimeParseException e) {
                return "Your Excellency, I struggle to understand thee. To specify a date, use format\n"
                        + "yyyy-mm-dd";
            }
            tasks.add(task);
            return "Understood. This task hath been added to thy list:\n" + task;
        } else if (commandType == Command.EVENT) {
            String[] params = message.split(" /");
            Task task;
            try {
                task = new Event(params[0].substring(6),
                        params[1].substring(5),
                        params[2].substring(3));
            } catch (DateTimeParseException e) {
                return "Your Excellency, I struggle to understand thee. To specify a date, use format\n"
                        + "yyyy-mm-dd";
            }
            tasks.add(task);
            return "Understood. This task hath been added to thy list:\n" + task;
        } else if (commandType == Command.FIND) {
            String keyword = message.substring(5);
            String output = "";
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).matches(keyword)) {
                    output += "\n" + (i + 1) + ". " + tasks.get(i);
                }
            }
            if (output.equals("")) {
                return "I regret to inform thee, Your Excellency, "
                        + "that no tasks bearing this keyword exist in thy list.";
            } else {
                return "Behold, the tasks that match thy keyword:\n" + output;
            }
        }
        return "Thou hast reached a place previously deemed unreachable. How didst thou arrive here?";
    }

    /**
     * Execute a command on the task list without speaking.
     *
     * @param commandType The type of command to execute.
     * @param message     The full command message.
     */
    void executeCommandSilently(Command commandType, String message) {
        if (commandType == Command.TODO) {
            Task task = new ToDo(message.substring(5));
            tasks.add(task);
        } else if (commandType == Command.DEADLINE) {
            String[] params = message.split(" /");
            Task task;
            try {
                task = new Deadline(params[0].substring(9), params[1].substring(3));
            } catch (DateTimeParseException e) {
                return;
            }
            tasks.add(task);
        } else if (commandType == Command.EVENT) {
            String[] params = message.split(" /");
            Task task;
            try {
                task = new Event(params[0].substring(6),
                        params[1].substring(5),
                        params[2].substring(3));
            } catch (DateTimeParseException e) {
                return;
            }
            tasks.add(task);
        } else if (commandType == Command.MARK) {
            int index = Integer.parseInt(message.substring(5));
            Task task;

            try {
                task = tasks.get(index - 1);
            } catch (IndexOutOfBoundsException e) {
                return;
            }
            task.mark();
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i != tasks.size() - 1) {
                output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            } else {
                output.append(i + 1).append(". ").append(tasks.get(i));
            }
        }
        return output.toString();
    }

    /**
     * Get the commands representation of the task list.
     *
     * @return The commands representation of the task list.
     */
    String getCommands() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (i != tasks.size() - 1) {
                output.append(task.getCommand()).append("\n");
                if (task.isDone) {
                    output.append("mark ").append(i + 1).append("\n");
                }
            } else {
                output.append(task.getCommand());
                if (task.isDone) {
                    output.append("\nmark ").append(i + 1);
                }
            }
        }
        return output.toString();
    }
}
