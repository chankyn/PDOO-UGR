/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;

/**
 *
 * @author Migue
 */
public class Jugador {
    //ATRIBUTOS
    private boolean encarcelado;
    private String nombre;
    private int saldo;
    private Sorpresa cartaLibertad;
    private Casilla casillaActual;
    private ArrayList<TituloPropiedad> propiedades;
    static int FactorEspeculador = 1;
    
    //MÉTODOS
    Jugador(String nombre,Casilla casillaActual){
        this.encarcelado = false;
        this.nombre = nombre;
        this.saldo = 7500;
        this.cartaLibertad = null;
        this.casillaActual = casillaActual;
        this.propiedades = new ArrayList();
    }
    //Constructor de copia.
    protected Jugador(Jugador otro){
        this.encarcelado = otro.encarcelado;
        this.nombre = otro.nombre;
        this.saldo = otro.saldo;
        this.cartaLibertad = otro.cartaLibertad;
        this.casillaActual = otro.casillaActual;
        this.propiedades = otro.propiedades;
    }
    public void prueba(){
        encarcelado=true;
    }
    public Sorpresa getCartaLibertad(){
        return cartaLibertad;
    }
    public String getNombre(){
        return nombre;
    }
    public Casilla getCasillaActual(){
        return casillaActual;
    }
    public ArrayList<Casilla> obtenerPropiedades(){
        ArrayList<Casilla> casillaPropiedades;
        casillaPropiedades = new ArrayList();
        for(TituloPropiedad titulo: propiedades)
            casillaPropiedades.add(titulo.getCasilla());
        return casillaPropiedades;
    }
    public boolean getEncarcelado(){
        return encarcelado;
    }
    public boolean tengoPropiedades(){
        return !propiedades.isEmpty();
    }
    protected boolean actualizarPosicion(Casilla casilla){
        boolean tengoPropietario=false;
        if (casilla.getNumeroCasilla() < casillaActual.getNumeroCasilla())
            this.modificarSaldo(Qytetet.SALDO_SALIDA);
        this.setCasillaActual(casilla);
        if (casilla.soyEdificable()){
            tengoPropietario = ((Calle) casilla).tengoPropietario();
            if(tengoPropietario){
                encarcelado = ((Calle) casilla).propietarioEncarcelado();
                if(!encarcelado)
                    if(!((Calle) casilla).estaHipotecada())
                        modificarSaldo(-((Calle) casilla).cobrarAlquiler());
            }
            if (casilla.getTipo() == TipoCasilla.IMPUESTO){
                int coste = casilla.getCoste();
                modificarSaldo(-coste);
            }   
        }
        return tengoPropietario;
    }
    boolean comprarTitulo(){
        boolean puedoComprar = false, tengoPropietario;
        int costeCompra;
        TituloPropiedad titulo;
        if (casillaActual.soyEdificable()){
            tengoPropietario = ((Calle) casillaActual).tengoPropietario();
            if (!tengoPropietario){
                costeCompra = casillaActual.getCoste();
                if (costeCompra <= saldo){
                    titulo = ((Calle) casillaActual).asignarPropietario(this);
                    propiedades.add(titulo);
                    modificarSaldo(-costeCompra);
                    puedoComprar = true;
                }
            }
        }
        return puedoComprar;
    }
    void irACarcel(Casilla casilla){
        this.setCasillaActual(casilla);
        this.setEncarcelado(true);
    }
    
