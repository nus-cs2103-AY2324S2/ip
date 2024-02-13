package main;

import main.TaskList;

import java.io.*;

public class Storage {
    String filePath;

    public static String loadFromFile(String filePath) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line = "";
            String str = "";

            while (line != null) {
//            addList(line);
                str += line + "\n";
                line = reader.readLine();
            }

            reader.close();
            return str;
        }catch (FileNotFoundException e){
            System.out.println("whoops, file not found");
            return "";
        }
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
