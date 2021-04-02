/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

public class Cruiser extends Ship{
    public Cruiser(int size, int firePower, String typeShip) {// on instancie la longueur et la puissance du bateau en question
        super(size, firePower, typeShip);
        this.mSize=5;
        this.mfirePower=4;
        this.mTypeShip="cruiser";
    }
    
}