    Sorpresa devolverCartaLibertad(){
        assert(cartaLibertad!=null);
        Sorpresa aux = this.cartaLibertad;
        cartaLibertad=null;
        return aux;
    }
    void setSaldo(int saldo){
        this.saldo = saldo;
    }
    void modificarSaldo(int cantidad){
        saldo+=cantidad;
    }
    public int getSaldo(){
        return saldo;
    }
    int obtenerCapital(){
        int capital = saldo,valorPropiedad;
        for (TituloPropiedad i:propiedades){
            if(!i.getHipotecada()){
                Calle calle = (Calle) i.getCasilla();
                valorPropiedad =  i.getAlquilerBase()+i.getPrecioEdificar()*(calle.getNumCasas()+calle.getNumHoteles());
                capital += valorPropiedad;
            }else
                capital -= i.getHipotecaBase();
        }
        /*
        Devuelve el capital del que dispone el jugador, que es igual a su
saldo más la suma de los valores de todas sus propiedades. El valor de una propiedad es
la suma de su coste más el número de casas y hoteles que haya construidos por el precio
de edificación. Si la propiedad estuviese hipotecada, se le restará el valor de la hipoteca
base.
        */
        return capital;
    }
    @Override
    public String toString() {
        String s;
        s = "Turno del jugador: "+nombre+"\n";
        s += "Tiene un saldo disponible de: "+saldo+"\n";
        s += "Esta en la casilla: "+casillaActual.getNumeroCasilla()+"\n";
        if (!encarcelado)
            s += "Actualmente NO esta encarcelado\n";
        else
            s += "Actualmente SI esta encarcelado\n";
        if (FactorEspeculador>1)
            s += "Es especulador\n";
        return s;
    }
    private int cuantasCasasHotelesTengo(){
        int total=0;
        Calle casilla;
        for (TituloPropiedad i:propiedades){
            casilla = (Calle)i.getCasilla();
            if (casilla!=null)
                total+=casilla.getNumCasas()+casilla.getNumHoteles();
        }
        return total;
    }
    //ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecada){};
    void pagarCobrarPorCasaYHotel(int cantidad){
        int numeroTotal = cuantasCasasHotelesTengo();
        modificarSaldo(numeroTotal*cantidad);
    }
    void setEncarcelado(boolean encarcelado){
        this.encarcelado=encarcelado;
    }
    boolean pagarLibertad(int PrecioLibertad){
        boolean tengoSaldo = tengoSaldo(PrecioLibertad);
        if(tengoSaldo)
            modificarSaldo(PrecioLibertad);
        return tengoSaldo;
    }
    boolean puedoEdificarCasa(Calle casilla){
        boolean esMia = esDeMipropiedad(casilla);
        boolean tengoSaldo = false;
        if (esMia){
            int costeEdificarCasa  = casilla.getTituloPropiedad().getPrecioEdificar();
            tengoSaldo = tengoSaldo(costeEdificarCasa);
        }
        return tengoSaldo;
    }
    //Comprueba que el propietario sea el jugador, que tenga saldo y que no haya 4 edificios ya construidos.
    boolean puedoEdificarHotel(Calle casilla){
         boolean esMia = esDeMipropiedad(casilla);
        boolean tengoSaldo = false;
        if (esMia){
            int costeEdificarHotel  = casilla.getTituloPropiedad().getPrecioEdificar();
            tengoSaldo = tengoSaldo(costeEdificarHotel);
        }
        return tengoSaldo;
    }
    boolean puedoHipotecar(Casilla casilla){
        return esDeMipropiedad(casilla);
    }
    boolean puedoPagarHipoteca(Calle casilla){
        return tengoSaldo(casilla.calcularValorHipoteca());
    }
    void setCartaLibertad(Sorpresa carta){
        cartaLibertad = carta;
    }
    void setCasillaActual(Casilla casilla){
        assert(casilla.getNumeroCasilla()<20);
        this.casillaActual=casilla;
    }
    boolean tengoCartaLibertad(){
        return cartaLibertad!=null;
    }
    private boolean esDeMipropiedad(Casilla casilla){
        boolean encontrado = false;
        for (int i=0;i<this.propiedades.size() && !encontrado;i++){
            if(propiedades.get(i).getCasilla()==casilla)
                encontrado = true;
        }
        return encontrado;
    }
    boolean puedoVenderPropiedad(Calle casilla){
        if(esDeMipropiedad(casilla) && !casilla.estaHipotecada())
            return true;
        return false;
    }
    private void eliminarDeMisPropiedades(Calle casilla){
        if (casilla!=null)
            propiedades.remove(propiedades.indexOf(casilla.getTituloPropiedad()));
    }
    ArrayList<TituloPropiedad> obtenerPropiedadesHipotecadas(boolean hipotecadas){
    ArrayList<TituloPropiedad> resultado = new ArrayList();
    
    for (TituloPropiedad i:propiedades)
        if(((Calle)i.getCasilla()).estaHipotecada() == hipotecadas)
            resultado.add(i);

    return resultado;
    }
    protected boolean tengoSaldo(int cantidad){
        return this.saldo>=cantidad;
    }
    void venderPropiedad(Calle casilla){
        int precioVenta = casilla.venderTitulo();
        modificarSaldo(precioVenta);
        eliminarDeMisPropiedades(casilla);
    }
    protected Especulador convertirme (int fianza){
        Especulador especulador = new Especulador(this,fianza);
        especulador.convertirme(fianza);
        for (TituloPropiedad propiedad: propiedades){
            propiedad.setPropietario(especulador);
        }
        return especulador;
    }
    protected void pagarImpuestos(int cantidad){
        modificarSaldo(-cantidad);
    }
    
    public static int getFactorEspeculador() {
        return FactorEspeculador;
    }

}
