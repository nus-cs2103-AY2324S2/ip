package main;

import java.io.IOException;

public class Poe {

    public static void main(String[] args) {
    }

    public String welcome() {
        try {
            String data = Storage.loadFromFile("./data.txt");
            TaskList taskList = new TaskList(data);
            return Ui.greetings() + taskList.remindTask();
        } catch (Exception e) {
            return Ui.greetings();
        }
    }
    public String getResponse(String input) {
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
