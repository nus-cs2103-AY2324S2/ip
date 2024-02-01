package lex;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import lex.storage.Storage;
import lex.tasks.Deadline;
import lex.tasks.Event;
import lex.tasks.Task;
import lex.tasks.Todo;

public class Lex {
    private static List<Task> list;

    public static void main(String[] args) {
        final var storage = new Storage();
        list = storage.load();

        System.out.println(Constant.SEPERATOR);
        System.out.print("Hello! I'm lex.Lex\nWhat can I do for you?\n");
        System.out.println(Constant.SEPERATOR);

        Scanner scanner = new Scanner(System.in);
        String input;

        event:
        while (scanner.hasNextLine()) {
            try {
                input = scanner.nextLine();
                String[] inputs = input.split(" ", 2);
                Command command = Command.valueOf(inputs[0].toUpperCase());
                int index;

                switch (command) {
                    case LIST:
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println((i + 1) + ". " + list.get(i));
                        }
                        break;
                    case TODO:
                        if (inputs.length < 2) {
                            throw new Exception("OOPS!!! Formatting error.");
                        }

                        list.add(new Todo(inputs[1]));
                        printAddSuccessMessage();
                        break;
                    case DEADLINE:
                        String[] deadlineInputs = inputs[1].split(" /by ", 2);
                        if (deadlineInputs.length < 2) {
                            throw new Exception("OOPS!!! Formatting error.");
                        }

                        list.add(new Deadline(deadlineInputs[0], LocalDate.parse(deadlineInputs[1])));
                        printAddSuccessMessage();
                        break;
                    case EVENT:
                        String[] eventInputs = inputs[1].split(" /from ", 2);
                        String[] eventTimeInputs = eventInputs[1].split(" /to ", 2);
                        if (eventTimeInputs.length < 2) {
                            throw new Exception("OOPS!!! Formatting error.");
                        }

                        list.add(new Event(eventInputs[0], LocalDate.parse(eventTimeInputs[0]), LocalDate.parse(eventTimeInputs[1])));
                        printAddSuccessMessage();
                        break;
                    case MARK:
                        index = Integer.parseInt(inputs[1]) - 1;
                        list.get(index).isDone(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(index));
                        break;
                    case UNMARK:
                        index = Integer.parseInt(inputs[1]) - 1;
                        list.get(index).isDone(false);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(list.get(index));
                        break;
                    case DELETE:
                        index = Integer.parseInt(inputs[1]) - 1;
                        Task removedTask = list.remove(index);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + removedTask);
                        System.out.println("Now you have " + list.size() + " lex.tasks in the list.");
                        break;
                    case BYE:
                        storage.save(list);
                        System.out.println("Bye. Hope to see you again soon!");
                        break event;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println(Constant.SEPERATOR);
            }
        }

        scanner.close();
    }

    public static void printAddSuccessMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + list.get(list.size() - 1));
        System.out.println("Now you have " + list.size() + " lex.tasks in the list.");
    }
}
