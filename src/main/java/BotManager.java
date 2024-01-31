import java.util.ArrayList;

public class BotManager {
    private final String name;
    private ArrayList<String> tasks;

    BotManager(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    void greeting() {
        System.out.println("    Hello! I'm " + name);
        System.out.println("    What can I do for you?\n");
    }

    void exit() {
        System.out.println("    Bye. Hope to see you again soon!\n");
    }

    void answer(String prompt) {
        if (prompt.equals("list")) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + tasks.get(i));
            }
            System.out.println();
        } else {
            tasks.add(prompt);
            System.out.println("    added " + prompt + "\n");
        }
    }
}
