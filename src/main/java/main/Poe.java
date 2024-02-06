package main;

import main.Parser;

import java.io.*;

public class Poe {

//    public void run(){
//        ui.greetings();
//        boolean isExit = false;
//        while (!isExit) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.showLine(); // show the divider line ("_______")
//                Command c = main.Parser.parse(fullCommand);
//                c.execute(tasks, ui, storage);
//                isExit = c.isExit();
//            } catch (DukeException e) {
//                ui.showError(e.getMessage());
//            } finally {
//                ui.showLine();
//            }
//        }
//
    public static void main(String[] args) throws IOException {

        Ui.greetings();
        try {
            String data = Storage.loadFromFile();
            TaskList taskList = new TaskList(data);
            taskList.printList();
            Parser.input(taskList);
        }catch (Exception e){
            TaskList taskList = new TaskList();
            Parser.input(taskList);
        }



    }
}
