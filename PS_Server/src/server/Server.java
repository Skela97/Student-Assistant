/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import threads.ProcessRequests;
import view.form.FrmServer;



/**
 *
 * @author Cartman
 */
public class Server extends Thread{
    FrmServer frmServer;
    Socket s;
    public Server() {
        
   
    
    }

    public Server(FrmServer frm) {
        frmServer = frm;
    }

    
    @Override
    public void run() {
        try {
            
            ServerSocket serverSocket=new ServerSocket(9000);
            frmServer.started();
            JOptionPane.showMessageDialog(null, "Server is up!");
            
            ClosingServer cs = new ClosingServer(serverSocket,this);
            cs.start();
            
            while(!isInterrupted()){
               
                System.out.println("Waiting for connection...");            
                 s=serverSocket.accept();
                System.out.println("Connected!");
                handleClient(s);
                
            }
        } catch (Exception ex) {
            try {
                ex.printStackTrace();
                frmServer.stopped();
                
                s.close();
            } catch (IOException ex1) {
                
            }
            
        }
    }
    
    private void handleClient(Socket socket) throws Exception {
        ProcessRequests processRequests = new ProcessRequests(socket);
        processRequests.start();
    }

    
    
    
        
        
    

    
    
}
