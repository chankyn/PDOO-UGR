package GUIQytetet;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modeloqytetet.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Migue
 */
public class ControladorQytetet extends javax.swing.JFrame {
    static Qytetet modeloQytetet;
    /**
     * Creates new form ControladorQytetet
     */
    public ControladorQytetet() {
        initComponents();
        
        setTitle("Qytetet");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        vistaTablero = new GUIQytetet.VistaTablero();
        jPanel2 = new javax.swing.JPanel();
        jbJugar = new javax.swing.JButton();
        jbComprar = new javax.swing.JButton();
        jbSiguienteJugador = new javax.swing.JButton();
        jbSalirCarcelPagando = new javax.swing.JButton();
        jbSalirCarcelDado = new javax.swing.JButton();
        jbVenderPropiedad = new javax.swing.JButton();
        jbCancelarHipoteca = new javax.swing.JButton();
        jbEdificarCasa = new javax.swing.JButton();
        jbEdificarHotel = new javax.swing.JButton();
        jbHipotecarPropiedad = new javax.swing.JButton();
        jbAplicarSorpresa = new javax.swing.JButton();
        jbTerminarJuego = new javax.swing.JButton();
        vistaQytetet = new GUIQytetet.VistaQytetet();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 5, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(vistaTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(vistaTablero, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 153, 51), 5, true));

