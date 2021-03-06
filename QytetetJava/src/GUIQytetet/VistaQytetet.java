/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIQytetet;
import java.util.ArrayList;
import modeloqytetet.*;
/**
 *
 * @author Migue
 */
public class VistaQytetet extends javax.swing.JPanel {

    /**
     * Creates new form VistaQytetet
     */
    public VistaQytetet() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vistaJugador = new GUIQytetet.VistaJugador();
        vistaPropiedades = new GUIQytetet.VistaPropiedades();
        vistaCasilla = new GUIQytetet.VistaCasilla();
        vistaCartaSopresa = new GUIQytetet.VistaCartaSopresa();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(vistaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(vistaPropiedades, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(vistaCasilla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(vistaCartaSopresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(vistaPropiedades, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(vistaJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(vistaCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(vistaCartaSopresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUIQytetet.VistaCartaSopresa vistaCartaSopresa;
    private GUIQytetet.VistaCasilla vistaCasilla;
    private GUIQytetet.VistaJugador vistaJugador;
    private GUIQytetet.VistaPropiedades vistaPropiedades;
    // End of variables declaration//GEN-END:variables
    public void actualizar(Qytetet juego){
        this.vistaPropiedades.BorrarOpciones();
        String propiedad = "";
        ArrayList<Casilla> propiedades = juego.getJugadorActual().obtenerPropiedades();
        for (Casilla casilla :propiedades){
            propiedad = ((Calle) casilla).getTituloPropiedad().getNombre();
            this.vistaPropiedades.actualizar(propiedad);

        }
        String carta = "EL jugador no dispone de ninguna carta sorpresa que pueda usar.\n";
        if(juego.getJugadorActual().getCartaLibertad() != null)
          carta = juego.getJugadorActual().getCartaLibertad().toString();
       this.vistaCartaSopresa.actualizar(carta);
       this.vistaCasilla.actualizar(juego.getJugadorActual().getCasillaActual().toString());
       this.vistaJugador.actualizar(juego.getJugadorActual().toString());
       
       this.repaint(); //Investiga para qué sirven estos métodos
       this.revalidate();
    }
    public String CalleSeleccionada(){
        String resultado = vistaPropiedades.CalleSeleccionada();
        return resultado;
    }
}
