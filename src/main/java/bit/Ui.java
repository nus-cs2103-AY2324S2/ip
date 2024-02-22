package bit;

/**
 * This class deals with returning strings to user
 */
public class Ui {
    private String greetingMessage = "Hi! This is Bit!\nWhat shall we do today?";
    public String greet() {
        return "Hi! This is Bit!\nWhat shall we do today?";
    }

    public String sayBye() {
        return "Alright. See you soon!!";
    }

    public String fileError() {
        return "Error! Something went wrong while creating your file!";
    }

    /**
     * Print out all tasks in task list.
     * @param tasklist list to be printed out.
     */
    public String listOut(Tasklist tasklist) {
        String s;
        s = "Sure! Here is the list:\n";
        for (int i = 0; i < tasklist.getSize(); i++) {
            if (tasklist.getTask(i) == null) {
                break;
            }
            s += (i + 1) + "." + tasklist.getTask(i).toString() + "\n";
        }
        return s;
    }

    /**
     * Used to acknowledge that mark command was successful.
     * @param task task that was marked.
     */
    public String sayMarked(Task task) {
        return "Done and dusted: " + task.toString();
    }

    /**
     * Used to acknowledge that unmark command was successful.
     * @param task task that was unmarked
     */
    public String sayUnmarked(Task task) {
        return "Alright, let me uncheck that for you: " + task.toString();
    }

    /**
     * Acknowledge to user that task was successfully added
     * @param i index of added task
     * @param string type of task
     * @param task the task itself
     */
    public String sayAdded(int i, String string, Task task) {
        switch (string) {
        case "todo":
            return "I have added this todo: " + i + '.' + task.toString();
        case "event":
            return "I have added this event: " + (i) + " " + task.toString();
        case "deadline":
            return "Done! I have added this deadline" + task.toString();
        default:
            return "";
        }
    }

    /**
     * Acknowledge that task was deleted
     * @param s Deleted task in string format.
     */
    public String sayDeleted(String s) {
        return "Got it! I have deleted this item: " + s;
    }

    /**
     * Print out error messages for error handling.
     * @param string Type of error to be handled.
     */
    public String handleErrorMessage(String string) {
        switch (string) {
        case "absent":
            return "Hey, I don't think you have added that yet!";
        case "Not a number":
            return "Woah! That is not a number!";
        case "forget":
            return "Did you forget something?";
        case "NotaDate":
            return "The date you entered is invalid.";
        default:
            return "Sorry, I don't understand what you mean\n";
        }
    }


    public String printException(Exception e) {
        return e.getMessage();
    }

    /**
     * List all items in tasklist containing the keyword.
     * @param key the keyword for the search.
     * @param tasklist the tasklist being searched.
     */
    public String listHits(String key, Tasklist tasklist) {
        String s = "Sure! Here are the matches:\n";
        int j = 1;
        for (int i = 0; i < tasklist.getSize(); i++) {
            if (tasklist.getTask(i) == null) {
                break;
            }
            Task task = tasklist.getTask(i);
            if (task.containsKeyword(key)) {
                s += (j) + "." + tasklist.getTask(i).toString() + "\n";
                j++;
            }
        }
        return s;
    }

    /**
     *  This method returns a list of all deadlines due in the specified amount
     *  of days as a string
     * @param daysLeft the number of days the deadlines we are looking for are due in at max
     * @param tasklist the list we are searching
     * @return the list of deadlines due in specified number of days
     */
    public String listDueSoon(int daysLeft, Tasklist tasklist) {
        String dueMessage = "Hmmm, this tasks are due soon:\n";
        int index = 1;
        for (int i = 0; i < tasklist.getSize(); i++) {
            Task task = tasklist.getTask(i);
            if (task instanceof Deadline) {
                if (((Deadline) task).isDueIn(daysLeft)) {
                    dueMessage += index + task.toString() + "\n";
                    index++;
                }
            }
        }
        return dueMessage;
    }
}
