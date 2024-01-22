import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        String logo = " ____              _    \n"
                + "|  _ \\  ___   ___ | | __      ╱|、\n"
                + "| | | |/ _ \\ / _ \\| |/ /    (˚ˎ 。7  \n"
                + "| |_| | |_| | |_| |   <      |、˜〵 \n"
                + "|____/ \\___/ \\___/|_|\\_\\     じしˍ,)ノ\n";
        System.out.println(line);
        System.out.println("Hello from Dook! :D meow\n" + logo);
        System.out.println("What can I do for you? uwu");
        System.out.println(line + "\n");
        Scanner sc = new Scanner(System.in);
        String input = "";
        TaskList tasks = new TaskList();
        while (true) {
            input = sc.nextLine();
            String[] cmds = input.split(" ", 2);
            System.out.println(line);
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are your tasks!");
                System.out.println(tasks);
            } else if (input.substring(0, 5).equals("mark ")) {
                int positionToMark = Integer.valueOf(input.substring(5));
                Task toMark = tasks.get(positionToMark - 1);
                toMark.markAsDone();
                System.out.println("Oki! :D Good job! I've marked this task as done:");
                System.out.println(toMark);
            } else if (input.substring(0, 7).equals("unmark ")) {
                int positionToUnmark = Integer.valueOf(input.substring(7));
                Task toUnmark = tasks.get(positionToUnmark - 1);
                toUnmark.markAsNotDone();
                System.out.println("Lazy bum. >:( I've marked this task as done:");
                System.out.println(toUnmark);
            } else {
                Task toAdd;
                if (cmds[0].equals("todo")) {
                    toAdd = new ToDo(cmds[1]);
                } else if (cmds[0].equals("deadline")) {
                    int index = cmds[1].indexOf(" /by ") + 5;
                    String dueBy = cmds[1].substring(index);
                    toAdd = new Deadline(cmds[1].substring(0, index - 5), dueBy);
                } else if (cmds[0].equals("event")) {
                    int indexFrom = cmds[1].indexOf(" /from ") + 6;
                    int indexTo = cmds[1].indexOf(" /to ") + 5;
                    String from = cmds[1].substring(indexFrom + 1, indexTo - 5);
                    String to = cmds[1].substring(indexTo);
                    toAdd = new Event(cmds[1].substring(0, indexFrom - 6), from, to);
                } else {
                    continue;
                }
                tasks.addTask(toAdd);
                System.out.println("Oki! I've added this task:");
                System.out.println(toAdd);
                tasks.printStatus();
            }
            System.out.println(line);
        }
        System.out.println("Bye :(. Hope to see you again soon! ≽^- ˕ -^≼");
        System.out.println(line);
    }
}
