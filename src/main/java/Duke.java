import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________\n";
        String greet = horizontalLine
                + "Greetings, mortal! I am Alastor, the Radio Demon at your service.\n"
                + "What desires or inquiries do you bring to my infernal realm?\n";
        String exit = horizontalLine
                + "Farewell, fleeting soul! 'Til our paths entwine once more.\n"
                + horizontalLine;
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>(100);

        System.out.println(greet);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                System.out.print(horizontalLine);
                System.out.println("Behold, my dear! Here unfurls the tasks in your list.");
                for (Task task:list)
                    System.out.println(list.indexOf(task) + "." + task.toString());
                System.out.print(horizontalLine);
                continue;
            }
            if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                list.get(index).mark();
                System.out.print(horizontalLine
                        + "Well, isn't this delightful! I've marked this task as done, my dear.\n"
                        + "  " + list.get(index).toString() + "\n"
                        + horizontalLine);
                continue;
            }
            if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                list.get(index).unmark();
                System.out.print(horizontalLine
                        + "Very well, my dear! I've noted this task as yet untouched.\n"
                        + "  " + list.get(index).toString() + "\n"
                        + horizontalLine);
                continue;
            }

            list.add(new Task(input));
            System.out.println(horizontalLine + "added: " + input + "\n" + horizontalLine);
        }
        System.out.println(exit);
    }
}
