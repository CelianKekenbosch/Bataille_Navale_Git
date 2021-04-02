package Modele;

import static Modele.Plateau.evalCase;
import static Modele.Plateau.fixerCase;

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
    }

    //Methode qui va faire jouer l'ordinateur, il faudra au prealable set un i et un j a -1 qui seront les coordonnees par defaut si l'IA n'a pas touche de bateaux
    //IL FAUT FOURNIR LE PLATEAU ADVERSE
    public void iaTurn(Plateau plateau, Ship dreadnought, Ship cruiser1, Ship cruiser2, Ship destroyer1, Ship destroyer2, Ship destroyer3, Ship submarine1, Ship submarine2, Ship submarine3, Ship submarine4, boolean submarineShot)
    {
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
                sinked = fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, true);
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
        
        //Si on a coulé un navire, on reset les coordonnees de tir
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
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false);
                    }
                    //On va tirer a droite du point (si on peut)
                    else if(new_j < 14)
                    {
                        old_i = new_i;
                        old_j = new_j;
                        new_j = new_j + 1;
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false); 
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
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false);
                    }
                    //On va tirer en dessous du point (si on peut)
                    else if(new_i < 14)
                    {
                        old_i = new_i;
                        old_j = new_j;
                        new_i = new_i + 1;
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false); 
                    }
                }
            }
            //Cas de la presence d'un navire (a la fusee eclairante)
            else if((plateau.grid[new_i][new_j] == 1)||(plateau.grid[new_i][new_j] == 2)||(plateau.grid[new_i][new_j] == 3)||(plateau.grid[new_i][new_j] == 4))
            {
                //On va tirer tout d'abord au sous marin pour etre sur de toucher, puis eventuellement plus tard, on tirera au plus gros calibres
                fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, true);
            }
            
            //Si on a pas touché de bateau au precedent tir, c'est qu'on a mal tirer aleatoirement
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
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false);
                    }
                    //On va tirer a droite du point (si on peut)
                    else if(new_j < 14)
                    {
                        old_i = new_i;
                        old_j = new_j;
                        new_j = new_j + 1;
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false);
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
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false);
                    }
                    //On va tirer en dessous du point (si on peut)
                    else if(new_i < 14)
                    {
                        old_i = new_i;
                        old_j = new_j;
                        new_i = new_i + 1;
                        fireIA(plateau, dreadnought, cruiser1, cruiser2, destroyer1, destroyer2, destroyer3, submarine1, submarine2, submarine3, submarine4, new_i, new_j, false); 
                    }
                }
            }
            
        }
    }
    
    //Methode de tir de l'IA, necessitant les infos de tous les objets Ship ainsi que les coordonnees de tir
    public boolean fireIA(Plateau plateau, Ship dreadnought, Ship cruiser1, Ship cruiser2, Ship destroyer1, Ship destroyer2, Ship destroyer3, Ship submarine1, Ship submarine2, Ship submarine3, Ship submarine4, int x, int y, boolean submarineShot)
    {
        boolean sinked = false;
        //On a deux cas, celui ou on ordonne un tir au sous-marin, et le tir regulier
        //Cas du tir au sous-marin
        if(submarineShot)
        {
            if(evalCase(plateau,x,y) == 1)
            {
                    fixerCase(plateau,x,y,5);
                    sinked = true;
                    return sinked;
            }
            
            if(dreadnought.isAlive)
            {
                    for(int z=0;z<dreadnought.mfirePower;z++)
                    {//pas tout à fait bon pour notre projet puisque cette méthode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15)){
                            fixerCase(plateau,(x+z),(y),5);
                    }if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15)){
                            fixerCase(plateau,(x),(y+z),5);
                    }if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0)){
                            fixerCase(plateau,(x-z),(y),5);
                    }if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0)){
                            fixerCase(plateau,(x),(y-z),5);
                    }
                    }
            }
            else if(cruiser1.isAlive)
            {
                for(int z=0;z<cruiser1.mfirePower;z++)
                    {//pas tout à fait bon pour notre projet puisque cette méthode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15)){
                            fixerCase(plateau,(x+z),(y),5);
                    }if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15)){
                            fixerCase(plateau,(x),(y+z),5);
                    }if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0)){
                            fixerCase(plateau,(x-z),(y),5);
                    }if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0)){
                            fixerCase(plateau,(x),(y-z),5);
                    }
                    }
            }
            else if(cruiser2.isAlive)
            {
                for(int z=0;z<cruiser2.mfirePower;z++)
                    {//pas tout à fait bon pour notre projet puisque cette méthode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15)){
                            fixerCase(plateau,(x+z),(y),5);
                    }if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15)){
                            fixerCase(plateau,(x),(y+z),5);
                    }if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0)){
                            fixerCase(plateau,(x-z),(y),5);
                    }if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0)){
                            fixerCase(plateau,(x),(y-z),5);
                    }
                    }
            }
            else if(destroyer1.isAlive)
            {
                for(int z=0;z<destroyer1.mfirePower;z++)
                    {//pas tout à fait bon pour notre projet puisque cette méthode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15)){
                            fixerCase(plateau,(x+z),(y),5);
                    }if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15)){
                            fixerCase(plateau,(x),(y+z),5);
                    }if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0)){
                            fixerCase(plateau,(x-z),(y),5);
                    }if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0)){
                            fixerCase(plateau,(x),(y-z),5);
                    }
                    }
            }
            else if(destroyer2.isAlive)
            {
                for(int z=0;z<destroyer2.mfirePower;z++)
                    {//pas tout à fait bon pour notre projet puisque cette méthode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15)){
                            fixerCase(plateau,(x+z),(y),5);
                    }if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15)){
                            fixerCase(plateau,(x),(y+z),5);
                    }if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0)){
                            fixerCase(plateau,(x-z),(y),5);
                    }if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0)){
                            fixerCase(plateau,(x),(y-z),5);
                    }
                    }
            }
            else if(destroyer3.isAlive)
            {
                for(int z=0;z<destroyer3.mfirePower;z++)
                    {//pas tout à fait bon pour notre projet puisque cette méthode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15)){
                            fixerCase(plateau,(x+z),(y),5);
                    }if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15)){
                            fixerCase(plateau,(x),(y+z),5);
                    }if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0)){
                            fixerCase(plateau,(x-z),(y),5);
                    }if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0)){
                            fixerCase(plateau,(x),(y-z),5);
                    }
                    }
            }
            else if(submarine1.isAlive)
            {
                for(int z=0;z<submarine1.mfirePower;z++)
                    {//pas tout à fait bon pour notre projet puisque cette méthode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15)){
                            fixerCase(plateau,(x+z),(y),5);
                    }if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15)){
                            fixerCase(plateau,(x),(y+z),5);
                    }if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0)){
                            fixerCase(plateau,(x-z),(y),5);
                    }if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0)){
                            fixerCase(plateau,(x),(y-z),5);
                    }
                    }
            }
            else if(submarine2.isAlive)
            {
                for(int z=0;z<submarine2.mfirePower;z++)
                    {//pas tout à fait bon pour notre projet puisque cette méthode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15)){
                            fixerCase(plateau,(x+z),(y),5);
                    }if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15)){
                            fixerCase(plateau,(x),(y+z),5);
                    }if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0)){
                            fixerCase(plateau,(x-z),(y),5);
                    }if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0)){
                            fixerCase(plateau,(x),(y-z),5);
                    }
                    }
            }
            else if(submarine3.isAlive)
            {
                for(int z=0;z<submarine3.mfirePower;z++)
                    {//pas tout à fait bon pour notre projet puisque cette méthode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15)){
                            fixerCase(plateau,(x+z),(y),5);
                    }if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15)){
                            fixerCase(plateau,(x),(y+z),5);
                    }if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0)){
                            fixerCase(plateau,(x-z),(y),5);
                    }if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0)){
                            fixerCase(plateau,(x),(y-z),5);
                    }
                    }
            }
            else if(submarine4.isAlive)
            {
                for(int z=0;z<submarine4.mfirePower;z++)
                    {//pas tout à fait bon pour notre projet puisque cette méthode va dans les 4 directions
                    if((evalCase(plateau,(x+z),(y))!=0)&&(evalCase(plateau,(x+z),(y))!=1)&&(x+z<15)){
                            fixerCase(plateau,(x+z),(y),5);
                    }if((evalCase(plateau,(x),(y+z))!=0)&&(evalCase(plateau,(x),(y+z))!=1)&&(y+z<15)){
                            fixerCase(plateau,(x),(y+z),5);
                    }if((evalCase(plateau,(x-z),(y))!=0)&&(evalCase(plateau,(x-z),(y))!=1)&&(x-z>=0)){
                            fixerCase(plateau,(x-z),(y),5);
                    }if((evalCase(plateau,(x),(y-z))!=0)&&(evalCase(plateau,(x),(y-z))!=1)&&(y-z>=0)){
                            fixerCase(plateau,(x),(y-z),5);
                    }
                    }
            }
        }
        
        //On actualise alors l'etat de tous les bateaux. Pour ce faire, on parcourt les coordonnees de chaque case des bateaux. 
        //Si celles-ci sont toutes a 5, on passe isAlive a false
        
        
        
        return sinked;
    }  */  
}