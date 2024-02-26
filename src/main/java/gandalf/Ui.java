package gandalf;

public class Ui {
    public Ui() {

    }
    public String listUi(TaskList tasks) {
        return "Total number of tasks so far: " + (tasks.getList().size());
    }
    public String mark() {
        return "Humans truly are remarkable creatures";
    }

    public String unmark() {
        return "Fret not, for its not about how much we do, but how much we did";
    }
    public String showExpenses(String expensesName, double totalSum) {
        return "Total expenses for " + expensesName + ": " + totalSum + " dollars";
    }
    public String delete() {
        return "I have removed the task";
    }
    public String bye() {
        return "So here at last, comes the end of our fellowship. I will not say: Do not weep. "
                + "For not all tears are an evil.";
    }
}
