package Modele;

import java.util.Scanner;
import static Modele.Plateau.evalCase;
import static Modele.Plateau.fixerCase;

public class Ship {

    public String mTypeShip;
    public int mSize;
    protected int mfirePower;
    protected boolean isVertical;
    protected static int mNbFuseeEclairante=1;
    public boolean isAlive = true;
    protected int xBoat;
    protected int yBoat;
    
    public Ship(String typeShip){//constructeur de la classe mÃ¨re Ship
    this.mTypeShip=typeShip;
    
    if(!"destroyer".equals(typeShip)){
        mNbFuseeEclairante=0;
    }
}
    
public static void fire(Plateau plateau, String typeShip, Ship dreadnought, Ship cruiser1, Ship cruiser2, Ship destroyer1, Ship destroyer2, Ship destroyer3, Ship submarine1, Ship submarine2, Ship submarine3, Ship submarine4)
{   
    int x,y;
    char c;
    Scanner input = new Scanner(System.in);
    do{
        System.out.println("Rentrez l'abcisse du point d'impact svp (entre 0 et 14)");
        y = input.nextInt();
    }while((y<0)||(y>14));
      
      
    do{
        System.out.println("Rentrez l'ordonnee du point d'impact svp (entre a et o)");
        c = input.next().charAt(0);
        x=(int)(c -'a');
    }while((x<0)||(x>14));

        

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
                if("submarine".equals(typeShip))
                {
                    fixerCase(plateau, x, y, 5);
                }
                else if("destroyer".equals(typeShip))
                {
                    if((plateau.grid[x][y] != 1)&&(plateau.grid[x][y] != 0))
                    {
                        fixerCase(plateau, x, y, 5);
                    }
                }
                else if("cruiser".equals(typeShip))
                {
                    //On va dessiner un "+" sans la barre du bas
                    if((plateau.grid[x][y] != 1)&&(plateau.grid[x][y] != 0))
                    {
                        fixerCase(plateau, x, y, 5);	//Case centrale
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
                else if("dreadnought".equals(typeShip))
                {
                    //On va dessiner un "+"
                    if((plateau.grid[x][y] != 1)&&(plateau.grid[x][y] != 0))
                    {
                        fixerCase(plateau, x, y, 5);	//Case centrale
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
    if(dreadnought.isVertical)
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
    }
    else
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
    }


    if(cruiser1.isVertical)
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
    }
    else
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
    }


    if(cruiser2.isVertical)
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
    }
    else
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
    }


    if(destroyer1.isVertical)
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
    }
    else
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
    }


    if(destroyer2.isVertical)
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
    }
    else
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
    }


    if(destroyer3.isVertical)
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
    }
    else
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
    }


    if(submarine1.isVertical)
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
    }
    else
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
    }


    if(submarine2.isVertical)
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
    }
    else
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
    }


    if(submarine3.isVertical)
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
    }
    else
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
    }


    if(submarine4.isVertical)
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
    }
    else
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
    }
}
  
  public static void fusee_eclairante(Plateau plateau3, String typeShip){
       int x; 
       int y;
       char c;
      if("destroyer".equals(typeShip)){
            Scanner scanner = new Scanner(System.in);
            if(Ship.mNbFuseeEclairante==0){
                System.out.println("Ce destroyer n'a plus de fusee eclairante, desole!");
            }else{       System.out.println("rentrez le point d'impact de la fusee eclairante(x,y) svp");
                
                do{
                    c= scanner.next().charAt(0);
                    x=(int)(c-'a');
                }while(x<=0||x>14);
                
                do{
                    y= scanner.nextInt();
                }while(y<=0||y>14);
         
                  
                /*on devra travailler sur les plateaux directement(les cases du plateau des tirs du joureur =les cases en question du plateau ordi)
                for(int w=0;w<4;w++){
                    for(int z=0;z<4;z++){
                            plateau2[2+2*(x+w)][2+3*(y+z)]=plateau3[2+2*(x+w)][2+3*(y+z)];
                            plateau2[2+2*(x+w)][3+3*(y+z)]=plateau3[2+2*(x+w)][3+3*(x+z)];
                    }
                }
*/

                mNbFuseeEclairante--;
                System.out.println("les informations qui viennent d'apparaitre sur votre plateau ne resteront pas longtemps, utilisez-les rapidement!");
            }
        }else{
            System.out.println("le navire n'est pas capable de tirer des fusees eclairantes");
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
      
      if(size!=1){
      do{
            System.out.println("quelles sont les coordonnees de l'avant du bateau que vous voulez bouger?");
            do{
                do{
                    c1= scanner.next().charAt(0);
                    x1=(int)(c1-'a');
                
                }while(x1<=0||x1>14);
      
                do{
                    y1= scanner.nextInt();
                
                }while(y1<=0||y1>14);
                
                if(evalCase(plateau,x1,y1)==0){
                    System.out.println("ce ne sont pas les coordonnees de l'avant d'un bateau, il s'agit de la mer, veillez recommencer svp");
                }
            }while(evalCase(plateau,x1,y1)==0);
      
            System.out.println("quelles sont les coordonnees de l'arriere du bateau que vous voulez bouger?");
            do{
                do{
                    c2= scanner.next().charAt(0);
                    x2=(int)(c2-'a');
                
                }while(x2<=0||x2>14);
      
                do{
                    y2= scanner.nextInt();
                
                }while(y2<=0||y2>14);
                
                if(evalCase(plateau,x2,y2)==0){
                    System.out.println("ce ne sont pas les coordonnees de l'arriere d'un bateau, il s'agit de la mer, veillez recommencer svp");
                }
            }while(evalCase(plateau,x2,y2)==0);
            
            if(((x1==x2)&&(y1==y2))||(((x1!=(x2+(size-1)))&&(x1!=(x2-(size-1))))&&((y1!=(y2+(size-1)))&&(y1!=(y2-(size-1)))))){
                System.out.println("l'espace entre les positions de l'avant et de l'arriere du bateau est incoherent, veillez recommencer svp");
            }
            
      }while(((x1==x2)&&(y1==y2))||(((x1!=(x2+(size-1)))&&(x1!=(x2-(size-1))))&&((y1!=(y2+(size-1)))&&(y1!=(y2-(size-1))))));
      
      
          if(x1==(x2+size)&&(y1==y2)){//le bateau est horizontale et l'avant se trouve Ã  droite de l'arriÃ¨re sur le plateau
              for(int i=0;i<size;i++){
              switch (evalCase(plateau,(x2+i),y1)) {
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
              if(ok==size){
                      System.out.println("Voulez-vous avancer ou reculer avce ce navire(a/r)?");
                      do{
                          movement= scanner.next().charAt(0);
                      }while(movement!='a'&&movement!='r');
                      if(movement=='a'){
                          if(!((x1+1>14)||(evalCase(plateau,(x1+1),y1)!=0))){
                                for(int j=0;j<size;j++){
                                  fixerCase(plateau,(x2+j+1),y2,(evalCase(plateau,(x2+j),y2)));
                             }
                                fixerCase(plateau,x2,y2,0);
                          }
                      else{
                           System.out.println("ce bateau ne peut pas avancer, reessayez");
                          }
                      }
                      else{
                          if(!((x2-1<0)||(evalCase(plateau,(x2-1),y1)!=0))){
                              for(int j=0;j<size;j++){
                                  fixerCase(plateau,(x2+j-1),y2,(evalCase(plateau,(x2+j),y1)));
                             }
                                fixerCase(plateau,x1,y1,0);
                          }
                          else{
                     }         System.out.println("ce bateau ne peut pas reculer, reessayez");
                          }
                      }
                      
                      
              }

          if(x1==(x2-size)&&(y1==y2)){//le bateau est horizontale et l'avant se trouve Ã  gauche de l'arriÃ¨re sur le plateau
              for(int i=0;i<size;i++){
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
              if(ok==size){
                      System.out.println("Voulez-vous avancer ou reculer avce ce navire(a/r)?");
                       do{
                          movement= scanner.next().charAt(0);
                      }while(movement!='a'&&movement!='r');
                      if(movement=='r'){
                           if(!((x2+1>14)||(evalCase(plateau,(x2+1),y1)!=0))){
                              for(int j=0;j<size;j++){
                                  fixerCase(plateau,(x1+j+1),y1,(evalCase(plateau,(x1+j),y1)));
                             }
                                fixerCase(plateau,x1,y1,0);
                          }
                           else{
                                System.out.println("ce bateau ne peut pas reculer, reessayez");
                           }
                      }
                      else{
                          if(!((x1-1<0)||(evalCase(plateau,(x1-1),y1)!=0))){
                              for(int j=0;j<size;j++){
                                  fixerCase(plateau,(x1+j-1),y1,(evalCase(plateau,(x1+j),y1)));
                             }
                                fixerCase(plateau,x2,y2,0);
                          }
                          else{
                                System.out.println("ce bateau ne peut pas avancer, reessayez");
                          }
                      } 
                  }

              }
          if(y1==(y2+size)&&(x1==x2)){//le bateau est verticale et l'avant se trouve en bas de l'arriÃ¨re sur le plateau
              for(int i=0;i<size;i++){
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
              if(ok==size){
                      System.out.println("Voulez-vous avancer ou reculer avce ce navire(a/r)?");
                       do{
                          movement= scanner.next().charAt(0);
                      }while(movement!='a'&&movement!='r');
                      if(movement=='a'){
                          if(!((y1+1>14)||(evalCase(plateau,x1,(y1+1))!=0))){
                              for(int j=0;j<size;j++){
                                  fixerCase(plateau,x2,(y2+j+1),(evalCase(plateau,x2,(y2+j))));
                             }
                                fixerCase(plateau,x2,y2,0);
                          }
                          else{
                               System.out.println("ce bateau ne peut pas avancer, reessayez");
                          }
                      }
                      else{
                          if(!((y2-1<0)||(evalCase(plateau,x2,(y1-1))!=0))){
                              for(int j=0;j<size;j++){
                                  fixerCase(plateau,x2,(y2+j-1),(evalCase(plateau,x2,(y2+j))));
                             }
                                fixerCase(plateau,x1,y1,0);
                          }
                          else{
                              System.out.println("ce bateau ne peut pas reculer, reessayez");
                          }
                      } 
                  }
              }
              
          if(y1==(y2+size)&&(x1==x2)){//le bateau est verticale et l'avant se trouve en haut de l'arriÃ¨re sur le plateau
              for(int i=0;i<size;i++){
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
                 if(ok==size){
                      System.out.println("Voulez-vous avancer ou reculer avce ce navire(a/r)?");
                       do{
                          movement= scanner.next().charAt(0);
                      }while(movement!='a'&&movement!='r');
                      if(movement=='a'){
                          if(!((y1-1<0)||(evalCase(plateau,x2,(y1-1))!=0))){
                              for(int j=0;j<size;j++){
                                  fixerCase(plateau,x1,(y1+j-1),(evalCase(plateau,x1,(y1+j))));
                             }
                                fixerCase(plateau,x2,y2,0);
                          }
                          else{
                               System.out.println("ce bateau ne peut pas avancer, reessayez");
                          }
                      }
                      else{
                          if(!((y2+1>14)||(evalCase(plateau,x1,(y2+1))!=0))){
                              for(int j=0;j<size;j++){
                                  fixerCase(plateau,x1,(y1+j+1),(evalCase(plateau,x1,(y1+j))));
                             }
                                fixerCase(plateau,x1,y1,0);
                          }
                          else{
                              System.out.println("ce bateau ne peut pas reculer, reessayez");
                          }
                      } 
                   }
              }
      if(marche==false){
          System.out.println("le bateau ne peut pas se deplacer car il a un emplacement deja  endommage, essayez de tirer plutot");
      }
      if(wtf==true){
          System.out.println("vous n'avez pas vraiment saisis les coordonnees de l'avant et arriere d'un navire puisqu'il y a de la mer entre les deux coordonnees, essayez de tirer ou rentrez d'autres coordonnees valides ...");
      }
      }
      else{
          do{
                System.out.println("quelles sont les coordonnees du bateau que vous voulez bouger?");
                do{
                    c1= scanner.next().charAt(0);
                    x1=(int)(c1-'a');
                
                }while(x1<=0||x1>14);
      
                do{
                    y1= scanner.nextInt();
                
                }while(y1<=0||y1>14);
                
                if(evalCase(plateau,x1,y1)==0){
                    System.out.println("ce ne sont pas les coordonnees de l'avant d'un bateau, il s'agit de la mer, veillez recommencer svp");
                }
                
            }while(evalCase(plateau,x1,y1)==0);
          if((evalCase(plateau,x1,y1)!=0)){
              do{
                  direction=scanner.nextLine();
              }while((!"haut".equals(direction))&&(!"bas".equals(direction))&&(!"gauche".equals(direction))&&(!"droite".equals(direction)));
              if("gauche".equals(direction)){
                  if(((evalCase(plateau,x1,(y1-1)))!=0)||((y1-1)<0)){
                      System.out.println("ce bateau ne peut pas se deplacer vers la gauche, reessayer svp");
                  }
                  else{
                      fixerCase(plateau,x1,(y1-1),(evalCase(plateau,x1,y1)));
                      fixerCase(plateau,x1,y1,0);
                  }
                  
              }
              if("droite".equals(direction)){
                  if(((evalCase(plateau,x1,(y1+1)))!=0)||((y1+1)>14)){
                      System.out.println("ce bateau ne peut pas se deplacer vers la droite, reessayer svp");
                  }
                  else{
                      fixerCase(plateau,x1,(y1+1),(evalCase(plateau,x1,y1)));
                      fixerCase(plateau,x1,y1,0);
                  }
              }
              if("haut".equals(direction)){
                  if(((evalCase(plateau,(x1-1),y1))!=0)||((x1-1)<0)){
                      System.out.println("ce bateau ne peut pas se deplacer vers le haut, reessayer svp");
                  }
                  else{
                      fixerCase(plateau,(x1-1),y1,(evalCase(plateau,x1,y1)));
                      fixerCase(plateau,x1,y1,0);
                  }
              }
              if("bas".equals(direction)){
                  if(((evalCase(plateau,(x1-1),y1))!=0)||((x1+1)>14)){
                      System.out.println("ce bateau ne peut pas se deplacer vers le bas, reessayer svp");
                  }
                  else{
                      fixerCase(plateau,(x1+1),y1,(evalCase(plateau,x1,y1)));
                      fixerCase(plateau,x1,y1,0);
                  }
              }
           }
      }
  }
}