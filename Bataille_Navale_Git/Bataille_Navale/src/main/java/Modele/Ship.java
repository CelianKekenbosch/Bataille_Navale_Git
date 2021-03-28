/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.Scanner;
import static Modele.Plateau.evalCase;
import static Modele.Plateau.fixerCase;

public abstract class Ship {

    protected String mTypeShip;
    protected int mSize;
    protected int mfirePower;
    protected boolean isVertical;
    protected int[][] coordonneesFront;
    protected int[][] coordonneesRear;
    protected static int mNbFuseeEclairante=1;
    
    public Ship(int size,int firePower, String typeShip){//constructeur de la classe mère Ship
    this.mSize= size;
    this.mfirePower= firePower;
    this.mTypeShip=typeShip;
    
    if(!"destroyer".equals(typeShip)){
        mNbFuseeEclairante=0;
    }
}
    
  public void fire(Plateau plateau,int firePower, int x, int y, String typeShip){
        System.out.println("rentrez les coordonnées du point d'impact svp");
        Scanner scanner = new Scanner(System.in);//on rentre les coordonnées du point d'impact avec un blindage de 0 à 14
        do{
            x= scanner.nextInt();
        }while(x<=0||x>14);
        do{
            y= scanner.nextInt();
        }while(y<=0||y>14);
        
        switch (evalCase(plateau,2+2*x,2+3*y)) {//en fonction de la valeur d'une case, il y un réusltat dufféret qand on tire dessu
            case 0:
                System.out.println("PLOUF!à l'eau...");//si on tire dans l'eau rien ne se passe
                break;
            case 5:
                System.out.println("Faites plus attention, vous avez déjà touché ce point du navire !");//pareil pour un débris/épave
                break;
            default:
                System.out.println("Touché!");//si on touche un navire, alors on applique une déflagration à partir du point d'impact
                
                if("subamrine".equals(typeShip)){
                    System.out.println("ET coulé!!!(un sous-marin en moins) Rien ne peut plus vous arrêter!");
                    fixerCase(plateau,x,y,5);    
                }else{
                for(int z=0;z<firePower;z++){//pas tout à fait bon pour notre projet puisque cette méthode va dans les 4 directions
                    if(evalCase(plateau,(x+z),(y))!=0){
                        do{
                            fixerCase(plateau,(x+z),(y),5);
                          }while(x+z<15);
                    }else if(evalCase(plateau,(x),(y+z))!=0){
                        do{
                            fixerCase(plateau,(x),(y+z),5);
                        }while(y+z<15);
                    }else if(evalCase(plateau,(x-z),(y))!=0){
                        do{
                            fixerCase(plateau,(x-z),(y),5);
                        }while(x-z>=0);
                    }else if(evalCase(plateau,(x),(y-z))!=0){
                        do{
                            fixerCase(plateau,(x),(y-z),5);
                        }while(y-z>=0);
                    }
                    else{
                        System.out.println("OU PAS!!!une ombre se déplace dans les profondeurs sans que vous puissiez l'endommager");
                    }
                break;
        }
        
    }    
    }
  }
  
  public void fusee_eclairante(Plateau plateau, int x, int y, String typeShip){
        if("destroyer".equals(typeShip)){
            Scanner scanner = new Scanner(System.in);
            if(Ship.mNbFuseeEclairante==0){
                System.out.println("Ce destroyer n'a plus de fusée éclairante, désolé!");
            }else{
                System.out.println("rentrez le point d'impact de la fusée éclairante(x,y) svp");
                do{
                    x= scanner.nextInt();
                }while(x<=0||x>14);
                do{
                    y= scanner.nextInt();
                }while(y<=0||y>14);
                  
                /*on devra travailler sur les plateaux directement(les cases du plateau des tirs du joureur =les cases en question du plateau ordi)
                for(int w=0;w<4;w++){
                    for(int z=0;z<4;z++){
                            plateau2[2+2*(i+w)][2+3*(j+z)]=plateau3[2+2*(i+w)][2+3*(j+z)];
                            plateau2[2+2*(i+w)][3+3*(j+z)]=plateau3[2+2*(i+w)][3+3*(j+z)];
                    }
                }
                */
                mNbFuseeEclairante--;
                System.out.println("les informations qui viennent d'apparaître sur votre plateau ne resteront pas longtemps, utilisez-les rapidement!");
            }
        }else{
            System.out.println("le navire n'est pas capable de tirer des fusées éclairantes");
        }
    }
  
  public void move(Plateau plateau, int size, int x, int y){
      System.out.println("quel bateau vous-vous bouger?");
      Scanner scanner = new Scanner(System.in);
      
      
  }
}
//test