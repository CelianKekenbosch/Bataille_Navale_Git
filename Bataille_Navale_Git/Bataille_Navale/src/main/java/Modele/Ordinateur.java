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
        //Cas ou l'ordinateur n'a pas trouve de bateaux
        if((new_i == -1) && (new_j == -1))
        {
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
                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                //On check la grille adverse pour savoir si on a touche qq chose
                boolean sortie_de_boucle = false; //A defaut de trouver une meilleure solution
                for(int p=0;((p<15) && (sortie_de_boucle == false));p++)
                {
                    for(int q=0;((q<15) && (sortie_de_boucle == false));q++)
                    {
                        if(plateau.grid[p][q] == 5) //Si la grille anonyme revele une epave
                        {
                            //On marque les coordonnees de l'impact, qui seront recuperees par la suite
                            new_i = p; 
                            new_j = q;
                            sortie_de_boucle = true;
                        }
                    }
                }
            }
            
            //Cas fusee eclairante
            if((r == 1)||(r == 2))
            {
                fusee_eclairante_IA(plateau, x, y, destroyer11, destroyer22, destroyer33); //IL FAUT CREER LA METHODE FUSEE ECLAIRANTE IA
                //Si la fusee eclairante touche un bateau adverse, il marque les coordonnees de la case en haut a gauche
                fusee = fusee + 1; //On indique qu'on a tire une fusee (compte)
                boolean sortie_de_boucle = false; //A defaut de trouver une meilleure solution
                for(int p=x;((p<15) && (sortie_de_boucle == false) && (p<x+5));p++)
                {
                    for(int q=y;((q<15) && (sortie_de_boucle == false) && (q<y+5));q++)
                    {
                        if((plateau.grid[p][q] != 5)&&(plateau.grid[p][q] != 0)) //Si la grille anonyme revele une presence
                        {
                            //On marque les nouveles coordonnees
                            new_i = p; 
                            new_j = q;
                            sortie_de_boucle = true;
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
        
        //Si on a coulÃ© un navire, on reset les coordonnees de tir
        if(sinked)
        {
            old_i = -1;
            old_j = -1;
            new_i = -1;
            new_j = -1;
            sinked = false;
        }
        
        //Cas ou l'ordinateur a trouve un bateau
        if(((new_i != -1)&&(new_j != -1))&&(sinked == false))
        {
            //On a les coordonnees i,j d'un point d'un bateau
            //Trois cas sont possibles
            
            //Cas de la presence d'une epave (si l'ordinateur a tire au hasard et touche un navire)
            if(plateau.grid[new_i][new_j] == 5)
            {
                //On genere un entier aleatoire qui vaut 0 ou 1
                //Si il vaut 0, on cherche a gauche ou a droite. Si il vaut 1, on cherche au dessus ou en dessous
                int randomHit;
                randomHit = 0 + (int)(Math.random() * ((1-0)+1));
                
                if(randomHit == 0)
                {
                    //On va tirer a gauche de ce point afin de trouver la suite du bateau (si on peut)
                    if(new_j > 0)
                    {
                        //On set-up les coordonnees actuelles du point afin d'y revenir si on ne touche pas
                        old_i = new_i;
                        old_j = new_j;
                        new_j = new_j - 1;
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                    }
                    //On va tirer a droite du point (si on peut)
                    else if(new_j < 14)
                    {
                        old_i = new_i;
                        old_j = new_j;
                        new_j = new_j + 1;
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44); 
                    }
                }
                if(randomHit == 1)
                {
                    //On va tirer au dessus de ce point afin de trouver la suite du bateau (si on peut)
                    if(new_i > 0)
                    {
                        //On set-up les coordonnees actuelles du point afin d'y revenir si on ne touche pas
                        old_i = new_i;
                        old_j = new_j;
                        new_i = new_i - 1;
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                    }
                    //On va tirer en dessous du point (si on peut)
                    else if(new_i < 14)
                    {
                        old_i = new_i;
                        old_j = new_j;
                        new_i = new_i + 1;
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44); 
                    }
                }
            }
            //Cas de la presence d'un navire (a la fusee eclairante)
            else if((plateau.grid[new_i][new_j] == 1)||(plateau.grid[new_i][new_j] == 2)||(plateau.grid[new_i][new_j] == 3)||(plateau.grid[new_i][new_j] == 4))
            {
                //On va tirer tout d'abord au sous marin pour etre sur de toucher, puis eventuellement plus tard, on tirera au plus gros calibres
                fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, true, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
            }
            
            //Si on a pas touche de bateau au precedent tir, c'est qu'on a mal tirer aleatoirement
            else
            {
                new_i = old_i;
                new_j = old_j;
                //Comme on revient sur une epave, on recommence un tir aleatoire
                //On genere un entier aleatoire qui vaut 0 ou 1
                //Si il vaut 0, on cherche a gauche ou a droite. Si il vaut 1, on cherche au dessus ou en dessous
                int randomHit;
                randomHit = 0 + (int)(Math.random() * ((1-0)+1));
                
                if(randomHit == 0)
                {
                    //On va tirer a gauche de ce point afin de trouver la suite du bateau (si on peut)
                    if(new_j > 0)
                    {
                        //On set-up les coordonnees actuelles du point afin d'y revenir si on ne touche pas
                        old_i = new_i;
                        old_j = new_j;
                        new_j = new_j - 1;
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                    }
                    //On va tirer a droite du point (si on peut)
                    else if(new_j < 14)
                    {
                        old_i = new_i;
                        old_j = new_j;
                        new_j = new_j + 1;
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                    }
                }
                if(randomHit == 1)
                {
                    //On va tirer au dessus de ce point afin de trouver la suite du bateau (si on peut)
                    if(new_i > 0)
                    {
                        //On set-up les coordonnees actuelles du point afin d'y revenir si on ne touche pas
                        old_i = new_i;
                        old_j = new_j;
                        new_i = new_i - 1;
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44);
                    }
                    //On va tirer en dessous du point (si on peut)
                    else if(new_i < 14)
                    {
                        old_i = new_i;
                        old_j = new_j;
                        new_i = new_i + 1;
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false, dreadnought11, cruiser11, cruiser22, destroyer11, destroyer22, destroyer33, submarine11, submarine22, submarine33, submarine44); 
                    }
                }
            }
            
        }
    }
    
    //Methode de tir de l'IA, necessitant les infos de tous les objets Ship ainsi que les coordonnees de tir
    public boolean fireIA(Plateau plateau, Ship dreadnought, Ship cruiser1, Ship cruiser2, Ship destroyer1, Ship destroyer2, Ship destroyer3, Ship submarine1, Ship submarine2, Ship submarine3, Ship submarine4,int x, int y, boolean submarineShot, Ship dreadnought11, Ship cruiser11, Ship cruiser22, Ship destroyer11, Ship destroyer22, Ship destroyer33, Ship submarine11, Ship submarine22, Ship submarine33, Ship submarine44)
    {
        boolean sinked = false;
        //On a deux cas, celui ou on ordonne un tir au sous-marin, et le tir regulier
        //Cas du tir au sous-marin
        if(submarineShot)
        {
            if((evalCase(plateau,x,y) == 1)||(evalCase(plateau,x,y) == 2))
            {
                    fixerCase(plateau,x,y,5);
                    sinked = true;
                    return sinked;
            }
        }
        //Cas general, ou on tire au plus gros calibre
        else
        {
            if(dreadnought11.isAlive)
            {
                for(int z=0;z<dreadnought11.mfirePower;z++)
                {   
                    //pas tout a fait bon pour notre projet puisque cette methode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15))
                    {
                            fixerCase(plateau,(x+z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15))
                    {
                            fixerCase(plateau,(x),(y+z),5);
                    }
                    if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0))
                    {
                            fixerCase(plateau,(x-z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0))
                    {
                            fixerCase(plateau,(x),(y-z),5);
                    }
                }
            }
            else if(cruiser11.isAlive)
            {
                for(int z=0;z<cruiser11.mfirePower;z++)
                {   
                    //pas tout a fait bon pour notre projet puisque cette methode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15))
                    {
                            fixerCase(plateau,(x+z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15))
                    {
                            fixerCase(plateau,(x),(y+z),5);
                    }
                    if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0))
                    {
                            fixerCase(plateau,(x-z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0))
                    {
                            fixerCase(plateau,(x),(y-z),5);
                    }
                }
            }
            else if(cruiser22.isAlive)
            {
                for(int z=0;z<cruiser22.mfirePower;z++)
                {   
                    //pas tout a fait bon pour notre projet puisque cette methode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15))
                    {
                            fixerCase(plateau,(x+z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15))
                    {
                            fixerCase(plateau,(x),(y+z),5);
                    }
                    if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0))
                    {
                            fixerCase(plateau,(x-z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0))
                    {
                            fixerCase(plateau,(x),(y-z),5);
                    }
                }
            }
            else if(destroyer11.isAlive)
            {
                for(int z=0;z<destroyer11.mfirePower;z++)
                {
                    //pas tout a fait bon pour notre projet puisque cette methode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15))
                    {
                            fixerCase(plateau,(x+z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15))
                    {
                            fixerCase(plateau,(x),(y+z),5);
                    }
                    if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0))
                    {
                            fixerCase(plateau,(x-z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0))
                    {
                            fixerCase(plateau,(x),(y-z),5);
                    }
                }
            }
            else if(destroyer22.isAlive)
            {
                for(int z=0;z<destroyer22.mfirePower;z++)
                {   
                    //pas tout a fait bon pour notre projet puisque cette methode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15))
                    {
                            fixerCase(plateau,(x+z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15))
                    {
                            fixerCase(plateau,(x),(y+z),5);
                    }
                    if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0))
                    {
                            fixerCase(plateau,(x-z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0))
                    {
                            fixerCase(plateau,(x),(y-z),5);
                    }
                }
            }
            else if(destroyer33.isAlive)
            {
                for(int z=0;z<destroyer33.mfirePower;z++)
                {
                    //pas tout a fait bon pour notre projet puisque cette methode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15))
                    {
                            fixerCase(plateau,(x+z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15))
                    {
                            fixerCase(plateau,(x),(y+z),5);
                    }
                    if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0))
                    {
                            fixerCase(plateau,(x-z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0))
                    {
                            fixerCase(plateau,(x),(y-z),5);
                    }
                }
            }
            else if(submarine11.isAlive)
            {
                for(int z=0;z<submarine11.mfirePower;z++)
                {
                    //pas tout a fait bon pour notre projet puisque cette methode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15))
                    {
                            fixerCase(plateau,(x+z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15))
                    {
                            fixerCase(plateau,(x),(y+z),5);
                    }
                    if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0))
                    {
                            fixerCase(plateau,(x-z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0))
                    {
                            fixerCase(plateau,(x),(y-z),5);
                    }
                }
            }
            else if(submarine22.isAlive)
            {
                for(int z=0;z<submarine22.mfirePower;z++)
                {
                    //pas tout a fait bon pour notre projet puisque cette methode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15))
                    {
                            fixerCase(plateau,(x+z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15))
                    {
                            fixerCase(plateau,(x),(y+z),5);
                    }
                    if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0))
                    {
                            fixerCase(plateau,(x-z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0))
                    {
                            fixerCase(plateau,(x),(y-z),5);
                    }
                }
            }
            else if(submarine33.isAlive)
            {
                for(int z=0;z<submarine33.mfirePower;z++)
                {
                    //pas tout a fait bon pour notre projet puisque cette methode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15))
                    {
                            fixerCase(plateau,(x+z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15))
                    {
                            fixerCase(plateau,(x),(y+z),5);
                    }
                    if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0))
                    {
                            fixerCase(plateau,(x-z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0))
                    {
                            fixerCase(plateau,(x),(y-z),5);
                    }
                }
            }
            else if(submarine44.isAlive)
            {
                for(int z=0;z<submarine44.mfirePower;z++)
                {
                    //pas tout a fait bon pour notre projet puisque cette methode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15))
                    {
                            fixerCase(plateau,(x+z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15))
                    {
                            fixerCase(plateau,(x),(y+z),5);
                    }
                    if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0))
                    {
                            fixerCase(plateau,(x-z),(y),5);
                    }
                    if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0))
                    {
                            fixerCase(plateau,(x),(y-z),5);
                    }
                }
            }
        }
        
        //On actualise alors l'etat de tous les bateaux. Pour ce faire, on parcourt les coordonnees de chaque case des bateaux. 
        //Si celles-ci sont toutes a 5, on passe isAlive a false
        
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
            }
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
            if(!alive)
            {
                sinked = true;
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