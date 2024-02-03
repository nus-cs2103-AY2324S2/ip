package anita;
public class Ui {

    public void greet() {
        String logo = "$$\\      $$\\  $$$$$$\\  $$\\   $$\\       $$\\      $$\\ $$$$$$\\ $$\\   $$\\ \n"
                + "$$$\\    $$$ |$$  __$$\\ $$ |  $$ |      $$ | $\\  $$ |\\_$$  _|$$$\\  $$ | \n"
                + "$$$$\\  $$$$ |$$ /  $$ |\\$$\\ $$  |      $$ |$$$\\ $$ |  $$ |  $$$$\\ $$ | \n"
                + "$$\\$$\\$$ $$ |$$$$$$$$ | \\$$$$  /       $$ $$ $$\\$$ |  $$ |  $$ $$\\$$ | \n"
                + "$$ \\$$$  $$ |$$  __$$ | $$  $$<        $$$$  _$$$$ |  $$ |  $$ \\$$$$ | \n"
                + "$$ |\\$  /$$ |$$ |  $$ |$$  /\\$$\\       $$$  / \\$$$ |  $$ |  $$ |\\$$$ | \n"
                + "$$ | \\_/ $$ |$$ |  $$ |$$ /  $$ |      $$  /   \\$$ |$$$$$$\\ $$ | \\$$ | \n"
                + "\\__|     \\__|\\__|  \\__|\\__|  \\__|      \\__/     \\__|\\______|\\__|  \\__| \n";
        System.out.println("Hello from\n" + logo);
        line();
        System.out.println("Hello! I'm Anita MaxWynn");
        System.out.println("What can I do for you?");
        line();
    }
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void line() {
        System.out.println("____________________________________________________________");
    }

    public void addTask(Task task) {
        System.out.println("Yer task has been added: \n  " + task);
    }

    public void setDoneMessage(Task task) {
        System.out.println("Cha Ching! Task Completed.");
        System.out.println(task);
    }

    public void setNotDoneMessage(Task task) {
        System.out.println("$$$ Task Incomplete :(");
        System.out.println(task);
    }

    public void removeTaskMessage(Task task) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + task);
    }

    public void listTaskMessage(int index, Task task) {
        System.out.println(index + ". " + task);
    }

    public void getNumberOfTasksMessage(int size) {
        System.out.println("Now you have " + size + " task(s) in the list.");
    }
}
