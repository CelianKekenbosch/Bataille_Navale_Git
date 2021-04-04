package Modele;

public class Plateau {
     //Attributs
    private final int nbLigne;
    private final int nbColonne;
    public final char [][] plateau;
    private int numPlateau;
    public final int VALUESEA=0;
    public final int VALUESUBMARINE=1;
    public final int VALUEDESTROYER=2;
    public final int VALUECRUISER=3;
    public final int VALUEDREADNOUGHT=4;
    public final int VALUEREMAINS=5;
    public char[][]premieresPositions;
    public int grid[][];
    
    //Constructeur qui initialise et pre-rempli le plateau
    public Plateau(int n, int p)
    {
        nbLigne = n;
        nbColonne = p;
        plateau = new char[nbLigne][nbColonne];
        grid=new int[15][15];
        
        //Initialisation du tableau
        for(int i=0;i<nbLigne;i++)
        {
            for(int j=0;j<nbColonne;j++)
            {
                plateau[i][j] = ' ';
            }
        }
        
        for(int i =0;i<15;i++){
            for(int j=0;j<15;j++)
            {
                grid[i][j] = 0;
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
    
    //Methode qui affiche les cases touchees chez l'IA
    public void affichePlateauMasque()
    {
        int i,j;
        for(i=0;i<nbLigne;i++)
        {
            for(j=0;j<nbColonne;j++)
            {
                switch (plateau[i][j]) {
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
                        System.out.print(plateau[i][j]);
                        break;
                }
            }
            System.out.println(" ");   
        }
    }
    
    //Recupere une grille dans laquelle on a les infos des navires pour les transferer au plateau d'affichage
    public void convertGridIntoPlateau()
    {
        int i,j;
        //On va parcourir la grille, et on va transferer chaque case de celle-ci dans un plateau
        for(i=0;i<15;i++)
        {
            for(j=0;j<15;j++)
            {
               if(grid[i][j]==0)
               {
                   plateau[2+2*i][2+3*j] = ' ';
                   plateau[2+2*i][3+3*j] = ' ';
               }
               if(grid[i][j]==1)
               {
                   plateau[2+2*i][2+3*j] = '#';
                   plateau[2+2*i][3+3*j] = '#';
               }
               if(grid[i][j]==2)
               {
                   plateau[2+2*i][2+3*j] = '%';
                   plateau[2+2*i][3+3*j] = '%';
               }
               if(grid[i][j]==3)
               {
                   plateau[2+2*i][2+3*j] = '@';
                   plateau[2+2*i][3+3*j] = '@';
               }
               if(grid[i][j]==4)
               {
                   plateau[2+2*i][2+3*j] = '*';
                   plateau[2+2*i][3+3*j] = '*';
               }
               if(grid[i][j]==5)
               {
                   plateau[2+2*i][2+3*j] = 'X';
                   plateau[2+2*i][3+3*j] = 'X';
               }
            }
        }
    }
    
    //valeur d'un des 2 emplacements de chaque case du plateau
    public static int evalCase(Plateau plateau,int i,int j){
        return plateau.grid[i][j];
    }

    //modifie la valeur des 2 emplacements de la case de coordonnees (i,j) du plateau en la fixant a valeur
    public static void fixerCase(Plateau plateau,int i,int j,int valeur){ 
        plateau.grid[i][j] = valeur;
    }

    //initilialise les cases du plateau a la mer(VALUESEA=0)
    public static  void initValPlateau(Plateau plateau){
        for (int i=0;i<15;i++){
            for(int j=0;j<15;j++){
                fixerCase(plateau,i,j,0);
            }
        }
    }
       
    //renvoie le caractere correspondant a une valeur v
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
    
    public static void randomPositionOneBoat(int grid[][], Ship bateau)
    {
        //On declare les coordonnees aleatoires que l'on va utiliser
        int i,j,cote;
        boolean bool;    //Si ce booleen passe a false, c'est que l'execution s'est mal passee
        
        //On repete l'operation de verification tant qu'on n'a pas une position valide
        do
        {
            //On repasse le booleen a true pour le cas ou on repete plusieurs fois la boucle
            bool = true;
            
            //On genere trois nombres aleatoires
            i = 0 + (int)(Math.random() * ((14-0)+1));
            j = 0 + (int)(Math.random() * ((14-0)+1));      
            cote = 1 + (int)(Math.random() * ((2-1)+1));    //1 represente la verticale et 2 l'horizontale
            
            if((i+bateau.mSize > 14) || (j+bateau.mSize > 14))
            {
                bool = false;
            }
            else
            {
                //Verification que les cases soient bien vides
                //On gere le cas vertical
                if(cote==1)
                {
                    for(int p=0;p<bateau.mSize;p++)
                    {
                        if(grid[i+p][j] != 0)
                        {
                            bool = false;
                        }
                    }
                }
            
                //On gere le cas horizontal
                if(cote==2)
                {
                    for(int p=0;p<bateau.mSize;p++)
                    {
                        if(grid[i][j+p] != 0)
                        {
                            bool = false;
                        }
                    }
                }
            }
        }while(bool == false);
        
        //Si on quitte le do while, c'est que l'on possede un i et un j valide, on peut alors placer le bateau
        //Cas vertical
        if(cote == 1)
        {
            if("dreadnought".equals(bateau.mTypeShip))
            {
                for(int p=i;p<i+bateau.mSize;p++)
                {
                    grid[p][j] = 4;
                }
            }
            if("cruiser".equals(bateau.mTypeShip))
            {
                for(int p=i;p<i+bateau.mSize;p++)
                {
                    grid[p][j] = 3;
                }
            }
            if("destroyer".equals(bateau.mTypeShip))
            {
                for(int p=i;p<i+bateau.mSize;p++)
                {
                    grid[p][j] = 2;
                }
            }
            if("submarine".equals(bateau.mTypeShip))
            {
                for(int p=i;p<i+bateau.mSize;p++)
                {
                    grid[p][j] = 1;
                }
            }
        }
        
        //Cas horizontal
        if(cote == 2)
        {
            if("dreadnought".equals(bateau.mTypeShip))
            {
                for(int p=j;p<j+bateau.mSize;p++)
                {
                    grid[i][p] = 4;
                }
            }
            if("cruiser".equals(bateau.mTypeShip))
            {
                for(int p=j;p<j+bateau.mSize;p++)
                {
                    grid[i][p] = 3;
                }
            }
            if("destroyer".equals(bateau.mTypeShip))
            {
                for(int p=j;p<j+bateau.mSize;p++)
                {
                    grid[i][p] = 2;
                }
            }
            if("submarine".equals(bateau.mTypeShip))
            {
                for(int p=j;p<j+bateau.mSize;p++)
                {
                    grid[i][p] = 1;
                }
            }
        }
        
        //On donne ces infos au bateau en question
        if(cote == 1)
        {
            bateau.isVertical = true;
        }
        bateau.xBoat = i;
        bateau.yBoat = j;
    }
}