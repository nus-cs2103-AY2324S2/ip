public class Store {
    private Task[] storage = new Task[100];
    private int count = 0;

    public String addText(String text) {
        Task task = new Task(text);
        storage[count++] = task;
        return "added: " + text;
    }
    public void displayStore() {
        for (int i = 0; i < count; ++i) {
            System.out.println((i + 1) + "." + storage[i].getStatusUpdate());
        }
    }

    public void markTask(int i) {
        Task task = storage[i - 1];
        task.markAsDone();
    }

    public void unmarkTask(int i) {
        Task task = storage[i - 1];
        task.markAsNotDone();
    }



}