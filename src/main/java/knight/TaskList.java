package knight;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

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
                Ui.speak("I regret to inform thee, Your Excellency, that thou lackest a task bearing this index in thy list.");
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
                Ui.speak("I regret to inform thee, Your Excellency, that thou lackest a task bearing this index in thy list.");
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
                Ui.speak("I regret to inform thee, Your Excellency, that thou lackest a task bearing this index in thy list.");
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
                task = new Event(params[0].substring(6), params[1].substring(5), params[2].substring(3));
            } catch (DateTimeParseException e) {
                Ui.speak("Your Excellency, I struggle to understand thee. To specify a date, use format\n"
                        + "yyyy-mm-dd");
                return;
            }
            tasks.add(task);
            Ui.speak("Understood. This task hath been added to thy list:\n" + task);
        }
    }

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
                task = new Event(params[0].substring(6), params[1].substring(5), params[2].substring(3));
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
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i != tasks.size() - 1) {
                output += (i + 1) + ". " + tasks.get(i) + "\n";
            } else {
                output += (i + 1) + ". " + tasks.get(i);
            }
        }
        return output;
    }

    String getCommands() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (i != tasks.size() - 1) {
                output += task.getCommand() + "\n";
                if (task.isDone) {
                    output += "mark " + (i + 1) + "\n";
                }
            } else {
                output += task.getCommand();
                if (task.isDone) {
                    output += "\nmark " + (i + 1);
                }
            }
        }
        return output;
    }
}
