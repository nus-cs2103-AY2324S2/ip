package kaiyap;

import exceptions.AlreadyExistsException;
import exceptions.InvalidInputException;
import exceptions.KaiYapException;
import exceptions.MissingInputException;
import java.util.Scanner;

public class KaiYap {

    Ui ui;
    Storage storage;
    TaskList taskList;
    Parser parser;
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    public KaiYap() {
        this.ui = new Ui();
        this.taskList = new TaskList(ui);
        this.ui.setTaskList(this.taskList);
        this.storage = new Storage(ui, taskList, System.getProperty("user.home") + "/data/", "kaiyap.txt");
        this.parser = new Parser();
    }

    public void startChat() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().strip();
        while (!input.equals("bye")) {
            String action = parser.decideAction(input);
            switch (action) {
                case "listInputs":
                    ui.listInputs();
                    break;
                case "mark":
                    try {
                        this.markAsDone(input);
                    } catch (KaiYapException e) {
                        ui.printError(e.getMessage());
                    }
                    break;
                case "unmark":
                    try {
                        this.unmarkDone(input);
                    } catch (KaiYapException e) {
                        ui.printError(e.getMessage());
                    }
                    break;
                case "delete":
                    try {
                        this.deleteTask(input);
                    } catch (KaiYapException e) {
                        ui.printError(e.getMessage());
                    }
                    break;
                default:
                    Task task = taskList.taskCreator(input);
                    if (task != null) {
                        this.taskList.add(task);
                        this.echo(task);
                        storage.saveData();
                    }
            }
            input = sc.nextLine().strip();
        }
    }

    void deleteTask(String input) throws KaiYapException {
        if (input.length() <= 7) {
            throw new MissingInputException("\tSorry, it seems like there is some missing input. Please try again! UwU :3");
        }
        int numericIndex = Integer.parseInt(input.substring(7).strip()) - 1;
        if (numericIndex >= this.taskList.size()) {
            throw new InvalidInputException("\tSorry, this task does not exist. Please try again! UwU :3");
        } else {
            ui.printTaskRemoved(taskList.get(numericIndex), taskList.size() - 1);
            taskList.remove(numericIndex);
        }
    }

    public void echo(Task task) {
        System.out.println("\t____________________________________________________________\n" +
                "\tGot it. I've added this task:\n\t\t" + task.toString() +
                "\n\tYou now have " + this.taskList.size() + (this.taskList.size() == 1 ? " task" : " tasks") + " in the list.\n" +
                "\t____________________________________________________________\n");
    }

    public void markAsDone(String index) throws KaiYapException {
        if (index.length() <= 5) {
            throw new MissingInputException("\tSorry, it seems like there is some missing input. Please try again! UwU :3");
        }
        int numericIndex = Integer.parseInt(index.substring(5).strip()) - 1;
        if (numericIndex >= this.taskList.size()) {
            throw new InvalidInputException("\tSorry, this task does not exist. Please try again! UwU :3");
        } else if (taskList.get(numericIndex).isTaskDone()) {
            throw new AlreadyExistsException("\tThis task has already been marked as done. Great job!");
        } else {
            taskList.get(numericIndex).setTaskDone(true);
            System.out.println(
                    "\t____________________________________________________________\n" +
                            "\tNice! I've marked this task as done:\n" +
                            "\t\t" + taskList.get(numericIndex).toString() +
                            "\n\t____________________________________________________________"
            );
            storage.saveData();
        }
    }

    public void unmarkDone(String index) throws KaiYapException {
        if (index.length() <= 7) {
            throw new MissingInputException("\tSorry, it seems like there is some missing input. Please try again! UwU :3");
        }
        int numericIndex = Integer.parseInt(index.substring(7).strip()) - 1;
        if (numericIndex >= taskList.size()) {
            throw new InvalidInputException("\tSorry, this task does not exist. Please try again! UwU :3");
        } else if (!taskList.get(numericIndex).isTaskDone()) {
            throw new AlreadyExistsException("\tThis task has already been marked as undone. Good luck!");
        } else {
            taskList.get(numericIndex).setTaskDone(false);
            System.out.println(
                    "\t____________________________________________________________\n" +
                            "\tOK, I've marked this task as not done yet:\n" +
                            "\t\t" + taskList.get(numericIndex).toString() +
                            "\n\t____________________________________________________________"
            );
            storage.saveData();
        }
    }

    public static void main(String[] args) {
        KaiYap yap = new KaiYap();
        yap.ui.sayHello();
        yap.storage.loadData();
        yap.startChat();
        yap.ui.sayBye();
    }
}
