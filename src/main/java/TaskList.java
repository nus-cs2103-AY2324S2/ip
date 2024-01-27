import exceptions.InvalidInputException;
import exceptions.KaiYapException;
import exceptions.MissingInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    Ui ui;

    public TaskList(Ui ui) {
        tasks = new ArrayList<>();
        this.ui = ui;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public Task taskCreator(String input) {
        try {
            KaiYap.TaskType type;
            try {
                type = KaiYap.TaskType.valueOf(input.split(" ")[0].toUpperCase());
            } catch (Exception e) {
                throw new InvalidInputException("I don't quite understand what you mean. Please try again! UwU :3");
            }
            Task task = null;
            switch (type) {
                case TODO:
                    try {
                        task = createTodo(input);
                    } catch (KaiYapException e) {
                        ui.printError(e.getMessage());
                    }
                    break;
                case DEADLINE:
                    try {
                        task = createDeadline(input);
                    } catch (KaiYapException e) {
                        ui.printError(e.getMessage());
                    }
                    break;
                case EVENT:
                    try {
                        task = createEvent(input);
                    } catch (KaiYapException e) {
                        ui.printError(e.getMessage());
                    }
                    break;
            }
            if (task != null) {
                return task;
            }
        } catch (KaiYapException e) {
            ui.printError(e.getMessage());
        }
        return null;
    }

    public Todo createTodo(String input) throws KaiYapException {
        if (input.equals("todo")) {
            throw new MissingInputException("Your todo needs a description. Please try again! UwU :3");
        } else {
            return new Todo(input.substring(input.indexOf(' ') + 1), input);
        }
    }

    public Deadline createDeadline(String input) throws KaiYapException {
        if (input.equals("deadline")) {
            throw new MissingInputException("Your deadline needs a description. Please try again! UwU :3");
        } else {
            try {
                return new Deadline(
                        input.substring(input.indexOf(" ") + 1, input.indexOf("/by")).strip(),
                        input,
                        LocalDateTime.parse(input.substring(input.indexOf("/by") + 3).strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                );
            } catch (DateTimeParseException d) {
                ui.printError("\tYour deadline format is wrong - please use the dd/MM/yyyy MMhh format! UwU :3");
                return null;
            } catch (Exception e) {
                throw new MissingInputException("\tYour deadline is missing some important information. Please try again! UwU :3");
            }
        }
    }

    public Event createEvent(String input) throws KaiYapException {
        if (input.equals("event")) {
            throw new MissingInputException("Your event needs a description. Please try again! UwU :3");
        } else {
            try {
                return new Event(
                        input.substring(input.indexOf(" ") + 1, input.indexOf("/from")).strip(),
                        input,
                        LocalDateTime.parse(input.substring(input.indexOf("/from") + 5, input.indexOf("/to")).strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                        LocalDateTime.parse(input.substring(input.indexOf("/to") + 3).strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
                );
            } catch (DateTimeParseException d) {
                ui.printError("\tYour event timeline format is wrong - please use the dd/MM/yyyy MMhh format! UwU :3");
                return null;
            } catch (Exception e) {
                throw new MissingInputException("Your deadline is missing some important information. Please try again! UwU :3");
            }
        }
    }

}
