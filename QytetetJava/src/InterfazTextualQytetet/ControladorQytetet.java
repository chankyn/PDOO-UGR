/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazTextualQytetet;

import java.util.ArrayList;
import java.util.HashMap;
import modeloqytetet.*;

/**
 *
 * @author Migue
 */
public class ControladorQytetet {

    static Qytetet juego;
    static Jugador jugador;
    static Casilla casilla;
    static VistaTextualQytetet vista = new VistaTextualQytetet();

    public static void inicializacionJuego() {
        juego = Qytetet.getInstance();
        //ArrayList<String> nombres = vista.obtenerNombreJugadores();
        ArrayList<String> nombres = new ArrayList();

        nombres.add("PepitoGrillo");
        nombres.add("MacarenaLaPortugesa");
        nombres.add("CamaronDeLaIsla");
        nombres.add("NapoleonBonaparte");

        juego.inicializarJuego(nombres);

        jugador = juego.getJugadorActual();
        casilla = jugador.getCasillaActual();

        vista.mostrar(juego.toString());
    }

    public static Casilla elegirPropiedad(ArrayList<Casilla> propiedades) {
        //este metodo toma una lista de propiedades y genera una lista de strings, con el numero y nombre de las propiedades
        //luego llama a la vista para que el usuario pueda elegir.
        vista.mostrar("\tCasilla\tTitulo");
        int seleccion;
        ArrayList<String> listaPropiedades = new ArrayList();
        for (Casilla casill : propiedades) {
            listaPropiedades.add("\t" + casill.getNumeroCasilla() + "\t" + ((Calle)casill).getTituloPropiedad().getNombre());
        }
        seleccion = vista.menuElegirPropiedad(listaPropiedades);
        return propiedades.get(seleccion);
    }

    private static boolean JugadorEnCarcel() {
        boolean libre = false;
        if (vista.menuSalirCarcel() == 1) {
            libre = juego.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
        } else {
            libre = juego.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
        }

        if (libre) {
            vista.mostrar("El jugador " + jugador.getNombre() + " ha conseguido salir de la carcel.");
        } else {
            vista.mostrar("El jugador " + jugador.getNombre() + " no ha conseguido salir de la carcel. Pasa el turno al siguiente jugador.");
        }
        return libre;
    }

    private static boolean juegoTipoSorpresa() {
        boolean noTienePropietario = juego.aplicarSorpresa();
        if (jugador.getEncarcelado()) {
            vista.mostrar("El jugador " + jugador.getNombre() + " ha sido encarcelado. Pulsa para continuar.");
            vista.in.nextLine();
            esFinalDeJuego();
        }
        if (jugador.getSaldo() <= 0) {
            vista.mostrar("El jugador " + jugador.getNombre() + " ha entrador en bancarrota. Pulsa para continuar.");
            vista.in.nextLine();
            esFinalDeJuego();
        }
        if (jugador.getCasillaActual() != casilla) {
            vista.mostrar("El jugador " + jugador.getNombre() + " ha cambiado de posicion, ahora esta en la casilla " + jugador.getCasillaActual());
            casilla = jugador.getCasillaActual();
            if (casilla.getTipo() == TipoCasilla.CALLE) {
                if (!((Calle)casilla).getTituloPropiedad().tengoPropietario()) {
                    if (vista.elegirQuieroComprar()) {
                        juego.comprarTituloPropiedad();
                    }
                } else {
                    vista.mostrar("La casilla " + casilla.getNumeroCasilla() + " tiene propietario, el jugador " + jugador.getNombre() + " tiene que pagar.");
                    vista.mostrar(jugador.getNombre() + " se ha quedado con un saldo total de " + jugador.getSaldo());
                }
            }
        }

        return noTienePropietario;
    }

