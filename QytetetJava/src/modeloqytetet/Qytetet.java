/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import GUIQytetet.Dado;
/**
 *
 * @author Migue
 */
public class Qytetet {
    //Atributos
    public static int MAX_JUGADORES=4;
    static int MAX_CARTAS = 12; //Ahora son dos mas con la mod de la practica 4.
    static int MAX_CASILLAS = 20;
    static int PRECIO_LIBERTAD = 200;
    static int SALDO_SALIDA = 1000;
    private Sorpresa cartaActual;
    private Tablero tablero;
    private ArrayList<Sorpresa> mazo = new ArrayList(MAX_CARTAS);
    private ArrayList<Jugador> jugadores = new ArrayList(MAX_JUGADORES);
    private Jugador jugadorActual;
    private Dado dado;

    
    /* Patrón Singleton*/
    private static final Qytetet instance = new Qytetet();
    public static Qytetet getInstance(){
        return instance;
    }
    //Constructor
    private Qytetet(){}

    public void inicializarJuego(ArrayList<String> nombres){
        assert(nombres.size()>=2);
        MAX_JUGADORES = nombres.size();
        
        inicializarJugadores(nombres);
        inicializarTablero();
        inicializarCartasSorpresa();
        salidaJugadores();
        cartaActual = mazo.get((int) (Math.random() * MAX_CARTAS-1));
        dado = GUIQytetet.Dado.getInstance();
    }
    
    //Métodos
    
    public boolean aplicarSorpresa(){
        boolean tienePropietario = false;
        if (cartaActual.getTipo() == TipoSorpresa.PAGARCOBRAR)
            jugadorActual.modificarSaldo(cartaActual.getValor());
        else if(cartaActual.getTipo() == TipoSorpresa.IRACASILLA){
            if (tablero.esCasillaCarcel(cartaActual.getValor()))
                encarcelarJugador();
            else{
                Casilla nuevaCasilla = tablero.obtenerCasillaNumero(cartaActual.getValor());
                tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
            }
        }else if(cartaActual.getTipo() == TipoSorpresa.PORCASAHOTEL)
            jugadorActual.pagarCobrarPorCasaYHotel(cartaActual.getValor());
        else if(cartaActual.getTipo() == TipoSorpresa.PORJUGADOR){
            for (Jugador jugador:jugadores)
                if (jugador!=jugadorActual)
                    jugador.modificarSaldo(cartaActual.getValor());
             jugadorActual.modificarSaldo(-cartaActual.getValor()*(MAX_JUGADORES-2));
        }
        if (cartaActual.getTipo() == TipoSorpresa.SALIRCARCEL)
            jugadorActual.setCartaLibertad(cartaActual);
        else{
            mazo.remove(cartaActual);
            //cartaActual = mazo.get(0);
            mazo.add(cartaActual);
        }
        if (cartaActual.getTipo() == TipoSorpresa.CONVERTIRME){
            jugadores.remove(jugadorActual);
            Especulador especulador = jugadorActual.convertirme(cartaActual.getValor());
            jugadores.add(especulador);
            jugadorActual = especulador;
        }
        return tienePropietario;
    }
    public boolean cancelarHipoteca(Calle casilla){
        boolean realizado = false;
        if(casilla.estaHipotecada())
            if(jugadorActual.puedoPagarHipoteca(casilla)){
                jugadorActual.modificarSaldo(-casilla.calcularValorHipoteca());
                realizado = true;
                casilla.titulo.setHipotecada(false);
            }
        return realizado;
    }
    public boolean comprarTituloPropiedad(){
        return jugadorActual.comprarTitulo();
    }

