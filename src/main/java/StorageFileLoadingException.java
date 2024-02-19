public class StorageFileLoadingException extends DamonExceptions{
    public StorageFileLoadingException() {
        this.message = "Sorry, there is no existing storage file to load :(";
    }
}
