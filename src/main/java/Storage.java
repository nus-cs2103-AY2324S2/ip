import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {

    File directory;
    File listFile;

    public Storage(String filename) {
        createFile(filename);
    }

    public void createFile(String filename) {
        try {
            //https://www.java67.com/2014/02/how-to-create-file-and-directory-in-java.html#:~:text=File%20provides%20methods%20like%20createNewFile,the%20directory%20is%20created%20successfully.
            boolean success = true;
            directory = new File("data");

            if (directory.exists()) {
                System.out.println("Directory already exists ...");

            } else {
                System.out.println("Directory not exists, creating now");

                success = directory.mkdir();
                if (success) {
                    System.out.printf("Successfully created new directory : %s%n", directory.getName());
                } else {
                    System.out.printf("Failed to create new directory: %s%n", directory.getName());
                }
            }
            listFile = new File(directory.getName() + "/" + filename + ".txt");

            if (success) {
                if (listFile.createNewFile()) {
                    System.out.println("File created: " + listFile.getName());
                } else {
                    System.out.println("File already exists. " + listFile.getAbsolutePath());
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
//            e.printStackTrace();
        }
    }

    public void appendToFile(String str) {
        //https://www.w3schools.com/java/java_files_create.asp
        try {
            FileWriter myWriter = new FileWriter(listFile, true);
            myWriter.write(str);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
//            e.printStackTrace();
        }
    }

    public void appendEntry(list_Entry entry) {
        appendToFile(entry.type + "," + entry.check + "," + entry.task + "," + entry.taskStart + "," + entry.taskEnd + "\n");
    }

    public void clearFile() {
        try {
            FileWriter myWriter = new FileWriter(listFile);
            myWriter.write("");
            myWriter.close();
            System.out.println("Successfully cleared the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
//            e.printStackTrace();
        }
    }



    public void loadList(ArrayList<list_Entry> loadList) {
//        ArrayList<list_Entry> loadList = new ArrayList<>();

        try {
//            boolean hasNextEntry = true;
            BufferedReader br = new BufferedReader(new FileReader(listFile));
            while (br.ready()) {
                String str = br.readLine();
                String [] words = str.split(",", 5);

                list_Entry temp;
              switch (list_Entry.taskType.valueOf(words[0])) {
                  case T:
                      temp = new list_Entry_Todo(words[2],(words[1].equals("true")));
                      break;
                  case D:
                      temp = new list_Entry_Deadline(words[2],(words[1].equals("true")), LocalDate.parse(words[4]));
                      break;
                  case E:
                      temp = new list_Entry_Event(words[2],(words[1].equals("true")),LocalDate.parse(words[3]), LocalDate.parse(words[4]));
                      break;
                  default:
                      temp = null;
                      System.out.println("ERROR: " + str);
                      break;
              }
                loadList.add(temp);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }

}
