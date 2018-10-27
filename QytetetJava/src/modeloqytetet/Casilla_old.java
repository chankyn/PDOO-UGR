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
/*
public class Casilla_old {
    private int numeroCasilla;
    private int coste;
    private int numHoteles=0;
    private int numCasas=0;
    private TipoCasilla tipo;
    private TituloPropiedad titulo;
    
    public Casilla_old(int numeroCasilla,int coste,TituloPropiedad titulo){
        this.numeroCasilla=numeroCasilla;
        this.coste=coste;
        this.tipo=TipoCasilla.CALLE;
        this.titulo=titulo;
        titulo.setCasilla(this);
    }
    public Casilla_old(int numeroCasilla, TipoCasilla tipo){
        this.numeroCasilla=numeroCasilla;
        this.coste=0;
        this.tipo=tipo;
        this.titulo=null; 
    }

    public int getNumeroCasilla() {
        return numeroCasilla;
    }

    int getCoste() {
        return coste;
    }

    int getNumHoteles() {
        return numHoteles;
    }

    int getNumCasas() {
        return numCasas;
    }

    public TipoCasilla getTipo() {
        return tipo;
    }

    public TituloPropiedad getTituloPropiedad() {
        return titulo;
    }


    void setNumHoteles(int numHoteles) {
        this.numHoteles = numHoteles;
    }

    void setNumCasas(int numCasas) {
        this.numCasas = numCasas;
    }
    void setTituloPropiedad(TituloPropiedad titulo){
        this.titulo=titulo;
    }

    @Override
    public String toString() {
        if (titulo!=null) 
            return "Casilla{" + "numeroCasilla=" + Integer.toString(numeroCasilla) + ", coste=" + Integer.toString(coste) + ", "
                + "numHoteles=" + Integer.toString(numHoteles) + ", numCasas=" + Integer.toString(numCasas) + ", tipo=" + tipo.toString()
                + ", titulo=" + titulo.toString() + '}';
        else
            return "Casilla{" + "numeroCasilla=" + Integer.toString(numeroCasilla) + ", coste=" + Integer.toString(coste) + ", "
                + "numHoteles=" + Integer.toString(numHoteles) + ", numCasas=" + Integer.toString(numCasas) + ", tipo=" + tipo.toString() + '}';
    }
    
    TituloPropiedad asignarPropietario(Jugador jugador){
        titulo.setPropietario(jugador);
        return titulo;
    }
    int calcularValorHipoteca(){
        return (int) (this.titulo.getHipotecaBase() + this.getNumCasas() * 0.5 * this.titulo.getHipotecaBase() + this.getNumHoteles() * this.titulo.getHipotecaBase());
    }
    public boolean estaHipotecada(){
        return titulo.getHipotecada();
    }
    boolean tengoPropietario(){
        return titulo.tengoPropietario();
    }
    boolean soyEdificable(){
        return this.tipo == TipoCasilla.CALLE;
    }
    //int cancelarHipoteca(){}
    int cobrarAlquiler(){
        int costeAlquilerBase = titulo.getAlquilerBase();
        int costeAlquiler = costeAlquilerBase + (int)(numCasas*0.5 + numHoteles*2);
        titulo.cobrarAlquier(costeAlquiler);
        
        return costeAlquiler;
    }
    int edificarCasa(){
        numCasas = numCasas+1;
        int costeEdificar = titulo.getPrecioEdificar();
        return costeEdificar;
    }
    int edificarHotel(){
        numHoteles = numHoteles+1;
        int costeEdificar = titulo.getPrecioEdificar();
        return costeEdificar;
    }
    //int getCosteHipoteca(){}
    //int getPrecioEdificar(){}
    int hipotecar(){
        titulo.setHipotecada(true);
        int cantidadRecibida = calcularValorHipoteca();
        return cantidadRecibida;
    }
    //int precioTotalComprar(){}
    boolean propietarioEncarcelado(){
        return titulo.propietarioEncarcelado();
    }
    boolean sePuedeEdificarCasa(int factorEspeculador){
       return numCasas<4*factorEspeculador;
    }
    boolean sePuedeEdificarHotel(int factorEspeculador){
        return numHoteles<4*factorEspeculador;
    }
    //boolean soyEdificable(){}
    int venderTitulo(){
        int precioCompra = coste + (numCasas + numHoteles) * titulo.getPrecioEdificar();
        float precioVenta = precioCompra + titulo.getFactorRevalorizacion() * precioCompra;
        titulo.setPropietario(null);
        numCasas = 0;
        numHoteles = 0;
        return (int) precioVenta;
    }
    //void asignarTituloPropiedad(){}

    
}
*/