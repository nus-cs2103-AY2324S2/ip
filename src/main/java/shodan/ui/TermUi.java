package shodan.ui;

import shodan.ShodanException;
import shodan.tasks.Task;

import java.util.List;

public class TermUi {
    public void showGreeting() {
        String logo = "   _____ __  ______  ____  ___    _   __  \n"
                + "  / ___// / / / __ \\/ __ \\/   |  / | / /\n"
                + "  \\__ \\/ /_/ / / / / / / / /| | /  |/ / \n"
                + " ___/ / __  / /_/ / /_/ / ___ |/ /|  /    \n"
                + "/____/_/ /_/\\____/_____/_/  |_/_/ |_/    \n";
        System.out.println(logo);
        System.out.println("Greetings, human.");
        System.out.println("What can I do for you?");
    }
    public void showExitMsg() {
        System.out.println("Goodbye.");
    }

    public void showPrompt() {
        System.out.print("> ");
    }

    public void listTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You currently have no tasks.");
        }
        System.out.println("Here is your list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(tasks.get(i));
        }
    }
    public void printMsg(String s) {
        System.out.println(s);
    }

    public void printError(ShodanException e) {
        System.out.println(e.getMessage());
    }
}
