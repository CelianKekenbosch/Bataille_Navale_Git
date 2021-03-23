/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

public abstract class Ship {

    protected String typeShip;
    protected int mSize;
    protected int mfirePower;
    protected boolean isVertical;
    protected int[][] coordonneesFront;
    protected int[][] coordonneesRear;
    
    public Ship(int size,int firePower){//constructeur de la classe mère Ship
    this.mSize= size;
    this.mfirePower= firePower;
}
    
    public void fire(int firePower){
        
    }
    
    public void Move(){
        
    }
    
}
