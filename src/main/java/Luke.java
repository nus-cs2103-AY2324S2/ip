import java.util.ArrayList;
import java.util.Scanner;

public class Luke {
    public static void main(String[] args) throws LukeException {
        // Greetings
        System.out.println("________________________________________________________________________");
        System.out.println("Hello! I'm Luke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________________________________________");

        // User inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        // Conditions
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("________________________________________________________________________");
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null) {
                        System.out.println(i + 1 + "." + list.get(i));

                    }
                }
                System.out.println("________________________________________________________________________");

            } else if (input.contains("mark")) {
                try {
                    if (input.equals("mark") || input.equals("unmark")) {
                        throw new LukeException("Hold up!! There must be a task to " + input + "!\n"
                                + "Please enter an index after " + input + ".");
                    }

                    try {
                        String[] instruction = input.split(" ");
                        String markOrUnmark = instruction[0];

                        if (!markOrUnmark.equals("mark") && !markOrUnmark.equals("unmark")) {
                            System.out.println("test");
                            throw new LukeException("Hold up!! I am sorry, but I don't know what you mean by that :'(");
                        }

                        int index = Integer.parseInt(instruction[1]) - 1;  // array is 0-indexed

                        if (index >= list.size()) {
                            throw new LukeException("Hold up!! There is no such task in the list.\n"
                                    + "Please enter a valid index after " + input.split(" ")[0] + ".");
                        }

                        if (markOrUnmark.equals("mark")) {
                            list.get(index).markAsDone();
                            System.out.println("________________________________________________________________________");
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(list.get(index));
                            System.out.println("________________________________________________________________________");

                        } else if (markOrUnmark.equals("unmark")) {
                            list.get(index).markAsUndone();
                            System.out.println("________________________________________________________________________");
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(list.get(index));
                            System.out.println("________________________________________________________________________");

                        } else {
                            throw new LukeException("Hold up!! I am sorry, but I don't know what you mean by that :'(");
                        }

                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw new LukeException("Hold up!! Please enter a valid index after "
                                + input.split(" ")[0] + ".");
                    }


                } catch (LukeException e) {
                    System.out.println("________________________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("________________________________________________________________________");
                }

            } else if (input.contains("delete")) {
                try {
                    if (input.equals("delete")) {
                        throw new LukeException("Hold up!! There must be a task to delete!\n"
                                + "Please enter an index after " + input + ".");
                    }

                    try {
                        String[] instruction = input.split(" ");
                        String delete = instruction[0];

                        if (!delete.equals("delete")) {
                            throw new LukeException("Hold up!! I am sorry, but I don't know what you mean by that :'(");
                        }

                        int index = Integer.parseInt(instruction[1]) - 1;  // array is 0-indexed

                        if (index >= list.size()) {
                            throw new LukeException("Hold up!! There is no such task in the list.\n"
                                    + "Please enter a valid index after delete.");
                        }

                        Task removedTask = list.get(index);
                        list.remove(index);
                        System.out.println("________________________________________________________________________");
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(removedTask);

                        if (list.size() <= 1) {
                            System.out.println("Now you have " + list.size() + " task in the list.");
                        } else {
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                        }
                        System.out.println("________________________________________________________________________");
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        throw new LukeException("Hold up!! Please enter a valid index after delete.");
                    }

                } catch (LukeException e) {
                    System.out.println("________________________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("________________________________________________________________________");
                }

            } else {
                try {
                    if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                        throw new LukeException("Hold up!! The description of a " + input + " cannot be empty.\n"
                                + "Please enter a description after your " + input + ".");
                    }

                    String[] instruction = input.split(" ");
                    String type = instruction[0];

                    if (type.equals("todo")) {
                        // 5 is the index after "todo ", so starts from index 5
                        String description = input.substring(5);
                        list.add(new Todo(description));

                    } else if (type.equals("deadline")) {
                        try {
                            // 9 is the index after "deadline ", so starts from index 9
                            // -1 to remove the space before "/by"
                            String description = input.substring(9, input.indexOf("/by") - 1);
                            // +4 to remove the "/by " and start from the index after "/by "
                            String by = input.substring(input.indexOf("/by") + 4);
                            list.add(new Deadline(description, by));

                        } catch (StringIndexOutOfBoundsException e) {
                            throw new TaskException("Hold up!! The description of a deadline cannot be empty.\n"
                                    + "Please follow this format: deadline <description> /by <date/day/time>.");
                        }

                    } else if (type.equals("event")) {
                        try {
                            // 6 is the index after "event ", so starts from index 6
                            // -1 to remove the space before "/from"
                            String description = input.substring(6, input.indexOf("/from") - 1);
                            // +6 to remove the "/from " and start from the index after "/from "
                            // -1 to remove the space before "/to"
                            String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                            // +4 to remove the "/to " and start from the index after "/to "
                            String to = input.substring(input.indexOf("/to") + 4);
                            list.add(new Event(description, from, to));
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new TaskException("Hold up!! The description of an event cannot be empty.\n"
                                    + "Please follow this format: event <description> "
                                    + "/from <date/day/time> /to <date/day/time>.");

                        }

                    } else {
                        throw new LukeException("Hold up!! I am sorry, but I don't know what you mean by that :'(");

                    }

                    System.out.println("________________________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + list.get(list.size() - 1));
                    if (list.size() <= 1) {
                        System.out.println("Now you have " + list.size() + " task in the list.");
                    } else {
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    }
                    System.out.println("________________________________________________________________________");
                } catch (LukeException | TaskException e) {
                    System.out.println("________________________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("________________________________________________________________________");
                }
            }
            input = sc.nextLine();

        }

        // Bye and exits
        System.out.println("________________________________________________________________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("________________________________________________________________________");

    }
}
