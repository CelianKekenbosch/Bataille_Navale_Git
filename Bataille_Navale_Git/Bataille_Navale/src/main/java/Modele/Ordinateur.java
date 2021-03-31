package Modele;

public class Ordinateur
{
    /*private String name;
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
    }*/

    //Methode qui va faire jouer l'ordinateur, il faudra au prealable set un i et un j a -1 qui seront les coordonnees par defaut si l'IA n'a pas touche de bateaux
    //IL FAUT FOURNIR LE PLATEAU ADVERSE ANONYME (dont la grid est soit 0 pour rien, soit 1 pour quelque chose, soit 2 pour une epave
    /*public int iaTurn(Plateau plateau)
    {
        //On cherche a nouveau un bateau, on reset sinked
        sinked = false;
        //Cas ou l'ordinateur n'a pas trouve de bateaux
        if((new_i == -1) && (new_j == -1))
        {
            //On va generer un entier egal a 0,1,2 ou 3. Si il est egal a 1 ou 2, l'ordinateur tentera de tirer une fusee eclairante, si c'est 3, il bougera un bateau
            int r;
            do{
            r = 0 + (int)(Math.random() * ((3-0)+1));
            }while((fusee == 3)&&((r == 1)||(r == 2))); //Si on a plus de fusee, on recommence

            //On va generer deux entier aleatoire representant les coordonnees d'un tir (entre 0 et 14)
            int x,y;
            
            //On genere x et y a nouveau si la case est une epave
            do{
            x = 0 + (int)(Math.random() * ((14-0)+1));
            y = 0 + (int)(Math.random() * ((14-0)+1));
            }while(plateau.grid[x][y] == 2);
            
            //Cas du tir
            if(r == 0)
            {
                fireIA(plateau,x,y,"submarine"); //IL FAUT CREER LA METHODE FIREIA 
                //On check la grille adverse pour savoir si on a touche qq chose
                boolean sortie_de_boucle = false; //A defaut de trouver une meilleure solution
                for(int p=0;((p<15) && (sortie_de_boucle == false));p++)
                {
                    for(int q=0;((q<15) && (sortie_de_boucle == false));q++)
                    {
                        if(plateau.grid[p][q] == 2) //Si la grille anonyme revele une epave
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
                fusee_eclairante_IA(plateau,x,y); //IL FAUT CREER LA METHODE FUSEE ECLAIRANTE IA
                //Si la fusee eclairante touche un bateau adverse, il marque les coordonnees de la case en haut a gauche
                boolean sortie_de_boucle = false; //A defaut de trouver une meilleure solution
                for(int p=0;((p<15) && (sortie_de_boucle == false));p++)
                {
                    for(int q=0;((q<15) && (sortie_de_boucle == false));q++)
                    {
                        if(plateau.grid[p][q] == 1) //Si la grille anonyme revele une presence
                        {
                            //On marque les nouveles coordonnees
                            new_i = p; 
                            new_j = q;
                            sortie_de_boucle = true;
                        }
                    }
                }
            }
            
            //Cas mouvement bateau
            if(r == 3)
            {
                moveIA(); //IL FAUT FINIR LA METHODE MOVE
            }
        }
        
        //Cas ou l'ordinateur a trouve un bateau
        if(((new_i != -1)&&(new_j != -1))&&(sinked == false))
        {
            //On a les coordonnees i,j d'un point d'un bateau
            //Deux cas sont possibles
            
            //Cas de la presence d'une epave (si l'ordinateur a tire au hasard et touche un navire
            if(plateau.grid[new_i][new_j] == 2)
            {
                //On va tirer a gauche de ce point afin de trouver la suite du bateau
                //On set-up les coordonnees actuelles du point afin d'y revenir si on ne touche pas
                old_i = new_i;
                old_j = new_j;
                new_j = new_j - 1;
                fireIA(plateau, new_i, new_j, "Dreadnought");
                
                
            }
            //Cas de la presence d'un navire (a la fusee eclairante)
            else if(plateau.grid[new_i][new_j] == 1)
            {
                
            }
            
            //Sinon, c'est qu'on a deja lock un bateau depuis un moment et on continue donc de l'attaquer
            else
            {
                
            }
            
        }
    }*/
}
