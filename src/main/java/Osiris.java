import java.util.ArrayList;
import java.util.Scanner;

public class Osiris {

    public static final String NAME = "Osiris";

    private final TaskManager taskManager = new TaskManager();

    public void startChat(){
        Scanner scanner = new Scanner(System.in);
        boolean terminateChat = false;

        this.printSeparator();
        this.outputIntroductions();
        this.printSeparator();

        while (!terminateChat){

            String userInput = scanner.nextLine();

            String[] inputtedWords = userInput.split(" ");

            switch (inputtedWords[0]) {
                case "bye":
                    terminateChat = true;
                    break;
                case "list":
                    this.printUserTasks();
                    break;
                case "mark":
                    try {
                        this.markTaskCompleted(Integer.parseInt(inputtedWords[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer format: " + inputtedWords[1]);
                        System.out.println("Please Reenter");
                    }
                    break;
                case "unmark":
                    try {
                        this.markTaskIncomplete(Integer.parseInt(inputtedWords[1]));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid integer format: " + inputtedWords[1]);
                        System.out.println("Please Reenter");
                    }
                    break;
                default:
                    this.addUserTask(userInput);
                    break;
            }

        }

        this.printSeparator();
        this.outputGoodbyes();
        this.printSeparator();
    }

    public void outputIntroductions() {
        String introductions = String.format("Hello! I'm %s. \nWhat can I do for you?", Osiris.NAME);
        System.out.println(introductions);
    }

    public void outputGoodbyes() {
        String goodbyes = "Bye. Hope to see you again soon!";
        System.out.println(goodbyes);
    }

    private void echoUserInput(String input) {
        this.printSeparator();
        System.out.println("     " + input);
        this.printSeparator();
    }

    private void addUserTask(String userInput) {
        this.taskManager.addUserTask(userInput);

        this.printSeparator();
        System.out.println("     Added Task: " + userInput);
        this.printSeparator();
    }

    private void markTaskCompleted(int index){
        this.taskManager.markTaskCompleted(index - 1);

        this.printSeparator();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("        " + this.taskManager.getTask(index - 1).toString());
        this.printSeparator();
    }

    private void markTaskIncomplete(int index) {
        this.taskManager.markTaskIncomplete(index - 1);

        this.printSeparator();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("        " + this.taskManager.getTask(index - 1).toString());
        this.printSeparator();
    }
    private void printUserTasks(){
        ArrayList<Task> toPrint = this.taskManager.getUserTasks();

        this.printSeparator();
        for (int i = 0; i < toPrint.size(); i++) {
            System.out.println("     " + (i + 1) + ". " + toPrint.get(i).toString());
        }
        this.printSeparator();
    }

    private void printSeparator() {
        System.out.println("----------------------------------------");
    }

}
