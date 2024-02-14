package baron.dao;

import java.io.File;

import baron.database.Database;
import baron.models.BaseModel;
/**
 * Abstraction class for DAo to implement all required methods.
 * TODO: Implement this to the other DAOs
 */
public abstract class BaseDao {
    private String filePath;
    public BaseDao(String filePath) {
        this.filePath = filePath;
    }
    /**
     * Creates a new modek with specified parameters
     *
     * @param model Model to be added
     */
    public void add(BaseModel model) {
        File table = Database.getTable(this.filePath);
        String data = this.toDataString(model);
        long id = Database.create(table.toPath(), data);
        model.setId((int) id);
    }

    /**
     * Deletes a line wit hthe specified line from the a specific file
     *
     * @param id   id of the line to delete
     */
    public void delete(long id) {
        File table = Database.getTable(this.filePath);
        Database.delete(table.toPath(), id);
    }

    public abstract BaseModel fromDataString(String input);
    public abstract <T> String toDataString(T t);
    public abstract BaseModel getFrom(String input);
}
