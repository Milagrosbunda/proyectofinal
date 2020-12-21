/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import Logica.Juego;
import Persistencia.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Delfina
 */
@WebServlet(name = "Empleados", urlPatterns = {"/Empleados"})
public class Empleados extends HttpServlet {

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
            request.getSession().setAttribute("txtID","Situacion:  ");
            int opcion = Integer.parseInt( request.getParameter("listaJuegos"));
            Juego elegido = new Juego();
        try {
            elegido = control.traerJuego(opcion);}
        catch (NonexistentEntityException ex) {
            request.getSession().setAttribute("txtErrorIn","  "+"  No se encontró el juego. ");}
            
            String nombre =request.getParameter("nombre");
            int contra =Integer.parseInt(request.getParameter("contra"));
            int contra2 =Integer.parseInt(request.getParameter("contra2"));
            
            if (contra!=contra2){request.getSession().setAttribute("txtErrorIn","  "+"Error! Las contraseñas no coinciden. ");}
            else{
                Empleado nuevo = new Empleado();
                nuevo.setJuego(elegido);
                nuevo.setNombre(nombre);
                nuevo.setContraseña(contra);
                control.crearEmpleado(nuevo);
                
                request.getSession().setAttribute("txtErrorIn","  "+"          ¡Se pudo dar de alta al empleado con exito!");
                request.getSession().setAttribute("txtID","  "+"  El ID del ultimo empleado es: "+Integer.toString(nuevo.getId_empleado()));
            }
            response.sendRedirect("Empleados.jsp");
        
            
        }
                


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
