import java.util.Scanner;

public class MeanDuke{

    //Text art to be used
    private static final String logo =
                  "███╗░░░███╗███████╗░█████╗░███╗░░██╗░░░░░░██████╗░██╗░░░██╗██╗░░██╗███████╗\n"
                + "████╗░████║██╔════╝██╔══██╗████╗░██║░░░░░░██╔══██╗██║░░░██║██║░██╔╝██╔════╝\n"
                + "██╔████╔██║█████╗░░███████║██╔██╗██║█████╗██║░░██║██║░░░██║█████═╝░█████╗░░\n"
                + "██║╚██╔╝██║██╔══╝░░██╔══██║██║╚████║╚════╝██║░░██║██║░░░██║██╔═██╗░██╔══╝░░\n"
                + "██║░╚═╝░██║███████╗██║░░██║██║░╚███║░░░░░░██████╔╝╚██████╔╝██║░╚██╗███████╗\n"
                + "╚═╝░░░░░╚═╝╚══════╝╚═╝░░╚═╝╚═╝░░╚══╝░░░░░░╚═════╝░░╚═════╝░╚═╝░░╚═╝╚══════╝\n";
    private static final String spacer = "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━";

    public static void main(String[] args) {

        //Creates an empty TaskList
        TaskList tasklist = new TaskList();

        //Prints intro
        String intro = logo + "What do you want this time?\n" + spacer;
        System.out.println(intro);


        //Reads each line of user input and perform respective actions
        Scanner inputScanner= new Scanner(System.in);
        String userInput = inputScanner.nextLine();

        while (!userInput.equals("end")) {  //Session terminates when user inputs "end"
            System.out.println(spacer);

            //add command: Add task to list and print out added task
            if (userInput.startsWith("add ")) {
                String taskString = userInput.substring(4);
                //Determine type of Task
                if(taskString.startsWith("todo ")){ // To do task
                    taskString = taskString.substring(5); // Remove command word "to do"
                    tasklist.add(new ToDo(taskString));
                    System.out.println("Added ToDo to list: " + taskString);

                } else if(taskString.startsWith("deadline ")) { // Deadline task
                    taskString = taskString.substring(9); // Remove command word "deadline"
                    int by_start_index = taskString.indexOf("/by "); // Obtain index of "/by"
                    if(by_start_index == -1){
                        System.out.println("Indicate the deadline using \"/by\"");
                    } else {
                        String description = taskString.substring(0, by_start_index).strip();
                        String by = taskString.substring(by_start_index + 4).strip();
                        tasklist.add(new Deadline(description, by));
                        System.out.println("Added deadline to list: " + description);
                    }

                } else if(taskString.startsWith("event ")) { // Event task
                    taskString = taskString.substring(6); // Remove command word "event"
                    int from_start_index = taskString.indexOf("/from "); // Obtain index of "/from"
                    int to_start_index = taskString.indexOf("/to ");
                    if(from_start_index == -1 || to_start_index == -1){
                        System.out.println("Indicate the period using \"/from\" and \"/to\"");
                    } else {
                        String description = taskString.substring(0, from_start_index).strip();
                        String from = taskString.substring(from_start_index + 6, to_start_index).strip();
                        String to = taskString.substring(to_start_index + 4).strip();
                        tasklist.add(new Event(description, from, to));
                        System.out.println("Added event to list: " + description);
                    }

                } else {
                    System.out.println("Usage: add <type> <description>, where <type> is \"todo\", \"deadline\" or \"event\"");
                }

            //list command: Print out current task list
            } else if (userInput.equals("list")) {
                System.out.println(tasklist);

            //mark command: Marks the stated task as done
            } else if (userInput.startsWith("mark ")) {
                String index_string = userInput.substring(5);
                try {
                    int index = Integer.parseInt(index_string) - 1;
                    if (tasklist.mark_done(index)) {
                        //Task successfully changed from not done to done
                        System.out.printf("Marked task: %s as completed.\nCould you have taken any longer?\n", index_string);
                    } else {
                        //Task was already completed
                        System.out.printf("You have already marked task %s as completed you imbecile.\n", index_string);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Give me the number of the task you want to mark after saying \"mark\".");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Dude... you don't even have a task " + index_string);
                }

            //unmark command: Marks the stated task as not done
            } else if (userInput.startsWith("unmark ")) {
                String index_string = userInput.substring(7);
                try {
                    int index = Integer.parseInt(index_string) - 1;
                    if (tasklist.unmark_done(index)) {
                        //Task successfully changed from done to not done
                        System.out.printf("Marked task: %s as not completed.\nWhy did you mark this in the first place?\n", index_string);
                    } else {
                        //Task was already not complete
                        System.out.printf("Task %s is already not completed. Maybe you should start working on it.\n", index_string);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Give me the number of the task you want to mark after saying \"unmark\".");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Dude... you don't even have a task " + index_string);
                }


            //Echo back user input
            } else {
                System.out.println(userInput);
            }

            System.out.println(spacer);
            userInput = inputScanner.nextLine();
        }

        //Outro message and end of program
        String outro = spacer + "\n" + "Finally you're finished, thought you would never stop yapping.\n" + spacer;
        System.out.println(outro);
    }
}
