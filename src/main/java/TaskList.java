import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class TaskList {
    private ArrayList<Task> listItems = new ArrayList<>();
    private FileHandler fileHandler = new FileHandler();
    public void addTask(Task task){
        this.listItems.add(task);
        OutputMessage.informItemAdded(task, this);
    }
    public TaskList(){
        this.listItems = fileHandler.loadTasks();
    }
    public void showList(){
        System.out.println("\t____________________________________________________________");
        System.out.println("\tPer your request, I am outlining the tasks from your designated list:");
        int index = 1;
        for (Task item : this.listItems){
            System.out.println("\t" + index + ". " + item);
            index++;
        }
        System.out.println("\t____________________________________________________________\n" );
    }
    public void addTodo(String cmd){
        String[] statement = cmd.split(" ", 2);
        try {
            Todo todo = new Todo(statement[1]);
            String[] data = {statement[1]};
            this.addTask(todo);
            fileHandler.addListStateRecord("todo", data);
        }catch(ArrayIndexOutOfBoundsException e) {
            OutputMessage.informBadTodoInput();
        }
    }

    public void addDeadline(String cmd){
        String[] statement1 = cmd.split(" ", 2);
        try {
            String[] statement2 = statement1[1].split(" /by ", 2);
            Deadline ddl = new Deadline(statement2[0], statement2[1]);
            String[] data = {statement2[0], statement2[1]};
            this.addTask(ddl);
            fileHandler.addListStateRecord("deadline", data);
        }catch(ArrayIndexOutOfBoundsException e) {
            OutputMessage.informBadDeadlineInput();
        }
    }
    public void addEvent(String cmd){
        String[] statement1 = cmd.split(" ", 2);
        try {
            String[] statement2 = statement1[1].split(" /from ", 2);
            String[] statement3 = statement2[1].split(" /to ", 2);
            Event event = new Event(statement2[0], statement3[0], statement3[1]);
            String[] data = {statement2[0], statement3[0], statement3[1]};
            addTask(event);
            fileHandler.addListStateRecord("event", data);
        }catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
            OutputMessage.informBadEventInput();
        }
    }
    public void markList(int index){
        this.listItems.get(index-1).mark();
        fileHandler.modifyStateRecord(true, index-1);
        OutputMessage.informListMarked(listItems.get(index-1));
    }
    public void unmarkList(int index){
        this.listItems.get(index-1).unmark();
        fileHandler.modifyStateRecord(false, index-1);
        OutputMessage.informListUnmarked(listItems.get(index-1));
    }
    public void deleteList(int index){
        Task task = listItems.remove(index-1);
        fileHandler.removeListStateRecord( index-1);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tI acknowledge your update. The specified task has been duly removed:");
        System.out.println("\t   "+task);
        System.out.println("\tCurrently, the list comprises  " + listItems.size() + " tasks.");
        System.out.println("\t____________________________________________________________\n");
    }
    public int getSize(){
        return this.listItems.size();
    }
    @Override
    public String toString(){
        return "\tCurrently, the list comprises  " + listItems.size() + " tasks.";
    }

}
