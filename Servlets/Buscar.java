/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.Entrada;
import Persistencia.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Delfina
 */
@WebServlet(name = "Buscar", urlPatterns = {"/Buscar"})
public class Buscar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Controladora control = new Controladora();

            int idBuscado = (int)(request.getAttribute("idB"));
       try {
            
            
            Entrada entrada = control.traerEntrada(idBuscado);
            
            int id = entrada.getId_entrada();
            String juego = entrada.getJuego().getNombre();
            Date fecha = entrada.getDia();
            int hora=entrada.getHora();
            String nombre=entrada.getCliente();
            int edad =entrada.getEdad();
            
            request.setAttribute("id",id);
            request.setAttribute("juego",juego );
            request.setAttribute("fecha", fecha);
            request.setAttribute("hora",hora );
            request.setAttribute("nombre", nombre);
            request.setAttribute("edad",edad );
            
            response.sendRedirect("Consultas.jsp");
  
        } catch (NonexistentEntityException ex) {
             request.getSession().setAttribute("id","No exite la entrada");
            response.sendRedirect("Consultas.jsp");
        }
        
          response.sendRedirect("Consultas.jsp");
        
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doTrace(req, resp); //To change body of generated methods, choose Tools | Templates.
        
     
        
    }

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            Controladora control = new Controladora();
            int idBuscado = Integer.parseInt(request.getParameter("idB"));
            
        try {
            Entrada entrada = control.traerEntrada(idBuscado);
            
            int id = entrada.getId_entrada();
            String juego = entrada.getJuego().getNombre();
            Date fecha = entrada.getDia();
            int hora=entrada.getHora();
            String nombre=entrada.getCliente();
            int edad =entrada.getEdad();

            request.getSession().setAttribute("id",id);
            request.getSession().setAttribute("juego",juego );
            request.getSession().setAttribute("fecha", fecha);
            request.getSession().setAttribute("hora",hora );
            request.getSession().setAttribute("nombre", nombre);
            request.getSession().setAttribute("edad",edad );
            
            response.sendRedirect("Consultas.jsp");

  
        } catch (Exception a) {
            response.sendRedirect("Consultas.jsp");
             request.getSession().setAttribute("id","No existe la entrada con ID: "+idBuscado);
            request.getSession().setAttribute("juego","" );
            request.getSession().setAttribute("fecha", "");
            request.getSession().setAttribute("hora","" );
            request.getSession().setAttribute("nombre", "");
            request.getSession().setAttribute("edad","" );

        }
        

    }




  

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
