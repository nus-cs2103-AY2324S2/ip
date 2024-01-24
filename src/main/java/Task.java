public class Task {
    private String taskName;
    private static String taskList[] = new String[100];

    private static int counter = 0;

    public Task() {


    }

    public Task(String taskName) {
        this.taskName = "added: " + taskName;
        Task.taskList[counter] = this.taskName;
        Task.counter++; 
    }

    public void echo() {
        String s = "----------------------------------------------------------\n" +
                this.taskName
                + "\n----------------------------------------------------------";
        System.out.println(s);
    }
}
