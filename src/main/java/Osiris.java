import java.util.ArrayList;
import java.util.Scanner;

public class Osiris {

    public static final String NAME = "Osiris";

    private final TaskManager userTasks = new TaskManager();

    public void startChat(){
        Scanner scanner = new Scanner(System.in);
        boolean terminateChat = false;

        this.printSeparator();
        this.outputIntroductions();
        this.printSeparator();

        while (!terminateChat){

            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                terminateChat = true;
            } else if (userInput.equals("list")) {
                this.printUserTasks();
            } else if (userInput.startsWith("mark")) {
                this.markTaskCompleted(Character.getNumericValue(userInput.charAt(5)));
            } else if (userInput.startsWith("unmark")) {
                this.markTaskIncomplete(Character.getNumericValue(userInput.charAt(7)));
            } else {
                this.addUserTask(userInput);
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
        this.userTasks.addUserTask(userInput);

        this.printSeparator();
        System.out.println("     Added Task: " + userInput);
        this.printSeparator();
    }

    private void markTaskCompleted(int index){
        this.userTasks.markTaskCompleted(index - 1);

        this.printSeparator();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("        " + this.userTasks.getTask(index - 1).toString());
        this.printSeparator();
    }

    private void markTaskIncomplete(int index) {
        this.userTasks.markTaskIncomplete(index - 1);

        this.printSeparator();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("        " + this.userTasks.getTask(index - 1).toString());
        this.printSeparator();
    }
    private void printUserTasks(){
        ArrayList<Task> toPrint = this.userTasks.getUserTasks();

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
