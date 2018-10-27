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
public class Calle extends Casilla{
    TituloPropiedad titulo;
    int numCasas = 0;
    int numHoteles = 0;
    public Calle(int numeroCasilla,int coste,TituloPropiedad titulo){
        this.numeroCasilla=numeroCasilla;
        this.coste=coste;
        this.tipo=TipoCasilla.CALLE;
        this.titulo=titulo;
        titulo.setCasilla(this);
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
    public int getNumHoteles() {
        return numHoteles;
    }
    public int getNumCasas() {
        return numCasas;
    }
    @Override
    public  TipoCasilla getTipo() {
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
    public String toString(){
        String calle;
        calle = "Número de casilla: "+numeroCasilla+"\n";
        calle += "Coste de alquiler: "+coste+"\n";
        calle += "Datos sobre la propiedad: \n"+titulo.toString()+"\n";
        calle += "Número de casas edificadas :"+numCasas+"\n";
        calle += "Número de numHoteles edificados :"+numHoteles+"\n";
        return calle;
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
    
    @Override
    boolean soyEdificable(){
        return this.tipo == TipoCasilla.CALLE;
    }
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
    int hipotecar(){
        titulo.setHipotecada(true);
        int cantidadRecibida = calcularValorHipoteca();
        return cantidadRecibida;
    }
    boolean propietarioEncarcelado(){
        return titulo.propietarioEncarcelado();
    }
    boolean sePuedeEdificarCasa(int factorEspeculador){
       return numCasas<4*factorEspeculador;
    }
    boolean sePuedeEdificarHotel(int factorEspeculador){
        return numHoteles<4*factorEspeculador;
    }
    int venderTitulo(){
        int precioCompra = coste + (numCasas + numHoteles) * titulo.getPrecioEdificar();
        float precioVenta = precioCompra + titulo.getFactorRevalorizacion() * precioCompra;
        titulo.setPropietario(null);
        numCasas = 0;
        numHoteles = 0;
        return (int) precioVenta;
    }
    
}
