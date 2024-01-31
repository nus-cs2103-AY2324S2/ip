package pan;

import java.util.Scanner;
import pan.enums.Commands;
import pan.enums.TaskStatus;
import pan.exceptions.InternalTestCases;

class Parser { 
    private Ui ui;
    private Scanner scanner;
    private TaskList taskList;

    /**
     * Constructs a Parser instance.
     *
     * @param ui Ui instance that parser depends on.
     * @param scanner Scanner instance responsible to deal with I/O operations in CLI.
     * @param taskList TaskList instance responsible for CRUD operations with chatbot's Tasks.
     */
    public Parser(Ui ui, Scanner scanner, TaskList taskList) {
        this.ui = ui;
        this.scanner = scanner;
        this.taskList = taskList;
    }

    /**
     * Represents the driver method to parse and validate user logic.
     */
    public void parseInput() {
        ui.hello();
        while (true) {
            try {
                System.out.println("");
                String instruction = scanner.nextLine();
                System.out.println("");

                if (instruction.equals("list")) {
                    taskList.list();
                    continue;
                } else if (instruction.equals(Commands.BYE.name().toLowerCase())) {
                    ui.bye();
                    break;
                } else if (instruction.matches("(mark) \\d+")) {
                    String index = instruction.substring(4).trim();
                    taskList.mark(Integer.parseInt(index));
                    continue;
                } else if (instruction.matches("(unmark) \\d+")) {
                    String index = instruction.substring(6).trim();
                    taskList.unmark(Integer.parseInt(index));
                    continue;
                } else if (instruction.matches("(todo)\\s(.+)")) {
                    String desc = instruction.substring(4).trim();
                    ToDos todos = new ToDos(desc, TaskStatus.INCOMPLETE);
                    taskList.add(todos);
                    continue;
                } else if (instruction.matches("(deadline)\\s(.+)\\s(/by)\\s(.+)")) {
                    String postfix = instruction.substring(8).trim();
                    String desc = postfix.split("/by")[0].trim();
                    String byDate = postfix.split("/by")[1].trim();
                    Deadlines deadlines = new Deadlines(desc, TaskStatus.INCOMPLETE, taskList.convertDate(byDate));
                    taskList.add(deadlines);
                    continue;
                } else if (instruction.matches("(event)\\s(.+)\\s(/from)\\s(.+)\\s(/to)\\s(.+)")) {
                    String postfix = instruction.substring(5).trim();
                    String desc = postfix.split("/from")[0].trim();
                    String from = postfix.split("/from")[1].split("/to")[0].trim();
                    String to = postfix.split("/from")[1].split("/to")[1].trim();
                    Events events = new Events(desc, TaskStatus.INCOMPLETE, taskList.convertDate(from), taskList.convertDate(to));
                    taskList.add(events);
                    continue;
                } else if (instruction.matches("(delete) \\d+")) {
                    String index = instruction.substring(6).trim();
                    taskList.delete(Integer.parseInt(index));
                    continue;
                } else {
                    // catch other test cases
                    InternalTestCases.testMissingParameters(instruction);
                    InternalTestCases.testInvalidCommand(instruction);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        scanner.close();
    }
}