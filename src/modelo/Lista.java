package modelo;

/**
*Declaración de la clase Lista: Lista dinámica de carácter genérico
*/
public class Lista <T> {

  //Atributos de la clase
  private Nodo inicio = null;
  private Nodo cola = null;
  private int size = 0;
  
  /**
  *Método add: Añade un objeto al final de la lista
  *@param pObjeto: Un objeto del tipo que se declare la lista
  */
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
  
  /**
  *Método add: Añade un objeto en una posición indicada
  *@param pObjeto: Un objeto del tipo que se declare la lista
  *@param pPosicion: Índice en el cual insertar el objeto
  *@return size: Tamaño de la lista
  */
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
  
  /**
  *Método imprimir: Imprime todos los objetos de la lista
  */
  public void imprimir(){
    Nodo nodoTemporal = this.inicio;
    while (nodoTemporal != null){
        System.out.println(nodoTemporal.objeto);
        nodoTemporal = nodoTemporal.siguiente;
    }
  }
 
  /**
   * Método getSize: obtiene el tamaño de la lista
   * 
   * @return size: número entero que indica el tamaño de la lista
   */
  public int getSize(){
    return this.size;
  }
  
  
  /**
  *Método clear: Vacía la lista
  */
  public void clear(){
    this.inicio = null;
    this.cola = null;
    size = 0;
  }
  
  
  /**
  *Método remove: Elimina un elemento en la lista
  *@param pIndice: Índice en el cual eliminar el objeto
  *@return: valor booleano
  */
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
  
  /**
  *Método remove: Método sobrecargado que elimina un objeto indicado
  *@param pObjeto: Un objeto del tipo que se declare la lista
  *@return: valor booleano
  */
  public boolean remove (T pObjeto){
    int indice = buscar(pObjeto);
    if (indice == -1){
      return false;
    }
    return remove(indice);
  }
  
  /**
  *Método getNodo: Encuentra un nodo en particular
  *@param pIndice: Índice donde está el nodo
  *@return nodoTemporal: Nodo buscado
  */
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
  
  /**
  *Método buscar: Busca un objeto en particular
  *@param pObjeto: Un objeto del tipo que se declare la lista
  *@return indice: Índice donde se encuentra el objeto
  */
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
  
  /**
  *Método get: Retorna un objeto en la posición buscada
  *@param pPosicion: Índice donde buscar
  *@return nodoTemporal.objeto: Objeto dentro del nodo
  */
  public T get (int pPosicion){
    Nodo nodoTemporal = getNodo(pPosicion-1);
    return (T) nodoTemporal.objeto;
  }
}
