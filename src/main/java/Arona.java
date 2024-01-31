import java.util.Scanner;

public class Arona {
    private String name;
    private String filePath = "./src/data/tasklist.txt";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    public Arona() throws TaskException {
        this.name = "";
        this.storage = new Storage(filePath);
        try {
            this.ui = new Ui();
            this.taskList = new TaskList(storage.load());
        } catch (FileException e) {
            this.taskList = new TaskList();
        }
    }

    public void greetings() {
        String logo ="    _____ \n" +
                /* */                "   /  _  \\_______   ____   _____ _____ \n" +
                /*   */              "  /  /_\\  \\_  __  \\/  _ \\ /     \\___  \\ \n" +
                /*   */      " /    |    \\  | \\_ ( <_> )   |   \\/ __ \\_ \n" +
                /* */                " \\____|__  /__|    \\____/|___|_  (____  / \n" +
                /* */                "         \\/                    \\/     \\/ \n";
        System.out.println("開始中... \n" + logo);
//        String reply = "Hi! I'm " + this.name + ". What can I do for you?";
        String reply = "こんにちは先生、私は" + this.name + "アロナです. \n"
                + "どういうご用件ですか?　\n"
                + "ここで先生のスケジュールが決まります";
        System.out.println(reply);
    }

    public void quitApplication() {
        String reply = "Goodbye sensei! Hope to see you soon!";
        System.out.println(reply);
    }

    public void run() throws FileException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                String command = input.split(" ", 0)[0];
                switch(command) {
                    case "bye":
                        quitApplication();
                        return;
                    case "list":
                        taskList.printTasks();
                        break;
                    case "mark":
                        if (input.split(" ", 0).length == 1) throw new AronaException("Sensei! Please provide a task number!");
                        int taskNum = Integer.parseInt(input.split(" ", 0)[1]);
                        taskList.changeTaskStatus(taskNum, true);
                        storage.save(taskList.getTasks());
                        break;
                    case "unmark":
                        if (input.split(" ", 0).length == 1) throw new AronaException("Sensei! Please provide a task number!");
                        taskNum = Integer.parseInt(input.split(" ", 0)[1]);
                        taskList.changeTaskStatus(taskNum, false);
                        storage.save(taskList.getTasks());
                        break;
                    case "delete":
                        if (input.split(" ", 0).length == 1) throw new AronaException("Sensei! Please provide a task number!");
                        taskNum = Integer.parseInt(input.split(" ", 0)[1]);
                        taskList.DeleteTask(taskNum);
                        storage.save(taskList.getTasks());
                        break;
                    default:
                        taskList.addTask(input);
                        storage.save(taskList.getTasks());
                        break;
                }
            } catch (TaskException e) {
                System.err.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.err.println(e.getMessage());
            } catch (AronaException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws TaskException, FileException {
        Arona arona = new Arona();
        arona.greetings();
        arona.run();
    }
}
