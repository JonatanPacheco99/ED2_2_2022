/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author vivia
 * @param <K>
 * @param <V>
 */
public class AVL<K extends Comparable<K>,V> extends ArbolBinarioBusqueda<K,V> {
    
    private static final byte DIFERENCE_MAX=1;  //DIFERENCIA DE ALTURA PARA BALANCEAR
    
    @Override
    public void Insertar(K claveAInsertar,V valorAInsertar) throws NullPointerException{
        if(claveAInsertar==null){
            throw new NullPointerException("clave nula");
        }
        if(valorAInsertar==null){
            throw new NullPointerException("valor nula");
        }
        //methd_1.0:recursivo
        this.raiz=insertar(this.raiz,claveAInsertar,valorAInsertar);
    }
    
    /**
     * methd_1.0:recursivo_insertar
     */
    private NodoBinario<K,V> insertar(NodoBinario<K,V> nodoActual,K claveAInsertar,V valorAInsertar){
        //tambien sirve si en caso el nodo es nulo 
        //entonces lo creara 
        if(NodoBinario.esNodoVacio(nodoActual)){    //es vacio el nodo
            NodoBinario<K,V> nuevoNodo=new NodoBinario<>(claveAInsertar,valorAInsertar);
            return nuevoNodo;
        }
        K claveActual=nodoActual.getClave();
        if(claveAInsertar.compareTo(claveActual)>0){    //clave a insertar es mayor 
            NodoBinario<K,V> supuestoHijoDerecha=insertar(nodoActual.getHijoDerecho(),claveAInsertar,valorAInsertar);
            nodoActual.setHijoDerecho(supuestoHijoDerecha);
            return balancear(nodoActual);
        }
        if(claveAInsertar.compareTo(claveActual)<0){    //clave a insertar es menor
            NodoBinario<K,V> supuestoHijoIzquierdo=insertar(nodoActual.getHijoIzquierdo(),claveAInsertar,valorAInsertar);
            nodoActual.setHijoIzquierdo(supuestoHijoIzquierdo);
            return balancear(nodoActual);
        }
        //encontro el nodo 
        //solo debemos reemplazar su valor
        nodoActual.setValor(valorAInsertar);
        return balancear(nodoActual);
    }
    
    private NodoBinario<K,V> balancear(NodoBinario<K,V> nodoActual){
        int alturaPorIzquierda=altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha=altura(nodoActual.getHijoDerecho());
        int diferencia=alturaPorIzquierda-alturaPorDerecha;
        
        if(diferencia>DIFERENCE_MAX){   //la rama izquierda sobrepasa de lo establecido
            //rotacion a derecha
            NodoBinario<K,V> nodoHijoIzquierdoNodoActual=nodoActual.getHijoIzquierdo();
            alturaPorIzquierda=altura(nodoHijoIzquierdoNodoActual.getHijoIzquierdo());
            alturaPorDerecha=altura(nodoHijoIzquierdoNodoActual.getHijoDerecho());
            if(alturaPorDerecha>alturaPorIzquierda){
                return rotacionDobleDerecha(nodoActual);
            }
            return rotacionSimpleDerecha(nodoActual);
            
        }else if(diferencia<-DIFERENCE_MAX){ //la rama derecha sobrepasa de lo establecido
            //rotacion a izquierda
            
            NodoBinario<K,V> nodoHijoDerechoNodoActual=nodoActual.getHijoDerecho();
            alturaPorIzquierda=altura(nodoHijoDerechoNodoActual.getHijoIzquierdo());
            alturaPorDerecha=altura(nodoHijoDerechoNodoActual.getHijoDerecho());
            if(alturaPorIzquierda>alturaPorDerecha){
                return rotacionDobleIzquierda(nodoActual);
            }
            return rotacionSimpleIzquierda(nodoActual);
        }
        return nodoActual;
    }
    
    private NodoBinario<K,V> rotacionSimpleIzquierda(NodoBinario<K,V> nodoActual){
        NodoBinario<K,V> nodoARotar=nodoActual.getHijoDerecho();
        nodoActual.setHijoDerecho(nodoARotar.getHijoIzquierdo());
        nodoARotar.setHijoIzquierdo(nodoActual);
        return nodoARotar;
    }
    
