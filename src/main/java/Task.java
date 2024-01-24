public class Task {
    private String taskName;
    private static String taskList[] = new String[100];

    private static int counter = 0;

    public Task() {


    }

    public Task(String taskName) {
        this.taskName = taskName;

        if (!this.taskName.equals("list")) {
            Task.taskList[counter] = this.taskName;
            Task.counter++;
        }
    }

    public void display() {

        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < Task.counter; i++) {
            System.out.println(i+1 + ". " + Task.taskList[i]);
        }
        System.out.println("----------------------------------------------------------");
    }

    public void add() {
        String s = "----------------------------------------------------------\n" +
                "Added: " + this.taskName

                + "\n----------------------------------------------------------";
        System.out.println(s);
    }
}
