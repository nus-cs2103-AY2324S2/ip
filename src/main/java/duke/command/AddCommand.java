package duke.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DescriptionFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDos;

/**
 * Class for "add" command.
 *
 */
public class AddCommand extends Command {
    private static final DateTimeFormatter DATE_FORMAT_INP = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");
    private static final DateTimeFormatter DATE_FORMAT_OUT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private String com;

    /**
     * Constructor for AddCommand.
     *
     * @param c command in String format.
     */
    public AddCommand(String c) {
        super(0);
        com = c;
    }

    @Override
    public void execute(TaskList tL, Ui ui, Storage st) {
        String[] inputs = com.split(" ");
        assert inputs.length > 0;
        String[] name;
        try {
            switch (inputs[0]) {
            case "event":
                if (inputs.length == 1) {
                    throw new DescriptionFormatException("Wrong format!, please use this format: "
                            + Event.getFormat());
                } else {
                    name = com.split("event ");
                    String[] desFromTo = name[1].split(" /from ");
                    String[] fromTo = desFromTo[1].split(" /to ");
                    if (desFromTo.length != 2 || fromTo.length != 2) {
                        throw new DescriptionFormatException("Wrong format!, please use this format: "
                                + Event.getFormat());
                    }
                    LocalDateTime ldtF = LocalDateTime.parse(fromTo[0], DATE_FORMAT_INP);
                    LocalDateTime ldtT = LocalDateTime.parse(fromTo[1], DATE_FORMAT_INP);
                    String out = tL.addTask(new Event(desFromTo[0], ldtF, ldtT));
                    ui.showMessage(out);
                    st.write(tL.getList());
                    setOut(out);
                }
                break;
            case "todo":
                if ((inputs.length == 1)) {
                    throw new DescriptionFormatException("Wrong format!, please use this format: "
                            + ToDos.getFormat());
                } else {
                    name = com.split("todo ");
                    if (name.length != 2) {
                        throw new DescriptionFormatException("Wrong format!, please use this format: "
                                + ToDos.getFormat());
                    } else {
                        name = com.split("todo ");
                        if (name.length != 2) {
                            throw new DescriptionFormatException("Wrong format!, please use this format: "
                                    + ToDos.getFormat());
                        }
                    }
                    String out = tL.addTask(new ToDos(name[1]));
                    ui.showMessage(out);
                    st.write(tL.getList());
                    setOut(out);
                }
                break;
            case "deadline":
                if (inputs.length == 1) {
                    throw new DescriptionFormatException("Wrong format!, please use this format: "
                            + Deadline.getFormat());
                } else {
                    name = com.split("deadline ");
                    String[] desBy = name[1].split(" /by ");
                    if (desBy.length != 2) {
                        throw new DescriptionFormatException("Wrong format!, please use this format: "
                                + Deadline.getFormat());
                    }
                    LocalDateTime ldt = LocalDateTime.parse(desBy[1], DATE_FORMAT_INP);
                    String out = tL.addTask(new Deadline(desBy[0], ldt));
                    ui.showMessage(out);
                    st.write(tL.getList());
                    setOut(out);
                }
                break;
            default:
            }
        } catch (DescriptionFormatException e) {
            ui.showMessage(e.getMessage());
            setOut(e.getMessage());
        } catch (DateTimeParseException e) {
            ui.showMessage("Please use the date format: yyyy-MM-dd HH:mm (e.g.: 2023-02-01 12:30)");
            setOut("Please use the date format: yyyy-MM-dd HH:mm (e.g.: 2023-02-01 12:30)");
        } catch (IOException e) {
            ui.showMessage("Save failed");
            setOut("Please use the date format: yyyy-MM-dd HH:mm (e.g.: 2023-02-01 12:30)");
        }
    }

    /**
     * Returns command in String format.
     *
     * @return command in String format.
     */
    public String getCom() {
        return com;
    }
}