    private static void GestionInmobiliaria(boolean encarcelado, boolean bancarrota) {
        if (!encarcelado && !bancarrota && jugador.tengoPropiedades()) {
            int opcion = vista.menuGestionInmobiliaria();
            while (opcion != 0){
                Calle c;
                c = (Calle)elegirPropiedad(jugador.obtenerPropiedades());
                switch (opcion) {
                    case 1:
                        if (juego.edificarCasa(c)) {
                            vista.mostrar("Se ha edificado una casa en la casilla " + c.getNumeroCasilla());
                            vista.mostrar("El saldo del jugador " + jugador.getNombre() + " desciente a " + jugador.getSaldo());
                        } else {
                            vista.mostrar("No se ha edificado una casa en la casilla " + c.getNumeroCasilla());
                        }
                        break;
                    case 2:
                        if (juego.edificarHotel(c)) {
                            vista.mostrar("Se ha edificado un hotel en la casilla " + c.getNumeroCasilla());
                            vista.mostrar("El saldo del jugador " + jugador.getNombre() + " desciente a " + jugador.getSaldo());
                        } else {
                            vista.mostrar("No se ha edificado un hotel en la casilla " + c.getNumeroCasilla());
                        }
                        break;
                    case 3:
                        if (juego.venderPropiedad(c)) {
                            vista.mostrar("Se ha vendido la casilla " + c.getNumeroCasilla());
                            vista.mostrar("El saldo del jugador " + jugador.getNombre() + " aumenta a " + jugador.getSaldo());
                        } else {
                            vista.mostrar("No se ha podido vender la casilla " + c.getNumeroCasilla());
                        }
                        break;
                    case 4:
                        if (juego.hipotecarPropiedad(c)) {
                            vista.mostrar("Se ha hipotecado la casilla " + c.getNumeroCasilla());
                            vista.mostrar("El saldo del jugador " + jugador.getNombre() + " aumenta a " + jugador.getSaldo());
                        } else {
                            vista.mostrar("No se ha podido vender la casilla " + c.getNumeroCasilla());
                        }
                        break;
                    case 5:
                        if (juego.cancelarHipoteca(c)) {
                            vista.mostrar("Se ha cancelado la hipoteca de la casilla " + c.getNumeroCasilla());
                            vista.mostrar("El saldo del jugador " + jugador.getNombre() + " desciende a " + jugador.getSaldo());
                        } else {
                            vista.mostrar("No se ha podido cancelar la hipoteca de la casilla " + c.getNumeroCasilla());
                        }
                        break;
                }
                opcion = vista.menuGestionInmobiliaria();
            }
        }
    }

    private static void esFinalDeJuego() {
        if (jugador.getSaldo() > 0 /*&& prueba*/) {
            vista.mostrar("Fin de turno. Pulsa una tecla cuando estes preparado.");
            vista.in.nextLine();
            juego.siguienteJugador();
            jugador = juego.getJugadorActual();
            desarrolloJuego();
        } else {
            vista.mostrar("El jugador " + jugador.getNombre() + " ha caido en bancarrota.");
            HashMap rank = juego.obtenerRanking();
            rank.forEach((k, v) -> vista.mostrar("Jugador: " + k + ": Saldo: " + v));
        }

    }

    private static void JugadorLibre() {
        boolean noTienePropietario = juego.jugar();
        casilla = jugador.getCasillaActual();
        vista.mostrar("Pulsa una tecla para avanzar a la casilla: " + casilla.getNumeroCasilla());
        vista.in.nextLine();
        boolean bancarrota = jugador.getSaldo() < 0;

        if (!bancarrota) {
            boolean encarcelado = jugador.getEncarcelado();
            if (!encarcelado) {
                TipoCasilla tipo = casilla.getTipo();
                if (tipo == TipoCasilla.SORPRESA) {
                    vista.mostrar("El jugador " + jugador.getNombre() + " ha caido en una casilla tipo sorpresa.\n");
                    vista.mostrar("Ha tocado la sopresa: " + juego.getCartaActual().getTexto());
                    //noTienePropietario = 
                    juegoTipoSorpresa();

                } else if (tipo == TipoCasilla.CALLE) {
                    noTienePropietario = !((Calle)casilla).getTituloPropiedad().tengoPropietario();
                    if (noTienePropietario) {
                        vista.mostrar("La casilla " + casilla.getNumeroCasilla() + " se puede comprar. Elige una opción.");
                        if (vista.elegirQuieroComprar()) {
                            juego.comprarTituloPropiedad();
                            vista.mostrar("El jugador " + jugador.getNombre() + " ha comprado la casilla " + casilla.getNumeroCasilla());
                            vista.mostrar(jugador.getNombre() + " se ha quedado con un saldo total de " + jugador.getSaldo());
                        } else {
                            vista.mostrar("El jugador " + jugador.getNombre() + " no ha comprado la casilla " + casilla.getNumeroCasilla());
                        }
                    } else if (!((Calle)casilla).estaHipotecada()) {
                        vista.mostrar("La casilla " + casilla.getNumeroCasilla() + " tiene propietario, el jugador " + jugador.getNombre() + " tiene que pagar.");
                        vista.mostrar(jugador.getNombre() + " se ha quedado con un saldo total de " + jugador.getSaldo());
                    }
                }
                //Gestion Inmobiliaria
                GestionInmobiliaria(encarcelado, bancarrota);
                esFinalDeJuego();
            }
        }
    }

    public static void desarrolloJuego() {
        boolean libre = true;

        jugador = juego.getJugadorActual();
        casilla = jugador.getCasillaActual();

        vista.mostrar("Turno del jugador: " + jugador.getNombre());
        vista.mostrar("Esta en la casilla: " + casilla.getNumeroCasilla());
        vista.mostrar("Es una casilla de tipo: " + casilla.getTipo());

        //jugador.prueba();
        if (jugador.getEncarcelado()) {
            libre = JugadorEnCarcel();
        }

        if (libre) {
            JugadorLibre();
        }
    }

    public static void main(String[] args) {
        inicializacionJuego();
        desarrolloJuego();
    }
}
