package main;

import main.TaskList;

import java.io.*;

public class Storage {
    String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static String loadFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./data.txt"));

        String line = "";
        String str ="";

        while(line != null){
//            addList(line);
            str += line + "\n";
            line = reader.readLine();
        }

        reader.close();
        return str;
    }

    public static void writeToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter("./data.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (int i = 0; i < taskList.getList().size() ; i++){
            printWriter.println(taskList.getList().get(i).toStringFile());
        }
        printWriter.close();
    }


}
