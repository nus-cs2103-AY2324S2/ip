import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String DELIMITER = "______________________________________";

        System.out.println(DELIMITER);
        System.out.println("Hello! I'm Drew");
        System.out.println("What can I do for you?");
        System.out.println(DELIMITER);

        String userInput = sc.nextLine();
        Task[] taskList = new Task[100];
        int listLength = 0;

        while (!userInput.equalsIgnoreCase("bye")){
            String reply = "";

            userInput = userInput.toLowerCase();
            Command userCommand = Command.UNKNOWN;
            int taskIndex = -1;
            int inputLength = userInput.length();

            if(inputLength == 4 && userInput.substring(0, 4).equals("list")) {
                userCommand = Command.LIST;
            } else if (inputLength > 4 && userInput.substring(0, 4).equals("mark")) {
                userCommand = Command.MARK;
                System.out.println(Integer.parseInt(userInput.substring(5)));
                taskIndex = Integer.parseInt(userInput.substring(5));
            } else if (inputLength > 6 && userInput.substring(0, 6).equals("unmark")) {
                userCommand = Command.UNMARK;
                taskIndex = Integer.parseInt(userInput.substring(7));
            } else {
                userCommand = Command.ADD;
            }

            switch (userCommand) {
                case LIST:
                    for(int i = 0; i < listLength; i++) {
                        reply = reply + Integer.toString(i + 1) + ". " +
                                taskList[i].statusString() + "\n";
                    }
                    break;
                case MARK:
                    taskList[taskIndex - 1].setDone();
                    reply = "Well done! I have marked this task as done:\n" +
                            taskList[taskIndex - 1].statusString() + "\n";
                    break;
                case UNMARK:
                    taskList[taskIndex - 1].setNotDone();
                    reply = "Ok. I have marked this task as not done yet:\n" +
                            taskList[taskIndex - 1].statusString() + "\n";
                    break;
                case ADD:
                    taskList[listLength] = new Task(userInput);
                    reply = String.format("added: %s\n", userInput);
                    listLength ++;
                    break;
            }


            System.out.println(DELIMITER);
            System.out.print(reply);
            System.out.println(DELIMITER);

            userInput = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
