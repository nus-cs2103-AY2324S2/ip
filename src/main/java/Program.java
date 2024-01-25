import java.util.Scanner;

public class Program {

    private final String NAME = "Linus";
    private Boolean running;
    private Scanner userInputScanner;
    private TaskList taskList;
    private PrintList printList;

    public Program() {
        this.running = true;
        this.userInputScanner = new Scanner(System.in);
        this.taskList = new TaskList();
        this.printList = new PrintList();

    }

    public void start() {
        this.greeting();
        while (this.running) {
            String userInput = this.userInputScanner.nextLine();
            this.readUserInput(userInput);
        }
    }

    private void readUserInput(String input) {
        String[] userInput = input.split(" ", 2);
        String command = userInput[0].toLowerCase();
        String taskNumber;

        try {
            switch (command) {
                case "bye":
                    this.end();
                    break;
                case "list":
                    this.taskList.getList(this.printList);
                    break;
                case "mark": case "unmark": case "delete":
                    taskNumber = userInput[1];
                    this.taskList.markOrDelete(command, taskNumber, this.printList);
                    break;
                case "todo": case "deadline": case "event":
                    String task = userInput[1];
                    this.taskList.addTask(command, task, this.printList);
                    break;
                default:
                    throw new DukeCeption("Sorry I don't recognize that command :/");
            }
        } catch (DukeCeption e) {
            printList.add(e.getMessage());
        } catch (Exception e) {
            printList.add(e.getMessage());
        } finally {
            this.printList.print();
        }

    }

    private void greeting() {
        String greeting = String.format("Hello I'm %s", this.NAME);
        String request = "What can I do for you?";
        this.printList.add(greeting);
        this.printList.add(request);
        this.printList.print();
    }

    private void end() {
        String exit = "Goodbye. See you later!";
        this.printList.add(exit);
        this.running = false;
    }
}
