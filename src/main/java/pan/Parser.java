package pan;

import pan.enums.Commands;
import pan.enums.TaskStatus;
import pan.exceptions.InternalTestCases;

/**
 * Parser - Encapsulates logic with I/O operations in the CLI
 * @author Jerome Goh
 */
public class Parser {
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a Parser instance.
     *
     * @param ui Ui instance that parser depends on.
     * @param taskList TaskList instance responsible for CRUD operations with chatbot's Tasks.
     */
    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Represents the driver method to parse and validate user logic.
     *
     * @param instruction The input instruction from the user.
     */
    public void parseInput(String instruction) {
        ui.hello();
        try {
            if (instruction.equals("list")) {
                taskList.list();
            } else if (instruction.equals(Commands.BYE.name().toLowerCase())) {
                ui.bye();
            } else if (instruction.matches("(mark) \\d+")) {
                String index = instruction.substring(4).trim();
                taskList.mark(Integer.parseInt(index));
            } else if (instruction.matches("(unmark) \\d+")) {
                String index = instruction.substring(6).trim();
                taskList.unmark(Integer.parseInt(index));
            } else if (instruction.matches("(todo)\\s(.+)")) {
                String desc = instruction.substring(4).trim();
                ToDos todos = new ToDos(desc, TaskStatus.INCOMPLETE);
                taskList.add(todos);
            } else if (instruction.matches("(deadline)\\s(.+)\\s(/by)\\s(.+)")) {
                String postfix = instruction.substring(8).trim();
                String desc = postfix.split("/by")[0].trim();
                String byDate = postfix.split("/by")[1].trim();
                Deadlines deadlines = new Deadlines(desc, TaskStatus.INCOMPLETE, taskList.convertDate(byDate));
                taskList.add(deadlines);
            } else if (instruction.matches("(event)\\s(.+)\\s(/from)\\s(.+)\\s(/to)\\s(.+)")) {
                String postfix = instruction.substring(5).trim();
                String desc = postfix.split("/from")[0].trim();
                String from = postfix.split("/from")[1].split("/to")[0].trim();
                String to = postfix.split("/from")[1].split("/to")[1].trim();
                Events events = new Events(desc, TaskStatus.INCOMPLETE,
                    taskList.convertDate(from), taskList.convertDate(to));
                taskList.add(events);
            } else if (instruction.matches("(delete) \\d+")) {
                String index = instruction.substring(6).trim();
                taskList.delete(Integer.parseInt(index));
            } else if (instruction.matches("\\bfind\\s(.+)")) {
                String searchKeyword = instruction.substring(4).trim();
                taskList.find(searchKeyword);
            } else {
                // catch other test cases
                InternalTestCases.testMissingParameters(instruction);
                InternalTestCases.testInvalidCommand(instruction);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
