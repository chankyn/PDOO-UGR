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
public class Dado {
    /* Patrón Singletón*/
    private static final Dado instance = new Dado();
    public static Dado getInstance(){
        return instance;
    }
    //Constructor
    private Dado(){}
    
    //Método
    int tirar(){
        //Función para calcular un número aleatorio entre 1 y 6.
        //return (int) (Math.random() * 6) + 1;
        return 3;
    }
}
