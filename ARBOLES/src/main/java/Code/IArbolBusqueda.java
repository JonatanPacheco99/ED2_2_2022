/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Code;

import java.util.List;

/**
 *
 * @author vivia
 */
public interface IArbolBusqueda<K extends Comparable <K>,V> {
    void Insertar(K claveAInsertar,V valorAInsertar) throws NullPointerException;
    void InsertarR(K claveAInsertar,V valorAInsertar) throws NullPointerException;
    V eliminar(K claveAEliminar) throws ExceptionClaveNoExiste;
    V buscar(K claveABuscar);
    boolean contiene(K claveAVuscar);
    int size();
    int altura();
    int cantHojas();
    boolean esArbolVacio();
    K minimo();
    K maximo();
    int cantHijosDerechosNivel(int nivel);
    int contarNodosConHijosDerecho();
    int contarNodosConHijosDerechoR();
    int contarNodosConHijosIzquierdoR();
    int contarNodosConHijosIzquierdo();
    List<K> recorridoPorNiveles();

    
}
