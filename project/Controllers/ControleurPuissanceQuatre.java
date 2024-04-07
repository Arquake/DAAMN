package project.Controllers;


import project.Models.Exception.InvalidColumException;
import project.Models.Exception.NombreRotationMaximumAtteintException;
import project.Models.Exception.RotationInactiveException;
import project.Models.Joueur;
import project.Models.PlateauPuissance;
import project.Views.AbstractIhm;
import project.Views.IhmPuissance;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Controller for the Puissance 4
 */
public class ControleurPuissanceQuatre extends AbstractController {

    private PlateauPuissance jeu;
    private final IhmPuissance ihm;

    HashMap<Joueur,Integer> nbRestantDeRotation = new HashMap<>();

    /**
     * Initialize the game
     *
     * @param ihm the Ihm ( View )
     */
    public ControleurPuissanceQuatre(AbstractIhm ihm) {
        super.setIhm(ihm);
        super.createPlayers();
        this.ihm = (IhmPuissance) super.getIhm();
    }

    /**
     * game loops until one player wins
     * makes the actual heap
     * initializes game with joueur 0
     */

    protected void playGame() {
        String rotation = ihm.demanderRotation();
        boolean isRotationActive = rotation.equalsIgnoreCase("Y");

        for (Joueur joueur : joueurs){
            nbRestantDeRotation.put(joueur,4);
        }

        int playerTurn = 0;

        jeu = new PlateauPuissance();
        ihm.afficherPlateau(jeu.toString());

        String coup;
        // Game loop
        while (true) {
            // Ask the current player for their move
            coup = ihm.demanderCoup(joueurs[playerTurn].getNom());
            try {
                gestionCoup(coup, playerTurn, isRotationActive);

                if (jeu.checkWin() != -1) {
                    joueurs[playerTurn].increaseScore();
                    ihm.victory(joueurs[playerTurn].getNom(), jeu.toString());
                    break;
                }
                if (jeu.boardCompleted()) {
                    ihm.noWinBoardFull(jeu.toString());
                    break;
                }
                ihm.afficherPlateau(jeu.toString());
                // If the move was successful, update the next player
                playerTurn = (playerTurn + 1) % 2;
            }
            catch (NombreRotationMaximumAtteintException e) {
                ihm.afficherErreur(e.getMessage());
            } catch (RotationInactiveException e) {
                ihm.afficherErreur(e.getMessage());
            } catch (InvalidColumException e){
                ihm.afficherErreur(e.getMessage());
            }
        }
    // Announce the winner and update their score
    }

    /**
     * Recoit le coup du joueur, le traite et le joue s'il est correcte
     * @param coup Le coup du joueur
     * @param playerTurn Le numero du joueur qui effectue le coup
     * @param isRotationActive Si la rotation du jeu est possible
     * @throws InvalidColumException Leve invalidColumnException si
     */
    private void gestionCoup(String coup, int playerTurn, boolean isRotationActive)
            throws InvalidColumException, NombreRotationMaximumAtteintException, RotationInactiveException {
        if (!(coup.isBlank() || coup.isEmpty())) {
            Pattern patternChiffre = Pattern.compile("^[0-9]", Pattern.CASE_INSENSITIVE);

            Matcher matcherChiffre = patternChiffre.matcher(coup);
            boolean matcherRotaHoraire = coup.equalsIgnoreCase("H");
            boolean matcherRotaAntiHoraire = coup.equalsIgnoreCase("A");

            if (matcherChiffre.find()) {
                int[] data = new int[2];
                data[0] = Integer.parseInt(matcherChiffre.group())-1;
                data[1] = playerTurn+1;
                jeu.jouerCoup(data);
            }
            else if (matcherRotaHoraire || matcherRotaAntiHoraire) {
                if (isRotationActive) {
                    if (nbRestantDeRotation.get(joueurs[playerTurn]) > 0) {
                        if (matcherRotaHoraire) {
                            jeu.tournerSensHoraire();
                        } else {
                            jeu.tournerSensAntiHoraire();
                        }
                        nbRestantDeRotation.replace(joueurs[playerTurn], nbRestantDeRotation.get(joueurs[playerTurn]) - 1);
                    } else { throw new NombreRotationMaximumAtteintException(); }
                } else { throw new RotationInactiveException(); }

            }
            else { throw new InvalidColumException();}
        } else { throw new InvalidColumException(); }
    }
}