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
    String respuesta;
    
    public Pregunta(){
        estado="libre";
    }
    
    public String getRespuesta(){
        return respuesta;
    }
    
    public void setRespuesta(String respuesta){
        this.respuesta=respuesta;
    }
    
    public void setPregunta(String pregunta){
        this.pregunta=pregunta;
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
