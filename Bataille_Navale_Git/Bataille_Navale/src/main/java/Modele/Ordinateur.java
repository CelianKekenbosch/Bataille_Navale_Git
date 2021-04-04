package Modele;

import static Modele.Plateau.evalCase;
import static Modele.Plateau.fixerCase;

public class Ordinateur
{
    private String name;
    private int old_i,old_j;    //Ancienne coordonnees utilisees pour abattre un bateau
    private int new_i,new_j;    //Nouvelle coordonnees utilisees pour abattre un bateau
    private boolean sinked;     //Ce booleen passe a true si un bateau a ete coule afin de reprendre la recherche
    private int fusee;
    
    public Ordinateur(String name)
    {
        this.name = name;
        old_i = -1;
        old_j = -1;
        new_i = -1;
        new_j = -1;
        sinked = false;
    }

    //Methode qui va faire jouer l'ordinateur, il faudra au prealable set un i et un j a -1 qui seront les coordonnees par defaut si l'IA n'a pas touche de bateaux
    //IL FAUT FOURNIR LE PLATEAU ADVERSE, LES PREMIERS NAVIRES SONT CEUX DU JOUEUR, LES AUTRES CEUX DE L'IA
    public void iaTurn(Plateau plateau, Ship dreadnought, Ship cruiser1, Ship cruiser2, Ship destroyer1, Ship destroyer2, Ship destroyer3, Ship submarine1, Ship submarine2, Ship submarine3, Ship submarine4, Ship dreadnought11, Ship cruiser11, Ship cruiser22, Ship destroyer11, Ship destroyer22, Ship destroyer33, Ship submarine11, Ship submarine22, Ship submarine33, Ship submarine44)
    {
        //Si on a coule un navire, on reset les coordonnees de tir
        if(sinked)
        {
            old_i = -1;
            old_j = -1;
            new_i = -1;
            new_j = -1;
            sinked = false;
            System.out.println("L'ordinateur ayant coule un de vos batiments va reprendre ses recherches !");
        }
        
        boolean antiDoubleAction = false;
        //Cas ou l'ordinateur n'a pas trouve de bateaux
        if((new_i == -1) && (new_j == -1))
        {
            antiDoubleAction = true;
            //On va generer un entier egal a 0,1,2 ou 3. Si il est egal a 1 ou 2, l'ordinateur tentera de tirer une fusee eclairante, si c'est 3, il bougera un bateau
            int r;
            do{
            r = 0 + (int)(Math.random() * ((2-0)+1));   //PAS LE 3 POUR LE MOMENT
            }while((fusee == 3)&&((r == 1)||(r == 2))); //Si on a plus de fusee, on recommence

            //On va generer deux entier aleatoire representant les coordonnees d'un tir (entre 0 et 14)
            int x,y;
            
            //On genere x et y a nouveau si la case est une epave
            do{
            x = 0 + (int)(Math.random() * ((14-0)+1));
            y = 0 + (int)(Math.random() * ((14-0)+1));
            }while(plateau.grid[x][y] == 5);
            
            //Cas du tir
            if(r == 0)
            {
                System.out.println("Tir aleatoire");
                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, x, y, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                //On check la grille adverse pour savoir si on a touche qq chose
                if(!sinked)
                {
                    if(plateau.grid[x][y] == 5)
                    {
                        //On marque les coordonnees de l'impact, qui seront recuperees par la suite
                        new_i = x; 
                        new_j = y;
                        old_i = x;
                        old_j = y;
                    }
                }
            }
            
            //Cas fusee eclairante
            if((r == 1)||(r == 2))
            {
                fusee_eclairante_IA(plateau, x, y, destroyer11, destroyer22, destroyer33);
                char f;
                f = (char) (x + 'a');
                System.out.println("L'ordinateur tire une fusee eclairante en " + f + ", " + y);
                //Si la fusee eclairante touche un bateau adverse, il marque les coordonnees de la case en haut a gauche
                fusee = fusee + 1; //On indique qu'on a tire une fusee (compte)
                boolean sortie_de_boucle = false; //A defaut de trouver une meilleure solution
                for(int p=x;((p<15) && (sortie_de_boucle == false) && (p<x+4));p++)
                {
                    for(int q=y;((q<15) && (sortie_de_boucle == false) && (q<y+4));q++)
                    {
                        if((plateau.grid[p][q] != 5)&&(plateau.grid[p][q] != 0)) //Si la grille anonyme revele une presence
                        {
                            //On marque les nouveles coordonnees
                            new_i = p; 
                            new_j = q;
                            old_i = p;
                            old_j = q;
                            sortie_de_boucle = true;
                            System.out.println("Des canons s'orientent vers vous..");
                        }
                    }
                }
            }
            
            /*//Cas mouvement bateau
            if(r == 3)
            {
                moveIA(); //IL FAUT FINIR LA METHODE MOVE
            }*/
        }
        
        //Si on a coule un navire, on reset les coordonnees de tir
        if(sinked)
        {
            old_i = -1;
            old_j = -1;
            new_i = -1;
            new_j = -1;
            sinked = false;
            System.out.println("L'ordinateur ayant coule un de vos batiments va reprendre ses recherches !");
        }
        
        //Cas ou l'ordinateur a trouve un bateau
        if(((new_i != -1)&&(new_j != -1))&&(sinked == false)&&(!antiDoubleAction))
        {
            //On a les coordonnees i,j d'un point d'un bateau
            //Trois cas sont possibles
            
            //CAS 1
            //Cas de la presence d'une epave (si l'ordinateur a tire au hasard et touche un navire) ou a tire suite a une fusee eclairante
            if((plateau.grid[new_i][new_j] == 5)&&((new_i == old_i)&&(new_j == old_j)))
            {
                boolean hasFired1 = false;
                System.out.println("L'ordinateur va tirer autour d'une epave !");
                //Il va tirer a gauche
                if(new_j>0)
                {
                    //Si il y a un sous-marin
                    if(plateau.grid[new_i][new_j-1] == 1)
                    {
                        new_j = new_j-1;
                        sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                        hasFired1 = true;
                    }
                    else if((plateau.grid[new_i][new_j-1] == 2)||(plateau.grid[new_i][new_j-1] == 3)||(plateau.grid[new_i][new_j-1] == 4))
                    {
                        new_j = new_j-1;
                        sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                        hasFired1 = true;
                    }
                }
                //Sinon au dessus
                if((new_i>0)&&(!hasFired1))
                {
                    //Si il y a un sous-marin
                    if(plateau.grid[new_i-1][new_j] == 1)
                    {
                        new_i = new_i-1;
                        sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                        hasFired1 = true;
                    }
                    else if((plateau.grid[new_i-1][new_j] == 2)||(plateau.grid[new_i-1][new_j] == 3)||(plateau.grid[new_i-1][new_j] == 4))
                    {
                        new_i = new_i-1;
                        sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                        hasFired1 = true;
                    }
                }
                //Sinon a droite
                if((new_j<14)&&(!hasFired1))
                {
                    //Si il y a un sous-marin
                    if(plateau.grid[new_i][new_j+1] == 1)
                    {
                        new_j = new_j+1;
                        sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                        hasFired1 = true;
                    }
                    else if((plateau.grid[new_i][new_j+1] == 2)||(plateau.grid[new_i][new_j+1] == 3)||(plateau.grid[new_i][new_j+1] == 4))
                    {
                        new_j = new_j+1;
                        sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                        hasFired1 = true;
                    }
                }
                //Sinon en dessous
                if((new_i<14)&&(!hasFired1))
                {
                    //Si il y a un sous-marin
                    if(plateau.grid[new_i+1][new_j] == 1)
                    {
                        new_i = new_i+1;
                        sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                        hasFired1 = true;
                    }
                    else if((plateau.grid[new_i+1][new_j] == 2)||(plateau.grid[new_i+1][new_j] == 3)||(plateau.grid[new_i+1][new_j] == 4))
                    {
                        new_i = new_i+1;
                        sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                        hasFired1 = true;
                    }
                }
            }
            //CAS 2
            //Cas de la presence d'un navire (a la fusee eclairante)
            else if((plateau.grid[new_i][new_j] == 1)||(plateau.grid[new_i][new_j] == 2)||(plateau.grid[new_i][new_j] == 3)||(plateau.grid[new_i][new_j] == 4))
            {
                System.out.println("L'ordinateur va tirer sur une cible reperee a la fusee eclairante");
                //On va tirer tout d'abord au sous marin pour etre sur de toucher, puis eventuellement plus tard, on tirera au plus gros calibres
                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
            }
            
            //CAS 3
            //CAS DE RECHERCHE DE DESTRUCTION COMPLETE DU NAVIRE
            else
            {
                //Ce cas est execute si old_i et old_j sont differents respectivement de new_i et new_j et qu'il y a presence d'une epave en new_i new_j
                System.out.println("Chef ! L'ennemi cherche a nous couler !!!");
                
                boolean hasFired = false;
                //Cas ou on a tire a gauche au prealable d'une epave
                if(new_j == old_j-1)
                {
                    //On va parcourir vers la gauche jusqu'a trouve une cible, tomber sur la mer ou arriver au bord du plateau (3 cas d'arrets)
                    for(int j=new_j;(j>0)&&(plateau.grid[new_i][j] != 0)&&(!hasFired);j--)
                    {
                        //Si on trouve une cible
                        if((plateau.grid[new_i][j-1] != 0)||(plateau.grid[new_i][j-1] != 5))
                        {
                            //Si il y a un sous-marin
                            if(plateau.grid[new_i][j-1] == 1)
                            {
                                new_j = j-1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, j-1, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                            else if((plateau.grid[new_i][j-1] == 2)||(plateau.grid[new_i][j-1] == 3)||(plateau.grid[new_i][j-1] == 4))
                            {
                                new_j = j-1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, j-1, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                        }
                    }
                    
                    //Si on arrive ici sans avoir tire, c'est qu'on est tombe soit sur la mer, soit sur le bord de la carte. On va donc partir dans l'autre sens
                    for(int j=old_j;(j<14)&&(plateau.grid[new_i][j] != 0)&&(!hasFired);j++)
                    {
                        //Si on trouve une cible
                        if((plateau.grid[new_i][j+1] != 0)||(plateau.grid[new_i][j+1] != 5))
                        {
                            //Si il y a un sous-marin
                            if(plateau.grid[new_i][j+1] == 1)
                            {
                                new_j = j+1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, j+1, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                            else if((plateau.grid[new_i][j+1] == 2)||(plateau.grid[new_i][j+1] == 3)||(plateau.grid[new_i][j+1] == 4))
                            {
                                new_j = j+1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, j+1, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                        }
                    }
                }
               
                //Cas ou on a tire a droite au prealable d'une epave
                if(new_j == old_j + 1)
                {
                    //On va parcourir vers la droite jusqu'a trouve une cible, tomber sur la mer ou arriver au bord du plateau (3 cas d'arrets)
                    for(int j=new_j;(j<14)&&(plateau.grid[new_i][j] != 0)&&(!hasFired);j++)
                    {
                        //Si on trouve une cible
                        if((plateau.grid[new_i][j+1] != 0)||(plateau.grid[new_i][j+1] != 5))
                        {
                            //Si il y a un sous-marin
                            if(plateau.grid[new_i][j+1] == 1)
                            {
                                new_j = j+1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, j+1, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                            else if((plateau.grid[new_i][j+1] == 2)||(plateau.grid[new_i][j+1] == 3)||(plateau.grid[new_i][j+1] == 4))
                            {
                                new_j = j+1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, j+1, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                        }
                    }
                    
                    //Si on arrive ici sans avoir tire, c'est qu'on est tombe soit sur la mer, soit sur le bord de la carte. On va donc partir dans l'autre sens
                    for(int j=old_j;(j>0)&&(plateau.grid[new_i][j] != 0)&&(!hasFired);j--)
                    {
                        //Si on trouve une cible
                        if((plateau.grid[new_i][j-1] != 0)||(plateau.grid[new_i][j-1] != 5))
                        {
                            //Si il y a un sous-marin
                            if(plateau.grid[new_i][j-1] == 1)
                            {
                                new_j = j-1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, j-1, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                            else if((plateau.grid[new_i][j-1] == 2)||(plateau.grid[new_i][j-1] == 3)||(plateau.grid[new_i][j-1] == 4))
                            {
                                new_j = j-1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, j-1, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                        }
                    }
                }
                //Cas ou on a tire au dessus au prealable d'une epave
                if(new_i == old_i - 1)
                {
                    //On va parcourir vers le haut jusqu'a trouve une cible, tomber sur la mer ou arriver au bord du plateau (3 cas d'arrets)
                    for(int i=new_i;(i>0)&&(plateau.grid[i][new_j] != 0)&&(!hasFired);i--)
                    {
                        //Si on trouve une cible
                        if((plateau.grid[i-1][new_j] != 0)||(plateau.grid[i-1][new_j] != 5))
                        {
                            //Si il y a un sous-marin
                            if(plateau.grid[i-1][new_j] == 1)
                            {
                                new_i = i-1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, i-1, new_j, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                            else if((plateau.grid[i-1][new_j] == 2)||(plateau.grid[i-1][new_j] == 3)||(plateau.grid[i-1][new_j] == 4))
                            {
                                new_i = i-1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, i-1, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                        }
                    }
                    
                    //Si on arrive ici sans avoir tire, c'est qu'on est tombe soit sur la mer, soit sur le bord de la carte. On va donc partir dans l'autre sens
                    for(int i=old_i;(i<14)&&(plateau.grid[i][new_j] != 0)&&(!hasFired);i++)
                    {
                        //Si on trouve une cible
                        if((plateau.grid[i+1][new_j] != 0)||(plateau.grid[i+1][new_j] != 5))
                        {
                            //Si il y a un sous-marin
                            if(plateau.grid[i+1][new_j] == 1)
                            {
                                new_i = i+1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, i+1, new_j, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                            else if((plateau.grid[i+1][new_j] == 2)||(plateau.grid[i+1][new_j] == 3)||(plateau.grid[i+1][new_j] == 4))
                            {
                                new_i = i+1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, i+1, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                        }
                    }
                }
                //Cas ou on a tire au dessous au prealable d'une epave
                if(new_i == old_i + 1)
                {
                    //On va parcourir vers le bas jusqu'a trouve une cible, tomber sur la mer ou arriver au bord du plateau (3 cas d'arrets)
                    for(int i=new_i;(i<14)&&(plateau.grid[i][new_j] != 0)&&(!hasFired);i++)
                    {
                        //Si on trouve une cible
                        if((plateau.grid[i+1][new_j] != 0)||(plateau.grid[i+1][new_j] != 5))
                        {
                            //Si il y a un sous-marin
                            if(plateau.grid[i+1][new_j] == 1)
                            {
                                new_i = i+1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, i+1, new_j, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                            else if((plateau.grid[i+1][new_j] == 2)||(plateau.grid[i+1][new_j] == 3)||(plateau.grid[i+1][new_j] == 4))
                            {
                                new_i = i+1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, i+1, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                        }
                    }
                    
                    //Si on arrive ici sans avoir tire, c'est qu'on est tombe soit sur la mer, soit sur le bord de la carte. On va donc partir dans l'autre sens
                    for(int i=old_i;(i>0)&&(plateau.grid[i][new_j] != 0)&&(!hasFired);i--)
                    {
                        //Si on trouve une cible
                        if((plateau.grid[i-1][new_j] != 0)||(plateau.grid[i-1][new_j] != 5))
                        {
                            //Si il y a un sous-marin
                            if(plateau.grid[i-1][new_j] == 1)
                            {
                                new_i = i-1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, i-1, new_j, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                            else if((plateau.grid[i-1][new_j] == 2)||(plateau.grid[i-1][new_j] == 3)||(plateau.grid[i-1][new_j] == 4))
                            {
                                new_i = i-1;
                                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, i-1, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                                hasFired = true;
                            }
                        }
                    }
                }
            }
            
        }
    }
    
    //Methode de tir de l'IA, necessitant les infos de tous les objets Ship ainsi que les coordonnees de tir
    public boolean fireIA(Plateau plateau, Ship dreadnought, Ship cruiser1, Ship cruiser2, Ship destroyer1, Ship destroyer2, Ship destroyer3, Ship submarine1, Ship submarine2, Ship submarine3, Ship submarine4,int x, int y, boolean submarineShot, Ship dreadnought11, Ship cruiser11, Ship cruiser22, Ship destroyer11, Ship destroyer22, Ship destroyer33, Ship submarine11, Ship submarine22, Ship submarine33, Ship submarine44)
    {
        char f;
        f = (char)(x + 'a');
        System.out.println("Tir de l'ordinateur en " + f + ", " + y + " chef !");
        
        boolean sinked = false;
        //On a deux cas, celui ou on ordonne un tir au sous-marin, et le tir regulier
        //Cas du tir au sous-marin
        if(submarineShot)
        {
            if(evalCase(plateau,x,y) == 1)
            {
                fixerCase(plateau,x,y,5);
                sinked = true;
                System.out.println("L'ordinateur tire au sous-marin ! ");
            }
            else if((evalCase(plateau,x,y) != 0)&&(evalCase(plateau,x,y) != 5))
            {
                fixerCase(plateau,x,y,5);
                System.out.println("L'ordinateur tire au sous-marin ! ");
            }
        }
        //Cas general, ou on tire au plus gros calibre
        else
        {
            if(dreadnought11.isAlive)
            {
                //On va dessiner un "+"
                if((plateau.grid[x][y] != 1)&&(plateau.grid[x][y] != 0))
                {
                    fixerCase(plateau, x, y, 5);	//Case centrale
                    System.out.println("L'ordinateur tire au cuirasse ! ");
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
            else if(cruiser11.isAlive)
            {
                //On va dessiner un "+" sans la barre du bas
                if((plateau.grid[x][y] != 1)&&(plateau.grid[x][y] != 0))
                {
                    fixerCase(plateau, x, y, 5);	//Case centrale
                    System.out.println("L'ordinateur tire au croiseur ! ");
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
            else if(cruiser22.isAlive)
            {
                //On va dessiner un "+" sans la barre du bas
                if((plateau.grid[x][y] != 1)&&(plateau.grid[x][y] != 0))
                {
                    fixerCase(plateau, x, y, 5);	//Case centrale
                    System.out.println("L'ordinateur tire au croiseur ! ");
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
            else if(destroyer11.isAlive)
            {
                if((plateau.grid[x][y] != 1)&&(plateau.grid[x][y] != 0))
                {
                    fixerCase(plateau, x, y, 5);
                    System.out.println("L'ordinateur tire au destroyer ! ");
                }
            }
            else if(destroyer22.isAlive)
            {
                if((plateau.grid[x][y] != 1)&&(plateau.grid[x][y] != 0))
                {
                    fixerCase(plateau, x, y, 5);
                    System.out.println("L'ordinateur tire au destroyer ! ");
                }
            }
            else if(destroyer33.isAlive)
            {
                if((plateau.grid[x][y] != 1)&&(plateau.grid[x][y] != 0))
                {
                    fixerCase(plateau, x, y, 5);
                    System.out.println("L'ordinateur tire au destroyer ! ");
                }
            }
            else if(submarine11.isAlive)
            {
                if(plateau.grid[x][y] != 0)
                {
                    fixerCase(plateau, x, y, 5);
                    System.out.println("L'ordinateur tire au sous-marin ! ");
                }
            }
            else if(submarine22.isAlive)
            {
                if(plateau.grid[x][y] != 0)
                {
                    fixerCase(plateau, x, y, 5);
                    System.out.println("L'ordinateur tire au sous-marin ! ");
                }
            }
            else if(submarine33.isAlive)
            {
                if(plateau.grid[x][y] != 0)
                {
                    fixerCase(plateau, x, y, 5);
                    System.out.println("L'ordinateur tire au sous-marin ! ");
                }
            }
            else if(submarine44.isAlive)
            {
                if(plateau.grid[x][y] != 0)
                {
                    fixerCase(plateau, x, y, 5);
                    System.out.println("L'ordinateur tire au sous-marin ! ");
                }
            }
        }
        
        //On actualise alors l'etat de tous les bateaux. Pour ce faire, on parcourt les coordonnees de chaque case des bateaux. 
        //Si celles-ci sont toutes a 5, on passe isAlive a false
        
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (cuirasse)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (cuirasse)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (croiseur)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (croiseur)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (croiseur)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (croiseur)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (destroyer)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (destroyer)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (destroyer)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (destroyer)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (destroyer)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (destroyer)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (sous-marin)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (sous-marin)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (sous-marin)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (sous-marin)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (sous-marin)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (sous-marin)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (sous-marin)");
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
            if(!alive)
            {
                sinked = true;
                System.out.println("Un de nos batiments a coule.. (sous-marin)");
            }
        }
        return sinked;
    }
    
    //Methode qui va modifier les champs fusee eclairante
    public void fusee_eclairante_IA(Plateau plateau, int x, int y, Ship destroyer11, Ship destroyer22, Ship destroyer33)
    {
        if(destroyer11.mNbFuseeEclairante == 1)
        {
            destroyer11.mNbFuseeEclairante = 0; 
        }
        else if(destroyer22.mNbFuseeEclairante == 1)
        {
            destroyer22.mNbFuseeEclairante = 0;
        }
        else if(destroyer33.mNbFuseeEclairante == 1)
        {
            destroyer33.mNbFuseeEclairante = 0;
        }
    }
}