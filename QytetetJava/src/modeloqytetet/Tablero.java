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
public class Tablero {
    private ArrayList<Casilla> casillas;
    private Casilla carcel;
    
    public Tablero(){
        inicializar();
    }

    public Casilla getCarcel() {
        return carcel;
    }
    
    @Override
    public String toString() {
        return "Tablero{" + "casillas=" + casillas.toString() + ", carcel=" + carcel.toString() + '}';
    }
    private void AsignarCasillaATituloPropiedad(){
        for(int i=0;i<casillas.size();i++)
            if(casillas.get(i).getTipo()==TipoCasilla.CALLE)
                ((Calle) casillas.get(i)).getTituloPropiedad().setCasilla(casillas.get(i));
    }
    private void inicializar(){
        casillas= new ArrayList();
        
        casillas.add(new OtraCasilla (0,TipoCasilla.SALIDA));
        casillas.add(new Calle (1,100,new TituloPropiedad("Calle El Llanillo", 250, (float) -0.1, 50, 350)));
        casillas.add(new Calle (2,150,new TituloPropiedad("Calle Alfonso XI", 280, (float) -0.1, 60, 250)));
        casillas.add(new OtraCasilla (3,TipoCasilla.SORPRESA));
        casillas.add(new Calle (4,200,new TituloPropiedad("Calle Miguel Hernández", 310, (float) -1.5, 50, 150)));
        casillas.add(new OtraCasilla (5,TipoCasilla.CARCEL));
        casillas.add(new Calle (6,250,new TituloPropiedad("Calle Abén Zayde", 350, (float) -1.2, 70, 400)));
        casillas.add(new Calle (7,300,new TituloPropiedad("Calle López de Haro", 390, (float) -1.2, 70, 650)));
        casillas.add(new OtraCasilla (8,TipoCasilla.SORPRESA));
        casillas.add(new Calle (9,350,new TituloPropiedad("Calle Alonso de Alcalá", 430, (float) -0.7, 60, 405)));
        casillas.add(new OtraCasilla (10,TipoCasilla.PARKING));
        casillas.add(new Calle (11,400,new TituloPropiedad("Calle Cipreses", 475, (float) -1.4, 50, 255)));
        casillas.add(new Calle (12,450,new TituloPropiedad("Paseo de los Álamos", 520, (float) -1.4, 60, 850)));
        casillas.add(new OtraCasilla (13,TipoCasilla.SORPRESA));
        casillas.add(new Calle (14,500,new TituloPropiedad("Avenida de Europa", 565, (float) -0.9, 80, 475)));
        casillas.add(new OtraCasilla (15,TipoCasilla.JUEZ));
        casillas.add(new Calle (16,600,new TituloPropiedad("Calle Málaga", 625, (float) -1.6, 60, 500)));
        casillas.add(new Calle (17,700,new TituloPropiedad("Calle Alférez Utrilla", 690, (float) -2.0, 100, 1000)));
        casillas.add(new OtraCasilla (18,TipoCasilla.IMPUESTO));
        casillas.add(new Calle (19,800,new TituloPropiedad("Avenida de Andalucía", 750, (float) 0.5, 100, 500)));
        
        AsignarCasillaATituloPropiedad();
        carcel=casillas.get(5);
    }
    boolean esCasillaCarcel (int numeroCasilla){
        return numeroCasilla==getCarcel().getNumeroCasilla();
    }
    Casilla obtenerCasillaNumero(int numeroCasilla){
        return casillas.get(numeroCasilla);
    }
    Casilla obtenerNuevaCasilla(Casilla casilla, int desplazamiento){
        int posicion=casilla.getNumeroCasilla()+desplazamiento;
        if(posicion <20){
            return casillas.get(posicion);
        }else
            return casillas.get(posicion-20);
    }
    
    
}
