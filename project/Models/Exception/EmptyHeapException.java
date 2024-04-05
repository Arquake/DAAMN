package project.Models.Exception;

public class EmptyHeapException extends Exception{
    public EmptyHeapException() {
        super("The heap is already empty");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
