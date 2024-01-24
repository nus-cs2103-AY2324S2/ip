import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demon {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Hello Master, I'm Demon \nWhat can I do for you today?");
        String input = sc.nextLine();
        // list to store actions specified by user.
        List<Task> list = new ArrayList<>();

        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("Entered: '" + input + "'");
            if (input.equalsIgnoreCase("list")) {
                System.out.println("---------------------------------------------------------");
                System.out.print("List of things to do :\n");
                for (int i = 1; i <= list.size(); i++) {
                    Task item = list.get(i-1);
                    System.out.println("\t" + i + "." + item.toString());
                }
                System.out.println("--------------------------------------------------------");
                System.out.println("Anything else? Please let me know: ");
                input = sc.nextLine();
            } else if (input.split(" ",2)[0].equalsIgnoreCase("unmark")) {
                try {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    Task item = list.get(num-1);
                    item.markNotDone();
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Sure Master, I've marked this task as not done :");
                    System.out.println(item.toString());
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Anything else? Please let me know: ");
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println(e + ". Please provide valid integer.");
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.split(" ",2)[0].equalsIgnoreCase("mark")) {
                try {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    Task item = list.get(num-1);
                    item.markDone();
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Sure Master, I've marked this task as done :");
                    System.out.println(item.toString());
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Anything else? Please let me know: ");
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println(e + ". Please provide valid integer.");
                } finally {
                    input = sc.nextLine();
                }

            } else if (input.split(" ",2)[0].equalsIgnoreCase("deadline")) {
                try {
                    String[] parts = input.split(" ",2);
                    if (parts.length < 2) {
                        throw new EmptyDescriptionException("Description is EMPTY!");
                    }
                    String deadline = parts[1];
                    String[] parts2 = deadline.split("/by");
                    if (parts2.length < 2) {
                        throw new NoTimingException("WOI! Please include deadline!");
                    }
                    String description = parts2[0];
                    String by = parts2[1];
                    Deadline item_deadline = new Deadline(description, by);
                    list.add(item_deadline);
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Yes Master, I've added this task: ");
                    System.out.println(item_deadline.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Anything else? Please let me know: ");
                } catch (NoTimingException | EmptyDescriptionException e) {
                    System.out.println("Error -> " + e);
                } finally {
                    input = sc.nextLine();
                }

            } else if (input.split(" ",2)[0].equalsIgnoreCase("todo")) {
                try {
                    String[] parts = input.split(" ",2);
                    if (parts.length < 2) {
                        throw new EmptyDescriptionException("Description is EMPTY!");
                    }
                    String toDo = parts[1];
                    Todo item_toDo = new Todo(toDo);
                    list.add(item_toDo);
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Yes Master, I've added this task: ");
                    System.out.println(item_toDo.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Anything else? Please let me know: ");
                } catch (EmptyDescriptionException e) {
                    System.out.println("Error -> " + e);
                } finally {
                    input = sc.nextLine();
                }

            } else if (input.split(" ",2)[0].equalsIgnoreCase("event")) {
                try {
                    String[] parts = input.split(" ",2);
                    if (parts.length < 2) {
                        throw new EmptyDescriptionException("Description is EMPTY!");
                    }
                    String description_date = parts[1];
                    String[] parts2 = description_date.split("/from");
                    if (parts2.length < 2) {
                        throw new NoTimingException("WOI! Please include deadline!");
                    }
                    String[] details = parts2[1].split("/to");
                    String description = parts2[0];
                    String from = details[0];
                    String to = details[1];
                    Event item_event = new Event(description, from, to);
                    list.add(item_event);
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Yes Master, I've added this task: ");
                    System.out.println(item_event.toString());
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Anything else? Please let me know: ");
                } catch (NoTimingException | EmptyDescriptionException e) {
                    System.out.println("Error -> " + e);
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.split(" ",2)[0].equalsIgnoreCase("delete")) {
                try {
                    String[] parts = input.split(" ");
                    if (parts.length < 2) {
                        throw new EmptyDescriptionException("You did not specify an number!");
                    }

                    System.out.println("--------------------------------------------------------");
                    System.out.println("Noted Master. I've removed this task:");
                    System.out.println(list.get(Integer.parseInt(parts[1]) - 1).toString());
                    list.remove(Integer.parseInt(parts[1]) - 1);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Anything else? Please let me know: ");
                } catch (EmptyDescriptionException e) {
                    System.out.println("Error -> e");
                } finally {
                    input = sc.nextLine();
                }
            } else {
                System.out.println("--------------------------------------------------------");
                System.out.println("OOPS! Looks like you have entered an invalid command! Try again.");
                System.out.println("--------------------------------------------------------");
                input = sc.nextLine();
            }
        }
        System.out.println("Entered: '" + input + "'");
        System.out.println("--------------------------------------------------------");
        System.out.println("Bye Master, hope you had a great time, see you!");
        System.out.println("--------------------------------------------------------");
    }
}
