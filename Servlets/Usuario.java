/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Delfina
 */
@WebServlet(name = "Usuario", urlPatterns = {"/Usuario"})
public class Usuario extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String status = "...";
        
        Controladora control = new Controladora();
        int id = Integer.parseInt(request.getParameter("id"));
        int contraseña = Integer.parseInt(request.getParameter("contra"));
        
        request.getSession().setAttribute("txtErrorIn",status);
        request.getSession().setAttribute("id", id);
        request.getSession().setAttribute("contra", contraseña);
        
        Boolean ok = control.validarEmpleado(id, contraseña);
        
        
        if (ok){response.sendRedirect("Entradas.jsp");}
        else{
            status = "¡Error! El ID y/o contraseña son incorrectos.";
            request.getSession().setAttribute("txtErrorIn", status);
            response.sendRedirect("index.jsp");
            }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp); 
        
     
        
        
    }

    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
