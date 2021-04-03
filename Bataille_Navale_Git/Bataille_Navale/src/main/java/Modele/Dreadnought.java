package Modele;

public class Dreadnought extends Ship
{
    public Dreadnought(int size, int firePower, String typeShip) 
    {// on instancie la longueur et la puissance du bateau en question
        super(typeShip);
        mSize=7;
        mfirePower=9;
        this.mTypeShip="dreadnought";
    }
}