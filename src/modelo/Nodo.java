package modelo;


public class Nodo <T> {

  public T objeto;
  public Nodo<T> siguiente = null;
  public Nodo<T> anterior = null;
    
  
  @Override
  public String toString(){
    return objeto.toString();
  }
  
  public T getData(){
    return objeto;
  }
}
