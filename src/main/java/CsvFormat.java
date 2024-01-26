public interface CsvFormat {
    /**
     * Returns the CSV string representation of this task.
     *
     * @return the CSV string representation of this task.
     */
    public String toCsv();

    /**
     * Returns an Task that implements this interface from the CSV string representation.
     *
     * @param csv CSV string representation of the Task.
     * @return a Task that implements this interface from the CSV string representation.
     */
    // public Event fromCsv(String csv);
}
