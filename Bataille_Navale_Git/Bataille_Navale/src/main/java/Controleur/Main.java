package Controleur;

import Modele.*;
import static Modele.Plateau.initValPlateau;
import java.util.Scanner;
import static Modele.Ship.move;
import static Modele.Ship.fusee_eclairante;
//import static Modele.Ship.fire;
import static Modele.Plateau.randomPositionOneBoat;

public class Main {

    public static void main(String[] args) {
        int choix;
        int action;
        int taille;
        String type;
        boolean victoire=false;
        boolean save=false;
        System.out.println("Bonjours! Vous jouez a la bataille Navale(differente), choissisez ce que vous voulez faire:");
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
            case 3://on affiche une aide deja ecrite
                help();
                break;
            case 2://on charge une partie deja sauvegardee
                break;
            default://on commence une toute nouvelle partie
                Ordinateur IA=new Ordinateur("IA");
                Plateau plateau = new Plateau(32,47);   //(32,47) est la taille de la grille avec tous les caracteres
                Plateau plateauTir = new Plateau(32,47);
                Plateau plateauIA = new Plateau(32,47);
                initValPlateau(plateau);//on initialise chaque case du plateau des bateaux du joueur a la valeur de la mer
                initValPlateau(plateauTir);//on initialise chaque case du plateau de tir du joueur a la valeur de la mer
                initValPlateau(plateauIA);//on initialise chaque case du plateau des bateaux du joueur a la valeur de la mer
                /*plateau.convertGridIntoPlateau(plateau.grid);
                plateauTir.convertGridIntoPlateau(plateauTir.grid);
                plateauIA.convertGridIntoPlateau(plateauIA.grid);*/
                //on cre la flotte du joueur 
                Submarine s1 = new Submarine(1,1,"submarine");
                Submarine s2 = new Submarine(1,1,"submarine");
                Submarine s3 = new Submarine(1,1,"submarine");
                Submarine s4 = new Submarine(1,1,"submarine");
                Destroyer d1 = new Destroyer(3,1,"destroyer");
                Destroyer d2 = new Destroyer(3,1,"destroyer");
                Destroyer d3 = new Destroyer(3,1,"destroyer");
                Cruiser c1 = new Cruiser(5,4,"cruiser");
                Cruiser c2 = new Cruiser(5,4,"cruiser");
                Dreadnought D1 = new Dreadnought(7,9,"dreadnought");
                //on appelle 10x  randomPositionOneBoat() pour les differents bateaux du joueur
                randomPositionOneBoat(plateau.grid,s1);
                randomPositionOneBoat(plateau.grid,s2);
                randomPositionOneBoat(plateau.grid,s3);
                randomPositionOneBoat(plateau.grid,s4);
                randomPositionOneBoat(plateau.grid,d1);
                randomPositionOneBoat(plateau.grid,d2);
                randomPositionOneBoat(plateau.grid,d3);
                randomPositionOneBoat(plateau.grid,c1);
                randomPositionOneBoat(plateau.grid,c2);
                randomPositionOneBoat(plateau.grid,D1);
                
                plateau.convertGridIntoPlateau(plateau.grid);

                //on cre la flotte de l'ordinateur
                Submarine IAs1 = new Submarine(1,1,"submarine");
                Submarine IAs2 = new Submarine(1,1,"submarine");
                Submarine IAs3 = new Submarine(1,1,"submarine");
                Submarine IAs4 = new Submarine(1,1,"submarine");
                Destroyer IAd1 = new Destroyer(3,1,"destroyer");
                Destroyer IAd2 = new Destroyer(3,1,"destroyer");
                Destroyer IAd3 = new Destroyer(3,1,"destroyer");
                Cruiser IAc1 = new Cruiser(5,4,"cruiser");
                Cruiser IAc2 = new Cruiser(5,4,"cruiser");
                Dreadnought IAD1 = new Dreadnought(7,9,"dreadnought");
                //on appelle 10x  randomPositionOneBoat() pour les differents bateaux de l'ordinateur
                randomPositionOneBoat(plateauIA.grid,IAs1);
                randomPositionOneBoat(plateauIA.grid,IAs2);
                randomPositionOneBoat(plateauIA.grid,IAs3);
                randomPositionOneBoat(plateauIA.grid,IAs4);
                randomPositionOneBoat(plateauIA.grid,IAd1);
                randomPositionOneBoat(plateauIA.grid,IAd2);
                randomPositionOneBoat(plateauIA.grid,IAd3);
                randomPositionOneBoat(plateauIA.grid,IAc1);
                randomPositionOneBoat(plateauIA.grid,IAc2);
                randomPositionOneBoat(plateauIA.grid,IAD1);
                
                plateauIA.convertGridIntoPlateau(plateauIA.grid);
                
                do{
                    
                    boolean repete;
                    do{
                    repete=false;
                    System.out.println("C'est votre tour, choissisez votre prochaine action:");
                    System.out.println("");
                    System.out.println("Position de votre flotte:");
                    plateau.affichePlateau();
                    System.out.println(" ");
                    System.out.println("Detruisez la flotte de l'ordinateur:");
                    plateauTir.affichePlateau();
                    System.out.println("1. tirer avec un bateau");
                    System.out.println("");
                    System.out.println("2. tirer une fusee eclairante avec un destroyer");
                    System.out.println("");
                    System.out.println("3. bouger un bateau intact d'une case ");
                    System.out.println("");
                    System.out.println("4. Afficher l'aide ");
                    System.out.println("");
                    System.out.println("5. Quitter et sauvegarder la partie actuelle ");
                    System.out.println("");
                    System.out.println("6. Afficher le plateau de l'odinateur(juste pour la soutenance) ");
                    Scanner scannerAction = new Scanner(System.in);
                    action=scannerAction.nextInt();
                    
                    switch(action){
                        case 1://petite modif
                        {
                            int typeShip;
                            boolean alive=true;
                            do{
                                
                                System.out.println("choisissez le type de bateau avec lequel vous voulez tirer(1 pour sous-marin, 2 destoyer, 3 croiseur et 4 cuirasse)");//pareil
                                Scanner scannerShip= new Scanner(System.in);
                                typeShip = scannerShip.nextInt();
                                if(typeShip==2){
                                    if (!(d1.isAlive)&&!(d2.isAlive)&&!(d3.isAlive)){
                                        alive=false;
                                    }
                                }
                                if(typeShip==3){
                                    if (!(c1.isAlive)&&!(c2.isAlive)){
                                        alive=false;
                                    }
                                }
                                if(typeShip==4){
                                    if (!(D1.isAlive)){
                                        alive=false;
                                    }    
                                }   
                            }while(((typeShip<1)||(typeShip>5))&&!(alive));
                            
                            switch(typeShip){
                                case 1:
                                    Submarine.fire(plateauIA,1,"submarine");
                                    break;
                                case 2:
                                    Destroyer.fire(plateauIA,1,"destroyer");
                                    break;
                                case 3 :
                                    Cruiser.fire(plateauIA,4,"destroyer");
                                    break;
                                default:
                                    Dreadnought.fire(plateauIA,9,"dreadnought");
                                    break;
                            }
                            System.out.println("votre tir a ete effectue!");
                            plateauTir.affichePlateau();//à changer car plateauTir=plateauIA
                            System.out.println("L'ordinateur va jouer, preparez vous a l'impact!");
                            
                            break;
                        }
                        
                        case 2://R
                            System.out.println("rentrez le type de bateau qui tire la fusee eclairante(il faut que ce soit un destroyer)");
                            Scanner scannerType = new Scanner(System.in);//a changer quand les bateaux seront nommes
                            type=scannerType.nextLine();
                            fusee_eclairante(plateauTir,plateauIA,type);
                            
                            break;
                        case 3://move a changer
                            System.out.println("rentrez la taille du bateau que vous voulez bouger");
                            Scanner scannerTest = new Scanner(System.in);//pareil
                            taille=scannerTest.nextInt();
                            move(plateau,taille);
                            plateau.affichePlateau();                          
                            System.out.println("votre mouvement a ete effectue!");
                            plateauTir.affichePlateau();//à changer car plateauTir=plateauIA
                            System.out.println("l'ordinateur va jouer, preparez vous a l'impact!");
                            break;
                        case 4://ok
                            help();
                            repete=true;
                            System.out.println("appuyez sur une touche pour continuer");
                            Scanner pause= new Scanner(System.in);
                            char touche;
                            touche=pause.next().charAt(0);
                            break;
                        case 5://R
                            System.out.println("vous avez decider de quitter la partie, elle sera sauvegardee ne vous inquietez pas!");
                            //quitter+sauvegarder
                            save=true;
                            break;
                        default://ok
                            plateauIA.affichePlateau();
                            repete=true;
                            System.out.println("appuyez sur une touche pour continuer");
                            Scanner pause1= new Scanner(System.in);
                            char touche1;
                            touche=pause1.next().charAt(0);
                            break;
                    }
                    }while(repete);//ok
                    System.out.println("c'est le tour de l'ordinateur");
                    System.out.println("l'ordinateur va faire son action :");
                    IA.iaTurn(plateau, D1, c1, c2, d1, d2, d3, s1, s2, s3, s4, IAD1, IAc1, IAc2, IAd1, IAd2, IAd3, IAs1, IAs2, IAs3, IAs4);
                    
                    
                }while((victoire==false)&&(save==false));
                
                break;
        }

    }
    
    //commence une partie(nouvelle si on n'a pas charge une ancienne)
    public static void startGame(){
        
    }
    
    //charge une ancienne partie deja enregistree
    public static void loadGame(){
        
    }
    
    //affiche une aide
    public static void help(){
        char bn;
        final String AIDE= "\" vous avez choisie aide , les regles du jeux sont les suivantes :\" \n" +
"                \"Vous devez deplacer tirer sur les bateaux ennemis . pour cela , vous tirerez sur les cases ennemis en entrant les coordonnees que vous voulez attaquer,\" \n" +
"                \"Vous pouvez egalement deplacer un navire s’il n’est pas touche par l’ennemi, pour cela entrez les coordonnes sur lesquels vous voulez l’affecter.\"\n" +
"                 \"un navire n'est coule que si toutes les cases qu'il occupe sont touchees\"\n " +
"                \"Pour gagner vous devez couler tous les navires de l’ennemis.\" \n" +
"                \"Vous pouvez quitter la partie ne appuyant sur Q.\"\n" +
"                 \"un cuirasse occupe 7 cases\"\n" +
"                 \"un croiseur occupe 5 cases\"\n" +
"                 \"un destroyer occupe 3 cases\"\n" +
"                 \"un sous marin occupe 1 case\"\n" +
"                 \"sur la grille de tirs les croix signifient que vous avez toucher un navire et il ne peut plus se deplacer\"\n" +
"                 \"sur votre grille les navires touches sont representes par des symboles \"\n" +
"                 \"La puissance de tir des navires s'organise de la maniere suivante : un destroyer et un sous marin peuvent toucher 1 case, un cuirasse peut toucher 8 cases adjacentes au point d'impact dans chaque directions, tandis qu'un croiseur peut toucher cases adjacentes au point d'impact dans chaque directions\" \n" +
"                 \"Les sous marins sont les seuls pouvant couler d'autres sous marins\"\n" +
"                \"destroyer peuvent lancer des fusees eclairantes qui vont afficher pendant un tour un carre de cote 4 sur la grille adverse \"; ";
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