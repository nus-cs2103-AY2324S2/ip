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
    public void introduce(String name) {
        System.out.println("--------------------------------------------------");
        System.out.println("What's up. I'm " + name + ".");
        System.out.println("I am here to help you create and manage your task list :)");
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
            } else if (command.toLowerCase().matches("list")) {
                // Print list
                taskList.returnList();
                continue;
            } else if (command.toLowerCase().matches("\\bmark\\b.*")) {
                // Mark task as done
                String[] words = command.split("\\s+");
                taskList.mark(Integer.parseInt(words[1]));
                continue;
            } else if (command.toLowerCase().matches("\\bunmark\\b.*")) {
                // Mark the task as undone
                String[] words = command.split("\\s+");
                taskList.unmark(Integer.parseInt(words[1]));
                continue;
            } else if (command.toLowerCase().matches("\\bdeadline\\b.*")) {
                // Add deadline task to task list
                String[] words = command.split("/by");
                String date = words[1].trim();
                taskList.addTask(new Deadline(words[0].substring(8).trim(), date));
                System.out.println("Added deadline.");
                continue;
            } else if (command.toLowerCase().matches("\\bevent\\b.*")) {
                // Add event task to task list
                String[] words = command.split("/from+");
                String[] from = words[1].split("/to");
                String date1 = from[0].trim();
                String date2 = from[1].trim();

                taskList.addTask(new Event(words[0].substring(5).trim(), date1, date2));
                System.out.println("Added event.");
            } else if (command.toLowerCase().matches("\\btodo\\b.*")) {
                // Add todo task to task list
                String[] words = command.split("todo");
                if (words[1].equals("")) {
                    throw new RiriException("You are adding nothing to your list");
                }
                taskList.addTask(new Todo(words[1].trim()));
                System.out.println("Added todo.");
                continue;
            } else if (command.toLowerCase().matches("\\bdelete\\b.*")) {
                // Delete tasks in tasks list
                String[] words = command.split("\\s+");
                taskList.delete(Integer.parseInt(words[1].trim()));
            } else if (command.toLowerCase().matches("\\bfind\\b.*")) {
                // Implement the find function through keyword
                String keyword = command.substring(4);
                System.out.println(taskList.searchTasks(keyword));
            } else if (command.trim().isEmpty()) {
                // If by mistake user presses return or space, nothing will happend
                continue;
            } else {
                throw new RiriException("Unable to process or understand command.");
            }
        }
    }
}
