import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String LOGO = "      ___          ___                     ___          ___          ___     \n"+
            "     /\\  \\        /\\  \\         ___       /\\__\\        /\\  \\        /\\__\\    \n"+
            "    /::\\  \\      /::\\  \\       /\\  \\     /::|  |      /::\\  \\      /::|  |   \n"+
            "   /:/\\:\\  \\    /:/\\:\\  \\      \\:\\  \\   /:|:|  |     /:/\\:\\  \\    /:|:|  |   \n"+
            "  /::\\~\\:\\  \\  /::\\~\\:\\  \\     /::\\__\\ /:/|:|__|__  /:/  \\:\\  \\  /:/|:|  |__ \n"+
            " /:/\\:\\ \\:\\__\\/:/\\:\\ \\:\\__\\ __/:/\\/__//:/ |::::\\__\\/:/__/ \\:\\__\\/:/ |:| /\\__\\\n"+
            " \\/__\\:\\/:/  /\\/__\\:\\/:/  //\\/:/  /   \\/__/~~/:/  /\\:\\  \\ /:/  /\\/__|:|/:/  /\n"+
            "      \\::/  /      \\::/  / \\::/__/          /:/  /  \\:\\  /:/  /     |:/:/  / \n"+
            "       \\/__/       /:/  /   \\:\\__\\         /:/  /    \\:\\/:/  /      |::/  /  \n"+
            "                  /:/  /     \\/__/        /:/  /      \\::/  /       /:/  /   \n"+
            "                  \\/__/                   \\/__/        \\/__/        \\/__/    \n";
    private static final List<String> GREETINGS = List.of(
            "Ah, there you are! Hello! Paimon wondered where you were! This is going to be so much fun, right?",
            "Ahoy there! It's great to see you! Paimon's hungry!",
            "Ah, Paimon missed you! It's been so long...",
            "Ad astra abyssosque, welcome to Paimon's house!",
            "Good morning, Traveler. Ah... what's it like out today? Paimon wants to hear your story."
    );

    private static final List<String> FAREWELLS = List.of(
            "Farewell, it was fun to meet you! Take care, see you later, and may you find many new treasures",
            "Farewell, until we meet again!",
            "Safe travels, and take care!",
            "Good luck! And don't spend all your Mora in one place.",
            "Adios!"
    );
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    private static final String HORIZONTAL_LINE =
            "___________________________________________________________________________";
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println(GREETINGS.get(1));
    }

    public void showFarewell() {
        this.scanner.close();
        System.out.println(FAREWELLS.get(1));
    }

    public void showTaskList(TaskList taskList) {
        this.showTaskListStatus(taskList);
        int count = 1;
        for (Task task : taskList) {
            System.out.printf("%d. %s\n", count, task);
            count++;
        }
    }

    public void showAddedTask(Task task) {
        System.out.println("Got it. I've added this task: \n" + task.toString());
    }

    public void showTaskListStatus(TaskList taskList) {
        System.out.printf("Now you have %d tasks in the list.%n", taskList.size());
    }

    public void showMarkedTask(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" + task.toString());
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n" + task.toString());
    }

    public void showDeletedTask(Task task) {
        System.out.println("Noted. I've removed this task: \n" + task.toString());
    }
}
