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
        /*ARBOL BINARIO BUSQUEDA*/
        /*
        IArbolBusqueda<Integer,String> arbol=new ArbolBinarioBusqueda<>();
        arbol.InsertarR(10,"Jon");
        arbol.InsertarR(20,"Jon2");
        arbol.InsertarR(30,"Jon");
        arbol.InsertarR(25,"Jon");
        arbol.InsertarR(28,"Jon");
        arbol.InsertarR(5,"Jon");
        arbol.InsertarR(8,"Jon");
        arbol.InsertarR(39,"Jon");
        System.out.println(arbol);
        System.out.println("RECORRIDO POR NIVELES: "+arbol.recorridoPorNiveles());
        //arbol.eliminar(20);
        System.out.println("RECORRIDO POR NIVELES: "+arbol.recorridoPorNiveles());
        System.out.println("CANTIDAD DE NODOS DER: "+arbol.contarNodosConHijosDerechoR());
        System.out.println("CANTIDAD DE NODOS DER: "+arbol.contarNodosConHijosDerecho());
        System.out.println("CANTIDAD DE NODOS IZQ: "+arbol.contarNodosConHijosIzquierdo());
        System.out.println("CANTIDAD DE NODOS IZQ: "+arbol.contarNodosConHijosIzquierdoR());
        System.out.println("ALTURA: "+arbol.altura());
        System.out.println("CANTIDAD DE HIJOS DER NIVEL 1: "+arbol.cantHijosDerechosNivel(1));
        System.out.println("CANTIDAD DE HIJOS DER NIVEL 0: "+arbol.cantHijosDerechosNivel(0));
        System.out.println("CANTIDAD DE HIJOS DER NIVEL 2: "+arbol.cantHijosDerechosNivel(2));
        */
        /*
        AVL
        */
        ArbolBinarioBusqueda<Integer,String> AB=new AVL<>();
        AB.Insertar(40, "JON");
        
        AB.Insertar(50, "JON");
        AB.Insertar(30, "JON");
        AB.Insertar(60, "JON");
        AB.Insertar(20, "JON");
        AB.Insertar(25, "JON");
        
        
        System.out.println("RECORRIDO POR NIVELES: "+AB.recorridoPorNiveles());
        System.out.println("ALTURA: "+AB.altura());
        AB.eliminar(30);
        System.out.println("RECORRIDO POR NIVELES: "+AB.recorridoPorNiveles());
        AB.eliminar(50);
        AB.eliminar(60);
        System.out.println("RECORRIDO POR NIVELES: "+AB.recorridoPorNiveles());
    }
    
}
