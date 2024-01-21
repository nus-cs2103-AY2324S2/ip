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
            String userInput = this.userInputScanner.next();
            this.readUserInput(userInput);
        }
    }

    private void readUserInput(String input) {
        String receiveInput;
        if (input.toLowerCase().equals("bye")) {
            this.end();
        } else if (input.toLowerCase().equals("list")) {
            this.taskList.getList(this.printList);
            this.printList.print();
        } else if (input.toLowerCase().equals("mark")) {
            int taskNumber = this.userInputScanner.nextInt();
            this.taskList.mark(taskNumber, this.printList);
            this.printList.print();
        } else if (input.toLowerCase().equals("unmark")) {
            int taskNumber = this.userInputScanner.nextInt();
            this.taskList.unmark(taskNumber, this.printList);
            this.printList.print();
        } else {
            input += this.userInputScanner.nextLine();
            receiveInput = this.taskList.addTask(input);
            this.textFormat(receiveInput);
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
        this.printList.print();
        this.running = false;
    }

    private void textFormat(String text) {
        this.printList.add(text);
        this.printList.print();
    }
    
}
