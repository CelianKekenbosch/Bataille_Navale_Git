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
  
  public static void move(Plateau plateau, int size){
      int x1;
      int y1;
      char c1;
      int x2;
      int y2;
      char c2;
      boolean marche=true;
      boolean wtf=false;
      char movement;
      int ok=0;
      String direction;
      Scanner scanner = new Scanner(System.in);
      
      if(size!=1){//si ça n'est pas un sous-marin
      do{//on recommence jusqu'a ce que les deux point soient pas les memes et que qu'il soient a une distance de la taille du navire(-1) verticalement ou horizontalement
            System.out.println("Quelles sont les coordonnees de l'avant du bateau que vous voulez bouger?");
            do{//on recommence jusqu'a ce que les coordonnnes rentrees corresponde a une case qui n'est pas la mer
                do{//on rentre une lettre (de a a 0)et elle est convertie en chiffre
                    c1= scanner.next().charAt(0);
                    x1=(int)(c1-'a');
                
                }while(x1<0||x1>14);
      
                do{//on rentre un chiffre de 0 a 14
                    y1= scanner.nextInt();
                
                }while(y1<0||y1>14);
                
                if(evalCase(plateau,x1,y1)==0){//message de probleme
                    System.out.println("Ce ne sont pas les coordonnees de l'avant d'un bateau, il s'agit de la mer, veillez recommencer svp");
                }
            }while(evalCase(plateau,x1,y1)==0);
      
            System.out.println("Quelles sont les coordonnees de l'arriere du bateau que vous voulez bouger?");
            do{//on recommence jusqu'a ce que les coordonnnes rentrees corresponde a une case qui n'est pas la mer
                do{//on rentre une lettre(de a a 0) et elle est convertie en chiffre
                    c2= scanner.next().charAt(0);
                    x2=(int)(c2-'a');
                
                }while(x2<0||x2>14);
      
                do{//on rentre un chiffre de 0 a 14
                    y2= scanner.nextInt();
                
                }while(y2<0||y2>14);
                
                if(evalCase(plateau,x2,y2)==0){//message de probleme
                    System.out.println("ce ne sont pas les coordonnees de l'arriere d'un bateau, il s'agit de la mer, veillez recommencer svp");
                }
            }while(evalCase(plateau,x2,y2)==0);
            
            if(((x1==x2)&&(y1==y2))||(((x1!=(x2+(size-1)))&&(x1!=(x2-(size-1))))&&((y1!=(y2+(size-1)))&&(y1!=(y2-(size-1)))))){//message de probleme si incoherence entre les points
                System.out.println("L'espace entre les positions de l'avant et de l'arriere du bateau est incoherent, veillez recommencer svp");
            }
            
      }while(((x1==x2)&&(y1==y2))||(((x1!=(x2+(size-1)))&&(x1!=(x2-(size-1))))&&((y1!=(y2+(size-1)))&&(y1!=(y2-(size-1))))));
      
      
          if(x1==(x2+size)&&(y1==y2)){//le bateau est verticale et l'avant se trouve en bas de l'arriere sur le plateau
              for(int i=0;i<size+1;i++){
              switch (evalCase(plateau,(x2+i),y1)) {//on parcourt toute les cases du bateau qu'on a choissit, et on verifie s'il y a au moins une case endommagee ou la mer, si oui, ok!=size
                  case 5:
                      marche=false;
                      break;
                  case 0:
                      wtf=true;
                      break;
                  default:
                      ok++;
                      break;
                      }
              }
              if(ok==size){//s'il n'y a pas de cases endommagees, on demande si le bateau avance ou recule
                      System.out.println("Voulez-vous avancer ou reculer avec ce navire(a/r)?");
                      do{//on rentre une lettre jusqu'a ce que la lettre soit un r ou un a
                          movement= scanner.next().charAt(0);
                      }while(movement!='a'&&movement!='r');
                      if(movement=='a'){//si c'est un a->avancer
                          if(((x1+1>14)||(evalCase(plateau,(x1+1),y1)!=0))){//si y'a des obstacles ou si on sort du plateau, ca sort un message d'erreur
                                System.out.println("ce bateau ne peut pas avancer, reessayez");
                          }
                      else{//on avance toute les cases du bateau, et on supprime(met a zero) la case de l'arriere 
                           for(int j=0;j<size;j++){
                                  fixerCase(plateau,(x2+j+1),y2,(evalCase(plateau,(x2+j),y2)));
                             }
                                fixerCase(plateau,x2,y2,0);
                          }
                      }
                      else{//si c'est un r->reculer
                          if(((x2-1<0)||(evalCase(plateau,(x2-1),y1)!=0))){//si y'a des obstacles ou si on sort du plateau, ca sort un message d'erreur
                              System.out.println("ce bateau ne peut pas reculer, reessayez");
                          }
                          else{
                     }         for(int j=0;j<size;j++){//on recule toute les cases du bateau, et on supprime(met a zero) la case de l'avant 
                                  fixerCase(plateau,(x2+j-1),y2,(evalCase(plateau,(x2+j),y1)));
                             }
                                fixerCase(plateau,x1,y1,0);
                          }
                      }
                      
                      
              }

          if(x1==(x2-size)&&(y1==y2)){//le bateau est verticale et l'avant se trouve en haut de l'arriere sur le plateau
              for(int i=0;i<size+1;i++){//on parcourt toute les cases du bateau qu'on a choissit, et on verifie s'il y a au moins une case endommagee ou la mer, si oui, ok!=size
              switch (evalCase(plateau,(x1+i),y1)) {
                  case 5:
                      marche=false;
                      break;
                  case 0:
                      wtf=true;
                      break;
                  default:
                      ok++;
                      break;
                      
                      }
              }
              if(ok==size){//s'il n'y a pas de cases endommagees, on demande si le bateau avance ou recule
                      System.out.println("Voulez-vous avancer ou reculer avec ce navire(a/r)?");
                       do{//on rentre une lettre jusqu'a ce que la lettre soit un r ou un a
                          movement= scanner.next().charAt(0);
                      }while(movement!='a'&&movement!='r');
                      if(movement=='r'){//si c'est un r->reculer
                           if(((x2+1>14)||(evalCase(plateau,(x2+1),y1)!=0))){//si y'a des obstacles ou si on sort du plateau, ca sort un message d'erreur
                              System.out.println("ce bateau ne peut pas reculer, reessayez");
                          }
                           else{
                                for(int j=0;j<size;j++){//on recule toute les cases du bateau, et on supprime(met a zero) la case de l'avant 
                                  fixerCase(plateau,(x1+j+1),y1,(evalCase(plateau,(x1+j),y1)));
                             }
                                fixerCase(plateau,x1,y1,0);
                           }
                      }
                      else{//si c'est un a->avancer
                          if(((x1-1<0)||(evalCase(plateau,(x1-1),y1)!=0))){//si y'a des obstacles ou si on sort du plateau, ca sort un message d'erreur
                              System.out.println("ce bateau ne peut pas avancer, reessayez");
                          }
                          else{
                                for(int j=0;j<size;j++){//on avance toute les cases du bateau, et on supprime(met a zero) la case de l'arriere 
                                  fixerCase(plateau,(x1+j-1),y1,(evalCase(plateau,(x1+j),y1)));
                             }
                                fixerCase(plateau,x2,y2,0);
                          }
                      } 
                  }

              }
          if(y1==(y2+size)&&(x1==x2)){//le bateau est horizontale et l'avant se trouve a droite de l'arriere sur le plateau
              for(int i=0;i<size+1;i++){//on parcourt toute les cases du bateau qu'on a choissit, et on verifie s'il y a au moins une case endommagee ou la mer, si oui, ok!=size
              switch (evalCase(plateau,x1,(y2+i))) {
                  case 5:
                      marche=false;
                      break;
                  case 0:
                      wtf=true;
                      break;
                  default:
                      ok++;
                      break;
                      }
              }
              if(ok==size){//s'il n'y a pas de cases endommagees, on demande si le bateau avance ou recule
                      System.out.println("Voulez-vous avancer ou reculer avec ce navire(a/r)?");
                       do{//on rentre une lettre jusqu'a ce que la lettre soit un r ou un a
                          movement= scanner.next().charAt(0);
                      }while(movement!='a'&&movement!='r');
                      if(movement=='a'){//si c'est un a->avancer
                          if(((y1+1>14)||(evalCase(plateau,x1,(y1+1))!=0))){//si y'a des obstacles ou si on sort du plateau, ca sort un message d'erreur
                              System.out.println("ce bateau ne peut pas avancer, reessayez");
                          }
                          else{//on avance toute les cases du bateau, et on supprime(met a zero) la case de l'arriere 
                               for(int j=0;j<size;j++){
                                  fixerCase(plateau,x2,(y2+j+1),(evalCase(plateau,x2,(y2+j))));
                             }
                                fixerCase(plateau,x2,y2,0);
                          }
                      }
                      else{//si c'est un r->reculer
                          if(((y2-1<0)||(evalCase(plateau,x2,(y1-1))!=0))){//si y'a des obstacles ou si on sort du plateau, ca sort un message d'erreur
                              System.out.println("ce bateau ne peut pas reculer, reessayez");
                          }
                          else{
                              for(int j=0;j<size;j++){//on recule toute les cases du bateau, et on supprime(met a zero) la case de l'avant 
                                  fixerCase(plateau,x2,(y2+j-1),(evalCase(plateau,x2,(y2+j))));
                             }
                                fixerCase(plateau,x1,y1,0);
                          }
                      } 
                  }
              }
              
          if(y1==(y2+size)&&(x1==x2)){//le bateau est horizontale et l'avant se trouve a gauche de l'arriere sur le plateau
              for(int i=0;i<size+1;i++){//on parcourt toute les cases du bateau qu'on a choissit, et on verifie s'il y a au moins une case endommagee ou la mer, si oui, ok!=size
              switch (evalCase(plateau,x1,(y1+i))) {
                  case 5:
                      marche=false;
                      break;
                  case 0:
                      wtf=true;
                      break;
                  default:
                      break;
                 }
              }
                 if(ok==size){//s'il n'y a pas de cases endommagees, on demande si le bateau avance ou recule
                      System.out.println("Voulez-vous avancer ou reculer avec ce navire(a/r)?");
                       do{//on rentre une lettre jusqu'a ce que la lettre soit un r ou un a
                          movement= scanner.next().charAt(0);
                      }while(movement!='a'&&movement!='r');
                      if(movement=='a'){//si c'est un a->avancer
                          if(((y1-1<0)||(evalCase(plateau,x2,(y1-1))!=0))){//si y'a des obstacles ou si on sort du plateau, ca sort un message d'erreur
                              System.out.println("ce bateau ne peut pas avancer, reessayez");
                          }
                          else{//on avance toute les cases du bateau, et on supprime(met a zero) la case de l'arriere 
                               for(int j=0;j<size;j++){
                                  fixerCase(plateau,x1,(y1+j-1),(evalCase(plateau,x1,(y1+j))));
                             }
                                fixerCase(plateau,x2,y2,0);
                          }
                      }
                      else{//si c'est un r->reculer
                          if(((y2+1>14)||(evalCase(plateau,x1,(y2+1))!=0))){//si y'a des obstacles ou si on sort du plateau, ca sort un message d'erreur
                              System.out.println("ce bateau ne peut pas reculer, reessayez");
                          }
                          else{//on recule toute les cases du bateau, et on supprime(met a zero) la case de l'avant 
                              for(int j=0;j<size;j++){
                                  fixerCase(plateau,x1,(y1+j+1),(evalCase(plateau,x1,(y1+j))));
                             }
                                fixerCase(plateau,x1,y1,0);
                          }
                      } 
                   }
              }
      if(marche==false){//quand on a teste s'il y avait des cases endommagees, on a return le boolean marche=false pcq il y avit au moins une case endommagee
          System.out.println("le bateau ne peut pas se deplacer car il a un emplacement deja  endommage, essayez de tirer plutot");
      }
      if(wtf==true){//quand on a teste s'il y avait des cases endommagees, on a return le boolean wtf=true pcq il y avait la mer au moins une fois
          System.out.println("vous n'avez pas vraiment saisis les coordonnees de l'avant et arriere d'un navire puisqu'il y a de la mer entre les deux coordonnees, essayez de tirer ou rentrez d'autres coordonnees valides ...");
      }
      }
      else{//si c'est un sous-marin
          do{//on rentre les coordonnees du points jusqu'a que ce soit pas la mer
                System.out.println("quelles sont les coordonnees du bateau que vous voulez bouger?");
                do{//on rentre une lettre(entre a et 0)qui est convertie en chiffre entre 0 et 14(jusqu'a que ce soit le cas)
                    c1= scanner.next().charAt(0);
                    x1=(int)(c1-'a');
                
                }while(x1<0||x1>14);
      
                do{//on rentreun chiffre compris entre 0 et 14(jusqu'a que ce soit le cas)
                    y1= scanner.nextInt();
                
                }while(y1<0||y1>14);
                
                if(evalCase(plateau,x1,y1)==0){//message d'erreur si la case rentree est la mer
                    System.out.println("ce ne sont pas les coordonnees de l'avant d'un bateau, il s'agit de la mer, veillez recommencer svp");
                }
                
            }while(evalCase(plateau,x1,y1)==0);
          if((evalCase(plateau,x1,y1)!=0)){//si on a bien rentre les coordonnes du points alors on peut continuer
              do{//on rentre un string qui correspnd a la direction et qui est blinde pour qu'on ne rentre pas n'importe quoi
                  direction=scanner.nextLine();
              }while((!"haut".equals(direction))&&(!"bas".equals(direction))&&(!"gauche".equals(direction))&&(!"droite".equals(direction)));
              if("gauche".equals(direction)){
                  if(((evalCase(plateau,x1,(y1-1)))!=0)||((y1-1)<0)){//si y'a un obstacle ou si on sort du plateau, ca sort un message d'erreur
                      System.out.println("ce bateau ne peut pas se deplacer vers la gauche, reessayer svp");
                  }
                  else{
                      fixerCase(plateau,x1,(y1-1),(evalCase(plateau,x1,y1)));//on bouge la case de 1 vers la gauche 
                      fixerCase(plateau,x1,y1,0);//on supprime(met a zero) l'ancienne case
                  }
                  
              }
              if("droite".equals(direction)){
                  if(((evalCase(plateau,x1,(y1+1)))!=0)||((y1+1)>14)){//si y'a un obstacle ou si on sort du plateau, ca sort un message d'erreur
                      System.out.println("ce bateau ne peut pas se deplacer vers la droite, reessayer svp");
                  }
                  else{
                      fixerCase(plateau,x1,(y1+1),(evalCase(plateau,x1,y1)));//on bouge la case de 1 vers la droite 
                      fixerCase(plateau,x1,y1,0);//on supprime(met a zero) l'ancienne case
                  }
              }
              if("haut".equals(direction)){
                  if(((evalCase(plateau,(x1-1),y1))!=0)||((x1-1)<0)){//si y'a un obstacle ou si on sort du plateau, ca sort un message d'erreur
                      System.out.println("ce bateau ne peut pas se deplacer vers le haut, reessayer svp");
                  }
                  else{
                      fixerCase(plateau,(x1-1),y1,(evalCase(plateau,x1,y1)));//on bouge la case de 1 vers le haut  
                      fixerCase(plateau,x1,y1,0);//on supprime(met a zero) l'ancienne case
                  }
              }
              if("bas".equals(direction)){
                  if(((evalCase(plateau,(x1-1),y1))!=0)||((x1+1)>14)){//si y'a un obstacle ou si on sort du plateau, ca sort un message d'erreur
                      System.out.println("ce bateau ne peut pas se deplacer vers le bas, reessayer svp");
                  }
                  else{
                      fixerCase(plateau,(x1+1),y1,(evalCase(plateau,x1,y1)));//on bouge la case de 1 vers le bas
                      fixerCase(plateau,x1,y1,0);//on supprime(met a zero) l'ancienne case
                  }
              }
           }
      }
  }
}