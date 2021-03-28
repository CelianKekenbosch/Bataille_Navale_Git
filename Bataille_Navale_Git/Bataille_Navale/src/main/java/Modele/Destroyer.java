/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

public class Destroyer extends Ship{
    
    public Destroyer(int size, int firePower, String typeShip) {// on instancie la longueur et la puissance du bateau en question
        super(size, firePower, typeShip);
        this.mSize=3;
        this.mfirePower=1;
        this.mTypeShip="destroyer";
    }
    
}