package baron.models;

/**
 * Abstract class that all models will inherit from. Provides a simple ID attribute only.
 */
public abstract class Model {
    private int id;

    /**
     * Defaults ID to 0.
     */
    public Model() {
        this.id = 0;
    }
    public Model(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
