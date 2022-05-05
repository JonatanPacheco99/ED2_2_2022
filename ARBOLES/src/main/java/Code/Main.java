/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Code;

/**
 *
 * @author vivia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ExceptionClaveNoExiste {
        // TODO code application logic here
        IArbolBusqueda<Integer,String> arbol=new ArbolBinarioBusqueda<>();
        arbol.Insertar(10,"Jon");
        arbol.Insertar(20,"Jon2");
        arbol.Insertar(30,"Jon");
        arbol.Insertar(25,"Jon");
        arbol.Insertar(28,"Jon");
        arbol.Insertar(5,"Jon");
        arbol.Insertar(8,"Jon");
        arbol.Insertar(39,"Jon");
        System.out.println(arbol);
        System.out.println("RECORRIDO POR NIVELES: "+arbol.recorridoPorNiveles());
        //arbol.eliminar(20);
        System.out.println("RECORRIDO POR NIVELES: "+arbol.recorridoPorNiveles());
        System.out.println("CANTIDAD DE NODOS DER: "+arbol.contarNodosConHijosDerechoR());
        System.out.println("CANTIDAD DE NODOS DER: "+arbol.contarNodosConHijosDerecho());
        System.out.println("CANTIDAD DE NODOS IZQ: "+arbol.contarNodosConHijosIzquierdo());
        System.out.println("CANTIDAD DE NODOS IZQ: "+arbol.contarNodosConHijosIzquierdoR());
    }
    
}
