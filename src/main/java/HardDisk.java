import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class HardDisk{
    private String filepath;

    public HardDisk(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> loadList() throws IOException, BelleException {
        ArrayList<Task> currList = new ArrayList<>();
        File f = new File(filepath);
        if (!f.exists()) {
            return currList;
        } else {
            Scanner listscanner = new Scanner(f);
            while (listscanner.hasNext()) {
                String currLine = listscanner.nextLine();
                String[] currTask = currLine.split(" , ");
                if (currTask[0].equals("T")) {
                    currList.add(new TodoTask(currTask[2], Boolean.valueOf(currTask[1])));
                } else if (currTask[0].equals("D")) {
                    currList.add(new DeadlineTask(currTask[2], Boolean.valueOf(currTask[1]),
                            currTask[3]));
                } else if (currTask[0].equals("E")) {
                    currList.add(new EventTask(currTask[2], Boolean.valueOf(currTask[1]),
                            currTask[3], currTask[4]));
                } else {
                    throw new BelleException("Not valid item in HardDisk");
                }
            }
            return currList;
        }

    }

    public void save(ArrayList<Task> currList) throws IOException, BelleException {
        File f = new File(this.filepath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }

        FileWriter fw = new FileWriter(this.filepath);
        String currItem = "";
        String div = " , ";
        for (Task i : currList) {
            if (i.getType().equals("T")) {
                currItem = i.getType() + div + i.getDone() + div + i.getName() + System.lineSeparator();
            } else if (i.getType().equals("D")) {
                DeadlineTask d = (DeadlineTask) i;
                currItem = d.getType() + div + d.getDone() + div + d.getName() + div
                        + d.getDeadline() + System.lineSeparator();
            } else if (i.getType().equals("E")) {
                EventTask e = (EventTask) i;
                currItem  = e.getType() + div + e.getDone() + div + e.getName()
                        + div + e.getStart() + div + e.getEnd() + System.lineSeparator();
            } else {
                throw new BelleException("There is an invalid Item when trying to save list");
            }
            fw.write(currItem);
        }
        fw.close();

    }


}

