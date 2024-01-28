public class Jimmy {
    private Task[] list = new Task[100];
    private int counter = 0;

    public void greetUser() {
        System.out.println("Hello! I'm Jimmy \nWhat can I do for you?");
    }
    public void storeUserTask(String input) {
        list[counter] = new Task(input);
        System.out.println("added: " + input);
        counter++;
    }
    public void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ": " + list[i].toString());
        }
    }
    public void markTaskComplete(int taskIndex) {
        Task curr = list[taskIndex];
        curr.markAsComplete();
    }
    public void markTaskIncomplete(int taskIndex) {
        Task curr = list[taskIndex];
        curr.markAsIncomplete();
    }
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
