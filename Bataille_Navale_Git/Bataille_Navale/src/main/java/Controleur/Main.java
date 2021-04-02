package Controleur;

import Modele.*;
import static Modele.Plateau.initValPlateau;
import java.util.Scanner;
import static Modele.Ship.move;
import static Modele.Ship.fusee_eclairante;
//import static Modele.Ship.fire;

public class Main {

    public static void main(String[] args) {
        int choix;
        int action;
        int taille;
        String type;
        boolean victoire=false;
        boolean save=false;
        System.out.println("Bonjours! Vous jouez à la bataille Navale(revisitée), choissisez ce que vous voulez faire:");
        System.out.println(" ");
        System.out.println(" 1. Commencer une nouvelle partie.");
        System.out.println(" ");
        System.out.println(" 2. Charger une ancienne partie.");
        System.out.println(" ");
        System.out.println(" 3. Afficher une aide.");
        System.out.println(" ");
        System.out.println(" 4. Quitter le Programme.");

        Scanner scanner = new Scanner(System.in);
        do{
            choix= scanner.nextInt();
        }while((choix!=1)&&(choix!=2)&&(choix!=3)&&(choix!=4));
        switch(choix){
            case 4://on arrete le programme de force
                System.exit(0);
                break;
            case 3://on affiche une aide déjà écrite
                help();
                break;
            case 2://on charge une partie déjà sauvegardée
                break;
            default://on commence une toute nouvelle partie
                Plateau plateau = new Plateau(32,47);   //(32,47) est la taille de la grille avec tous les caracteres
                Plateau plateauTir = new Plateau(32,47);
                Plateau plateauIA = new Plateau(32,47);
                initValPlateau(plateau);//on initialise chaque case du plateau des bateaux du joueur à la valeur de la mer
                initValPlateau(plateauTir);//on initialise chaque case du plateau de tir du joueur à la valeur de la mer
                initValPlateau(plateauIA);//on initialise chaque case du plateau des bateaux du joueur à la valeur de la mer
                plateau.convertGridIntoPlateau(plateau.grid);
                plateauTir.convertGridIntoPlateau(plateauTir.grid);//c une différente méthode de convertion pour cette grille
                plateauIA.convertGridIntoPlateau(plateauIA.grid);
                //on appelle 10x  randomPositionOneBoat() pour les différents bateaux du joueur
                /* */
                //on appelle 10x  randomPositionOneBoat() pour les différents bateaux de l'ordinateur
                do{
                    
                    
                    System.out.println("C'est votre tour, choissisez votre prochaine action:");
                    System.out.println("");
                    plateau.affichePlateau();
                    System.out.println(" ");
                    plateauTir.affichePlateau();
                    System.out.println("1. tirer avec un bateau");
                    System.out.println("");
                    System.out.println("2. tirer une fusée éclairante avec un destroyer");
                    System.out.println("");
                    System.out.println("3. bouger un bateau intact d'une case ");
                    System.out.println("");
                    System.out.println("4. Afficher l'aide ");
                    System.out.println("");
                    System.out.println("5. Quitter et sauvegarder la partie actuelle ");
                    System.out.println("");
                    System.out.println("6. Afficher le plateau de l'odinateur(juste pour la soutenance ");
                    Scanner scannerAction = new Scanner(System.in);
                    action=scannerAction.nextInt();
                    switch(action){
                        case 1:
                            System.out.println("choissiser le bateau avec lequel vous voulez tirer");//pareil
                            //Scanner scannerShip= new Sscanner(System.in);
                            //nameShip=scannerShip.nextLine();
                            //nameShip.fire(plateauTir, nameShip.firePower,nameShip.typeShip);
                            plateau.affichePlateau();
                            System.out.println(" ");
                            plateauTir.affichePlateau();
                            break;
                        case 2:
                            System.out.println("rentrez le type de bateau qui tire la fusée éclairante(il faut que ce soit un destroyer)");
                            Scanner scannerType = new Scanner(System.in);//à changer quand les bateaux seront nommés
                            type=scannerType.nextLine();
                            fusee_eclairante(plateauTir,plateauIA,type);
                            
                            break;
                        case 3:
                            System.out.println("rentrez la taille du bateau que vous voulez bouger");
                            Scanner scannerTest = new Scanner(System.in);//pareil
                            taille=scannerTest.nextInt();
                            move(plateau,taille);
                            plateau.affichePlateau();
                            System.out.println(" ");
                            plateauTir.affichePlateau();
                            break;
                        case 4:
                            help();
                            break;
                        case 5:
                            System.out.println("Vous avez décider de quitter la partie, elle sera sauvegardée ne vous inquiétez pas!");
                            //quitter+sauvegarder
                            save=true;
                            break;
                        default:
                            plateauIA.affichePlateau();
                            break;
                    }
                    System.out.println("C'est le tour de l'ordinateur");
                    System.out.println("l'ordinateur va faire son action :");
                    //iaTurn();
                    
                    
                }while((victoire==false)&&(save==false));
                
                break;
        }
        
        

    }
    
    //commence une partie(nouvelle si on n'a pas chargé une ancienne)
    public static void startGame(){
        
    }
    
    //charge une ancienne partie déjà enregistrée
    public static void loadGame(){
        
    }
    
    //affiche une aide
    public static void help(){
        char bn;
        final String AIDE= "\" vous avez choisie aide , les regles du jeux sont les suivantes :\" \n" +
"                \"Vous devez deplacer tirer sur les bateaux ennemis . pour cela , vous tirerez sur les cases ennemis en entrant les coordonnées que vous voulez attaquer,\" \n" +
"                \"Vous pouvez egalement deplacer un navire s’il n’est pas touché par l’ennemi, pour cela entrez les coordonnés sur lesquels vous voulez l’affecter.\"\n" +
"                 \"un navire n'est coulé que si toutes les cases qu'il occupe sont touchées\"\n " +
"                \"Pour gagner vous devez couler tous les navires de l’ennemis.\" \n" +
"                \"Vous pouvez quitter la partie ne appuyant sur Q.\"\n" +
"                 \"un cuirassé occupe 7 cases\"\n" +
"                 \"un croiseur occupe 5 cases\"\n" +
"                 \"un destroyer occupe 3 cases\"\n" +
"                 \"un sous marin occupe 1 case\"\n" +
"                 \"sur la grille de tirs les croix signifient que vous avez toucher un navire et il ne peut plus se déplacer\"\n" +
"                 \"sur votre grille les navires touchés sont représentés par des symboles \"\n" +
"                 \"La puissance de tir des navires s'organise de la maniere suivante : un destroyer et un sous marin peuvent toucher 1 case, un cuirassé peut toucher 8 cases adjacentes au point d'impact dans chaque directions, tandis qu'un croiseur peut toucher cases adjacentes au point d'impact dans chaque directions\" \n" +
"                 \"Les sous marins sont les seuls pouvant couler d'autres sous marins\"\n" +
"                \"destroyer peuvent lancer des fusées eclairantes qui vont afficher pendant un tour un carré de coté 4 sur la grille adverse \"; ";
        System.out.println("voulez-vous afficher une aide ?(Y/N)");
        Scanner scanner = new Scanner(System.in);
        do{
            bn = scanner.next().charAt(0);
        if((bn=='Y')||(bn=='y')){
            System.out.println(AIDE);
        }else if(bn=='N'||(bn=='n')){
            System.out.println("OK,pas de soucis");
        }
        }while(!((bn=='Y')||(bn=='y')||(bn=='N')||(bn=='n')));
        
        
        
    }
       
}