package modelo;

public class Lista <T> {

  private Nodo inicio = null;
  private Nodo cola = null;
  private int size = 0;
  
  public void add (T pObjeto){
    if (this.inicio == null){
      Nodo nodoTemporal = new Nodo<T>();
      nodoTemporal.objeto = pObjeto;
      this.inicio = nodoTemporal;
      this.cola = nodoTemporal;
    }
    
    else{
      Nodo nodoTemporal = new Nodo<T>();
      nodoTemporal.objeto = pObjeto;
      this.cola.siguiente = nodoTemporal;
      nodoTemporal.anterior = cola;
      this.cola = nodoTemporal;
    }
    size++;
  }
  
  public boolean add(T pObjeto, int pPosicion){
    if (pPosicion > size){
      return false;
    }
    int indice = 1;
    Nodo puntero = this.inicio;
    while (indice < pPosicion-1){
      puntero = puntero.siguiente;
      indice++;
    }
    
    Nodo nodoTemporal = new Nodo<T>();
    nodoTemporal.objeto =pObjeto;
    nodoTemporal.siguiente = puntero.siguiente;
    nodoTemporal.anterior = nodoTemporal;
    
    puntero.siguiente = nodoTemporal;
    nodoTemporal.anterior = puntero;
    
    return true;
  }
  
  public void imprimir(){
    Nodo nodoTemporal = this.inicio;
    while (nodoTemporal != null){
        System.out.println(nodoTemporal.objeto);
        nodoTemporal = nodoTemporal.siguiente;
    }
  }
 
  public int getSize(){
    return this.size;
  }
  
  public void clear(){
    this.inicio = null;
    this.cola = null;
    size = 0;
  }
  
  public boolean remove(int pIndice){
    if (pIndice >= this.size || pIndice < 0){
      throw new IndexOutOfBoundsException("pos = " + pIndice + " no existe");
    }
    Nodo nodoPorEliminar = getNodo(pIndice);
    if (nodoPorEliminar == null){
      return false;
    }
    Nodo anterior = nodoPorEliminar.anterior;
    Nodo siguiente = nodoPorEliminar.siguiente;
    
    anterior.siguiente = siguiente;
    if (siguiente != null){
      siguiente.anterior = nodoPorEliminar.anterior;
    }
    nodoPorEliminar = null;
    return true;
  }
  
  public boolean remove (T pObjeto){
    int indice = buscar(pObjeto);
    if (indice == -1){
      return false;
    }
    return remove(indice);
  }
  
  private Nodo getNodo(int pIndice){
    if (pIndice > this.size){
      throw new IndexOutOfBoundsException();
    }
    Nodo nodoTemporal = inicio;
    for (int contador = 0; contador <= pIndice; contador++){
      nodoTemporal = nodoTemporal.siguiente;
    }
    return nodoTemporal;
  }
  
  public int buscar(T pObjeto){
    if (pObjeto == null){
      return -1;
    }
    Nodo actual = inicio;
    int indice = -1;
    while (actual.siguiente != null){
      ++indice;
      actual = actual.siguiente;
      if (pObjeto.equals(actual.objeto)){
        return indice;
      }
    }
    return -1;
  }
}
