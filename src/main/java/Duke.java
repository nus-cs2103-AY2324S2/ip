import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Duke Class
 * This is the main class for the chatbot.
 */
public class Duke {
    /**
     * validateInput Method
     * To check whether the user have key in the input correctly.
     * @param input
     * @return String array containing instruction and descriptions.
     * @throws DukeException
     */
    public static String[] validateInput(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException("Helloooo!! please type something and follow the format below!!\n" +
                    "todo 'task description'\n" +
                    "deadline 'task description' /by 'time'\n" +
                    "event 'task description' /from 'start time' /to 'end time'");
        }
        String[] subStr = input.split(" ", 2);
        if (!subStr[0].equalsIgnoreCase(Instruction.TODO.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.DEADLINE.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.EVENT.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.LIST.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.MARK.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.UNMARK.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.BYE.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.DELETE.toString())) {
            throw new DukeException("Sorry! I don't get the instruction!\n" +
                    "please use either todo, deadline, event, list, mark, unmark, bye or delete!");
        }

        if (subStr[0].equalsIgnoreCase(Instruction.TODO.toString()) && subStr.length < 2) {
            throw new DukeException("Sorry! You need to include a description for your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.DEADLINE.toString()) && subStr.length < 2) {
            throw new DukeException("Sorry! You need to include a description and deadline for your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.EVENT.toString()) && subStr.length < 2) {
            throw new DukeException("Sorry! You need to include a description, start time and end time for your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.MARK.toString()) && subStr.length < 2) {
            throw new DukeException("Sorry! You need to include a number to mark your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.UNMARK.toString()) && subStr.length < 2) {
            throw new DukeException("Sorry! You need to include a number to unmark your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.DELETE.toString()) && subStr.length < 2) {
            throw new DukeException("Sorry! You need to include a number to delete your task!");
        } else {
            return subStr;
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm GHBot");
        System.out.println("What can I do for you?");
        List<Task> lst = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String str = sc.nextLine();
            try {
                String[] subStr = validateInput(str);
                String instr = subStr[0];

                if (instr.equalsIgnoreCase(Instruction.BYE.toString())) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                } else if (instr.equalsIgnoreCase(Instruction.LIST.toString())) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println(i + 1 + "." + lst.get(i));
                    }

                } else if (instr.equalsIgnoreCase(Instruction.MARK.toString())) {
                    int lstNo = Integer.parseInt(subStr[1]);
                    for (int i = 0; i < lst.size(); i++) {
                        if (i + 1 == lstNo) {
                            lst.get(i).isCompleted();
                            System.out.println("Nice! I've marked this task as done:\n" + lst.get(i));
                        }
                    }

                } else if (instr.equalsIgnoreCase(Instruction.UNMARK.toString())) {
                    int lstNo = Integer.parseInt(subStr[1]);
                    for (int i = 0; i < lst.size(); i++) {
                        if (i + 1 == lstNo) {
                            lst.get(i).isNotCompleted();
                            System.out.println("OK, I've marked this task as not done yet:\n" + lst.get(i));
                        }
                    }

                } else if (instr.equalsIgnoreCase(Instruction.TODO.toString())) {
                    Todo todo = new Todo(subStr[1]);
                    lst.add(todo);
                    System.out.println("Got it. I've added this task:\n" + todo);
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");

                } else if (instr.equalsIgnoreCase(Instruction.DEADLINE.toString())) {
                    String[] ss = subStr[1].split("/by");
                    Deadline deadline = new Deadline(ss[0], ss[1]);
                    lst.add(deadline);
                    System.out.println("Got it. I've added this task:\n" + deadline);
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");

                } else if (instr.equalsIgnoreCase(Instruction.EVENT.toString())) {
                    String[] ss = subStr[1].split("/from");
                    String[] ss2 = ss[1].split("/to");
                    Event event = new Event(ss[0], ss2[0], ss2[1]);
                    lst.add(event);
                    System.out.println("Got it. I've added this task:\n" + event);
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");
                } else if (instr.equalsIgnoreCase(Instruction.DELETE.toString())) {
                    int lstNo = Integer.parseInt(subStr[1]);
                    System.out.println("Noted. I've removed this task:\n" + lst.get(lstNo - 1));
                    lst.remove(lstNo - 1);
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");
                }
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
