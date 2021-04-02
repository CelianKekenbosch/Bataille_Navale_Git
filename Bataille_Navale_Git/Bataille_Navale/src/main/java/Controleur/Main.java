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
       
}