package Modele;

public class Destroyer extends Ship
{
    public Destroyer(int size, int firePower, String typeShip) 
    {// on instancie la longueur et la puissance du bateau en question
        super(typeShip);
        mSize=3;
        mfirePower=1;
        this.mTypeShip="destroyer";
    }
}