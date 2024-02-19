package main;

import java.io.IOException;

public class Poe {

    public static void main(String[] args) throws IOException {
        System.out.println(Ui.greetings());
        try {
            String data = Storage.loadFromFile("./data.txt");
            TaskList taskList = new TaskList(data);
            System.out.println(taskList.printList());
            Parser parser = new Parser(taskList);
        } catch (Exception e) {
            TaskList taskList = new TaskList();
            Parser parser = new Parser(taskList);
        }
    }

    public String getResponse(String input){
        String response;
        try {
            String data = Storage.loadFromFile("./data.txt");
            TaskList taskList = new TaskList(data);
            Parser parser = new Parser(taskList);
            response = parser.processInput(input);
        } catch (Exception e) {
            TaskList taskList = new TaskList();
            Parser parse = new Parser(taskList);
            response = parse.processInput(input);
        }

        return response;
    }
}
