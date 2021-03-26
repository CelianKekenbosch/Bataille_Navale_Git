package Controleur;

import Modele.*;
import static Modele.Plateau.initValPlateau;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Plateau plateau = new Plateau(32,47);   //(32,47) est la taille de la grille avec tous les caracteres
        plateau.affichePlateau();
        int grid[][] = new int[15][15];
        //Initialisation du tableau
        for(int i=0;i<15;i++)
        {
            for(int j=0;j<15;j++)
            {
                grid[i][j] = 0;
                initValPlateau(plateau);//on initilialise chaque case du plalteau à la valeur de la mer
            }
        }

        plateau.convertGridIntoPlateau(plateau.grid);
        plateau.affichePlateau();

    }
    
    //commence une partie(nouvelle si on n'a pas chargé une ancienne)
    public void startGame(){
        
    }
    
    //charge une ancienne partie déjà enregistrée
    public void loadGame(){
        
    }
    
    //affiche une aide
    public void help(){
        final String AIDE= " ";//il reste à écrire l'aide à la main
        System.out.println("voulez-vous afficher une aide ?(Y/N)");
        Scanner scanner = new Scanner(System.in);
        boolean bn=scanner.nextBoolean();
        if(bn==true){
            System.out.println(AIDE);
        }else if(bn==false){
            System.out.println("OK,pas de soucis");
        }
        
    }
    
    //stop le programme
    public void quitProgram(){
        //je pense qu'il faut utiliser la methode exit()
    }  
       
}