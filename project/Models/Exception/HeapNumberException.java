package project.Models.Exception;

public class HeapNumberException extends Exception{
    /**
     * Exception for when the number of the Heap was not expected
     * Exception pour quand le nombre du tas n'était pas attendu
     */
    public HeapNumberException() {
        super("The number of the Heap was not expected");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
