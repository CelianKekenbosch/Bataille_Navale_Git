package Modele;

import java.util.Scanner;
import static Modele.Plateau.evalCase;
import static Modele.Plateau.fixerCase;

public class Ship {

    public String mTypeShip;
    public int mSize;
    protected int mfirePower;
    protected boolean isVertical;
    public int mNbFuseeEclairante=1;
    public boolean isAlive = true;
    protected int xBoat;
    protected int yBoat;
    
    public Ship(String typeShip){//constructeur de la classe mÃ¨re Ship
    this.mTypeShip=typeShip;
    if(!("destroyer".equals(typeShip))){
        mNbFuseeEclairante=0;
    }
}
    
public static void fire(Plateau plateau, String typeShip, Ship dreadnought, Ship cruiser1, Ship cruiser2, Ship destroyer1, Ship destroyer2, Ship destroyer3, Ship submarine1, Ship submarine2, Ship submarine3, Ship submarine4)
{   
    int x,y;
    char c;
    Scanner input = new Scanner(System.in);
    do{
        System.out.println("Rentrez l'ordonnee du point d'impact svp (entre a et o)");
        c = input.next().charAt(0);
        x=(int)(c -'a');
    }while((x<0)||(x>14));
    
    do{
        System.out.println("Rentrez l'abcisse du point d'impact svp (entre 0 et 14)");
        y = input.nextInt();
    }while((y<0)||(y>14));
      
    switch(evalCase(plateau,x,y)) 
    {
        //en fonction de la valeur d'une case, il y un resultat different quand on tire dessus
        case 0:
            System.out.println("PLOUF! A l'eau...");//si on tire dans l'eau rien ne se passe
            break;
            
        case 5:
            System.out.println("Faites plus attention, vous avez deja  touche ce point du navire !");   //pareil pour un debris/epave
            break;
            
        default:
            if(("submarine".equals(typeShip))&&(evalCase(plateau,x,y) == 1))
            {
            System.out.println("ET coule!!!(un sous-marin en moins) Rien ne peut plus vous arreter!");
            fixerCase(plateau,x,y,5);    
            }
            else
            {
                if(("submarine".equals(typeShip))&&(plateau.grid[x][y] != 0))
                {
                    fixerCase(plateau, x, y, 5);
                    System.out.println("Touche !");
                }
                if("destroyer".equals(typeShip))
                {
                    if((plateau.grid[x][y] != 1)&&(plateau.grid[x][y] != 0))
                    {
                        fixerCase(plateau, x, y, 5);
                        System.out.println("Touche !");
                    }
                }
                if("cruiser".equals(typeShip))
                {    
                    //On va dessiner un "+" sans la barre du bas
                    if((plateau.grid[x][y] != 1)&&(plateau.grid[x][y] != 0))
                    {
                        fixerCase(plateau, x, y, 5);	//Case centrale
                        System.out.println("Touche !");
                    }
                    if((x-1)>=0)
                    {
                        if((plateau.grid[x-1][y] != 1)&&(plateau.grid[x-1][y] != 0)&&(plateau.grid[x][y]==5))
                        {
                            fixerCase(plateau, x-1, y, 5);	//Case de gauche
                        }
                    }
                    if((x+1)<15)
                    {
                        if((plateau.grid[x+1][y] != 1)&&(plateau.grid[x+1][y] != 0)&&(plateau.grid[x][y]==5))
                        {
                            fixerCase(plateau, x+1, y, 5);	//Case de droite
                        }
                    }
                    if((y-1)>=0)
                    {
                        if((plateau.grid[x][y-1] != 1)&&(plateau.grid[x][y-1] != 0)&&(plateau.grid[x][y]==5))
                        {
                            fixerCase(plateau, x, y-1, 5);  //Case du dessus
                        }
                    }
                }
                if("dreadnought".equals(typeShip))
                { 
                    //On va dessiner un "+"
                    if((plateau.grid[x][y] != 1)&&(plateau.grid[x][y] != 0))
                    {
                        fixerCase(plateau, x, y, 5);	//Case centrale
                        System.out.println("Touche !");
                    }
                    if((x-1)>=0)
                    {
                        if((plateau.grid[x-1][y] != 1)&&(plateau.grid[x-1][y] != 0)&&(plateau.grid[x][y]==5))
                        {
                            fixerCase(plateau, x-1, y, 5);	//Case de gauche
                        }
                    }
                    if((x+1)<15)
                    {
                        if((plateau.grid[x+1][y] != 1)&&(plateau.grid[x+1][y] != 0)&&(plateau.grid[x][y]==5))
                        {
                            fixerCase(plateau, x+1, y, 5);	//Case de droite
                        }
                    }
                    if((y-1)>=0)
                    {
                        if((plateau.grid[x][y-1] != 1)&&(plateau.grid[x][y-1] != 0)&&(plateau.grid[x][y]==5))
                        {
                            fixerCase(plateau, x, y-1, 5);  //Case du dessus
                        }
                    }
                    if((y+1)<15)
                    {
                        if((plateau.grid[x][y+1] != 1)&&(plateau.grid[x][y+1] != 0)&&(plateau.grid[x][y]==5))
                        {
                            fixerCase(plateau, x, y+1, 5);  //Case du dessous
                        }
                    }
                    if(((x-1)>=0)&&((y-1)>=0))
                    {
                        if((plateau.grid[x-1][y-1] != 1)&&(plateau.grid[x-1][y-1] != 0)&&(plateau.grid[x][y]==5))
                        {
                            fixerCase(plateau, x-1, y-1, 5);  //Case nord ouest
                        }
                    }
                    if(((x+1)<15)&&((y-1)>=0))
                    {
                        if((plateau.grid[x+1][y-1] != 1)&&(plateau.grid[x+1][y-1] != 0)&&(plateau.grid[x][y]==5))
                        {
                            fixerCase(plateau, x+1, y-1, 5);  //Case nord est
                        }
                    }
                    if(((y+1)<15)&&((x-1)>=0))
                    {
                        if((plateau.grid[x-1][y+1] != 1)&&(plateau.grid[x-1][y+1] != 0)&&(plateau.grid[x][y]==5))
                        {
                            fixerCase(plateau, x-1, y+1, 5);  //Case sud ouest
                        }
                    }
                    if(((y+1)<15)&&((x+1)<15))
                    {
                        if((plateau.grid[x+1][y+1] != 1)&&(plateau.grid[x+1][y+1] != 0)&&(plateau.grid[x][y]==5))
                        {
                            fixerCase(plateau, x+1, y+1, 5);  //Case sud est
                        }
                    }
                }
            }	
    }    
    
    //Pour chaque bateau, on regarde si il est vertical ou horizontal, et on update ses cases en fonction
    if((dreadnought.isVertical)&&(dreadnought.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int i=0;i<dreadnought.mSize;i++)
        {
            if(plateau.grid[dreadnought.xBoat+i][dreadnought.yBoat] == 4)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        dreadnought.isAlive = alive;
        
        if(!dreadnought.isAlive)
        {
            System.out.println("COULE !!! Un cuirasse de moins !!!");
        }
    }
    else if((!dreadnought.isVertical)&&(dreadnought.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int j=0;j<dreadnought.mSize;j++)
        {
            if(plateau.grid[dreadnought.xBoat][dreadnought.yBoat+j] == 4)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        dreadnought.isAlive = alive;
        
        if(!dreadnought.isAlive)
        {
            System.out.println("COULE !!! Un cuirasse de moins !!!");
        }
    }


    if((cruiser1.isVertical)&&(cruiser1.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int i=0;i<cruiser1.mSize;i++)
        {
            if(plateau.grid[cruiser1.xBoat+i][cruiser1.yBoat] == 3)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        cruiser1.isAlive = alive;
        
        if(!cruiser1.isAlive)
        {
            System.out.println("COULE !!! Un croiseur de moins !!!");
        }
    }
    else if((!cruiser1.isVertical)&&(cruiser1.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int j=0;j<cruiser1.mSize;j++)
        {
            if(plateau.grid[cruiser1.xBoat][cruiser1.yBoat+j] == 3)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        cruiser1.isAlive = alive;
        
        if(!cruiser1.isAlive)
        {
            System.out.println("COULE !!! Un croiseur de moins !!!");
        }
    }


    if((cruiser2.isVertical)&&(cruiser2.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int i=0;i<cruiser2.mSize;i++)
        {
            if(plateau.grid[cruiser2.xBoat+i][cruiser2.yBoat] == 3)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        cruiser2.isAlive = alive;
        
        if(!cruiser2.isAlive)
        {
            System.out.println("COULE !!! Un croiseur de moins !!!");
        }
    }
    else if((!cruiser2.isVertical)&&(cruiser2.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int j=0;j<cruiser2.mSize;j++)
        {
            if(plateau.grid[cruiser2.xBoat][cruiser2.yBoat+j] == 3)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        cruiser2.isAlive = alive;
        
        if(!cruiser2.isAlive)
        {
            System.out.println("COULE !!! Un croiseur de moins !!!");
        }
    }


    if((destroyer1.isVertical)&&(destroyer1.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int i=0;i<destroyer1.mSize;i++)
        {
            if(plateau.grid[destroyer1.xBoat+i][destroyer1.yBoat] == 2)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        destroyer1.isAlive = alive;
        
        if(!destroyer1.isAlive)
        {
            System.out.println("COULE !!! Un destroyer de moins !!!");
        }
    }
    else if((!destroyer1.isVertical)&&(destroyer1.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int j=0;j<destroyer1.mSize;j++)
        {
            if(plateau.grid[destroyer1.xBoat][destroyer1.yBoat+j] == 2)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        destroyer1.isAlive = alive;
        
        if(!destroyer1.isAlive)
        {
            System.out.println("COULE !!! Un destroyer de moins !!!");
        }
    }


    if((destroyer2.isVertical)&&(destroyer2.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int i=0;i<destroyer2.mSize;i++)
        {
            if(plateau.grid[destroyer2.xBoat+i][destroyer2.yBoat] == 2)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        destroyer2.isAlive = alive;
        
        if(!destroyer2.isAlive)
        {
            System.out.println("COULE !!! Un destroyer de moins !!!");
        }
    }
    else if((!destroyer2.isVertical)&&(destroyer2.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int j=0;j<destroyer2.mSize;j++)
        {
            if(plateau.grid[destroyer2.xBoat][destroyer2.yBoat+j] == 2)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        destroyer2.isAlive = alive;
        
        if(!destroyer2.isAlive)
        {
            System.out.println("COULE !!! Un destroyer de moins !!!");
        }
    }


    if((destroyer3.isVertical)&&(destroyer3.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int i=0;i<destroyer3.mSize;i++)
        {
            if(plateau.grid[destroyer3.xBoat+i][destroyer3.yBoat] == 2)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        destroyer3.isAlive = alive;
        
        if(!destroyer3.isAlive)
        {
            System.out.println("COULE !!! Un destroyer de moins !!!");
        }
    }
    else if((!destroyer3.isVertical)&&(destroyer3.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int j=0;j<destroyer3.mSize;j++)
        {
            if(plateau.grid[destroyer3.xBoat][destroyer3.yBoat+j] == 2)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        destroyer3.isAlive = alive;
        
        if(!destroyer3.isAlive)
        {
            System.out.println("COULE !!! Un destroyer de moins !!!");
        }
    }


    if((submarine1.isVertical)&&(submarine1.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int i=0;i<submarine1.mSize;i++)
        {
            if(plateau.grid[submarine1.xBoat+i][submarine1.yBoat] == 1)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        submarine1.isAlive = alive;
        
        if(!submarine1.isAlive)
        {
            System.out.println("COULE !!! Un sous-marin de moins !!!");
        }
    }
    else if((!submarine1.isVertical)&&(submarine1.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int j=0;j<submarine1.mSize;j++)
        {
            if(plateau.grid[submarine1.xBoat][submarine1.yBoat+j] == 1)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        submarine1.isAlive = alive;
        
        if(!submarine1.isAlive)
        {
            System.out.println("COULE !!! Un sous-marin de moins !!!");
        }
    }


    if((submarine2.isVertical)&&(submarine2.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int i=0;i<submarine2.mSize;i++)
        {
            if(plateau.grid[submarine2.xBoat+i][submarine2.yBoat] == 1)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        submarine2.isAlive = alive;
        
        if(!submarine2.isAlive)
        {
            System.out.println("COULE !!! Un sous-marin de moins !!!");
        }
    }
    else if((!submarine2.isVertical)&&(submarine2.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int j=0;j<submarine2.mSize;j++)
        {
            if(plateau.grid[submarine2.xBoat][submarine2.yBoat+j] == 1)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        submarine2.isAlive = alive;
        
        if(!submarine2.isAlive)
        {
            System.out.println("COULE !!! Un sous-marin de moins !!!");
        }
    }


    if((submarine3.isVertical)&&(submarine3.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int i=0;i<submarine3.mSize;i++)
        {
            if(plateau.grid[submarine3.xBoat+i][submarine3.yBoat] == 1)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        submarine3.isAlive = alive;
        
        if(!submarine3.isAlive)
        {
            System.out.println("COULE !!! Un sous-marin de moins !!!");
        }
    }
    else if((!submarine3.isVertical)&&(submarine3.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int j=0;j<submarine3.mSize;j++)
        {
            if(plateau.grid[submarine3.xBoat][submarine3.yBoat+j] == 1)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        submarine3.isAlive = alive;
        
        if(!submarine3.isAlive)
        {
            System.out.println("COULE !!! Un sous-marin de moins !!!");
        }
    }


    if((submarine4.isVertical)&&(submarine4.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int i=0;i<submarine4.mSize;i++)
        {
            if(plateau.grid[submarine4.xBoat+i][submarine4.yBoat] == 1)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        submarine4.isAlive = alive;
        
        if(!submarine4.isAlive)
        {
            System.out.println("COULE !!! Un sous-marin de moins !!!");
        }
    }
    else if((!submarine4.isVertical)&&(submarine4.isAlive))
    {
        boolean alive = false; //Par defaut, le bateau est considere comme coule
        for(int j=0;j<submarine4.mSize;j++)
        {
            if(plateau.grid[submarine4.xBoat][submarine4.yBoat+j] == 1)   //Si une seule de ses cases est alive, on dit qu'il n'est as coule
            {
                alive = true;
            }
        }
        submarine4.isAlive = alive;
        
        if(!submarine4.isAlive)
        {
            System.out.println("COULE !!! Un sous-marin de moins !!!");
        }
    }
}
  
//Methode qui affiche les navires adverses dans la zone d'effet sous la forme "X" et qui renvoie un booleen egal a true si on peut tirer ou false si on ne peut pas
//IL FAUT LE PLATEAU DE L'IA
public static boolean fusee_eclairante(Plateau plateau, Ship destroyer1, Ship destroyer2, Ship destroyer3)
{
        boolean possedeFusee = true;
	//On va d'abord verifier qu'il existe une fusee eclairante dans les stocks
	if((destroyer1.mNbFuseeEclairante == 0)&&(destroyer2.mNbFuseeEclairante == 0)&&(destroyer3.mNbFuseeEclairante == 0))
	{
            possedeFusee = false;
            System.out.println("Vous n'avez plus de fusee eclairante..");
            return possedeFusee;
	}
	else
	{
            if(destroyer1.mNbFuseeEclairante == 1)
            {
                destroyer1.mNbFuseeEclairante = 0;
            }
            else if(destroyer2.mNbFuseeEclairante == 1)
            {
                destroyer2.mNbFuseeEclairante = 0;
            }
            else if(destroyer3.mNbFuseeEclairante == 1)
            {
                destroyer3.mNbFuseeEclairante = 0;
            }
            int x,y;
            char c;
            Scanner input = new Scanner(System.in);		

            do{
                    System.out.println("Rentrez l'ordonnee du point d'impact svp (entre a et o)");
                    c = input.next().charAt(0);
                    x=(int)(c -'a');
            }while((x<0)||(x>14));

            do{
                    System.out.println("Rentrez l'abcisse du point d'impact svp (entre 0 et 14)");
                    y = input.nextInt();
            }while((y<0)||(y>14));

            //On va reveler une zone 4*4 au joueur le temps d'un tour
            int i,j;
            for(i=0;i<32;i++)
            {
                for(j=0;j<47;j++)
                {
                    if(plateau.plateau[i][j] == 'X')
                    {
                        System.out.print("X");
                    }
                    else if((plateau.plateau[i][j] == '*')&&((i>=2+2*x)&&(i<=2+2*(x+3))&&(j>=2+3*y)&&(j<=3+3*(y+3))))
                    {
                        System.out.print("~");
                    }
                    else if((plateau.plateau[i][j] == '@')&&((i>=2+2*x)&&(i<=2+2*(x+3))&&(j>=2+3*y)&&(j<=3+3*(y+3))))
                    {
                        System.out.print("~");
                    }
                    else if((plateau.plateau[i][j] == '%')&&((i>=2+2*x)&&(i<=2+2*(x+3))&&(j>=2+3*y)&&(j<=3+3*(y+3))))
                    {
                        System.out.print("~");
                    }
                    else if((plateau.plateau[i][j] == '#')&&((i>=2+2*x)&&(i<=2+2*(x+3))&&(j>=2+3*y)&&(j<=3+3*(y+3))))
                    {
                        System.out.print("~");
                    }
                    else
                    {
                        switch (plateau.plateau[i][j]) 
                        {
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
                }
                System.out.println(" ");
            }
        return possedeFusee;
    }
}

//Methode de deplacement des navires du joueur qui retourne si oui ou non on a pu deplacer un bateau
public static boolean move(Plateau plateau, Ship dreadnought, Ship cruiser1, Ship cruiser2, Ship destroyer1, Ship destroyer2, Ship destroyer3, Ship submarine1, Ship submarine2, Ship submarine3, Ship submarine4)
{
    boolean moved = false;
    boolean canMove = true;
    
    //On va demander a l'utilisateur de saisir les coordonnees de l'avant du navire, si celles-ci n'existent pas on va demander de saisir l'arriere. Sinon, c'est que le bateau en question n'existe pas
    int x,y;
    char c,mvt;
    Scanner input = new Scanner(System.in);
    do{
        System.out.println("Rentrez l'ordonnee de l'avant du navire svp (entre a et o)");
        c = input.next().charAt(0);
        x=(int)(c -'a');
    }while((x<0)||(x>14));

    do{
        System.out.println("Rentrez l'abcisse de l'avant du navire svp (entre 0 et 14)");
        y = input.nextInt();
    }while((y<0)||(y>14));
    
    //On va maintenant parcourir chaque navire jusqu'a trouver celui dont les coordonnees sont celles-ci
    if((dreadnought.xBoat == x)&&(dreadnought.yBoat == y))
    {
        //On va verifier si le bateau est deplacable
        if(dreadnought.isVertical)
        {
            //Pour ce faire on parcourt ce bateau
            for(int i=dreadnought.xBoat;i<dreadnought.mSize;i++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[i][dreadnought.yBoat] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    //On verifie que l'on ne sorte pas du plateau
                    if(dreadnought.xBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[dreadnought.xBoat-1][dreadnought.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[dreadnought.xBoat+dreadnought.mSize-1][dreadnought.yBoat] = 0;
                            plateau.grid[dreadnought.xBoat-1][dreadnought.yBoat] = 4;
                            dreadnought.xBoat = dreadnought.xBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(dreadnought.xBoat+dreadnought.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[dreadnought.xBoat+dreadnought.mSize][dreadnought.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[dreadnought.xBoat+dreadnought.mSize][dreadnought.yBoat] = 4;
                            plateau.grid[dreadnought.xBoat][dreadnought.yBoat] = 0;
                            dreadnought.xBoat = dreadnought.xBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }
        //Si ce bateau est horizontal
        else
        {
            //Pour ce faire on parcourt ce bateau
            for(int j=dreadnought.yBoat;j<dreadnought.mSize;j++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[dreadnought.xBoat][j] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    if(dreadnought.yBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[dreadnought.xBoat][dreadnought.yBoat-1] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[dreadnought.xBoat][dreadnought.yBoat+dreadnought.mSize-1] = 0;
                            plateau.grid[dreadnought.xBoat][dreadnought.yBoat-1] = 4;
                            dreadnought.yBoat = dreadnought.yBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(dreadnought.yBoat+dreadnought.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[dreadnought.xBoat][dreadnought.yBoat+dreadnought.mSize] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[dreadnought.xBoat][dreadnought.yBoat+dreadnought.mSize] = 4;
                            plateau.grid[dreadnought.xBoat][dreadnought.yBoat] = 0;
                            dreadnought.yBoat = dreadnought.yBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }  
    }
    else if((cruiser1.xBoat == x)&&(cruiser1.yBoat == y))
    {
        //On va verifier si le bateau est deplacable
        if(cruiser1.isVertical)
        {
            //Pour ce faire on parcourt ce bateau
            for(int i=cruiser1.xBoat;i<cruiser1.mSize;i++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[i][cruiser1.yBoat] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    //On verifie que l'on ne sorte pas du plateau
                    if(cruiser1.xBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[cruiser1.xBoat-1][cruiser1.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[cruiser1.xBoat+cruiser1.mSize-1][cruiser1.yBoat] = 0;
                            plateau.grid[cruiser1.xBoat-1][cruiser1.yBoat] = 3;
                            cruiser1.xBoat = cruiser1.xBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(cruiser1.xBoat+cruiser1.mSize<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[cruiser1.xBoat+cruiser1.mSize][cruiser1.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[cruiser1.xBoat+cruiser1.mSize][cruiser1.yBoat] = 3;
                            plateau.grid[cruiser1.xBoat][cruiser1.yBoat] = 0;
                            cruiser1.xBoat = cruiser1.xBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }
        //Si ce bateau est horizontal
        else
        {
            //Pour ce faire on parcourt ce bateau
            for(int j=cruiser1.yBoat;j<cruiser1.mSize;j++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[cruiser1.xBoat][j] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    if(cruiser1.yBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[cruiser1.xBoat][cruiser1.yBoat-1] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[cruiser1.xBoat][cruiser1.yBoat+cruiser1.mSize-1] = 0;
                            plateau.grid[cruiser1.xBoat][cruiser1.yBoat-1] = 3;
                            cruiser1.yBoat = cruiser1.yBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(cruiser1.yBoat+cruiser1.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[cruiser1.xBoat][cruiser1.yBoat+cruiser1.mSize] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[cruiser1.xBoat][cruiser1.yBoat+cruiser1.mSize] = 3;
                            plateau.grid[cruiser1.xBoat][cruiser1.yBoat] = 0;
                            cruiser1.yBoat = cruiser1.yBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }  
    }
    else if((cruiser2.xBoat == x)&&(cruiser2.yBoat == y))
    {
        //On va verifier si le bateau est deplacable
        if(cruiser2.isVertical)
        {
            //Pour ce faire on parcourt ce bateau
            for(int i=cruiser2.xBoat;i<cruiser2.mSize;i++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[i][cruiser2.yBoat] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    //On verifie que l'on ne sorte pas du plateau
                    if(cruiser2.xBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[cruiser2.xBoat-1][cruiser2.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[cruiser2.xBoat+cruiser2.mSize-1][cruiser2.yBoat] = 0;
                            plateau.grid[cruiser2.xBoat-1][cruiser2.yBoat] = 3;
                            cruiser2.xBoat = cruiser2.xBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(cruiser2.xBoat+cruiser2.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[cruiser2.xBoat+cruiser2.mSize][cruiser2.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[cruiser2.xBoat+cruiser2.mSize][cruiser2.yBoat] = 3;
                            plateau.grid[cruiser2.xBoat][cruiser2.yBoat] = 0;
                            cruiser2.xBoat = cruiser2.xBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }
        //Si ce bateau est horizontal
        else
        {
            //Pour ce faire on parcourt ce bateau
            for(int j=cruiser2.yBoat;j<cruiser2.mSize;j++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[cruiser2.xBoat][j] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    if(cruiser2.yBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[cruiser2.xBoat][cruiser2.yBoat-1] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[cruiser2.xBoat][cruiser2.yBoat+cruiser2.mSize-1] = 0;
                            plateau.grid[cruiser2.xBoat][cruiser2.yBoat-1] = 3;
                            cruiser2.yBoat = cruiser2.yBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(cruiser2.yBoat+cruiser2.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[cruiser2.xBoat][cruiser2.yBoat+cruiser2.mSize] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[cruiser2.xBoat][cruiser2.yBoat+cruiser2.mSize] = 3;
                            plateau.grid[cruiser2.xBoat][cruiser2.yBoat] = 0;
                            cruiser2.yBoat = cruiser2.yBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }  
    }
    else if((destroyer1.xBoat == x)&&(destroyer1.yBoat == y))
    {
        //On va verifier si le bateau est deplacable
        if(destroyer1.isVertical)
        {
            //Pour ce faire on parcourt ce bateau
            for(int i=destroyer1.xBoat;i<destroyer1.mSize;i++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[i][destroyer1.yBoat] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    //On verifie que l'on ne sorte pas du plateau
                    if(destroyer1.xBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[destroyer1.xBoat-1][destroyer1.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[destroyer1.xBoat+destroyer1.mSize-1][destroyer1.yBoat] = 0;
                            plateau.grid[destroyer1.xBoat-1][destroyer1.yBoat] = 2;
                            destroyer1.xBoat = destroyer1.xBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(destroyer1.xBoat+destroyer1.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[destroyer1.xBoat+destroyer1.mSize][destroyer1.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[destroyer1.xBoat+destroyer1.mSize][destroyer1.yBoat] = 2;
                            plateau.grid[destroyer1.xBoat][destroyer1.yBoat] = 0;
                            destroyer1.xBoat = destroyer1.xBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }
        //Si ce bateau est horizontal
        else
        {
            //Pour ce faire on parcourt ce bateau
            for(int j=destroyer1.yBoat;j<destroyer1.mSize;j++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[destroyer1.xBoat][j] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    if(destroyer1.yBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[destroyer1.xBoat][destroyer1.yBoat-1] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[destroyer1.xBoat][destroyer1.yBoat+destroyer1.mSize-1] = 0;
                            plateau.grid[destroyer1.xBoat][destroyer1.yBoat-1] = 2;
                            destroyer1.yBoat = destroyer1.yBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(destroyer1.yBoat+destroyer1.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[destroyer1.xBoat][destroyer1.yBoat+destroyer1.mSize] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[destroyer1.xBoat][destroyer1.yBoat+destroyer1.mSize] = 2;
                            plateau.grid[destroyer1.xBoat][destroyer1.yBoat] = 0;
                            destroyer1.yBoat = destroyer1.yBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }  
    }
    else if((destroyer2.xBoat == x)&&(destroyer2.yBoat == y))
    {
        //On va verifier si le bateau est deplacable
        if(destroyer2.isVertical)
        {
            //Pour ce faire on parcourt ce bateau
            for(int i=destroyer2.xBoat;i<destroyer2.mSize;i++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[i][destroyer2.yBoat] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    //On verifie que l'on ne sorte pas du plateau
                    if(destroyer2.xBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[destroyer2.xBoat-1][destroyer2.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[destroyer2.xBoat+destroyer2.mSize-1][destroyer2.yBoat] = 0;
                            plateau.grid[destroyer2.xBoat-1][destroyer2.yBoat] = 2;
                            destroyer2.xBoat = destroyer2.xBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(destroyer2.xBoat+destroyer2.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[destroyer2.xBoat+destroyer2.mSize][destroyer2.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[destroyer2.xBoat+destroyer2.mSize][destroyer2.yBoat] = 2;
                            plateau.grid[destroyer2.xBoat][destroyer2.yBoat] = 0;
                            destroyer2.xBoat = destroyer2.xBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }
        //Si ce bateau est horizontal
        else
        {
            //Pour ce faire on parcourt ce bateau
            for(int j=destroyer2.yBoat;j<destroyer2.mSize;j++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[destroyer2.xBoat][j] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    if(destroyer2.yBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[destroyer2.xBoat][destroyer2.yBoat-1] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[destroyer2.xBoat][destroyer2.yBoat+destroyer2.mSize-1] = 0;
                            plateau.grid[destroyer2.xBoat][destroyer2.yBoat-1] = 2;
                            destroyer2.yBoat = destroyer2.yBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(destroyer2.yBoat+destroyer2.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[destroyer2.xBoat][destroyer2.yBoat+destroyer2.mSize] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[destroyer2.xBoat][destroyer2.yBoat+destroyer2.mSize] = 2;
                            plateau.grid[destroyer2.xBoat][destroyer2.yBoat] = 0;
                            destroyer2.yBoat = destroyer2.yBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }  
    }
    else if((destroyer3.xBoat == x)&&(destroyer3.yBoat == y))
    {
        //On va verifier si le bateau est deplacable
        if(destroyer3.isVertical)
        {
            //Pour ce faire on parcourt ce bateau
            for(int i=destroyer3.xBoat;i<destroyer3.mSize;i++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[i][destroyer3.yBoat] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    //On verifie que l'on ne sorte pas du plateau
                    if(destroyer3.xBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[destroyer3.xBoat-1][destroyer3.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[destroyer3.xBoat+destroyer3.mSize-1][destroyer3.yBoat] = 0;
                            plateau.grid[destroyer3.xBoat-1][destroyer3.yBoat] = 2;
                            destroyer3.xBoat = destroyer3.xBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(destroyer3.xBoat+destroyer3.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[destroyer3.xBoat+destroyer3.mSize][destroyer3.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[destroyer3.xBoat+destroyer3.mSize][destroyer3.yBoat] = 2;
                            plateau.grid[destroyer3.xBoat][destroyer3.yBoat] = 0;
                            destroyer3.xBoat = destroyer3.xBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }
        //Si ce bateau est horizontal
        else
        {
            //Pour ce faire on parcourt ce bateau
            for(int j=destroyer3.yBoat;j<destroyer3.mSize;j++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[destroyer3.xBoat][j] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    if(destroyer3.yBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[destroyer3.xBoat][destroyer3.yBoat-1] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[destroyer3.xBoat][destroyer3.yBoat+destroyer3.mSize-1] = 0;
                            plateau.grid[destroyer3.xBoat][destroyer3.yBoat-1] = 2;
                            destroyer3.yBoat = destroyer3.yBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(destroyer3.yBoat+destroyer3.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[destroyer3.xBoat][destroyer3.yBoat+destroyer3.mSize] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[destroyer3.xBoat][destroyer3.yBoat+destroyer3.mSize] = 2;
                            plateau.grid[destroyer3.xBoat][destroyer3.yBoat] = 0;
                            destroyer3.yBoat = destroyer3.yBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }  
    }
    else if((submarine1.xBoat == x)&&(submarine1.yBoat == y))
    {
        //On va verifier si le bateau est deplacable
        if(submarine1.isVertical)
        {
            //Pour ce faire on parcourt ce bateau
            for(int i=submarine1.xBoat;i<submarine1.mSize;i++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[i][submarine1.yBoat] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    //On verifie que l'on ne sorte pas du plateau
                    if(submarine1.xBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine1.xBoat-1][submarine1.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine1.xBoat+submarine1.mSize-1][submarine1.yBoat] = 0;
                            plateau.grid[submarine1.xBoat-1][submarine1.yBoat] = 1;
                            submarine1.xBoat = submarine1.xBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(submarine1.xBoat+submarine1.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine1.xBoat+submarine1.mSize][submarine1.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine1.xBoat+submarine1.mSize][submarine1.yBoat] = 1;
                            plateau.grid[submarine1.xBoat][submarine1.yBoat] = 0;
                            submarine1.xBoat = submarine1.xBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }
        //Si ce bateau est horizontal
        else
        {
            //Pour ce faire on parcourt ce bateau
            for(int j=submarine1.yBoat;j<submarine1.mSize;j++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[submarine1.xBoat][j] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    if(submarine1.yBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine1.xBoat][submarine1.yBoat-1] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine1.xBoat][submarine1.yBoat+submarine1.mSize-1] = 0;
                            plateau.grid[submarine1.xBoat][submarine1.yBoat-1] = 1;
                            submarine1.yBoat = submarine1.yBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(submarine1.yBoat+submarine1.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine1.xBoat][submarine1.yBoat+submarine1.mSize] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine1.xBoat][submarine1.yBoat+submarine1.mSize] = 1;
                            plateau.grid[submarine1.xBoat][submarine1.yBoat] = 0;
                            submarine1.yBoat = submarine1.yBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }  
    }
    else if((submarine2.xBoat == x)&&(submarine2.yBoat == y))
    {
        //On va verifier si le bateau est deplacable
        if(submarine2.isVertical)
        {
            //Pour ce faire on parcourt ce bateau
            for(int i=submarine2.xBoat;i<submarine2.mSize;i++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[i][submarine2.yBoat] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    //On verifie que l'on ne sorte pas du plateau
                    if(submarine2.xBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine2.xBoat-1][submarine2.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine2.xBoat+submarine2.mSize-1][submarine2.yBoat] = 0;
                            plateau.grid[submarine2.xBoat-1][submarine2.yBoat] = 1;
                            submarine2.xBoat = submarine2.xBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(submarine2.xBoat+submarine2.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine2.xBoat+submarine2.mSize][submarine2.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine2.xBoat+submarine2.mSize][submarine2.yBoat] = 1;
                            plateau.grid[submarine2.xBoat][submarine2.yBoat] = 0;
                            submarine2.xBoat = submarine2.xBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }
        //Si ce bateau est horizontal
        else
        {
            //Pour ce faire on parcourt ce bateau
            for(int j=submarine2.yBoat;j<submarine2.mSize;j++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[submarine2.xBoat][j] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    if(submarine2.yBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine2.xBoat][submarine2.yBoat-1] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine2.xBoat][submarine2.yBoat+submarine2.mSize-1] = 0;
                            plateau.grid[submarine2.xBoat][submarine2.yBoat-1] = 1;
                            submarine2.yBoat = submarine2.yBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(submarine2.yBoat+submarine2.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine2.xBoat][submarine2.yBoat+submarine2.mSize] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine2.xBoat][submarine2.yBoat+submarine2.mSize] = 1;
                            plateau.grid[submarine2.xBoat][submarine2.yBoat] = 0;
                            submarine2.yBoat = submarine2.yBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }  
    }
    else if((submarine3.xBoat == x)&&(submarine3.yBoat == y))
    {
        //On va verifier si le bateau est deplacable
        if(submarine3.isVertical)
        {
            //Pour ce faire on parcourt ce bateau
            for(int i=submarine3.xBoat;i<submarine3.mSize;i++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[i][submarine3.yBoat] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    //On verifie que l'on ne sorte pas du plateau
                    if(submarine3.xBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine3.xBoat-1][submarine3.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine3.xBoat+submarine3.mSize-1][submarine3.yBoat] = 0;
                            plateau.grid[submarine3.xBoat-1][submarine3.yBoat] = 1;
                            submarine3.xBoat = submarine3.xBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(submarine3.xBoat+submarine3.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine3.xBoat+submarine3.mSize][submarine3.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine3.xBoat+submarine3.mSize][submarine3.yBoat] = 1;
                            plateau.grid[submarine3.xBoat][submarine3.yBoat] = 0;
                            submarine3.xBoat = submarine3.xBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }
        //Si ce bateau est horizontal
        else
        {
            //Pour ce faire on parcourt ce bateau
            for(int j=submarine3.yBoat;j<submarine3.mSize;j++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[submarine3.xBoat][j] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    if(submarine3.yBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine3.xBoat][submarine3.yBoat-1] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine3.xBoat][submarine3.yBoat+submarine3.mSize-1] = 0;
                            plateau.grid[submarine3.xBoat][submarine3.yBoat-1] = 1;
                            submarine3.yBoat = submarine3.yBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(submarine3.yBoat+submarine3.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine3.xBoat][submarine3.yBoat+submarine3.mSize] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine3.xBoat][submarine3.yBoat+submarine3.mSize] = 1;
                            plateau.grid[submarine3.xBoat][submarine3.yBoat] = 0;
                            submarine3.yBoat = submarine3.yBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }  
    }
    else if((submarine4.xBoat == x)&&(submarine4.yBoat == y))
    {
        //On va verifier si le bateau est deplacable
        if(submarine4.isVertical)
        {
            //Pour ce faire on parcourt ce bateau
            for(int i=submarine4.xBoat;i<submarine4.mSize;i++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[i][submarine4.yBoat] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    //On verifie que l'on ne sorte pas du plateau
                    if(submarine4.xBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine4.xBoat-1][submarine4.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine4.xBoat+submarine4.mSize-1][submarine4.yBoat] = 0;
                            plateau.grid[submarine4.xBoat-1][submarine4.yBoat] = 1;
                            submarine4.xBoat = submarine4.xBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(submarine4.xBoat+submarine4.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine4.xBoat+submarine4.mSize][submarine4.yBoat] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine4.xBoat+submarine4.mSize][submarine4.yBoat] = 1;
                            plateau.grid[submarine4.xBoat][submarine4.yBoat] = 0;
                            submarine4.xBoat = submarine4.xBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }
        //Si ce bateau est horizontal
        else
        {
            //Pour ce faire on parcourt ce bateau
            for(int j=submarine4.yBoat;j<submarine4.mSize;j++)
            {
                //Si on tombe sur une epave
                if(plateau.grid[submarine4.xBoat][j] == 5)
                {
                    //Alors canMove devient false
                    canMove = false;
                }
            }
            //Si canMove est True, alors on peut bouger le bateau
            if(canMove)
            {
                //On va demander au joueur comment il veut bouger le bateau
                System.out.println("Souhaitez vous avancer votre navire (a) ou reculer votre navire (r) ?");
                do{
                    mvt = input.next().charAt(0);
                }while((mvt != 'a')&&(mvt != 'r'));
                
                //On va verifier si la case de devant ou de derriere est libre
                if(mvt == 'a')
                {
                    if(submarine4.yBoat>0)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine4.xBoat][submarine4.yBoat-1] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine4.xBoat][submarine4.yBoat+submarine4.mSize-1] = 0;
                            plateau.grid[submarine4.xBoat][submarine4.yBoat-1] = 1;
                            submarine4.yBoat = submarine4.yBoat - 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    }
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
                else if(mvt == 'r')
                {
                    if(submarine4.yBoat+submarine4.mSize-1<14)
                    {
                        //Si la case est libre
                        if(plateau.grid[submarine4.xBoat][submarine4.yBoat+submarine4.mSize] == 0)
                        {
                            //Alors on bouge le bateau en entier d'une case
                            plateau.grid[submarine4.xBoat][submarine4.yBoat+submarine4.mSize] = 1;
                            plateau.grid[submarine4.xBoat][submarine4.yBoat] = 0;
                            submarine4.yBoat = submarine4.yBoat + 1;
                            moved = true;
                        }   
                        else
                        {
                            System.out.println("Il n'y a pas la place.. Retour au menu d'action");
                        }
                    } 
                    //On sort du plateau..
                    else
                    {
                        System.out.println("Vous ne pouvez pas sortir du plateau..");
                    }
                }
            }
            else
            {
                System.out.println("Le bateau est endommage.. Il n'est pas deplacable. Retour au menu d'action");
            }
        }  
    }
    return moved;
}
}