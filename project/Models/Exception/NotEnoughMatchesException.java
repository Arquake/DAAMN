package project.Models.Exception;

public class NotEnoughMatchesException extends Exception{
    /**
     * Exception for when there are not enough matches in the heap to remove
     * Exception pour quand il n'y a pas assez d'allumettes dans le tas pour en retirer
     */
    public NotEnoughMatchesException() {
        super("Not enough matches in the heap to remove");
    }
}
