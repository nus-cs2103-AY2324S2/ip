import java.util.ArrayList;

public class ToDoList {
    private final ArrayList<Task>  tasks = new ArrayList<>();

    public void addToList(String s){
        if (s.trim().isEmpty()) {
            System.out.println("Task cannot be empty. Please enter a valid task.");
        }
        else {
            tasks.add(new Task(s));
            System.out.println(
                    "____________________________________________________________\n" +
                            "     added: " + s + "\n" +
                            "____________________________________________________________\n");
            }
    }

    public void showLists(){
        if (tasks.size() == 0){
            System.out.println( "____________________________________________________________\n"
                    + "NOTHING IN TO DO LIST 🎉\n"
                    +"____________________________________________________________");

        } else{
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append('.').append("\t").append(tasks.get(i)).append("\n");
        }
            System.out.println("____________________________________________________________\n" + sb + "____________________________________________________________\n");

        }
    }
    public void showMark(int taskNumber) {
        if (!isValidIndex(taskNumber)) {
            System.out.println("Please input valid number  \n to see the available number(s) of your task type list");
        } else {
            Task t = tasks.get(taskNumber - 1);
            t.markAsDone();
            System.out.println("____________________________________________________________\n" +
                    "Nice! I've marked this task as done:\n" +
                    t + "\n" +
                    "____________________________________________________________");
        }
    }
    public void showUnmark(int taskNumber){
        if (!isValidIndex(taskNumber)) {
            System.out.println("Please input valid number  \nto see the available number(s) of your task type list");
        } else {
            Task t = tasks.get(taskNumber - 1);
            t.markAsNotDone();
            System.out.println("____________________________________________________________\n" +
                    "OK, I've marked this task as not done yet:\n" +
                    t + "\n" +
                    "____________________________________________________________");
        }
    }
    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }




}
