/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vladimir
 */
public class ClosingServer extends Thread {
    
    ServerSocket ss;
    Server s;
    Boolean end = false;

    public ClosingServer(ServerSocket ss, Server s) {
        this.ss = ss;
        this.s = s;
    }

    @Override
    public void run() {
       
        while(!end){
         
            if(s.isInterrupted()){
            try {
                ss.close();
                end = true;
                
            } catch (IOException ex) {
                Logger.getLogger(ClosingServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            }
        
        
        
        }
        
        
        
        
    }
    
    
    


    
}
