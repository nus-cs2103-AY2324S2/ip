public class Task {
    private String taskName;

    public Task() {

    }

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void echo() {
        String s = "----------------------------------------------------------\n" +
                this.taskName
                + "\n----------------------------------------------------------";
        System.out.println(s);
    }
}
