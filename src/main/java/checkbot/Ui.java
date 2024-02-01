package checkbot;

import checkbot.task.TodoList;

public class Ui {
    public static final String INDENTATION = "  ";
    private static final String SEPARATOR = "____________________________________________________________";

    public void showWelcome() {
        print(
                "Hello, I'm Checkbot, your personal assistant.\n" +
                        "What tasks do you have to do?"
        );
    }

    public void showGoodbye() {
        print("Goodbye!");
    }

    public void showSubList(TodoList subList) {
        if (subList.getLength() == 0) {
            print("You have no tasks in your list matching your query!");
            return;
        }
        print(
                "Here are the matching tasks in your list:\n" +
                        subList
        );
    }

    public void print(String str) {
        System.out.println(INDENTATION + SEPARATOR);
        for (String chunk : str.split("\n")) {
            System.out.println(INDENTATION + chunk);
        }
        System.out.println(INDENTATION + SEPARATOR);
    }
}
