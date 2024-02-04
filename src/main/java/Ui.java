public class Ui {
    private Storage storage;
    private TaskList tasks;
    private String input;

    public Ui(Storage storage, String input){
        this.storage = storage;
        this.input = input;
    }

    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    public static void sayHi() {
        String name = "____________________________________________________________ \n"
                + "Hello! I'm RATZCHAT \n"
                + "How can I help you today?";
        System.out.println(name);
        printLine();
    }

    /**
     * Prints a line of underscores for formatting purposes.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printLoadingError() {
        System.out.println("You have not made a file yet!");
    }

    public static void printBye() {
        System.out.println("BYEBYE. Thank you for using RATZCHAT!");
    }
    public static void printList(TaskList tasks) {
        System.out.println("These are your to-dos: ");
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    public static void printNotMarked(Task task) {
        System.out.println("I've unmarked this task! It is now not done yet:\n  " + task);
    }

    public static void printMarked(Task task) {
        System.out.println("I've marked this task as done:\n  " + task);
    }

    public static void printWrongTaskNumber() {
        System.out.println("Please specify which task number and make sure it's correct!");
    }

    public void addDeadline(Deadlines d) {
        System.out.println("Ok! I've added this deadline: " + d);
    }

    public void invalidDate() {
        System.out.println("Invalid input format. Please provide a valid date/time.");
    }

    public void invalidRequest() {
        System.out.println("I'm sorry, I don't understand! Please type your request again.");
    }

    public void invalidTask() {
        System.out.println("Sorry, please complete your request by specifying the details of the task!");
    }

    /**
     * Handles the creation and addition of Event tasks to the task list.
     * User input specifying the Event description and date range.
     */



    public void handleInput(String input) {
        Parser p = new Parser(input, this, tasks, storage);
        printLine();
        p.parse();

        System.out.println("I'm sorry, I don't understand! Please type your request again.");
        printLine();
    }


}