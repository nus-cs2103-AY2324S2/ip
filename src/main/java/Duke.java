import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>(100);
        String message = "____________________________________________________________\n" +
                "Hello! I'm Jux\n" +
                "What can I do for you?\n";
        String end = "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________"
                ;
        System.out.println(message);
        while(true) {

            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    break;
                } else if(input.equals("list")) {
                    if (list.isEmpty()) {
                        throw new DukeException(" YOUR LIST IS EMPTY");
                    }
                    System.out.println("Here are the tasks in your list!");
                    for (int i = 0; i < list.size();i++) {
                        int j = i + 1;
                        String listMessage = j + "." +  list.get(i);
                        System.out.println(listMessage);
                    }
                } else if(input.startsWith("mark")) {

                    if (input.length() > 5 ) {
                        String listStringNumber =  input.substring(5);
                        // future error detection for non-numerals
                        int convertedToNumber = Integer.parseInt(listStringNumber) - 1;
                        // future error when list is empty
                        if (convertedToNumber < 0 || convertedToNumber >= list.size()) {
                            throw new DukeException("NUMBER NOT IN LIST, PLEASE ADD A TASK OR " +
                                    "CHOOSE A DIFFERENT NUMBER WITHIN 1 AND"
                                    + list.size());
                        }
                        if (list.get(convertedToNumber).getStatusIcon().equals("X")) {
                            throw new DukeException("TASK ALREADY MARKED");
                        }
                        list.get(convertedToNumber).toggleIsDone();
                        // check if already marked then return error
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(convertedToNumber));

                    } else {
                        throw new DukeException("PLEASE INSERT NUMBER TO MARK");
                    }

                } else if (input.startsWith("unmark")) {
                    String listStringNumber =  input.substring(7);
                    // future error detection for non-numerals
                    int convertedToNumber = Integer.parseInt(listStringNumber) - 1;
                    // future error when list is empty
                    if (convertedToNumber < 0 || convertedToNumber >= list.size()) {
                        throw new DukeException("NUMBER NOT IN LIST, PLEASE ADD A TASK OR " +
                                "CHOOSE A DIFFERENT NUMBER WITHIN 1 AND"
                                + list.size());
                    }
                    if (list.get(convertedToNumber).getStatusIcon().equals(" ")) {
                        throw new DukeException("TASK ALREADY UNMARKED");
                    }
                    list.get(convertedToNumber).toggleIsDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.get(convertedToNumber));

                } else if( input.startsWith("delete") ){
                    String listStringNumber =  input.substring(7);
                    // future error detection for non-numerals
                    int convertedToNumber = Integer.parseInt(listStringNumber) - 1;
                    // future error when list is empty
                    if (convertedToNumber < 0 || convertedToNumber >= list.size()) {
                        throw new DukeException("NUMBER NOT IN LIST, PLEASE ADD A TASK OR " +
                                "CHOOSE A DIFFERENT NUMBER WITHIN 1 AND"
                                + list.size());
                    }
                    String removedItem = list.get(convertedToNumber).toString();
                    list.remove(convertedToNumber);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removedItem);
                    if (list.isEmpty()) {
                        System.out.println("Now, your list is empty!");
                    } else if (list.size() == 1) {
                        System.out.println("You now have 1 task remaining");
                    } else{
                            System.out.println("You now have " + list.size() + " tasks remaining");
                    }

                }else {
                    String desc = "";
                    Task task;
                    if (input.startsWith("todo")) {
                        if (input.length() > 5) {
                            desc = input.substring(5);
                            task = new Todo(desc);
                        } else {
                            throw new DukeException("PLEASE INSERT DESCRIPTION FOR YOUR TODO");
                        }

                    } else if (input.startsWith("event")) {
                        if (input.length() >7 ) {
                            String regex = ".*" + '/'+ ".*" + '/' + ".*";
                            if (!input.matches(regex)) {
                                throw new DukeException("insert time for event such as event /monday /sunday");
                            }
                            desc = input.substring(6, input.indexOf("/"));
                            String firstDate = input.substring(input.indexOf("/") + 1, input.lastIndexOf("/"));
                            String endDate = input.substring(input.lastIndexOf("/") + 1);
                            task = new Event(desc, firstDate, endDate);
                        } else {
                            throw new DukeException("PLEASE INSERT DESCRIPTION FOR YOUR EVENT");
                        }

                    } else if (input.startsWith("deadline")) {
                        if (input.length() >10) {
                            if (!input.contains("/")) {
                                throw new DukeException("insert time after deadline such as deadline /monday");
                            }
                            desc = input.substring(9, input.indexOf("/"));
                            String date = input.substring(input.indexOf("/") + 1);
                            task = new Deadline(desc, date);
                        } else {
                            throw new DukeException("PLEASE INSERT DESCRIPTION FOR YOUR DEADLINE");
                        }

                    } else {
                        throw new DukeException("SORRY I DO NOT KNOW WHAT THAT MEANS, PLEASE TRY AGAIN!");
                    }

                    list.add(task);
                    System.out.println("You entered:" );
                    System.out.println(task);
                    if (list.size() == 1) {
                        System.out.println("You now have 1 task remaining");
                    } else{
                        System.out.println("You now have " + list.size() + " tasks remaining");
                    }

                }
            } catch (DukeException e) {
                System.err.println(e.getMessage());
            }



        }
        System.out.println(end);


    }
}

