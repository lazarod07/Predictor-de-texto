package Paquete;

/**
 *
 * @author lazarod
 */
public class Nodo_Arbol {
    String letra;
    Nodo_Arbol vector [] = new Nodo_Arbol[29];
    int tam;
    Nodo_Arbol link;
    public Nodo_Arbol(String l){
        this.letra = l;
        this.tam = 0;
        link = null;
    }
}
