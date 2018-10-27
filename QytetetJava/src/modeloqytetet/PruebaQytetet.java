/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

import java.util.ArrayList;

/**
 *
 * @author MiguelÁngel
 */
public class PruebaQytetet {
    private static ArrayList<Sorpresa> mazo = new ArrayList();
    private static Tablero tablero = new Tablero();
    
    private static void inicializarSorpresas(){
        mazo.add(new Sorpresa ("Gana 500",500,TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("Pierde 500",-500,TipoSorpresa.PAGARCOBRAR));
        mazo.add(new Sorpresa ("Ir a la casilla 10",10,TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Ir a la casilla 20",20,TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Ir a la carcel",tablero.getCarcel().getNumeroCasilla(),TipoSorpresa.IRACASILLA));
        mazo.add(new Sorpresa ("Gana 100 por casa/hotel",100,TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Pierde 100 por casa/hotel",-100,TipoSorpresa.PORCASAHOTEL));
        mazo.add(new Sorpresa ("Recibe 200 de cada jugador",200,TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("Regala 200 de cada jugador",200,TipoSorpresa.PORJUGADOR));
        mazo.add(new Sorpresa ("Usando esta carta sales de la carcel",0,TipoSorpresa.SALIRCARCEL));
    }
    private static ArrayList obtenerSorpresaValorMayorA0(){
        ArrayList<Sorpresa> resultado = new ArrayList();
        for (Sorpresa i:mazo)
            if(i.getValor()>0)
                resultado.add(i);
        return resultado;
    }
    private static ArrayList obtenerSorpresaTipoSorpresaIrCasilla(){
        ArrayList<Sorpresa> resultado = new ArrayList();
        for (Sorpresa i:mazo)
            if(i.getTipo()==TipoSorpresa.IRACASILLA)
               resultado.add(i);
        return resultado;
    }
    private static ArrayList obtenerSorpresaTipoSorpresa(TipoSorpresa tipo){
        ArrayList<Sorpresa> resultado = new ArrayList();
        for (Sorpresa i:mazo)
            if(i.getTipo()==tipo){
                System.out.print(i.getTipo());
                resultado.add(i);
            }
        
        return resultado;
    }
    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) {
        Qytetet mc = Qytetet.getInstance();
        ArrayList<String> nombres = new ArrayList();
        nombres.add("pepe");
        nombres.add("maria");
        nombres.add("ramon");
        
        mc.inicializarJuego(nombres);
        System.out.println(mc.getJugadorActual().getCasillaActual().getNumeroCasilla());
        System.out.println("HOLA");
    }
    */
}
