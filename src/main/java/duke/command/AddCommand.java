package duke.command;

import duke.*;
import duke.exception.DescriptionFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDos;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String com;
    private static final DateTimeFormatter dFormatInp = DateTimeFormatter.ofPattern("yyyy-MM-dd' 'HH:mm");
    private static final DateTimeFormatter dFormatOut = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");


    public AddCommand(String c) {
        super(0);
        com = c;
    }

    @Override
    public void execute(TaskList tL, Ui ui, Storage st) {
        String[] inputs = com.split(" ");
        String[] name;
        try {
            switch (inputs[0]) {
                case "event": // when the task added is event
                    if(inputs.length == 1){
                        throw new DescriptionFormatException("Wrong format!, please use this format: "
                                + Event.getFormat()); //when the description format is wrong (e.g: no desc)
                    } else {
                        name = com.split("event ");
                        String[] desFromTo = name[1].split(" /from ");
                        String[] fromTo = desFromTo[1].split(" /to ");
                        if (desFromTo.length != 2 || fromTo.length != 2){
                            throw new DescriptionFormatException("Wrong format!, please use this format: "
                                    + Event.getFormat());
                        }
                        LocalDateTime ldtf = LocalDateTime.parse(fromTo[0], dFormatInp);
                        LocalDateTime ldtt = LocalDateTime.parse(fromTo[1], dFormatInp);
                        String out = tL.addTask(new Event(desFromTo[0], ldtf, ldtt));
                        ui.showMessage(out);
                        st.write(tL.getList());
                    }
                    break;
                case "todo": // when the task added is todo
                    if((inputs.length == 1)){
                        throw new DescriptionFormatException("Wrong format!, please use this format: "
                                + ToDos.getFormat()); //when the description format is wrong (e.g: no desc)
                    } else {
                        name = com.split("todo ");
                        if(name.length != 2){
                            throw new DescriptionFormatException("Wrong format!, please use this format: "
                                    + ToDos.getFormat());
                        }
                        String out = tL.addTask(new ToDos(name[1]));
                        ui.showMessage(out);
                        st.write(tL.getList());
                    }
                    break;
                case "deadline": // when the task added is deadline
                    if(inputs.length == 1){
                        throw new DescriptionFormatException("Wrong format!, please use this format: "
                                + Deadline.getFormat()); //when the description format is wrong (e.g: no desc)
                    } else {
                        name = com.split("deadline ");
                        String[] desBy = name[1].split(" /by ");
                        if(desBy.length != 2) {
                            throw new DescriptionFormatException("Wrong format!, please use this format: "
                                    + Deadline.getFormat());
                        }
                        LocalDateTime ldt = LocalDateTime.parse(desBy[1], dFormatInp);
                        String out = tL.addTask(new Deadline(desBy[0], ldt));
                        ui.showMessage(out);
                        st.write(tL.getList());
                    }
                    break;
            }
        } catch (DescriptionFormatException e){ // the description format exception handling
            ui.showMessage(e.getMessage());
        } catch (DateTimeParseException e){
            ui.showMessage("Please use the date format: yyyy-MM-dd HH:mm (e.g.: 2023-02-01 12:30)");
        } catch (IOException e){
            ui.showMessage("Save failed");
        }
    }

    public String getCom(){
        return com;
    }
}
