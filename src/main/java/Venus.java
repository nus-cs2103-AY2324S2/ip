import java.util.ArrayList;
import java.util.Scanner;
public class Venus {

    public static void main(String[] args) {
        String start =
                "    ____________________________________________________________\n"
                        + "    Hello! I'm Venus\n"
                        + "    What can I do for you?\n"
                        + "    ____________________________________________________________\n";
        String end =
                "    ____________________________________________________________\n"
                        + "    Bye. Hope to see you again soon!\n"
                        + "    ____________________________________________________________\n";
        String indented_lines = "    ____________________________________________________________\n";
        System.out.println(start);

        ArrayList<Task> data = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String words = sc.nextLine();

            while (!words.equals("bye")) {
                if (words.equals("list")) {
                    int i = 1;
                    System.out.println(indented_lines
                            + "     Here are the tasks in your list:");
                    for (Task s : data) {
                        System.out.println("     " + i + "." + s);
                        i++;
                    }
                    System.out.println(indented_lines);
                } else if (words.startsWith("mark ")) {
                    try {
                        int index = Integer.valueOf(words.substring(5)) - 1;
                        data.get(index).mark();
                        System.out.println(indented_lines
                                + "     Nice! I've marked this task as done:\n"
                                + "       "
                                + data.get(index).toString()
                                + "\n"
                                + indented_lines);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(indented_lines
                                + "     Incorrect name or spelling for mark, please check\n"
                                + indented_lines);
                    } catch (NumberFormatException e) {
                        System.out.println(indented_lines
                                + "     Incorrect arguments for mark, please check\n"
                                + indented_lines);
                    }
                } else if (words.startsWith("unmark ")) {
                    try {
                        int index = Integer.valueOf(words.substring(7)) - 1;
                        data.get(index).unmark();
                        System.out.println(indented_lines
                                + "     OK, I've marked this task as not done yet:\n"
                                + "       "
                                + data.get(index).toString()
                                + "\n"
                                + indented_lines);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(indented_lines
                                + "     Incorrect name or spelling for unmark, please check\n"
                                + indented_lines);
                    } catch (NumberFormatException e) {
                        System.out.println(indented_lines
                                + "     Incorrect arguments for unmark, please check\n"
                                + indented_lines);
                    }
                } else if (words.startsWith("todo ")) {
                    try {
                        String word = words.substring(5);
                        TODO todo = new TODO(word);
                        data.add(todo);
                        System.out.println(indented_lines
                                + "     Got it. I've added this task:\n"
                                + "       "
                                + todo.toString()
                                + "\n"
                                + "     Now you have " + data.size() + " tasks in the list.\n"
                                + indented_lines);
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println(indented_lines
                                + "     Incorrect arguments for todo, please check\n"
                                + indented_lines);
                    }
                } else if (words.startsWith("deadline ")) {
                    try {
                        String dString = words.substring(9);
                        String[] parts = dString.split("/by");
                        if (parts.length != 1) {
                            throw new DukeException("Incorrect, choose a specific deadline only please");
                        }
                        System.out.println(parts.length);
                        parts[0] = parts[0].trim();
                        parts[1] = parts[1].trim();
                        Deadline deadline = new Deadline(parts[0], parts[1]);
                        data.add(deadline);
                        System.out.println(indented_lines
                                + "     Got it. I've added this task:\n"
                                + "       "
                                + deadline.toString()
                                + "\n"
                                + "     Now you have " + data.size() + " tasks in the list.\n"
                                + indented_lines);
                    } catch (Exception e) {
                        System.out.println(indented_lines
                                + "    "
                                + e.getMessage()
                                + "\n"
                                + indented_lines);
                    }
                } else if (words.startsWith("event ")) {
                    try {
                        String dString = words.substring(6);
                        String[] parts = dString.split("/");
                        if (parts.length != 2) {
                            throw new DukeException("Incorrect arguments for events");
                        }
                        for (int i = 0; i < 3; i++) {
                            parts[i] = parts[i].trim();
                        }
                        Event event = new Event(parts[0], parts[1], parts[2]);
                        data.add(event);
                        System.out.println(indented_lines
                                + "     Got it. I've added this task:\n"
                                + "       "
                                + event.toString()
                                + "\n"
                                + "     Now you have " + data.size() + " tasks in the list.\n"
                                + indented_lines);
                    } catch (DukeException e) {
                        System.out.println(indented_lines
                                + "    "
                                + e.getMessage()
                                + "\n"
                                + indented_lines);
                    } catch (Exception e) {
                        System.out.println(indented_lines
                                + "An unknown exception is created, please report to the devs"
                                + "\n"
                                + indented_lines);
                    }
                } else {
                    Task ts = new Task(words);
                    data.add(ts);
                    System.out.println(indented_lines
                            + "     added: "
                            + words +
                            "\n" + indented_lines);
                }
                words = sc.nextLine();
            }
        sc.close();
        System.out.println(end);
    }
}
