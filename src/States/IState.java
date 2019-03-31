
package States;

import Jarro.Jarro;

/**
 *
 * @author ferna
 */
public interface IState {

    public int getNivel();

    public void setNivel(int nivel);

    public boolean verificaSeEhRaiz();

    public int getEstadoJarro(int indice);

    public void setEstadoJarro(int indice, int water);

    public int[] getLimiteMaximoJarro();

    public int getLimiteMaximoJarro(int indice);

    public int getnJarros();

    public void setnJarros(int nJarros);
    
    public Jarro getJarrosByIndex(int index);
    
    public void setValorDeChegada(int valorDeChegada);
    
    public int getValorDeChegada();
    
    public int getHeuristica();
    
    public void setHeuristica(int heuristica);
    
    public int getfN();
    
    public void setfN(int fN);

}
