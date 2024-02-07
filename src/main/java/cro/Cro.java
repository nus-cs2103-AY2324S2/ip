package cro;

public class Cro {

    public TaskList taskList;
    public Storage storage;
    private Ui ui;
    public Cro(String filePath) {
        ui = new Ui(this);
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (CroException e) {
            e.getMessage();
            taskList = new TaskList();
        }
    }
    public void run() {
        while (ui.reading()) {
        }
        storage.updateSave(taskList);
    }
    public static void main(String[] args){

        new Cro("saveFile.txt").run();


    }
}
