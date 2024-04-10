package project.Models.Exception;

public class EmptyHeapException extends Exception{
    /**
     * Exception for when the heap is empty
     * Exception pour quand le tas est vide
     */
    public EmptyHeapException() {
        super("The heap is already empty");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
