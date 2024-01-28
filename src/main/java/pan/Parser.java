import java.util.Scanner;
import enums.Commands;
import enums.TaskStatus;
import exceptions.InternalTestCases;
import exceptions.TaskIndexException;

class Parser { 
    private Ui ui;
    private Scanner scanner;
    private TaskList taskList;

    public Parser(Ui ui, Scanner scanner, TaskList taskList) {
        this.ui = ui;
        this.scanner = scanner;
        this.taskList = taskList;
    }

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
                    if (Integer.parseInt(index) > taskList.getTasks().size()) {
                        throw new TaskIndexException("You have entered an invalid index!");
                    }
                    taskList.mark(Integer.parseInt(index));
                    continue;
                } else if (instruction.matches("(unmark) \\d+")) {
                    String index = instruction.substring(6).trim();
                    if (Integer.parseInt(index) > taskList.getTasks().size()) {
                        throw new TaskIndexException("You have entered an invalid index!");
                    }
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
                    if (Integer.parseInt(index) > taskList.getTasks().size()) {
                        throw new TaskIndexException("You have entered an invalid index!");
                    }
                    taskList.delete(Integer.parseInt(index));
                    continue;
                } else {
                    // catch other test cases
                    InternalTestCases.TestMissingParameters(instruction);
                    InternalTestCases.TestInvalidCommand(instruction);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        scanner.close();
    }
}