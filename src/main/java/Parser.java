import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    private boolean finished;

    public Parser() {
        finished = false;
    }

    public void parse(String[] in) throws DukeException {
        try {
            if (in[0].equalsIgnoreCase("bye")) {
                finished = true;
            } else if (in[0].equalsIgnoreCase("list")) {
                TaskList.list();
            } else if (in[0].equalsIgnoreCase("mark")) {
                mark(Integer.parseInt(in[1].strip()) - 1);
            } else if (in[0].equalsIgnoreCase("unmark")) {
                unmark(Integer.parseInt(in[1].strip()) - 1);
            } else if (in[0].equalsIgnoreCase("todo")) {
                toDo(in[1]);
            } else if (in[0].equalsIgnoreCase("deadline")) {
                deadline(in[1]);
            } else if (in[0].equalsIgnoreCase("event")) {
                event(in[1]);
            } else if (in[0].equalsIgnoreCase("delete")) {
                delete(Integer.parseInt(in[1].strip()) - 1);
            } else {
                throw new DukeException("Please do enter a new proper command.\n");
            }
        } catch (DukeException e) {
            throw new DukeException(e.toString());
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a number for the task that you wish to edit.\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a number for the task that is on the list.\n");
        } catch (IOException e) {
            throw new DukeException("Error when writing to file\n");
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the date in format of yyyy-mm-dd.\n");
        }
    }

    public boolean isFinished() {
        return finished;
    }

    private static void mark(int num) throws DukeException, IOException {
        TaskList.mark(num);
    }

    private static void unmark(int num) throws DukeException,IOException {
        TaskList.unmark(num);
    }

    private static void toDo(String out) throws DukeException,IOException {
        if (out.length() <= 1) {
            throw new DukeException("Please enter something that you want to do. \n");
        } else {
            TaskList.toDo(out);
        }
    }

    private static void deadline(String out) throws DukeException,IOException, DateTimeParseException {
        if (out.length() <= 1) {
            throw new DukeException("Please enter something that you want to do. \n");
        } else {
            String[] split = out.split("/by");
            if (split[0].length() <= 1) {
                throw new DukeException("Please enter something that you want to do. \n");
            } else if (split.length != 2 || split[1].length() <= 1) {
                throw new DukeException("Please enter the deadline of the task. \n");
            } else {
                LocalDate date = LocalDate.parse(split[1].strip());
                TaskList.deadline(split[0],date);
            }
        }
    }

    private static void event(String out) throws DukeException,IOException {
        if (out.length() <= 1) {
            throw new DukeException("Please enter something that you want to do. \n");
        } else {
            String[] split1 = out.split("/from");
            if (split1[0].length() <= 1) {
                throw new DukeException("Please enter something that you want to do. \n");
            } else if (split1.length != 2) {
                throw new DukeException("Please enter the duration of the event. \n");
            } else {
                String[] split2 = split1[1].split("/to");
                if (split2.length != 2) {
                    throw new DukeException("Please enter the ending time of the event. \n");
                } else {
                    TaskList.event(split1[0], split2[0], split2[1]);
                }
            }
        }
    }

    private static void delete(int num) throws DukeException,IOException {
        TaskList.delete(num);
    }

}