    private NodoBinario<K,V> rotacionSimpleDerecha(NodoBinario<K,V> nodoActual){
        NodoBinario<K,V> nodoARotar=nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoARotar.getHijoDerecho());
        nodoARotar.setHijoDerecho(nodoActual);
        return nodoARotar;
    }
    
    private NodoBinario<K,V> rotacionDobleIzquierda(NodoBinario<K,V> nodoActual){
        //primero: rotacion simple a derecha
        //segundo: rotacion simple a izquierda
        NodoBinario<K,V> primerNodoQueRotar=rotacionSimpleDerecha(nodoActual.getHijoDerecho());
        nodoActual.setHijoDerecho(primerNodoQueRotar);
        return rotacionSimpleIzquierda(nodoActual);
    }
    
    private NodoBinario<K,V> rotacionDobleDerecha(NodoBinario<K,V> nodoActual){
        //primero: rotacion simple a izquierda
        //segundo: rotacion simple a derecha
        NodoBinario<K,V> primerNodoQueRotar=rotacionSimpleIzquierda(nodoActual.getHijoIzquierdo());
        nodoActual.setHijoIzquierdo(primerNodoQueRotar);
        return rotacionSimpleDerecha(nodoActual);
    }
    
    @Override
    public V eliminar(K claveAEliminar) throws ExceptionClaveNoExiste {
        if(claveAEliminar==null){
            throw new IllegalArgumentException("Clave a eliminar no pude ser nula");
        }
        V valorARetornar=this.buscar(claveAEliminar);
        if(valorARetornar==null){
            throw new IllegalArgumentException("Clave no encontrada");
        }
        this.raiz=eliminar(this.raiz,claveAEliminar);
        return valorARetornar;
    }
    
    private NodoBinario<K,V> eliminar(NodoBinario<K,V> nodoActual,K claveAEliminar){
        K claveActual=nodoActual.getClave();
        if(claveAEliminar.compareTo(claveActual)>0){
            //puede que me devuelva el mismo que era antes
            NodoBinario<K,V> posibleNuevoHijoDerecho=eliminar(nodoActual.getHijoDerecho(),claveAEliminar);
            nodoActual.setHijoDerecho(posibleNuevoHijoDerecho);
            return balancear(nodoActual);
        }
        if(claveAEliminar.compareTo(claveActual)<0){
            //puede que me devuelva el mismo que era antes
            NodoBinario<K,V> posibleNuevoHijoIzquierdo=eliminar(nodoActual.getHijoIzquierdo(),claveAEliminar);
            nodoActual.setHijoIzquierdo(posibleNuevoHijoIzquierdo);
            return balancear(nodoActual);
        }
        //llego a encontrarlo
        //caso1; esHoja
        if(nodoActual.esHoja()){
            return NodoBinario.nodoVacio();
        }
        //caso2 tenga un hijo
        //caso2.1 tiene hijo izq
        if(!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()){
            return balancear(nodoActual.getHijoIzquierdo());
        }
        //caso2.2 tiene hijo der
        if(nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()){
            return balancear(nodoActual.getHijoDerecho());
        }
        //caso 3: tiene dos hijos
        //buscamos su nodo izquierdo para reemplazar
        NodoBinario<K,V> nodoReemplazo=this.buscarNodoSucesor(nodoActual.getHijoDerecho());
        // este nodo puede ser caso 1 hoja o caso 2 tiene solo hijo derecho
        NodoBinario<K,V> posibleNuevoHijoDerecho=eliminar(nodoActual.getHijoDerecho(),nodoReemplazo.getClave());
        //exite dos formas : eliminar el nodo o reemplazar los datos;
        //reeplazar dato
        nodoActual.setHijoDerecho(posibleNuevoHijoDerecho);
        nodoActual.setClave(nodoReemplazo.getClave());
        nodoActual.setValor(nodoReemplazo.getValor());
        return balancear(nodoActual);
        
    }
    
    private NodoBinario<K,V> buscarNodoSucesor(NodoBinario<K,V> nodoActual){
        NodoBinario<K,V> nodoAnterior=null;
        while(!NodoBinario.esNodoVacio(nodoActual)){
            nodoAnterior=nodoActual;
            nodoActual=nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior;
    }
    
    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido=new ArrayList<>();
        if(!this.esArbolVacio()){
            Queue <NodoBinario<K,V>> colaDeNodos=new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while(!colaDeNodos.isEmpty()){
                NodoBinario<K,V> nodoActual=colaDeNodos.poll();
                recorrido.add(nodoActual.getClave());
                if(!nodoActual.esVacioHijoIzquierdo()){
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if(!nodoActual.esVacioHijoDerecho()){
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
            }
        }
        return recorrido;
    }
}
