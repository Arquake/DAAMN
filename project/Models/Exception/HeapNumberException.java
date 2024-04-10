package project.Models.Exception;

public class HeapNumberException extends Exception{
    public HeapNumberException() {
        super("The number of the Heap was not expected");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
