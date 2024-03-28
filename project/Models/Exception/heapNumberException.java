package project.Models.Exception;

public class heapNumberException extends Exception{
    public heapNumberException() {
        super("The number of the Heap was greater than expected");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
