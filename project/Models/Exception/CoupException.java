package project.Models.Exception;

public class CoupException extends Exception{
    /**
     * Exception for when the input is invalid
     * Exception pour quand l'entrée est invalide
     */
    public CoupException() {
        super("Invalid Input");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
