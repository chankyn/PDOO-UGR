/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author Migue
 */
public abstract class Casilla {
    protected int numeroCasilla;
    protected int coste;
    protected TipoCasilla tipo;
    
    //Métodos compartidos Get/Set
    public abstract int getNumeroCasilla();
    abstract int getCoste();
    public abstract TipoCasilla getTipo();
    
    //Método compartido.
    abstract boolean soyEdificable();
}
