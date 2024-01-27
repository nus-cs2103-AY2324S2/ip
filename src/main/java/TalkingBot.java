import java.util.Scanner;
public class TalkingBot {
    public static void main(String[] args) {
        String hLine = "\t____________________________________________________________";
        String welcome = "\tHello! I'm TalkingBot\n\tWhat can I do for you?";

        String fileName = "./data/talkingbot.txt";

        System.out.println(hLine);
        System.out.println(welcome);
        System.out.println(hLine);

        try {
            SaveFile saveFile = new SaveFile(fileName);
            Scanner scanner = new Scanner(System.in);
            TaskList taskList = new TaskList();
            try {
                taskList = saveFile.getTasksFromFile();
            } catch (TalkingBotException e) {
                System.out.println("\t" + e);
                System.out.println("\tUsing new file instead...");
                System.out.println(hLine);
            }


            while (true) {
                String entry = scanner.nextLine();
                if (entry.equals("bye")) {
                    saveFile.saveTasksToFile(taskList);
                    break;
                }
                String[] curCommand = entry.split(" ");
                System.out.println(hLine);
                switch (curCommand[0]) {
                case "list":
                    System.out.println(taskList);
                    break;
                case "mark":
                case "unmark":
                    int markIdxInt = Integer.valueOf(curCommand[1]);
                    Task modifiedTask = taskList.getTask(markIdxInt - 1);
                    if (curCommand[0].equals("mark")) {
                        System.out.println("\tNice! I've marked this task as done:");
                        modifiedTask.setDone(true);
                        System.out.println("\t" + modifiedTask);
                    } else {
                        System.out.println("\tAlright, I've marked this task as undone.");
                        modifiedTask.setDone(false);
                        System.out.println("\t" + modifiedTask);
                    }
                    taskList.setTask(markIdxInt - 1, modifiedTask);
                    break;
                case "todo":
                    // Fallthrough
                case "deadline":
                    // Fallthrough
                case "event":
                    StringBuilder sbDescription = new StringBuilder();
                    for (int idx = 1; idx < curCommand.length; idx++) {
                        sbDescription.append(curCommand[idx]);
                        if (idx < curCommand.length - 1) {
                            sbDescription.append(" ");
                        }
                    }
                    String fullDescription = sbDescription.toString();
                    try {
                        Task curTask = Task.generateTask(fullDescription, curCommand[0]);
                        taskList.addTask(curTask);
                        System.out.println("\tAlright, I've added this task to your list:");
                        System.out.println("\t\t" + curTask);
                        System.out.println(String.format("\tYou now have %d tasks in the list.", taskList.getSize()));
                    } catch (TalkingBotException e) {
                        System.out.println("\t" + e);
                    }
                    break;
                case "delete":
                    int deleteIdxInt = Integer.valueOf(curCommand[1]);
                    Task removedTask = taskList.removeTask(deleteIdxInt - 1);
                    System.out.println("\tRemoving task:");
                    System.out.println(String.format("\t\t%s", removedTask));
                    System.out.println(String.format("\tYou now have %d tasks in the list.", taskList.getSize()));
                    break;
                case "save":
                    try {
                        System.out.println("\tSaving tasks to file: " + fileName);
                        saveFile.saveTasksToFile(taskList);
                        System.out.println("\tSave done!");
                    } catch (TalkingBotException e) {
                        System.out.println(e);
                    }
                    break;
                default:
                    System.out.println("\tERROR! Unknown command detected.");
                    break;
                }
                System.out.println(hLine);
            }

            String bye = "\tBye. Hope to see you again soon!";
            System.out.println(hLine);
            System.out.println(bye);
            System.out.println(hLine);
            scanner.close();
        } catch (TalkingBotException e) {
            System.out.println(e);
        }
    }
}
