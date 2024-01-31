package Duke.Parser;

import Duke.DukeException.DukeException;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    private boolean finished;

    public Parser() {
        finished = false;
    }

    public String[] parse(String in) throws DukeException {
        String[] split = in.split(" ", 2);
        if(split[0].equalsIgnoreCase("bye") || split[0].equalsIgnoreCase("list")) {
            return split;
        } else if(split.length == 1) {
            throw new DukeException("Please enter something that you want to do. \n");
        } else {
            return split;
        }
    }

    public boolean isFinished() {
        return finished;
    }

    public void bye() {
        finished = true;
    }

    public int stringToNum(String num) throws DukeException {
        try {
            return Integer.parseInt(num) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a number for the task that you wish to edit.\n");
        }
    }

    public String toDo(String out) throws DukeException {
        if (out.length() <= 1) {
            throw new DukeException("Please enter something that you want to do. \n");
        } else {
            return out;
        }
    }

    public String[] deadline(String out) throws DukeException {
        if (out.length() <= 1) {
            throw new DukeException("Please enter something that you want to do. \n");
        } else {
            String[] split = out.split(" /by ");
            if (split[0].length() <= 1) {
                throw new DukeException("Please enter something that you want to do. \n");
            } else if (split.length != 2 || split[1].length() <= 1) {
                throw new DukeException("Please enter the deadline of the task. \n");
            } else {
                return split;
            }
        }
    }

    public String[] event(String out) throws DukeException {
        if (out.length() <= 1) {
            throw new DukeException("Please enter something that you want to do. \n");
        } else {
            String[] split1 = out.split(" /from ");
            if (split1[0].length() <= 1) {
                throw new DukeException("Please enter something that you want to do. \n");
            } else if (split1.length != 2) {
                throw new DukeException("Please enter the duration of the event. \n");
            } else {
                String[] split2 = split1[1].split(" /to ");
                if (split2.length != 2) {
                    throw new DukeException("Please enter the ending time of the event. \n");
                } else {
                    return new String[]{split1[0], split2[0], split2[1]};
                }
            }
        }
    }

    public LocalDate stringToDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date.strip());
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the date in format of yyyy-mm-dd.\n");
        }
    }

}
