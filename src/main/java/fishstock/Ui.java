package fishstock;

import java.util.Scanner;
import fishstock.FishStock.Keyword;

class Ui {
    protected static void printMsg(String msg) {
        System.out.println(msg);
    }
    protected static void printError(String error) {
        System.out.println(error);
    }

    protected void run(TaskList list) {
        String line = "____________________________________________________________";
        Scanner sc = new Scanner(System.in);
        String input;
        boolean exit = false;

        Ui.printMsg(line + "\nHello, I'm FishStock.\nI might help if I feel like it.");

        while (!exit) {
            Ui.printMsg(line + "\n");
            input = sc.nextLine();
            Ui.printMsg(line);

            Keyword keyword = Parser.parse(input);

            switch (keyword) {
            case BYE:
                exit = true;
                break;
            case LIST:
                Ui.printMsg("Here are the tasks in your list:");
                list.printTasks();
                break;
            case MARK:
                // Fallthrough
            case UNMARK:
                list.changeMark(keyword, input);
                break;
            case DELETE:
                list.deleteTask(input);
                break;
            case TODO:
                list.addTask(Todo.of(input));
                break;
            case DEADLINE:
                list.addTask(Deadline.of(input));
                break;
            case EVENT:
                list.addTask(Event.of(input));
                break;
            default:
                Ui.printMsg("OH NOSE! Wakaranai... :(");
                break;
            }
        }
        Ui.printMsg("Bye! You'll be back ;)\n" + line);
    }
}
