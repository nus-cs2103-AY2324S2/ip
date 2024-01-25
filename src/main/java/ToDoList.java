import java.util.ArrayList;

public class ToDoList {
    private final ArrayList<String>  tasks = new ArrayList<>();

    public void addToList(String s){
        if (s.trim().isEmpty()) {
            System.out.println("Task cannot be empty. Please enter a valid task.");
        }
        else {
            tasks.add(s);
            System.out.println(
                    "____________________________________________________________\n" +
                            "     added: " + s + "\n" +
                            "____________________________________________________________\n");
            }
    }
    public String toString(){
        if (tasks.size() == 0){
            return "____________________________________________________________\n"
                    + "NOTHING IN TO DO LIST 🎉\n"
                    +"____________________________________________________________";

        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append('.').append("\t").append(tasks.get(i)).append("\n");
        }
        return "____________________________________________________________\n" + sb + "____________________________________________________________\n";
    }


}
