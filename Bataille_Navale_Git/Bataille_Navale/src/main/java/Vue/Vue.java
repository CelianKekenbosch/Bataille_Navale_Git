package Vue;

import Modele.Plateau;

public class Vue{

    private static final int nbLigne = 32;
    private static final int nbColonne = 47;

    //Methode qui print entierement un plateau pre-rempli/modifie dans la console
    public static void affichePlateau(Plateau plateau)
    {
        int i,j;
        for(i=0;i<nbLigne;i++)
        {
            for(j=0;j<nbColonne;j++)
            {
                System.out.print(plateau.plateau[i][j]);
            }
            System.out.println(" ");   
        }
    }
    
    //Methode qui affiche les cases touchees chez l'IA
    public static void affichePlateauMasque(Plateau plateau)
    {
        int i,j;
        for(i=0;i<nbLigne;i++)
        {
            for(j=0;j<nbColonne;j++)
            {
                switch (plateau.plateau[i][j]) {
                    case 'X':
                        System.out.print("X");
                        break;
                    case '*':
                        System.out.print(" ");
                        break;
                    case '@':
                        System.out.print(" ");
                        break;
                    case '%':
                        System.out.print(" ");
                        break;
                    case '#':
                        System.out.print(" ");
                        break;
                    default:
                        System.out.print(plateau.plateau[i][j]);
                        break;
                }
            }
            System.out.println(" ");   
        }
    }
}
