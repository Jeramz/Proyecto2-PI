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
    String[] opciones;
    
    public Pregunta(){
        estado="libre";
    }
    
    public void setOpciones(String op1,String op2,String op3,String op4){
        String[] aux={op1,op2,op3,op4};
        this.opciones=aux;
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
