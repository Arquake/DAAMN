package project.Models.Exception;

public class HeapNumberException extends Exception{
    public HeapNumberException() {
        super("The number of the Heap was greater than expected");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
