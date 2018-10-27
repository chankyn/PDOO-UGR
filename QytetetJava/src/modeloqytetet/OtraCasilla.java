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
public class OtraCasilla extends Casilla{
    public OtraCasilla(int numeroCasilla,int coste, TipoCasilla tipo){
        this.numeroCasilla=numeroCasilla;
        this.coste=coste;
        this.tipo=tipo;
    }
    public OtraCasilla(int numeroCasilla, TipoCasilla tipo){
        this.numeroCasilla=numeroCasilla;
        this.coste=0;
        this.tipo=tipo;
    }
    //Métodos Get/Set
    @Override
    public int getNumeroCasilla() {
        return numeroCasilla;
    }
    @Override
    int getCoste() {
        return coste;
    }
    @Override
    public TipoCasilla getTipo() {
        return tipo;
    }
    
    @Override
    public String toString(){
        String casilla;
        casilla = "Número de casilla: "+numeroCasilla+"\n";
        casilla += "Tipo de casilla: "+tipo.toString()+"\n";
        return casilla;
    }
    
    @Override
    boolean soyEdificable(){
        return this.tipo == TipoCasilla.CALLE;
    }
}
