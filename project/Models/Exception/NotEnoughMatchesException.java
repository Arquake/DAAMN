package project.Models.Exception;

public class NotEnoughMatchesException extends Exception{
    public NotEnoughMatchesException() {
        super("Not enough matches in the heap to remove");
    }
}
