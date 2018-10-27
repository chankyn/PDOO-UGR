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
public class Especulador extends Jugador{
    private int fianza;
    static int FactorEspeculador = 2;
    
    public Especulador(Jugador jugador, int fianza) {
        super(jugador);
        this.fianza = fianza;
    }
    
    @Override
    protected Especulador convertirme(int fianza){
        this.fianza = fianza;
        return this;
    }
    @Override
    protected void pagarImpuestos(int cantidad){
        int impuesto = cantidad/2;
        modificarSaldo(-impuesto);
    }
    @Override
    protected void irACarcel(Casilla casilla){
        if (!pagarFianza(fianza)){
            super.irACarcel(casilla);
        }
    }
    private boolean pagarFianza(int cantidad){
        boolean resultado = false;
        if(tengoSaldo(cantidad)){
            modificarSaldo(-cantidad);
            resultado = true;   
        }
        return resultado;
    }
    
    public static int getFactorEspeculador(){
        return FactorEspeculador;
    }
}
