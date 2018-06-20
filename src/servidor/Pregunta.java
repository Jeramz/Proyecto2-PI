/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author jesus
 */
public class Pregunta {
    String estado;
    String pregunta;
    
    public Pregunta(){
        estado="libre";
    }
    
    public void setPregunta(String pregunta){
    }
    
    public String getPregunta(){
        return pregunta;
    }
    
    public void setEstado(String estado){
        this.estado=estado;
    }
    
    public String getEstado(){
        return estado;
    }
}
