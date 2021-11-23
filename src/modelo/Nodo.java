package modelo;

/**
*Declaración de la clase Nodo: Elementos que componen una lista
*/
public class Nodo <T> {

  //Atributos de la clase
  public T objeto;
  public Nodo<T> siguiente = null;
  public Nodo<T> anterior = null;
    
  /**
  * Método toString: llama a los métodos get de los atributos para colocarlos en una misma cadena de caracteres.
  * 
  * @return mensaje: String que posee los valores de cada atributo.
  */
  @Override
  public String toString(){
    return objeto.toString();
  }
  
  /**
  *Método getData: Retorna el objeto del nodo
  *@return objeto: Un objeto del tipo que se declare la lista
  */
  public T getData(){
    return objeto;
  }
}
