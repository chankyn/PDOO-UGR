/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloqytetet;

/**
 *
 * @author MiguelÁngel
 */
public class Sorpresa {
    private String texto;
    private TipoSorpresa tipo;
    private int valor;
    
    public Sorpresa(String texto, int valor, TipoSorpresa tipo){
        this.texto=texto;
        this.valor=valor;
        this.tipo=tipo;
    }

    public String getTexto() {
        return texto;
    }

    public TipoSorpresa getTipo() {
        return tipo;
    }

    int getValor() {
        return valor;
    }

    void setTexto(String texto) {
        this.texto = texto;
    }

    void setTipo(TipoSorpresa tipo) {
        this.tipo = tipo;
    }

    void setValor(int valor) {
        this.valor = valor;
    }
    
    @Override
    public String toString() {
        String sorpresa;
        sorpresa = "Texto de la carta: "+texto+"\n";
        sorpresa = "Valor: "+valor+"\n";
        return sorpresa;
    }
}
