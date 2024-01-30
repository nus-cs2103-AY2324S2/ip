import java.util.*;
import java.util.function.ToDoubleFunction;

public class Duke {
    public static void main(String[] args) {

        String line = "_____________________________________________";
        String greeting = "Hello! I'm Donald.\nWhat can I do for you?\n";
        System.out.println(line);
        System.out.println(greeting + line + "\n");

        Scanner sc = new Scanner(System.in);
        String input;
        List<Task> list = new ArrayList<Task>();
        int index;
        Task t;

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            System.out.println(line);
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                index = 1;
                for (Task s : list) {
                    System.out.println(index + "." + s);
                    index++;
                }
            } else {
                try {
                    String[] split = input.split(" ");
                    String command = split[0];
                    String desc;

                    switch (command) {
                        case "mark":
                            if (split.length < 2) {
                                throw new DukeException("Command description cannot be empty!");
                            }
                            desc = input.split(command + " ")[1];
                            split = input.split(" ");
                            index = Integer.parseInt(split[1]) - 1;
                            if (index + 1 > list.size()) {
                                throw new DukeException("You only have " + list.size() + " tasks in the list.");
                            }
                            t = list.get(index);
                            t.mark();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(t.toString());
                            list.set(index, t);
                            break;
                        case "unmark":
                            if (split.length < 2) {
                                throw new DukeException("Command description cannot be empty!");
                            }
                            desc = input.split(command + " ")[1];
                            split = input.split(" ");
                            index = Integer.parseInt(split[1]) - 1;
                            if (index + 1 > list.size()) {
                                throw new DukeException("You only have " + list.size() + " tasks in the list.");
                            }
                            t = list.get(index);
                            t.unmark();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(t.toString());
                            list.set(index, t);
                            break;
                        case "delete":
                            if (split.length < 2) {
                                throw new DukeException("Command description cannot be empty!");
                            }
                            desc = input.split(command + " ")[1];
                            split = input.split(" ");
                            index = Integer.parseInt(split[1]) - 1;
                            if (index + 1 > list.size()) {
                                throw new DukeException("You only have " + list.size() + " tasks in the list.");
                            }
                            t = list.remove(index);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(t.toString());
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                            break;
                        case "todo":
                            if (split.length < 2) {
                                throw new DukeException("Command description cannot be empty!");
                            }
                            desc = input.split(command + " ")[1];
                            t = new ToDo(desc);
                            System.out.println(t.toString());
                            list.add(t);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("Now you have " + list.size() + " tasks in this list.");
                            break;
                        case "deadline":
                            if (split.length < 2) {
                                throw new DukeException("Command description cannot be empty!");
                            }
                            desc = input.split(command + " ")[1];
                            split = desc.split("/by");
                            if (split.length < 2) {
                                throw new DukeException("Deadline /by cannot be empty!");
                            }
                            desc = split[0];
                            String by = split[1];
                            t = new Deadline(desc, by);
                            System.out.println(t.toString());
                            list.add(t);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("Now you have " + list.size() + " tasks in this list.");
                            break;
                        case "event":
                            if (split.length < 2) {
                                throw new DukeException("Command description cannot be empty!");
                            }
                            desc = input.split(command + " ")[1];
                            split = desc.split("/from");
                            if (split.length < 2) {
                                throw new DukeException("Event /from cannot be empty!");
                            }
                            desc = split[0];
                            split = split[1].split("/to");
                            if (split.length < 2) {
                                throw new DukeException("Event /to cannot be empty!");
                            }
                            String from = split[0];
                            String to = split[1];
                            System.out.println("Got it. I've added this task:");
                            t = new Event(desc, from, to);
                            System.out.println(t.toString());
                            list.add(t);
                            System.out.println("Now you have " + list.size() + " tasks in this list.");
                            break;
                        default:
                            throw new DukeException("I'm sorry, I don't know what that means.\n" +
                                    "Please input valid commands (i.e. [command] [description]).\n" +
                                    "You can choose from the following available commands:\n" +
                                    "   * todo [desc]\n" +
                                    "   * event [desc] /from [desc] /to [desc]\n" +
                                    "   * deadline [desc] /by [desc]\n" +
                                    "   * list\n" +
                                    "   * mark [number]\n" +
                                    "   * unmark [number]");
                    }
                } catch (DukeException de) {
                    System.out.println(de.toString());
                } catch (NumberFormatException nfe) {
                    System.out.println("DukeException: Please input valid integer.");
                }
            }
            System.out.println(line + "\n");
        }

        String closing = "Bye. Hope to see you again soon!\n";
        System.out.println(closing + line);
    }
}
