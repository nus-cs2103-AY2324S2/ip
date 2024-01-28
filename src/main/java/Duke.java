public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.loadTaskList()); 
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

    public void run() {
        this.ui = new Ui(System.in); 
        System.out.println(this.ui.greet());

        String instr = this.ui.getInstr(); 
        while (!instr.equals("bye")) {
            if (instr.equals("list")) {
                this.tasks.listOut();
            } else {
                String cmdWord = instr.split(" ")[0];
                try {
                    if (cmdWord.equals("unmark")) {
                        this.tasks.unmark(instr, this.storage);
                    } else if (cmdWord.equals("mark")) {
                        this.tasks.mark(instr, this.storage);
                    } else if (cmdWord.equals("delete")) {
                        this.tasks.delete(instr, this.storage);
                    } else {
                        if (cmdWord.equals("todo")) {
                            this.tasks.addTask(TaskList.TaskCommand.TODO, instr, this.storage);
                        } else if (cmdWord.equals("deadline")) {
                            this.tasks.addTask(TaskList.TaskCommand.DEADLINE, instr, this.storage);
                        } else if (cmdWord.equals("event")) {
                            this.tasks.addTask(TaskList.TaskCommand.EVENT, instr, this.storage);
                        } else {
                            throw new DukeException("OOPS!!! What is that? I'm sorry, but I don't recognise this command :-( \nTry another command!"); 
                        } 
                    }
                } catch (DukeException e) {
                    System.out.println(e.toString());
                }
            }  
            instr = this.ui.getInstr(); 
        }
        System.out.println(this.ui.exit());
        this.ui.close();
    }
    public static void main(String[] args) {
        new Duke("src/main/data/caching.txt").run();
    }
}
