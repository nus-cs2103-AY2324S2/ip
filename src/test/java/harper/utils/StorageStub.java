package harper.utils;

public class StorageStub extends Storage {
    public StorageStub(String folderName, String fileName) {
        super(folderName, fileName);
    }

    @Override
    public void save(TaskList taskList) {
    }
}
