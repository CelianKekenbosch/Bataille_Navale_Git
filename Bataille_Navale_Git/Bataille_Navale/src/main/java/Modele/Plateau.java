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
   
    public void randomPositionOneBoat(char grid[][], int boatSize, String typeShip)
    {
        //On declare les coordonnees aleatoires que l'on va utiliser
        int i,j,cote;
        boolean bool = true;    //Si ce booleen passe a false, c'est que l'execution s'est mal passée
        
        //On repete l'operation de verification tant qu'on n'a pas une position valide
        do
        {
            //On repasse le booleen a true pour le cas ou on repete plusieurs fois la boucle
            bool = true;
            
            //On genere trois nombres aleatoires
            i = 0 + (int)(Math.random() * ((14-0)+1));
            j = 0 + (int)(Math.random() * ((14-0)+1));      
            cote = 1 + (int)(Math.random() * ((2-1)+1));    //1 represente la verticale et 2 l'horizontale
            
            //Pour tester le random
            System.out.println(i+j+cote);
            
            if((i+boatSize > 14) || (j+boatSize > 14))
            {
                bool = false;
            }
            else
            {
                //Verification que les cases soient bien vides
                //On gere le cas vertical
                if(cote==1)
                {
                    for(int p=0;p<boatSize;p++)
                    {
                        if(grid[i+p][j] != ' ')
                        {
                            bool = false;
                        }
                    }
                }
            
                //On gere le cas horizontal
                if(cote==2)
                {
                    for(int p=0;p<boatSize;p++)
                    {
                        if(grid[i][j+p] != ' ')
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
            if("Dreadnought".equals(typeShip))
            {
                for(int p=i;p<=i+boatSize;p++)
                {
                    grid[p][j] = '*';
                }
            }
            if("Cruiser".equals(typeShip))
            {
                for(int p=i;p<=i+boatSize;p++)
                {
                    grid[p][j] = '@';
                }
            }
            if("Destroyer".equals(typeShip))
            {
                for(int p=i;p<=i+boatSize;p++)
                {
                    grid[p][j] = '%';
                }
            }
            if("Submarine".equals(typeShip))
            {
                for(int p=i;p<=i+boatSize;p++)
                {
                    grid[p][j] = '#';
                }
            }
        }
        
        //Cas horizontal
        if(cote == 2)
        {
            if("Dreadnought".equals(typeShip))
            {
                for(int p=j;p<=j+boatSize;p++)
                {
                    grid[i][p] = '*';
                }
            }
            if("Cruiser".equals(typeShip))
            {
                for(int p=j;p<=j+boatSize;p++)
                {
                    grid[i][p] = '@';
                }
            }
            if("Destroyer".equals(typeShip))
            {
                for(int p=j;p<=j+boatSize;p++)
                {
                    grid[i][p] = '%';
                }
            }
            if("Submarine".equals(typeShip))
            {
                for(int p=j;p<=j+boatSize;p++)
                {
                    grid[i][p] = '#';
                }
            }
        }
    }
}
