public class UserInterface {
    public static final String WELCOME = "\tHi, I'm Gronk!\n"
            + "\tWhat's up today?";

    public static final String GOODBYE = "\tSystem closing. Goodbye!";

    public static final String LINE = "  ----------------------------------------";

    public UserInterface() {}

    public void printGoodbye() {
        System.out.println(GOODBYE);
    }

    public void printHello() {
        printMessage(WELCOME);
    }

    public void printMessage(String message) {
        String prettyMessage = LINE + "\n" + message + "\n" + LINE;
        System.out.println(prettyMessage);
    }

    public String returnAllTasks(TaskList taskList) {
        try {
            if (taskList.getSize() == 0) {
                throw new EmptyListException();
            }
            String m = "";
            for (int j = 0; j < taskList.getSize(); j++) {
                m += "\t" + Integer.toString(j + 1) + ". " + taskList.getTask(j).toString() + "\n";
            }
            return m.substring(0, m.length() - 1);
        } catch (EmptyListException e) {
            printMessage(e.toString());
        }
        return "";
    }
}
