package Modele;

public class Plateau {
     //Attributs
    private final int nbLigne;
    private final int nbColonne;
    private final char [][] plateau;
    private int numPlateau;
    public final int VALUESEA=0;
    public final int VALUESUBMARINE=1;
    public final int VALUEDESTROYER=2;
    public final int VALUECRUISER=3;
    public final int VALUEDREADNOUGHT=4;
    public final int VALUEREMAINS=5;
    public char[][]premieresPositions;
    public int[][] cases;
    public char grid[][];
    
    //Constructeur qui initialise et pre-rempli le plateau
    public Plateau(int n, int p)
    {
        nbLigne = n;
        nbColonne = p;
        plateau = new char[nbLigne][nbColonne];
        cases= new int[32][47];
        
        //Initialisation du tableau
        for(int i=0;i<nbLigne;i++)
        {
            for(int j=0;j<nbColonne;j++)
            {
                plateau[i][j] = ' ';
            }
        }
        
        //Pre-remplissage du tableau
        //On rempli le tableau avec les chiffres et les lettres indicateurs
        char chiffre = '0';
        char lettre = 'a';
        for(int i=3;i<nbColonne;i=i+3)
        {
            plateau[0][i] = chiffre;
            chiffre++;
            if(chiffre==':')
            {
                chiffre = '0';
            }
        }
        for(int i=32;i<nbColonne;i=i+3)
        {
            plateau[0][i] = '1';
        }
        for(int i=0;i<15;i++)
        {
            plateau[2+2*i][0] = lettre;
            lettre++;
        }
        
        //On rempli le tableau en ajoutant les '+'
        for(int i=0;i<nbLigne;i++)
        {
            for(int j=1;j<=nbColonne;j=j+3)
            {
                plateau[i][j] = '+';
            }
        }
        
        //On rempli le tableau en ajoutant les '|'
        for(int i=0;i<nbLigne;i=i+2)
        {
            for(int j=1;j<=nbColonne;j=j+3)
            {
                plateau[i][j] = '|';
            }
        }
        
        //On rempli le tableau en ajoutant les '-'
        for(int i=0;i<16;i++)
        {
            for(int j=0;j<15;j++)
            {
                plateau[1+2*i][2+3*j] = '-';
                plateau[1+2*i][3+3*j] = '-';
            }
        }
    }

    //Methode qui print entierement un plateau pre-rempli/modifie dans la console
    public void affichePlateau()
    {
        int i,j;
        for(i=0;i<nbLigne;i++)
        {
            for(j=0;j<nbColonne;j++)
            {
                System.out.print(plateau[i][j]);
            }
            System.out.println(" ");
        }
    }
    
    //Recupere une grille dans laquelle on a les infos des navires pour les transferer au plateau d'affichage
    public void convertGridIntoPlateau(char grid[][])
    {
        int i,j;
        //On va parcourir la grille, et on va transferer chaque case de celle-ci dans un plateau
        for(i=0;i<15;i++)
        {
            for(j=0;j<15;j++)
            {
               plateau[2+2*i][2+3*j] = grid[i][j];
               plateau[2+2*i][3+3*j] = grid[i][j];
            }
        }
    }
       
    //renvoie le caractère correspondant à une valeur v
    public char symboleCase1(int v){
        switch (v) {
            case VALUESEA:
                return' ';
            case VALUESUBMARINE:
                return'#';
            case VALUEDESTROYER:
                return'%';
            case VALUECRUISER:
                return'@';
            case VALUEDREADNOUGHT:
                return'*';
            default:
                return'X';
        }
    }
    
    public char symboleCase2(int v){
        switch (v) {
            case VALUESEA:
                return' ';
            case VALUESUBMARINE:
                return' ';
            case VALUEDESTROYER:
                return' ';
            case VALUECRUISER:
                return' ';
            case VALUEDREADNOUGHT:
                return' ';
            default:
                return'X';
        }
    }
   
    public void get1StPosition(Plateau plateau, int i, int j){
        i=(int) ((Math.random()*((14-0)+1))+0);
        j=(int) ((Math.random()*((14-0)+1))+0);

    }
}
