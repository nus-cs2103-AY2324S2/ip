import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> listItems = new ArrayList<>();
    private void addTask(Task task){
        this.listItems.add(task);
        OutputMessage.informItemAdded(task, this);
    }
    @Override
    public String toString(){
        return "\tCurrently, the list comprises  " + listItems.size() + " tasks.";
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
            this.addTask(todo);
        }catch(ArrayIndexOutOfBoundsException e) {
            OutputMessage.informBadTodoInput();
        }
    }

    public void addDeadline(String cmd){
        String[] statement1 = cmd.split(" ", 2);
        try {
            String[] statement2 = statement1[1].split(" /by ", 2);
            Deadline ddl = new Deadline(statement2[0], statement2[1]);
            this.addTask(ddl);
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
            addTask(event);
        }catch(ArrayIndexOutOfBoundsException e) {
            OutputMessage.informBadEventInput();
        }
    }
    public void markList(int index){
        this.listItems.get(index-1).mark();
        OutputMessage.informListMarked(listItems.get(index-1));
    }

    public void unmarkList(int index){
        this.listItems.get(index-1).unmark();
        OutputMessage.informListUnmarked(listItems.get(index-1));
    }

    public void deleteList(int index){
        Task task = listItems.remove(index-1);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tI acknowledge your update. The specified task has been duly removed:");
        System.out.println("\t   "+task);
        System.out.println("\tCurrently, the list comprises  " + listItems.size() + " tasks.");
        System.out.println("\t____________________________________________________________\n");
    }

}
