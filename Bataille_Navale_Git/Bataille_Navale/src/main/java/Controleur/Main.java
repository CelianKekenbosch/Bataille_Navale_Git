package Controleur;

import Modele.*;
import static Modele.Plateau.initValPlateau;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Plateau plateau = new Plateau(32,47);   //(32,47) est la taille de la grille avec tous les caracteres
        
        initValPlateau(plateau);//on initilialise chaque case du plateau à la valeur de la mer
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
        final String AIDE= " vous avez choisie aide , les regles du jeux sont les suivantes :\n" +
                "Vous devez deplacer tirer sur les bateaux ennemis . pour cela , vous tirerez sur les cases ennemis en entrant les coordonnées que vous voulez attaquer,\n" +
                "Vous pouvez egalement deplacer un navire s’il n’est pas touché par l’ennemi, pour cela entrez les coordonnés sur lesquels vous voulez l’affecter.\n"
                + "un navire n'est coulé que si toutes les cases qu'il occupe sont touchées" +
                "Pour gagner vous devez couler tous les navires de l’ennemis.\n" +
                "Vous pouvez quitter la partie ne appuyant sur Q\n."
                + "un cuirassé occupe 7 cases\n"
                + "un croiseur occupe 5 cases\n"
                + "un destroyer occupe 3 cases\n"
                + "un sous marin occupe 1 case\n"
                + "sur la grille de tirs les croix signifient que vous avez toucher un navire et il ne peut plus se déplacer\n"
                + "sur votre grille les navires touchés sont représentés par des symboles \n"
                + "La puissance de tir des navires s'organise de la maniere suivante : un destroyer et un sous marin peuvent toucher 1 case, un cuirassé peut toucher 8 cases adjacentes au point d'impact dans chaque directions, tandis qu'un croiseur peut toucher cases adjacentes au point d'impact dans chaque directions\n" 
                + "Les sous marins sont les seuls pouvant couler d'autres sous marins\n"
                + "destroyer peuvent lancer des fusées eclairantes qui vont afficher pendant un tour un carré de coté 4 sur la grille adverse\n ";
       
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