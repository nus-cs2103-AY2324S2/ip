import java.util.Scanner;

public class Shirmin {
    public static void displayLine() {
        System.out.println(" ".repeat(4) + "_".repeat(50));
    }
    public static String gap() {
        return "    ";
    }
    public static void wrapInLines(String line){
        displayLine();
        System.out.println(line);
        displayLine();
    }
    public static void echo(String line) {
        wrapInLines(line);
    }
    public static void greet() {
        wrapInLines(gap() + "Hello! I'm Shirmin" + "\n"
                + gap() + "What can I do for you?");
    }
    public static void exit() {
        wrapInLines(gap() +"Bye. Hope to see you again soon!");
    }


    static Task[] taskList = new Task[100];
    static int currIndex = 0;
    public static void listFunction(String line){
        String[] command =  line.split(" ", 2);
        if (line.equals("list")) {
            displayList(taskList);
        } else if (command[0].equals("mark")) {
        try {
            int taskIndex = Integer.parseInt(command[1]) - 1;
            if (taskIndex < currIndex) {
                taskList[taskIndex].markDone();
                displayLine();
                System.out.println(gap() + "Nice! I've marked this task as done:");
                System.out.println(gap() + gap() + taskList[taskIndex]);
                displayLine();

            } else { // out of range
                System.out.println("invalid, out of range");
            }

        } catch (NumberFormatException e) { // decided to handle as exception e.g. a task named "mark papers" is not valid
            System.out.println("Invalid task number: " + command[1]);
        }

        } else if(command[0].equals("unmark")) {
            try {
                int taskIndex = Integer.parseInt(command[1]) - 1;
                if (taskIndex < currIndex) {
                    taskList[taskIndex].markUndone();
                    displayLine();
                    System.out.println(gap() + "OK, I've marked this task as not done yet:");
                    System.out.println(gap() + gap() + taskList[taskIndex]);
                    displayLine();
                } else { // out of range
                    System.out.println("invalid, out of range");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number: " + command[1]);
            }
        } else if (command[0].equals("todo")) {
            Task newTodo = new Todo(command[1]);
            taskList[currIndex] = newTodo;
            currIndex++;
            addMessage(newTodo, currIndex);
        }



        else {
            wrapInLines(gap() +"added: " + line);
            taskList[currIndex] = new Task(line);
            currIndex++;
        }
    }
    public static <T extends Task> void addMessage(T task, Integer number){
        displayLine();
        System.out.println(gap() + "Got it. I've added this task:");
        System.out.println(gap() + task.toString());
        System.out.println(gap() + "Now you have " + number.toString() + " tasks in the list.");
        displayLine();
    }
    public static void displayList(Task[] list) {
        displayLine();
        int i = 1;
        for (Task t: list) {
            if (t != null) {
                System.out.println(gap() + i + "." + t.toString());
                i++;
            }
        }
        displayLine();
    }


    public static void main(String[] args){
        greet();
        Scanner scanner = new Scanner(System.in);
        String currLine = scanner.nextLine();
        while(!currLine.equals("bye")) {
            listFunction(currLine);
            currLine = scanner.nextLine();
        }
        exit();
    }
}
