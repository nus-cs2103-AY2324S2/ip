package duke;

/**
 * Handles the parsing of inputs from the user.
 */
public class Parser {
    /**
     * Handles the parsing of inputs from the user
     * Runs another function based on the input
     *
     * @param taskList the current list of tasks the user has
     * @param request the input from the user
     * @param reading true if this method is used to read the save file, else false
     * @param isDone whether to mark tasks created by this method to be done or not
     */
    public static void commands(TaskList taskList, String request, boolean reading, boolean isDone) {
        String[] words = request.split(" ", 0);
        int length = words.length;
        boolean addSuccessful = false;

        switch (words[0]) {
            case "true": {
                commands(taskList, request.substring(5), reading, true);
                break;
            }
            case "false": {
                commands(taskList, request.substring(6), reading, false);
                break;
            }

            case "list": {
                System.out.println(taskList.list());
                break;
            }
            case "mark": {
                int index = Integer.parseInt(words[1]) - 1;
                if(index > taskList.getSize() - 1 || index < 0) {
                    System.out.println("Invalid index!");
                } else {
                    taskList.get(index).mark();
                }
                break;
            }
            case "unmark": {
                int index = Integer.parseInt(words[1]) - 1;
                if(index > taskList.getSize() - 1 || index < 0) {
                    System.out.println("Invalid index!");
                } else {
                    taskList.get(index).unmark();
                }
                break;
            }
            case "delete": {
                int index = Integer.parseInt(words[1]) - 1;
                taskList.delete(index);
                break;
            }
            case "todo": {
                addSuccessful = taskList.addToDo(words, !reading, isDone);
                break;
            }
            case "deadline": {
                addSuccessful = taskList.addDeadline(words, !reading, isDone);
                break;
            }
            case "event": {
                addSuccessful = taskList.addEvent(words, !reading, isDone);
                break;
            }
            default:
                System.out.println("Sorry, I don't know this command :(");
                break;
        }
    }
}
