/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.*;
import java.io.*;

/**
 *
 * @author Jesus
 */
public class ServerThread extends Thread {
    
    private Socket          socket   = null;
    private ServidorGUI    server   = null;
    private int             ID       = -1;
    private DataInputStream streamIn =  null;
    private DataOutputStream streamOu =  null;
    int v=0;
    boolean multi=true;
    
       public ServerThread(ServidorGUI _server, Socket _socket)
   {  
       server = _server;  socket = _socket;  
       ID = socket.getPort();
   }
       
       public void run()
   {  
     System.out.println("Server Thread " + ID + " running.");
     while (true)
      {  
          try
         {  
            String valor=streamIn.readUTF();
            System.out.println("[s] lo que manda el cliente "+getName()+" : "+valor);
          
            if(valor.equals("hola")){
                streamOu.writeUTF("[s] del servidor manda al cliente :" +getName()+" en respuesta de hola");
                streamOu.flush();
                multi=true;
           }else{
                streamOu.writeUTF("[s]: Recibido");
                streamOu.flush();
            }
            if(multi){
                
                // mensaje multicast a clientes
                   String linea="[s] mensaje a todos los que estan conectados multicast!!!!"+v++;
                   //Creamos el buffer a enviar
                   byte [] buffer = linea.getBytes ();
                   //Pasamos los datos al datagrama
                   server.getDgp().setData (buffer);
                   //Establecemos la longitud
                   server.getDgp().setLength (buffer.length);
                   //Y por último enviamos:
                   server.getSmc().send (server.getDgp());
                   multi=false;
                
            }

         }catch(IOException ioe) {  
            // ioe.printStackTrace();
        }  
                       
      }
   }
       
          public void open() throws IOException
   {  
       streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
       streamOu = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
       streamOu.flush();
  }
   
   
   public void close() throws IOException
   {  
       if (socket != null)   
           socket.close();
      
       if (streamIn != null)  
           streamIn.close();
       
        if (streamOu!= null)  
           streamOu.close();
        
   }
       
}
