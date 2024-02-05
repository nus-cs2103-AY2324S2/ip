package duke.gui;

import duke.exception.HALException;
import duke.task.TaskList;

import java.util.Objects;
import java.util.Scanner;


public class Ui {
    static String nameLogo =
            "__/\\\\\\________/\\\\\\_____/\\\\\\\\\\\\\\\\\\_____/\\\\\\____________________________/\\\\\\\\\\\\\\\\\\________/\\\\\\\\\\\\\\________/\\\\\\\\\\\\\\________/\\\\\\\\\\\\\\____        \n" +
            " _\\/\\\\\\_______\\/\\\\\\___/\\\\\\\\\\\\\\\\\\\\\\\\\\__\\/\\\\\\__________________________/\\\\\\///////\\\\\\____/\\\\\\/////\\\\\\____/\\\\\\/////\\\\\\____/\\\\\\/////\\\\\\__       \n" +
            "  _\\/\\\\\\_______\\/\\\\\\__/\\\\\\/////////\\\\\\_\\/\\\\\\_________________________/\\\\\\______\\//\\\\\\__/\\\\\\____\\//\\\\\\__/\\\\\\____\\//\\\\\\__/\\\\\\____\\//\\\\\\_      \n" +
            "   _\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\_\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\________________________\\//\\\\\\_____/\\\\\\\\\\_\\/\\\\\\_____\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_     \n" +
            "    _\\/\\\\\\/////////\\\\\\_\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\_\\/\\\\\\_________________________\\///\\\\\\\\\\\\\\\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_    \n" +
            "     _\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\/////////\\\\\\_\\/\\\\\\___________________________\\////////\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_\\/\\\\\\_____\\/\\\\\\_   \n" +
            "      _\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_________________________/\\\\________/\\\\\\__\\//\\\\\\____/\\\\\\__\\//\\\\\\____/\\\\\\__\\//\\\\\\____/\\\\\\__  \n" +
            "       _\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\_______\\/\\\\\\_\\/\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\____________\\//\\\\\\\\\\\\\\\\\\\\\\/____\\///\\\\\\\\\\\\\\/____\\///\\\\\\\\\\\\\\/____\\///\\\\\\\\\\\\\\/___ \n" +
            "        _\\///________\\///__\\///________\\///__\\///////////////______________\\///////////________\\///////________\\///////________\\///////_____\n";

    static String divider = "______________________________________________________________________________";
    TaskList taskList;
    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }
    public void start() {
        // greet user
        System.out.println(divider);
        System.out.println("Good afternoon, gentlemen. I am\n" + nameLogo);
        System.out.println("How may I assist you?");
        System.out.println(divider);

        //take in input
        Scanner scanner = new Scanner(System.in);
        while (true) {

            String userInput = scanner.nextLine();
            String[] userInputArray = userInput.split(" ");

            System.out.println(divider);
            if (userInput.equalsIgnoreCase("bye")) {
                break;

            } else if (userInput.equalsIgnoreCase("list")) {
                taskList.listTasks();

            } else if (userInputArray[0].equalsIgnoreCase("mark")) {
                String taskString = taskList.markAsDone(Integer.parseInt(userInputArray[1]) - 1);
                System.out.println("Nice! I've marked this task as done:\n" + taskString);

            } else if (userInputArray[0].equalsIgnoreCase("unmark")) {
                String taskString = taskList.markAsUndone(Integer.parseInt(userInputArray[1]) - 1);
                System.out.println("OK, I've marked this task as not done yet:\n" + taskString);

            } else if (userInputArray[0].equalsIgnoreCase("todo") ||
                       userInputArray[0].equalsIgnoreCase("deadline") ||
                       userInputArray[0].equalsIgnoreCase("event")) {

                String taskString = taskList.addTask(userInput);

                // If there is no error, display the messages that task has been added
                if (!Objects.equals(taskString, "error")) {
                    System.out.println("Roger. I have added this task.");
                    System.out.println(taskString);
                    System.out.printf("Now you have %d tasks in the list.", taskList.getNumberOfTasks());
                }

            } else if (userInputArray[0].equalsIgnoreCase("delete")) {
                String taskString = taskList.removeTask(Integer.parseInt(userInputArray[1]) - 1);

                System.out.println("Roger. I have removed this task.");
                System.out.println(taskString);
                System.out.printf("Now you have %d tasks in the list.", taskList.getNumberOfTasks());
            } else {
                // Unknown keyword error
                try {
                    throw new HALException("Unknown Keyword!");
                } catch (HALException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("\n" + divider);

        }

        System.out.println(divider);
        System.out.println("This mission is too important for me to allow you to jeopardize it. Goodbye.");
        System.out.println(divider);

    }
}
