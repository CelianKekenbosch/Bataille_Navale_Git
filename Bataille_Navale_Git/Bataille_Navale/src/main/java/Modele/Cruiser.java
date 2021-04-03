package Modele;

public class Cruiser extends Ship
{
    public Cruiser(int size, int firePower, String typeShip) 
    {// on instancie la longueur et la puissance du bateau en question
        super(typeShip);
        mSize=5;
        mfirePower=4;
        this.mTypeShip="cruiser";
    }
}