    public Sorpresa getCartaActual(){
        return cartaActual;
    }
    public Jugador getJugadorActual(){
        return jugadorActual;
    }
    public boolean hipotecarPropiedad(Calle casilla){
        boolean sePuedeEdificar = false,puedoHipotecar = false;
        int cantidadRecibida = 0;
        if (casilla.soyEdificable()){
            sePuedeEdificar = !casilla.estaHipotecada();
            if (sePuedeEdificar){
                puedoHipotecar = jugadorActual.puedoHipotecar(casilla);
                if (puedoHipotecar){
                    cantidadRecibida = casilla.hipotecar();
                    jugadorActual.modificarSaldo(cantidadRecibida);
                }
            }
        }
        return puedoHipotecar;
    }
    public boolean intentarSalirCarcel(MetodoSalirCarcel metodo){
        boolean libre = false;
        if (metodo == MetodoSalirCarcel.TIRANDODADO){
            int valorDado = dado.nextNumber();
            if (valorDado>5) libre = true;
        }else{
            boolean tengoSaldo = jugadorActual.pagarLibertad(-PRECIO_LIBERTAD);
            libre = tengoSaldo;
        }
        if (libre)
            jugadorActual.setEncarcelado(false);
        return libre;
    }
    public boolean jugar(){
        boolean tienePropietario = false;
        int valorDado = dado.nextNumber();
        Casilla casillaPosicion = jugadorActual.getCasillaActual();
        Casilla nuevaCasilla = tablero.obtenerNuevaCasilla(casillaPosicion, valorDado);
        tienePropietario = jugadorActual.actualizarPosicion(nuevaCasilla);
        
        if (!nuevaCasilla.soyEdificable())
            if (nuevaCasilla.getTipo() == TipoCasilla.JUEZ ){
                encarcelarJugador();
            }else if (nuevaCasilla.getTipo() == TipoCasilla.SORPRESA){
                cartaActual = mazo.get(0);
                //aplicarSorpresa();
            }else if (nuevaCasilla.getTipo() == TipoCasilla.IMPUESTO)
                jugadorActual.pagarImpuestos(1000);
                
        return tienePropietario;
    }
    public HashMap obtenerRanking(){
        HashMap<String,Integer> ranking;
        ranking = new HashMap<String,Integer>();
        for (Jugador jugador: jugadores){
            int capital = jugador.obtenerCapital();
            ranking.put(jugador.getNombre(),capital);
        }
        return ranking;
    }
    public boolean venderPropiedad(Calle casilla){
        boolean vendido = false;
        if (casilla.soyEdificable()){
            boolean puedoVender = jugadorActual.puedoVenderPropiedad(casilla);
            if (puedoVender){
                jugadorActual.venderPropiedad(casilla);
                vendido = true;
            }
        }
        return vendido;
    }
    private void encarcelarJugador(){
        if(!jugadorActual.tengoCartaLibertad()){
            Casilla casillaCarcel = tablero.getCarcel();
            jugadorActual.irACarcel(casillaCarcel);
        }else{
            Sorpresa carta = jugadorActual.devolverCartaLibertad();
            mazo.add(carta);
        }
    }
    private void inicializarCartasSorpresa(){
        mazo.add(new Sorpresa ("CARTA ESPECULADOR 1.",3000,TipoSorpresa.CONVERTIRME));
        mazo.add(new Sorpresa ("Gana 500",500,TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("Pierde 500",-500,TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("Ir a la casilla 10",10,TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Ir a la casilla 16",16,TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Ir a la carcel",tablero.getCarcel().getNumeroCasilla(),TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Gana 100 por casa/hotel",100,TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Pierde 100 por casa/hotel",-100,TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Recibe 200 de cada jugador",200,TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("Regala 200 de cada jugador",200,TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("Usando esta carta sales de la carcel",0,TipoSorpresa.SALIRCARCEL));
        mazo.add(new Sorpresa ("CARTA ESPECULADOR 1.",3000,TipoSorpresa.CONVERTIRME));
        mazo.add(new Sorpresa ("CARTA ESPECULADOR 2.",5000,TipoSorpresa.CONVERTIRME));
        
    }
    private void inicializarJugadores(ArrayList<String> nombres){
        for(String i:nombres)
            jugadores.add(new Jugador(i,null));
    }
    private void inicializarTablero(){
        tablero = new Tablero();
    }
    public void siguienteJugador(){
        int posicionJugador = jugadores.indexOf(jugadorActual);
        if (jugadorActual==jugadores.get(MAX_JUGADORES-1))
            jugadorActual=jugadores.get(0);
        else
            jugadorActual=jugadores.get(posicionJugador+1);
        
    }
    private void salidaJugadores(){
        for(Jugador i:jugadores){
            i.setCasillaActual(tablero.obtenerCasillaNumero(0));
            i.setSaldo(7500);
        }
        jugadorActual = jugadores.get( (int) (Math.random() * MAX_JUGADORES-1) );
    }
    public ArrayList<Casilla> propiedadesHipotecadasJugador(boolean hipotecadas){
        ArrayList<Casilla> resultado = new ArrayList();
        ArrayList<TituloPropiedad> propiedades = new ArrayList();
        if (jugadorActual.tengoPropiedades()){
            propiedades = jugadorActual.obtenerPropiedadesHipotecadas(hipotecadas);
            for (int i = 0;i<propiedades.size();i++)
                resultado.add(propiedades.get(i).getCasilla());
        }
        return resultado;
    }
    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }
    public boolean edificarCasa(Calle casilla){
        boolean puedoEdificar = false;
        boolean sePuedeEdificar;
        int costeEdificarCasa;
        if (casilla.soyEdificable()){
            if(jugadorActual.getClass().getName().equals("modeloqytetet.Jugador"))
                sePuedeEdificar = casilla.sePuedeEdificarCasa(Jugador.getFactorEspeculador());
            else
                sePuedeEdificar = casilla.sePuedeEdificarCasa(Especulador.getFactorEspeculador());
            
            if(sePuedeEdificar){
                puedoEdificar = jugadorActual.puedoEdificarCasa(casilla);
                if (puedoEdificar){
                    costeEdificarCasa = casilla.edificarCasa();
                    jugadorActual.modificarSaldo(-costeEdificarCasa);
                    puedoEdificar = true;
                }
            }
        }
        return puedoEdificar;
    }
    public boolean edificarHotel(Calle casilla){
        boolean puedoEdificar = false;
        boolean sePuedeEdificar;
        int costeEdificarHotel;
        if (casilla.soyEdificable()){
            if(jugadorActual.getClass().getName().equals("modeloqytetet.Jugador"))
                sePuedeEdificar = casilla.sePuedeEdificarHotel(Jugador.getFactorEspeculador());
            else
                sePuedeEdificar = casilla.sePuedeEdificarHotel(Especulador.getFactorEspeculador());
            
            if(sePuedeEdificar){
                puedoEdificar = jugadorActual.puedoEdificarHotel(casilla);
                if (puedoEdificar){
                    costeEdificarHotel = casilla.edificarHotel();
                    jugadorActual.modificarSaldo(-costeEdificarHotel);
                    puedoEdificar = true;
                }
            }
        }
        return puedoEdificar;
    }
    @Override
    public String toString() {
        return "TABLERO: "+tablero.toString()+"\n"+"SORPRESAS: "+mazo.toString()+"\n"+"JUGADORES: "+jugadores.toString();
    }
}

