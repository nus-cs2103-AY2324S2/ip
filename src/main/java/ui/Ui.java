package ui;

public class Ui {
    public void showLine() {
        System.out.println("________________________________________");
    }

    public void greet() {
        System.out.println("Hello! I'm Wei.\n" + "What can I do for you?");
        showLine();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showList(String list) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(list);
    }

    public void showAddMessage(String taskName) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskName);
    }

    public void showDeleteMessage(String taskName) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskName);
    }

    public void showMarkedMessage(String taskName) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskName);
    }

    public void showUnmarkedMessage(String taskName) {
        System.out.println("Noted! I've unmarked this task:");
        System.out.println(taskName);
    }

        public void showNumberOfRemainingTasks(int sizeOfTaskList) {
        System.out.println("Now you have " + sizeOfTaskList + " tasks in the list.");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

}
