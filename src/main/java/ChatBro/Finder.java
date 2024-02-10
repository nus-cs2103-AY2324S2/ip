package ChatBro;

public class Finder {
    public static void findTask(String keyword) {
        int count = 0;
        for (int i = 1; i <= TaskManager.getTaskCount(); i++) {
            if (TaskManager.getTask(i).getDescription().contains(keyword)) {
                if (count == 0) {
                    Ui.printLine();
                    Ui.printWithoutLine("Here are the matching tasks in your list bro:");
                }
                count++;
                Ui.printWithoutLine(count + ". " + TaskManager.getTask(i).toString());
            }
        }
        if (count == 0) {
            Ui.printMessage("Oops, no matching tasks found in your list bro.");
        }
    }
}
