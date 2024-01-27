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
        boolean hasStopped = false;
        Task task;

        Ui.printMsg(line + "\nHello, I'm FishStock.\nI might help if I feel like it.");

        while (!hasStopped) {
            Ui.printMsg(line + "\n");
            input = sc.nextLine();
            Ui.printMsg(line);

            Keyword keyword = Parser.parse(input);

            try {
                switch (keyword) {
                case BYE:
                    hasStopped = true;
                    break;
                case LIST:
                    Ui.printMsg("Here are the tasks in your list:");
                    list.printTasks();
                    break;
                case MARK:
                    task = list.changeMark(keyword, input);
                    Ui.printMsg("Did you actually finish this? \uD83E\uDD14:\n" + "  " + task);
                    break;
                case UNMARK:
                    task = list.changeMark(keyword, input);
                    Ui.printMsg("I knew you didn't finish it! \uD83D\uDE0F:\n" + "  " + task);
                    break;
                case DELETE:
                    task = list.deleteTask(input);
                    Ui.printMsg("This task has been removed:\n  " + task +
                            "\n" + "Now you have " + list.getSize() +
                            " task(s) in total.");
                    break;
                case TODO:
                    // Fallthrough
                case DEADLINE:
                    // Fallthrough
                case EVENT:
                    task = list.addTask(keyword, input);
                    Ui.printMsg("This task has been added:\n  " + task +
                            "\n" + "Now you have " + list.getSize() +
                            " task(s) in total.");
                    break;
                default:
                    Ui.printMsg("OH NOSE! Wakaranai... :(");
                    break;
                }
            } catch (FishStockException e) {
                Ui.printError(e.getMessage());
            }
        }
        Ui.printMsg("Bye! You'll be back ;)\n" + line);
    }
}
