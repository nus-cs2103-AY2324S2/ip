package duke;

import java.util.Scanner;
/**
 *  This class handles the logic and ui for the chatbot.
 */
public class Ui {
    /**
     * Introduces our chatbot and prompts the user to start asking questions
     * @param name is the name given to our chatbot
     * */
    public void introduction(String name) {
        System.out.println("--------------------------------------------------");
        System.out.println("What's up. I'm " + name + ".");
        System.out.println("I'm about to blow your world.");
        System.out.println("--------------------------------------------------");
    }
    /**
     * Introduces our chatbot and prompts the user to start asking questions
     * */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-------------------------");
    }

    /**
     * This method handles the logic for chatting with the user
     * @param taskList the task list that stores all the tasks created by the user
     */
    public void chat(TaskList taskList) throws RiriException {
        boolean isOn = true;
        Scanner sc = new Scanner(System.in);

        while (isOn) {
            String command = sc.nextLine();
            if (command.matches("bye")) {
                isOn = false;
                break;
            } else if (command.matches("list")) {
                taskList.returnList();
                continue;
            } else if (command.matches("\\bmark\\b.*")) {
                String[] words = command.split("\\s+");
                taskList.mark(Integer.parseInt(words[1]));
                continue;
            } else if (command.matches("\\bunmark\\b.*")) {
                String[] words = command.split("\\s+");
                taskList.unmark(Integer.parseInt(words[1]));
                continue;
            } else if (command.matches("\\bdeadline\\b.*")) {
                String[] words = command.split("/by");
                String date = words[1].trim();
                taskList.addTask(new Deadline(words[0].substring(8).trim(), date));
                System.out.println("Added deadline.");
                continue;
            } else if (command.matches("\\bevent\\b.*")) {
                String[] words = command.split("/from+");
                String[] from = words[1].split("/to");
                String date1 = from[0].trim();
                String date2 = from[1].trim();

                taskList.addTask(new Event(words[0].substring(5).trim(), date1, date2));
                System.out.println("Added event.");
            } else if (command.matches("\\btodo\\b.*")) {
                String[] words = command.split("todo");
                if (words[1] == "") {
                    throw new RiriException("You are adding nothing to your list");
                }
                taskList.addTask(new Todo(words[1].trim()));
                System.out.println("Added todo.");
                continue;
            } else if (command.matches("\\bdelete\\b.*")) {
                String[] words = command.split("\\s+");
                taskList.delete(Integer.parseInt(words[1].trim()));
            } else if (command.trim().isEmpty()) {
                continue;
            } else {
                throw new RiriException("Unable to process or understand command.");
            }
        }
    }
}
