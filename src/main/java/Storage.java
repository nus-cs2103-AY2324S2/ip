import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

	private String filePath;

	public Storage(String filePath) {
		this.filePath = filePath;
	}

	public ArrayList<Task> load() {
		ArrayList<Task> store = new ArrayList<Task>();
		try {
			File f = new File(filePath);
			Scanner s = new Scanner(f); // create a Scanner using the File as the source
			while (s.hasNext()) {
				Task nextTask = Parser.parseFromStorage(s.nextLine());
				store.add(nextTask);
			}
		} catch (FileNotFoundException e) {
			try {
				File f = new File(filePath);
				f.getParentFile().mkdirs();
				f.createNewFile();
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return store;
	}

	public void saveTasks(TaskList store) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		String toSave = "";
		for (Task task : store.getTasks()) {
			toSave = toSave + task.getType() + ";;;"
					+ (task.getStatus() ? 1 : 0) + ";;;"
					+ task.getDetails() + "\n";
		}
		fw.write(toSave);
		fw.close();
	}


}
