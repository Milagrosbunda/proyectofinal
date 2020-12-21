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
@WebServlet(name = "Eliminar", urlPatterns = {"/Eliminar"})
public class Eliminar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Controladora control = new Controladora();
        try {
            int idBuscado = (int) (request.getSession().getAttribute("id"));
            
            control.eliminarEntrada(idBuscado);
            
            request.getSession().setAttribute("txtSituacion","Se eliminó con exito la entrada con el ID "+idBuscado);
            request.getSession().setAttribute("id","");
            request.getSession().setAttribute("juego","" );
            request.getSession().setAttribute("fecha", "");
            request.getSession().setAttribute("hora","" );
            request.getSession().setAttribute("nombre", "");
            request.getSession().setAttribute("edad","" );
            
        } catch (NonexistentEntityException ex) {
            request.getSession().setAttribute("id","No exite la entrada");
        }
        response.sendRedirect("Consultas.jsp");
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
