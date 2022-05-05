/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code;

import java.util.*;
import java.util.List;

/**
 *
 * @author vivia
 * @param <K>
 */
public class ArbolBinarioBusqueda<K extends Comparable<K>,V> implements IArbolBusqueda<K,V> {
    
    protected NodoBinario<K,V> raiz;

    @Override
    public void Insertar(K claveAInsertar, V valorAInsertar) throws NullPointerException {
        if(claveAInsertar==null){
            throw new NullPointerException("No se permite clave nulo");
        }
        if(valorAInsertar==null){
            throw new NullPointerException("No se permite valor nulo");
        }
        if(this.esArbolVacio()){
            this.raiz=new NodoBinario<>(claveAInsertar,valorAInsertar);
        }
        NodoBinario<K,V> nodoAnterior=NodoBinario.nodoVacio();
        NodoBinario<K,V> nodoActual=this.raiz;
        while(!NodoBinario.esNodoVacio(nodoActual)){
            K claveActual=nodoActual.getClave();
            nodoAnterior=nodoActual;
            if(claveAInsertar.compareTo(claveActual)<0){
                nodoActual=nodoActual.getHijoIzquierdo();
            }else if(claveAInsertar.compareTo(claveActual)>0){
                nodoActual=nodoActual.getHijoDerecho();
            }else{
                nodoActual.setValor(valorAInsertar);
                return;
            }
        }
        K claveAnterior=nodoAnterior.getClave();
        NodoBinario<K,V> nuevoNodo=new NodoBinario<>(claveAInsertar,valorAInsertar);
        if(claveAInsertar.compareTo(claveAnterior)<0){
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        }else{
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
    }
    
    @Override
    public void InsertarR(K claveAInsertar,V valorAInsertar) throws NullPointerException{
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
            return nodoActual;
        }
        if(claveAInsertar.compareTo(claveActual)<0){    //clave a insertar es menor
            NodoBinario<K,V> supuestoHijoIzquierdo=insertar(nodoActual.getHijoIzquierdo(),claveAInsertar,valorAInsertar);
            nodoActual.setHijoIzquierdo(supuestoHijoIzquierdo);
            return nodoActual;
        }
        //encontro el nodo 
        //solo debemos reemplazar su valor
        nodoActual.setValor(valorAInsertar);
        return nodoActual;
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
            return nodoActual;
        }
        if(claveAEliminar.compareTo(claveActual)<0){
            //puede que me devuelva el mismo que era antes
            NodoBinario<K,V> posibleNuevoHijoIzquierdo=eliminar(nodoActual.getHijoIzquierdo(),claveAEliminar);
            nodoActual.setHijoIzquierdo(posibleNuevoHijoIzquierdo);
            return nodoActual;
        }
        //llego a encontrarlo
        //caso1; esHoja
        if(nodoActual.esHoja()){
            return NodoBinario.nodoVacio();
        }
        //caso2 tenga un hijo
        //caso2.1 tiene hijo izq
        if(!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()){
            return nodoActual.getHijoIzquierdo();
        }
        //caso2.2 tiene hijo der
        if(nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()){
            return nodoActual.getHijoDerecho();
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
        return nodoActual;
        
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
    public K minimo(){
        if(this.esArbolVacio()){
            return null;
        }
        NodoBinario<K,V> nodoActual=this.raiz;
        NodoBinario<K,V> nodoAnterior=NodoBinario.nodoVacio();
        while(!NodoBinario.esNodoVacio(nodoActual)){
            nodoAnterior=nodoActual;
            nodoActual=nodoActual.getHijoIzquierdo();
        }
        return nodoAnterior.getClave();
    }
    
    @Override
    public K maximo(){
        if(this.esArbolVacio()){
            return null;
        }
        NodoBinario<K,V> nodoActual=this.raiz;
        NodoBinario<K,V> nodoAnterior=NodoBinario.nodoVacio();
        while(!NodoBinario.esNodoVacio(nodoActual)){
            nodoAnterior=nodoActual;
            nodoActual=nodoActual.getHijoDerecho();
        }
        return nodoAnterior.getClave();
    }
    @Override
    public int contarNodosConHijosIzquierdo(){
        int cant=0;
        if(!this.esArbolVacio()){
            Queue <NodoBinario<K,V>> colaDeNodos=new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while(!colaDeNodos.isEmpty()){
                NodoBinario<K,V> nodoActual=colaDeNodos.poll();
                if(!nodoActual.esVacioHijoIzquierdo()){
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                    cant++;
                }
                if(!nodoActual.esVacioHijoDerecho()){
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                }
            }
        }
        return cant;
    }
    @Override
    public int contarNodosConHijosIzquierdoR(){
        return contarNodosConHijosIzquierdo(this.raiz);
    }
    
    private int contarNodosConHijosIzquierdo(NodoBinario<K,V> nodoActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantNodoSubIz=contarNodosConHijosIzquierdo(nodoActual.getHijoIzquierdo());
        int cantNodoSubDer=contarNodosConHijosIzquierdo(nodoActual.getHijoDerecho());
        if(!nodoActual.esVacioHijoIzquierdo()){
            return cantNodoSubIz + cantNodoSubDer+1;
        }
        return cantNodoSubIz + cantNodoSubDer;
    }
    
    @Override
    public int contarNodosConHijosDerecho(){
        int cant=0;
        if(!this.esArbolVacio()){
            Queue <NodoBinario<K,V>> colaDeNodos=new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while(!colaDeNodos.isEmpty()){
                NodoBinario<K,V> nodoActual=colaDeNodos.poll();
                if(!nodoActual.esVacioHijoIzquierdo()){
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if(!nodoActual.esVacioHijoDerecho()){
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                    cant++;
                }
            }
        }
        return cant;
    }
    @Override
    public int contarNodosConHijosDerechoR(){
        return contarNodosConHijosDerecho(this.raiz);
    }
    private int contarNodosConHijosDerecho(NodoBinario<K,V> nodoActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int cantNodoSubIz=contarNodosConHijosDerecho(nodoActual.getHijoIzquierdo());
        int cantNodoSubDer=contarNodosConHijosDerecho(nodoActual.getHijoDerecho());
        if(!nodoActual.esVacioHijoDerecho()){
            return cantNodoSubIz + cantNodoSubDer+1;
        }
        return cantNodoSubIz + cantNodoSubDer;
    }
    @Override
    public V buscar(K claveABuscar) {
        if(claveABuscar==null){
            throw new IllegalArgumentException("Clave a eliminar no pude ser nula");
        }
        if(this.esArbolVacio()){
            return null;
        }
        NodoBinario<K,V> nodoActual=this.raiz;
        while(!NodoBinario.esNodoVacio(nodoActual)){
            K claveActual=nodoActual.getClave();
            
            if(claveABuscar.compareTo(claveActual)==0){
                return nodoActual.getValor();
            }else if(claveABuscar.compareTo(claveActual)<0){
                nodoActual=nodoActual.getHijoIzquierdo();
            }else{
                nodoActual=nodoActual.getHijoDerecho();
            }
        }
        return null; //no lo encontro
    }

    @Override
    public boolean contiene(K claveAVuscar) {
        return this.buscar(claveAVuscar)!=null;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }
    
    public int altura(NodoBinario<K,V> nodoActual){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        int alturaPorDerecha=altura(nodoActual.getHijoDerecho());
        int alturaPorIzquierda=altura(nodoActual.getHijoIzquierdo());
        if(alturaPorDerecha>alturaPorIzquierda){
            return alturaPorDerecha+1;
        }
        return alturaPorIzquierda+1;
    }

    @Override
    public int cantHojas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }
    /**
    * nivel: se cuenta desde 0
    * altura: desde 1
    */
    
    @Override
    public int cantHijosDerechosNivel(int nivel){
        /*if(nivel>this.altura()-1){
            return 0;
        }
        return cantHijosDerechosNivel(this.raiz,nivel);*/
        int cant=0;
        int cont=0;
        if(!this.esArbolVacio()){
            Queue <NodoBinario<K,V>> colaDeNodos=new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            while(!colaDeNodos.isEmpty()){
                NodoBinario<K,V> nodoActual=colaDeNodos.poll();
                
                if(!nodoActual.esVacioHijoIzquierdo()){
                    colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                }
                if(!nodoActual.esVacioHijoDerecho()){
                    colaDeNodos.offer(nodoActual.getHijoDerecho());
                    //cuenta desde ya lo que bajo el hijo derecho y lo acumula
                    if(cont>nivel){
                        cant++;
                    }
                }
                cont++;
            }
        }
        return cant;
    }
    
    private int cantHijosDerechosNivel(NodoBinario<K,V> nodoActual,int nivel){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return 0;
        }
        //encontre el nivel de donde debo buscar
        if(nivel==0){
            int cantPorIzquierda=cantHijosDerechosNivel(nodoActual.getHijoIzquierdo(),0);
            int cantPorDerecha=cantHijosDerechosNivel(nodoActual.getHijoDerecho(),0);
            //hago el trabajo donde elnodo es 0
            if(!nodoActual.esVacioHijoDerecho()){
                return cantPorIzquierda+cantPorDerecha+1;
            }
            return cantPorIzquierda+cantPorDerecha;
            
        }
        int cantPorIzquierda=cantHijosDerechosNivel(nodoActual.getHijoIzquierdo(),nivel-1);
        int cantPorDerecha=cantHijosDerechosNivel(nodoActual.getHijoDerecho(),nivel-1);
        return cantPorIzquierda+cantPorDerecha;
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