        jbJugar.setText("Jugar");
        jbJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbJugarActionPerformed(evt);
            }
        });

        jbComprar.setText("Comprar");
        jbComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbComprarActionPerformed(evt);
            }
        });

        jbSiguienteJugador.setText("Pasar Turno");
        jbSiguienteJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSiguienteJugadorActionPerformed(evt);
            }
        });

        jbSalirCarcelPagando.setText("Intentar salir de la cárcel pagando");
        jbSalirCarcelPagando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirCarcelPagandoActionPerformed(evt);
            }
        });

        jbSalirCarcelDado.setText("Intentar salir de la cárcel tirando el dado");
        jbSalirCarcelDado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirCarcelDadoActionPerformed(evt);
            }
        });

        jbVenderPropiedad.setText("Vender propiedad");
        jbVenderPropiedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbVenderPropiedadActionPerformed(evt);
            }
        });

        jbCancelarHipoteca.setText("Cancelar Hipoteca");
        jbCancelarHipoteca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelarHipotecaActionPerformed(evt);
            }
        });

        jbEdificarCasa.setText("Edificar Casa");
        jbEdificarCasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEdificarCasaActionPerformed(evt);
            }
        });

        jbEdificarHotel.setText("Edificar Hotel");
        jbEdificarHotel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEdificarHotelActionPerformed(evt);
            }
        });

        jbHipotecarPropiedad.setText("Hipotecar propiedad");
        jbHipotecarPropiedad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbHipotecarPropiedadActionPerformed(evt);
            }
        });

        jbAplicarSorpresa.setText("Aplicar Sorpresa");
        jbAplicarSorpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAplicarSorpresaActionPerformed(evt);
            }
        });

        jbTerminarJuego.setText("Terminar Juego");
        jbTerminarJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbTerminarJuegoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbSalirCarcelPagando)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jbJugar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbSiguienteJugador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbAplicarSorpresa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbComprar))
                    .addComponent(jbSalirCarcelDado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbEdificarCasa)
                            .addComponent(jbEdificarHotel)
                            .addComponent(jbVenderPropiedad)
                            .addComponent(jbCancelarHipoteca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbHipotecarPropiedad))
                .addGap(60, 60, 60)
                .addComponent(jbTerminarJuego)
                .addGap(51, 51, 51))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbComprar)
                        .addComponent(jbJugar)
                        .addComponent(jbSiguienteJugador)
                        .addComponent(jbAplicarSorpresa))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jbEdificarCasa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEdificarHotel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbHipotecarPropiedad)
                            .addComponent(jbSalirCarcelPagando)
                            .addComponent(jbTerminarJuego))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbVenderPropiedad)
                            .addComponent(jbSalirCarcelDado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbCancelarHipoteca))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vistaQytetet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(vistaQytetet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirCarcelDadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirCarcelDadoActionPerformed
        boolean resultado = modeloQytetet.intentarSalirCarcel(MetodoSalirCarcel.TIRANDODADO);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);
        if(resultado){
            JOptionPane.showMessageDialog(this, "Sales de la cárcel");
            this.jbJugar.setEnabled(true);
        }else {
            JOptionPane.showMessageDialog(this, "NO sales de la carcel");
            this.jbSiguienteJugador.setEnabled(true);
        }
        this.vistaQytetet.actualizar(modeloQytetet);
    }//GEN-LAST:event_jbSalirCarcelDadoActionPerformed

    private void jbSalirCarcelPagandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirCarcelPagandoActionPerformed
        boolean resultado = modeloQytetet.intentarSalirCarcel(MetodoSalirCarcel.PAGANDOLIBERTAD);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);
        if(resultado){
            JOptionPane.showMessageDialog(this, "Sales de la cárcel");
            this.jbJugar.setEnabled(true);
        }else {
            JOptionPane.showMessageDialog(this, "NO sales de la carcel");
            this.jbSiguienteJugador.setEnabled(true);
        }
        this.vistaQytetet.actualizar(modeloQytetet);
    }//GEN-LAST:event_jbSalirCarcelPagandoActionPerformed

    private void jbComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbComprarActionPerformed
        boolean resultado = false;

        resultado = modeloQytetet.comprarTituloPropiedad();

        if (resultado){
            JOptionPane.showMessageDialog(this, modeloQytetet.getJugadorActual().getNombre()+" ha comprado la propiedad.");
            this.jbJugar.setEnabled(false);
            this.jbHipotecarPropiedad.setEnabled(true);
            this.jbEdificarCasa.setEnabled(true);
            this.jbEdificarHotel.setEnabled(true);
            this.jbVenderPropiedad.setEnabled(true);
            this.vistaQytetet.actualizar(modeloQytetet);
        }
        this.jbComprar.setEnabled(false);
    }//GEN-LAST:event_jbComprarActionPerformed

    private void jbSiguienteJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSiguienteJugadorActionPerformed
        
        modeloQytetet.siguienteJugador();
        habilitarComenzarTurno();
        this.vistaQytetet.actualizar(modeloQytetet);
    }//GEN-LAST:event_jbSiguienteJugadorActionPerformed

    private void jbJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbJugarActionPerformed

        
        if (modeloQytetet.getJugadorActual().getEncarcelado())
            this.jbJugar.setEnabled(false);
        else{
            modeloQytetet.jugar();
            if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo()==TipoCasilla.SORPRESA)
                this.jbAplicarSorpresa.setEnabled(true);
            else{
                this.jbSiguienteJugador.setEnabled(true);
                if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo()==TipoCasilla.CALLE)
                    this.jbComprar.setEnabled(true);
                if (modeloQytetet.getJugadorActual().tengoPropiedades()){
                    this.jbHipotecarPropiedad.setEnabled(true);
                    this.jbEdificarCasa.setEnabled(true);
                    this.jbEdificarHotel.setEnabled(true);
                    this.jbVenderPropiedad.setEnabled(true);
                    this.jbCancelarHipoteca.setEnabled(true);
                }
            }
        }
        if (modeloQytetet.getJugadorActual().getSaldo()<0)
            terminarJuego();
        vistaTablero.mover(modeloQytetet);
        this.jbJugar.setEnabled(false);
        this.vistaQytetet.actualizar(modeloQytetet);
    }//GEN-LAST:event_jbJugarActionPerformed

    private void jbAplicarSorpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAplicarSorpresaActionPerformed
        this.jbAplicarSorpresa.setEnabled(false);
        JOptionPane.showMessageDialog(this, "Ha tocado la carta sorpresa: "+modeloQytetet.getCartaActual().getTexto());
        modeloQytetet.aplicarSorpresa();
        this.jbSiguienteJugador.setEnabled(true);
        if (modeloQytetet.getJugadorActual().getCasillaActual().getTipo()==TipoCasilla.CALLE)
            this.jbComprar.setEnabled(true);
        if (modeloQytetet.getJugadorActual().tengoPropiedades()){
            this.jbHipotecarPropiedad.setEnabled(true);
            this.jbEdificarCasa.setEnabled(true);
            this.jbEdificarHotel.setEnabled(true);
            this.jbVenderPropiedad.setEnabled(true);
            this.jbCancelarHipoteca.setEnabled(true);
        }
    }//GEN-LAST:event_jbAplicarSorpresaActionPerformed

    private void jbEdificarCasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEdificarCasaActionPerformed
        String nombreCalle = this.vistaQytetet.CalleSeleccionada().replaceAll("[\n\r]", "");
        String comparaNombre = "";
        Calle calle = null;
        boolean resultado = false;
        if (nombreCalle.equals("")){
            this.vistaQytetet.actualizar(modeloQytetet);
        }else{
            ArrayList<Casilla> casillas = modeloQytetet.getJugadorActual().obtenerPropiedades();
            for (Casilla casilla : casillas){
                comparaNombre = ((Calle)casilla).getTituloPropiedad().getNombre();
                if(comparaNombre.equals(nombreCalle))
                    calle = ((Calle)casilla);
            }
            if(calle!=null)
                resultado = modeloQytetet.edificarCasa(calle);
        }
        if (resultado){
            JOptionPane.showMessageDialog(this, "Se ha edificado una casa en: "+nombreCalle);
            vistaTablero.pintarCasa(calle.getNumeroCasilla(), calle.getNumCasas());
        }else if(!nombreCalle.equals("")){
            JOptionPane.showMessageDialog(this, "No se puede edificar una casa en: "+nombreCalle);
            this.jbEdificarCasa.setEnabled(false);
        }
        this.vistaQytetet.actualizar(modeloQytetet);
    }//GEN-LAST:event_jbEdificarCasaActionPerformed

    private void jbHipotecarPropiedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbHipotecarPropiedadActionPerformed
        String nombreCalle = this.vistaQytetet.CalleSeleccionada().replaceAll("[\n\r]", "");
        String comparaNombre = "";
        Calle calle = null;
        boolean resultado = false;
        if (nombreCalle.equals("")){
            JOptionPane.showMessageDialog(this, "Seleccione una propiedad de la lista.");
            this.vistaQytetet.actualizar(modeloQytetet);
        }else{
            ArrayList<Casilla> casillas = modeloQytetet.getJugadorActual().obtenerPropiedades();
            for (Casilla casilla : casillas){
                comparaNombre = ((Calle)casilla).getTituloPropiedad().getNombre();
                if(comparaNombre.equals(nombreCalle))
                    calle = ((Calle)casilla);
            }
            if(calle!=null)
                resultado = modeloQytetet.hipotecarPropiedad(calle);
        }
        if (resultado)
            JOptionPane.showMessageDialog(this, "Se ha hipotecado la propiedad: "+nombreCalle);
        else if(!nombreCalle.equals(""))
            JOptionPane.showMessageDialog(this, "No se puede hipotecar la propiedad: "+nombreCalle);
        this.jbHipotecarPropiedad.setEnabled(false);
        this.jbCancelarHipoteca.setEnabled(true);
        this.vistaQytetet.actualizar(modeloQytetet);
    }//GEN-LAST:event_jbHipotecarPropiedadActionPerformed

    private void jbEdificarHotelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEdificarHotelActionPerformed
        String nombreCalle = this.vistaQytetet.CalleSeleccionada().replaceAll("[\n\r]", "");
        String comparaNombre = "";
        Calle calle = null;
        boolean resultado = false;
        if (nombreCalle.equals("")){
            JOptionPane.showMessageDialog(this, "Seleccione una propiedad de la lista.");
            this.vistaQytetet.actualizar(modeloQytetet);
        }else{
            ArrayList<Casilla> casillas = modeloQytetet.getJugadorActual().obtenerPropiedades();
            for (Casilla casilla : casillas){
                comparaNombre = ((Calle)casilla).getTituloPropiedad().getNombre();
                if(comparaNombre.equals(nombreCalle))
                    calle = ((Calle)casilla);
            }
            if(calle!=null)
                resultado = modeloQytetet.edificarHotel(calle);
        }
        if (resultado){
            JOptionPane.showMessageDialog(this, "Se ha edificado un hotel en: "+nombreCalle);
            vistaTablero.pintarHotel(calle.getNumeroCasilla(), calle.getNumHoteles());
        }else if(!nombreCalle.equals("")){
            JOptionPane.showMessageDialog(this, "No se puede edificar un hotel en: "+nombreCalle);
            this.jbEdificarHotel.setEnabled(false);
        }
        this.vistaQytetet.actualizar(modeloQytetet);
    }//GEN-LAST:event_jbEdificarHotelActionPerformed

    private void jbVenderPropiedadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbVenderPropiedadActionPerformed
        String nombreCalle = this.vistaQytetet.CalleSeleccionada().replaceAll("[\n\r]", "");
        String comparaNombre = "";
        Calle calle = null;
        boolean resultado = false;
        if (nombreCalle.equals("")){
            JOptionPane.showMessageDialog(this, "Seleccione una propiedad de la lista.");
            this.vistaQytetet.actualizar(modeloQytetet);
        }else{
            ArrayList<Casilla> casillas = modeloQytetet.getJugadorActual().obtenerPropiedades();
            for (Casilla casilla : casillas){
                comparaNombre = ((Calle)casilla).getTituloPropiedad().getNombre();
                if(comparaNombre.equals(nombreCalle))
                    calle = ((Calle)casilla);
            }
            if(calle!=null)
                resultado = modeloQytetet.venderPropiedad(calle);
        }
        if (resultado){
            JOptionPane.showMessageDialog(this, "Se ha vendido la propiedad: "+nombreCalle);
            vistaTablero.eliminarCasasHoteles(calle.getNumeroCasilla());
        }else if(!nombreCalle.equals(""))
            JOptionPane.showMessageDialog(this, "No se vender la propiedad: "+nombreCalle);

        this.jbVenderPropiedad.setEnabled(false);
        this.vistaQytetet.actualizar(modeloQytetet);
    }//GEN-LAST:event_jbVenderPropiedadActionPerformed

    private void jbCancelarHipotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelarHipotecaActionPerformed
        String nombreCalle = this.vistaQytetet.CalleSeleccionada().replaceAll("[\n\r]", "");
        String comparaNombre = "";
        Calle calle = null;
        boolean resultado = false;
        if (nombreCalle.equals("")){
            JOptionPane.showMessageDialog(this, "Seleccione una propiedad de la lista.");
            this.vistaQytetet.actualizar(modeloQytetet);
        }else{
            ArrayList<Casilla> casillas = modeloQytetet.getJugadorActual().obtenerPropiedades();
            for (Casilla casilla : casillas){
                comparaNombre = ((Calle)casilla).getTituloPropiedad().getNombre();
                if(comparaNombre.equals(nombreCalle))
                    calle = ((Calle)casilla);
            }
            if(calle!=null)
                resultado = modeloQytetet.cancelarHipoteca(calle);
        }
        if (resultado)
            JOptionPane.showMessageDialog(this, "Se ha cancelado la hipoteca de la propiedad: "+nombreCalle);
        else if(!nombreCalle.equals(""))
            JOptionPane.showMessageDialog(this, "No se ha cancelado la hipoteca de la propiedad: "+nombreCalle);

        this.jbCancelarHipoteca.setEnabled(false);
        this.jbHipotecarPropiedad.setEnabled(true);
        this.vistaQytetet.actualizar(modeloQytetet);
    }//GEN-LAST:event_jbCancelarHipotecaActionPerformed
    private void terminarJuego(){
        this.jbAplicarSorpresa.setEnabled(false);
        this.jbCancelarHipoteca.setEnabled(false);
        this.jbComprar.setEnabled(false);
        this.jbEdificarCasa.setEnabled(false);
        this.jbEdificarHotel.setEnabled(false);
        this.jbHipotecarPropiedad.setEnabled(false);
        this.jbSalirCarcelDado.setEnabled(false);
        this.jbSalirCarcelPagando.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(false);
        this.jbVenderPropiedad.setEnabled(false);
        HashMap ranking; ranking = modeloQytetet.obtenerRanking();
        String resultado = ranking.toString();
        
        JOptionPane.showMessageDialog(this, "Resultados finales: "+resultado);
        JOptionPane.showMessageDialog(this, "El ganador ha sido: "+ranking.keySet().iterator().next());
        dispose(); //Cerrar juego actual.
    }
    private void jbTerminarJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbTerminarJuegoActionPerformed
        terminarJuego();
    }//GEN-LAST:event_jbTerminarJuegoActionPerformed

    /**
     * @param args the command line arguments
     */
    public void actualizar(){
        vistaQytetet.actualizar(modeloQytetet);
        
    }
    public void iniciar(int num){
        vistaTablero.iniciar(num);
    }
    public void habilitarComenzarTurno(){
        this.jbComprar.setEnabled(false);
        this.jbSiguienteJugador.setEnabled(false);
        this.jbAplicarSorpresa.setEnabled(false);
        this.jbHipotecarPropiedad.setEnabled(false);
        this.jbEdificarCasa.setEnabled(false);
        this.jbEdificarHotel.setEnabled(false);
        this.jbVenderPropiedad.setEnabled(false);
        this.jbCancelarHipoteca.setEnabled(false);
        
        if(modeloQytetet.getJugadorActual().getEncarcelado()){
            this.jbSalirCarcelPagando.setEnabled(true);
            this.jbSalirCarcelDado.setEnabled(true);
        }
        else{
            this.jbSalirCarcelPagando.setEnabled(false);
            this.jbSalirCarcelDado.setEnabled(false);
            this.jbJugar.setEnabled(true);
        }
    }
    //static ControladorQytetet controladorQytetet= new ControladorQytetet();
    public static void main(String[] args){
        ControladorQytetet controladorQytetet= new ControladorQytetet();
        CapturaNombreJugadores capturaNombres = new CapturaNombreJugadores(controladorQytetet, true);
        ArrayList<String> nombres= capturaNombres.obtenerNombres();
                
        Dado.createInstance(controladorQytetet);
        
        ControladorQytetet.modeloQytetet = Qytetet.getInstance();
        modeloQytetet.inicializarJuego(nombres);
        
        controladorQytetet.actualizar();
        controladorQytetet.iniciar(nombres.size());
        
        controladorQytetet.habilitarComenzarTurno();
        controladorQytetet.setVisible(true); //Esta debe ser la última línea de código del main

}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbAplicarSorpresa;
    private javax.swing.JButton jbCancelarHipoteca;
    private javax.swing.JButton jbComprar;
    private javax.swing.JButton jbEdificarCasa;
    private javax.swing.JButton jbEdificarHotel;
    private javax.swing.JButton jbHipotecarPropiedad;
    private javax.swing.JButton jbJugar;
    private javax.swing.JButton jbSalirCarcelDado;
    private javax.swing.JButton jbSalirCarcelPagando;
    private javax.swing.JButton jbSiguienteJugador;
    private javax.swing.JButton jbTerminarJuego;
    private javax.swing.JButton jbVenderPropiedad;
    private GUIQytetet.VistaQytetet vistaQytetet;
    private GUIQytetet.VistaTablero vistaTablero;
    // End of variables declaration//GEN-END:variables
   
}
