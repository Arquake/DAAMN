package project.Models.Exception;

public class InvalidColumException extends Exception {
    /**
     * Exception appelée quand la colonne visée n'existe pas ou est pleine .
     */
    public InvalidColumException() {
        super();
    }

    /**
     * get le message
     * @return message
     */
    @Override
    public String getMessage() {
        return "La colonne visée n'existe pas ou est pleine.";
    }
}
