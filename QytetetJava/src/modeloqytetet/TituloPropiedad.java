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
public class TituloPropiedad {
    //ATRIBUTOS
    private String nombre;
    private boolean hipotecada;
    private int alquilerBase;
    private float factorRevalorizacion;
    private int hipotecaBase;
    private int precioEdificar;
    private Jugador propietario;
    private Casilla casilla;
    
    //MÉTODOS
    public TituloPropiedad(String nombre,int alquilerBase,float factorRevalorizacion,int hipotecaBase,int precioEdificar){
        this.nombre = nombre;
        this.alquilerBase = alquilerBase;
        this.factorRevalorizacion = factorRevalorizacion;
        this.hipotecaBase = hipotecaBase;
        this.precioEdificar = precioEdificar;
        this.hipotecada = false;
        this.propietario = null;
        this.casilla = null;
    }

    public String getNombre() {
        return nombre;
    }

    boolean getHipotecada() {
        return hipotecada;
    }

    int getAlquilerBase() {
        return alquilerBase;
    }

    float getFactorRevalorizacion() {
        return factorRevalorizacion;
    }

    int getHipotecaBase() {
        return hipotecaBase;
    }

    int getPrecioEdificar() {
        return precioEdificar;
    }

    
    @Override
    public String toString() {
        String titulo;
        titulo = "Nombre de la propiedad: "+nombre+"\n";
        titulo += "Alquiler base: "+alquilerBase+"\n";
        titulo += "Revalorización: "+factorRevalorizacion+"\n";
        titulo += "Precio por construir: "+precioEdificar+"\n";
        if (hipotecada)
            titulo += "Propiedad hipotecada\n";
        else
            titulo += "Propiedad NO hipotecada\n";
        return titulo;
    }
    
    boolean propietarioEncarcelado(){
        return propietario.getEncarcelado();
    }
    void setCasilla(Casilla casilla){
        this.casilla=casilla;
    }
    
    void setHipotecada(boolean hipotecada) {
        this.hipotecada = hipotecada;
    }
    void setPropietario(Jugador propietario){
        this.propietario=propietario;
    }
    public boolean tengoPropietario(){
        return propietario!=null;
    }
    Casilla getCasilla(){
        return casilla;
    }
    void cobrarAlquier(int coste){
        propietario.modificarSaldo(-coste);
    }
   
}
