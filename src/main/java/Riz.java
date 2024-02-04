import java.util.*;

public class Riz {
    private TaskList taskList;
    private Storage storage;

    public Riz(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.loadFromFile());
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String dotted = "-----------------------------------";
        //greetings
        Ui.printGreetings();
        boolean running = true;

        while (running) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                running = false;
                Ui.printGoodbye();
            } else {
                Parser.parse(scanner, this.taskList, this.storage, input);
            }
            /*
            String[] token = input.split(" ", 2);
            if (token[0].equals("bye")) {
                running = false;
                Ui.printGoodbye();
            } else if (token[0].equals("clear")) {
                Ui.printClearConfirmation();
                if (scanner.nextLine().equals("y")) {
                    tasks.clear();
                    this.storage.writeToFile(tasks);
                    Ui.printAllCleared();
                }
            } else if (token[0].equals("mark")) {
                boolean isNumber = true;
                if (token.length != 2) {
                    Ui.markError();
                }
                for (char c : token[1].toCharArray()) {
                    if (!Character.isDigit(c)) {
                        isNumber = false;
                    }
                }
                if (!isNumber) {
                    Ui.markError();
                }
                int curr = Integer.parseInt(token[1]) - 1;
                Task task = tasks.get(curr);
                task.mark();
                this.storage.writeToFile(tasks);
                System.out.println("Awesome..., I've marked this task as completed...");
                System.out.println(task);
                System.out.println("\n");
            } else if (token[0].equals("unmark")) {
                boolean isNumber = true;
                if (token.length != 2) {
                    Ui.unmarkError();
                }
                for (char c : token[1].toCharArray()) {
                    if (!Character.isDigit(c)) {
                        isNumber = false;
                    }
                }
                if (!isNumber) {
                    Ui.unmarkError();
                }
                int curr = Integer.parseInt(token[1]) - 1;
                Task task = tasks.get(curr);
                task.unmark();
                this.storage.writeToFile(tasks);
                System.out.println("Oops... Guess it's not done yet...");
                System.out.println(task);
                System.out.println("\n");
            } else if (token[0].equals("list")) {
                int size = tasks.size();
                System.out.println("Here are the items in your To-Do List...");
                this.storage.printFromFile();
                System.out.println("\n");
            } else if (token[0].equals("delete")) {
                boolean isNumber = true;
                if (token.length != 2) {
                    Ui.deleteError();
                }
                for (char c : token[1].toCharArray()) {
                    if (!Character.isDigit(c)) {
                        isNumber = false;
                    }
                }
                if (!isNumber) {
                    Ui.deleteError();
                }
                int curr = Integer.parseInt(token[1]) - 1;
                Task task = tasks.get(curr);
                tasks.remove(curr);
                this.storage.writeToFile(tasks);
                System.out.println("Boo... planning to slack off?");
                System.out.println("Removed: " + task + "...");
                System.out.println("\n");
            } else {
                if (token[0].equals("todo")) {
                    if (token.length != 2) {
                        Ui.todoError();
                    } else {
                        Task task = new ToDo(token[1]);
                        tasks.add(task);
                        this.storage.writeToFile(tasks);
                        System.out.println("added: " + task + "...");
                    }
                } else if (token[0].equals("deadline")) {
                    if (token.length != 2) {
                        Ui.deadlineError();
                    }
                    String[] details = token[1].split(" /by ");
                    if (details.length < 2) {
                        Ui.deadlineError();
                    } else {
                        Task task = new Deadline(details[0], details[1]);
                        tasks.add(task);
                        this.storage.writeToFile(tasks);
                        System.out.println("added: " + task + "...");
                    }
                } else if (token[0].equals("event")) {
                    if (token.length != 2) {
                        Ui.eventError();
                    }
                    String[] details = token[1].split(" /from |\\ /to ");
                    if (details.length != 3) {
                        Ui.eventError();
                    } else {
                        Task task = new Event(details[0], details[1], details[2]);
                        tasks.add(task);
                        this.storage.writeToFile(tasks);
                        System.out.println("added: " + task + "...");
                    }
                } else {
                    Ui.yapError();
                }
                int size = this.storage.countFromFile();
                System.out.println("You currently have " + size + " things to do...");
                System.out.println("\n");
            }*/
        }
    }
    public static void main(String[] args) {
        Riz riz = new Riz("./data/riz.txt");
        riz.run();
    }
}