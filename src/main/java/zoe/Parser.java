package zoe;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Processes input from user
 */
public class Parser {
    public TaskList tl;
    protected Ui ui;

    private Saver s;
    public Parser(TaskList tl) {
        this.tl = tl;
        this.ui = new Ui();
        this.s = new Saver(this.tl.getTasks());
    }
    public String process(String command) {
        if (command.contains("_")) {
            return ui.avoidUnderscores();
        }

        int idx = command.indexOf(" ");

        if (idx > -1) {
            String[] str = command.split(" ", 2);
            if (str[1].trim().length() == 0 || str[1].charAt(0) == ('/')) {
                return ui.noDescription();
            }
            return carryOutLongCommand(str[0], str[1]);

        } else {
            return carryOutShortCommand(command);
        }
    }

    /**
     * Carries out short commands like list
     * If invalid command like empty todo is given, calls the respective responses
     * @param command
     */
    public String carryOutShortCommand(String command) {
        String res = "";
        if (command.equals("list")) {
            if (tl.getSize() < 1) {
                res += "Sorry you don't have any tasks currently D:";
            } else {
                for (int i = 1; i <= tl.getSize(); i++) {
                    res += String.format("%d.%s\n", i, tl.get(i).getStatus());
                }
            }

        } else {
            if (command.equals("todo")) {
                res += ui.todoDescription();
            } else {
                res += ui.invalidCommand();
            }

        }

        return res;
    }

    /**
     * Carries out long commands to deal with the different forms of task
     * @param command
     * @param commandDescription
     */
    public String carryOutLongCommand(String command, String commandDescription) {
        try {
            switch (command){
            case "todo":
                ToDo td = new ToDo(commandDescription);
                tl.add(td);
                s.saveTo("./data/");
                return ui.addedTask(td, tl.getSize());

            case "deadline":
                Deadline dl = new Deadline(commandDescription);
                tl.add(dl);
                s.saveTo("./data/");
                return ui.addedTask(dl, tl.getSize());

            case "event":
                Event e = new Event(commandDescription);
                tl.add(e);
                s.saveTo("./data/");
                return ui.addedTask(e, tl.getSize());

            case "find":
                String res = "";
                int counter = 0;
                for (Task t: tl.getTasks()) {
                    if (t.getStatus().contains(commandDescription)) {
                        counter++;
                        res += String.format("%d.%s\n", counter, t.getStatus());
                    }
                }

                if (counter == 0) {
                    res += ui.taskDoesNotExist(tl.getSize());
                }

                return res;

            case "mark":
                tl.markAsDone(Integer.parseInt(commandDescription.trim()));
                return ui.markedTask(tl.get(Integer.parseInt(commandDescription.trim())));

            case "unmark":
                tl.unmark(Integer.parseInt(commandDescription.trim()));
                return ui.unmarkedTask(tl.get(Integer.parseInt(commandDescription.trim())));

            case "delete":
                Task deletedTask = tl.get(Integer.parseInt(commandDescription.trim()));
                tl.delete(Integer.parseInt(commandDescription.trim()));
                return ui.deletedTask(deletedTask, tl.getSize());

            default:
                return ui.invalidCommand();
            }

        } catch (IndexOutOfBoundsException e) {
            if (command.equals("event")) {
                return ui.eventFormat();
            } else if (command.equals("deadline")) {
                return ui.deadlineFormat();
            }
            return ui.invalidIndex(tl.getSize());

        } catch (DateTimeParseException d) {
            return ui.invalidDate();

        } catch (NumberFormatException n) {
            return ui.invalidIndexFormat();
        }
    }
}