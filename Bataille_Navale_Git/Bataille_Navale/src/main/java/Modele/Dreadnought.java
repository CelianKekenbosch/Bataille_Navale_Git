/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

public class Dreadnought extends Ship{
    public Dreadnought(int size, int firePower, String typeShip) {// on instancie la longueur et la puissance du bateau en question
        super(size, firePower, typeShip);
        this.mSize=7;
        this.mfirePower=9;
        this.mTypeShip="dreadnought";
    }
    
}
