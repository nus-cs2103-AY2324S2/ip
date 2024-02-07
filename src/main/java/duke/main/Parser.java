package duke.main;
import duke.task.*;
import duke.exception.*;

import java.util.Date;

public class Parser {
    private TaskList taskList;
    private Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public boolean isExit(String input) {
        return input.equals("bye");
    }

    public void parse(String input) throws UnknownInputException {
        int taskEnd = input.indexOf(" ");
        String commandType = taskEnd > 0 ? input.substring(0, taskEnd) : "list";
        try {
            TaskType type = TaskType.valueOf(commandType);
            String details = input.substring(taskEnd + 1);

            switch (type) {
                case todo:
                    this.taskList.add(new ToDo(details));
                    this.ui.printOnAdd();
                    break;
                case deadline:
                    String[] d = details.split("/by ");
                    try {
                        this.taskList.add(new Deadline(d[0], d[1]));
                    } catch (DateFormatException DFE) {
                        this.ui.printException(DFE);
                    }
                    this.ui.printOnAdd();
                    break;
                case event:
                    String[] v1 = details.split("/from ");
                    String[] v2 = v1[1].split("/to ");
                    try {
                        this.taskList.add(new Event(v1[0], v2[0], v2[1]));
                    } catch (ArrayIndexOutOfBoundsException | DateFormatException formatException) {
                        this.ui.printException(formatException);
                    }
                    this.ui.printOnAdd();
                    break;
                case delete:
                    int deleteIndex = Integer.parseInt(details) - 1;
                    this.ui.printOnDelete(deleteIndex);
                    this.taskList.remove(deleteIndex);
                    this.ui.printTotal();
                    try {
                        this.ui.printOnDelete(deleteIndex);
                        this.taskList.remove(deleteIndex);
                        this.ui.printTotal();
                    } catch (ArrayIndexOutOfBoundsException arrIndexEx) {
                        this.ui.printException(arrIndexEx);
                    }
                    break;
                case mark:
                    int markIndex = Integer.parseInt(details) - 1;
                    try {
                        this.taskList.mark(markIndex);
                        this.ui.printOnMark(markIndex);
                    } catch (ArrayIndexOutOfBoundsException arrEx) {
                        this.ui.printException(arrEx);
                    }
                    break;
                case unmark:
                    int unmarkIndex = Integer.parseInt(details) - 1;
                    try {
                        this.taskList.unmark(unmarkIndex);
                        this.ui.printOnUnmark(unmarkIndex);
                    } catch (ArrayIndexOutOfBoundsException arrException) {
                        this.ui.printException(arrException);
                    }
                case find:
                    this.ui.printOnFind(taskList.find(details));
                    break;
                case list:
                    this.ui.printList(taskList);
                    break;
                default:
                    throw new UnknownInputException();
            }
        } catch (IllegalArgumentException e) {
            throw new UnknownInputException();
        }


    }

}


