/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code;

/**
 *
 * @author vivia
 */
public class NodoBinario<K,V> {
    private K clave;
    private V valor;
    NodoBinario<K,V> HijoIzquierdo;
    NodoBinario<K,V> HijoDerecho;
    
    public NodoBinario(K clave,V valor){
        this.clave=clave;
        this.valor=valor;
    }

    public K getClave() {
        return clave;
    }

    public void setClave(K clave) {
        this.clave = clave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public NodoBinario<K, V> getHijoIzquierdo() {
        return HijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoBinario<K, V> HijoIzquierdo) {
        this.HijoIzquierdo = HijoIzquierdo;
    }

    public NodoBinario<K, V> getHijoDerecho() {
        return HijoDerecho;
    }

    public void setHijoDerecho(NodoBinario<K, V> HijoDerecho) {
        this.HijoDerecho = HijoDerecho;
    }
    
    public static NodoBinario nodoVacio(){
        return null;
    }
    
    public static boolean esNodoVacio(NodoBinario elNodo){
        return elNodo==NodoBinario.nodoVacio();
    }
    
    public boolean esVacioHijoIzquierdo(){
        return NodoBinario.esNodoVacio(this.HijoIzquierdo);
    }
    
    public boolean esVacioHijoDerecho(){
        return NodoBinario.esNodoVacio(this.HijoDerecho);
    }
    
    public boolean esHoja(){
        return this.esVacioHijoDerecho() && this.esVacioHijoIzquierdo();
    }
}
