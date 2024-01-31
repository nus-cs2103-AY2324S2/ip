import java.util.ArrayList;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        System.out.println(Constant.SEPERATOR);
        System.out.print("Hello! I'm Lex\nWhat can I do for you?\n");
        System.out.println(Constant.SEPERATOR);

        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> list = new ArrayList<>();

        event:
        while (true) {
            input = scanner.nextLine();
            String[] inputs = input.split(" ");

            switch(inputs[0]) {
                case "list":
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ". " + list.get(i));
                    }
                    System.out.println(Constant.SEPERATOR);
                    break;
                case "mark":
                    int index = Integer.parseInt(inputs[1]) - 1;
                    list.get(index).isDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(index));
                    System.out.println(Constant.SEPERATOR);
                    break;
                case "unmark":
                    index = Integer.parseInt(inputs[1]) - 1;
                    list.get(index).isDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.get(index));
                    System.out.println(Constant.SEPERATOR);
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(Constant.SEPERATOR);
                    break event;
                default:
                    list.add(new Task(input));
                    System.out.println("added: " + input);
                    System.out.println(Constant.SEPERATOR);
                    break;
            }
        }

        scanner.close();
    }
}
