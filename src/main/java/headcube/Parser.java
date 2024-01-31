package headcube;
public class Parser {
    private Ui ui;
    private TaskList taskList;

    private Storage storage;
    public Parser(Ui ui, TaskList taskList, Storage storage){
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }
    public void parse(String input) {
        try {
            String[] split = input.split(" ",2);

            if (input.equals("list")) {
                ui.list(taskList);
            } else if (split[0].equals("mark")){
                taskList.mark(Integer.parseInt(split[1]));

            } else if (split[0].equals("delete")) {
                taskList.delete(Integer.parseInt(split[1]));
            } else if (input.equals("save")) {
                storage.save(taskList);
            } else {
                String[] string = input.split(" ",2 );
                String event = string[0];
                String description;

                if (event.equals("todo")) {
                    if (split.length < 2 || split[1].isBlank()) {
                        throw new HeadCubeException("Todo cannot be empty!!");
                    }
                    taskList.add(new ToDos(split[1]));
                } else if (event.equals("deadline")) {
                    String[] parts = split[1].split(" /by ",2);
                    description = parts[0];
                    String by = parts[1];
                    taskList.add(new Deadlines(description,by));
                } else if (event.equals("event")) {
                    String[] parts = split[1].split(" /from ", 2);
                    description = parts[0];
                    String[] times = parts[1].split(" /to ", 2);
                    String start = times[0].trim();
                    String end = times[1].trim();
                    taskList.add(new Events(description, start, end));
                } else {
                    throw new HeadCubeException("I do not understand what that means!!");
                }
                System.out.println("Got it. I've added this task:\n  "
                        + taskList.get(taskList.size() - 1));
                System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
            }
        } catch (HeadCubeException e) {
            ui.error(e.getMessage());
        }
    }

}
