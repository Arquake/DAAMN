package project.Models.Exception;

/**
 * Exception appelée si le joueur tente une rotation de la grille dans le puissance 4 alors que ce n'est pas activé
 */
public class RotationInactiveException extends Exception {
    /**
     * Crer avec le message
     */
    public RotationInactiveException() {
        super("La rotation n'est pas active pour cette partie.");
    }

    /**
     * recupere le message
     * @return message
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
