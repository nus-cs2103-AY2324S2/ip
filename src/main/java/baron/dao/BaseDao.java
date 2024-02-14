package baron.dao;

import java.io.File;
import java.util.List;

import baron.database.Database;
import baron.models.BaseModel;
/**
 * Abstraction class for DAo to implement all required methods.
 * TODO: Implement this to the other DAOs
 */
public abstract class BaseDao {
    protected String name;
    public BaseDao(String name) {
        this.name = name;
    }
    /**
     * Creates a new modek with specified parameters
     *
     * @param model Model to be added
     */
    public void add(BaseModel model) {
        File table = Database.getTable(this.name);
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
        File table = Database.getTable(this.name);
        Database.delete(table.toPath(), id);
    }

    /**
     * Converts user input from chat into object.
     *
     * @param input User input
     * @return Converted object
     */
    public abstract BaseModel fromInputString(String input);
    /**
     * Converts data input from file into object.
     *
     * @param input User input
     * @return Converted object
     */
    public abstract BaseModel fromDataString(String input);
    // TODO: Either remove the implementation or make it abstract somehow
    public String toDataString(BaseModel t) {
        return "";
    };
    public abstract BaseModel getFrom(String input);

    /**
     * Gets all models from the data file
     *
     * @return Returns a list of models from data file
     */
    public abstract <T> List<T> getItems();
}
