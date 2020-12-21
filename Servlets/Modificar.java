package Servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Logica.Controladora;
import Logica.Entrada;
import Persistencia.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
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
@WebServlet(urlPatterns = {"/Modificar"})
public class Modificar extends HttpServlet {

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
          request.setAttribute("id","");
            request.setAttribute("juego","" );
            request.setAttribute("fecha", "");
            request.setAttribute("hora","" );
            request.setAttribute("nombre", "");
            request.setAttribute("edad","" );
         Controladora control = new Controladora();

        try {
            
             int idBuscado = Integer.parseInt(request.getParameter("idM"));
        
        String fechaS = (request.getParameter("fechaN"));
        int horaN = Integer.parseInt(request.getParameter("horaN"));
        String clienteN= (request.getParameter("clienteN"));
        int edadN = Integer.parseInt(request.getParameter("edadN"));
        
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        Date fechaN = new Date();
        try {
            fechaN = formatter.parse(fechaS);
            control.resetMes(fechaN);
            if(fechaN.getMonth()==11){fechaN.setYear(fechaN.getYear()+1);}
        } catch (ParseException ex) {
            Logger.getLogger(Modificar.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        Entrada entrada = control.traerEntrada(idBuscado);
            Boolean hora = false;
            Boolean edad = false;
            
            if(edadN<entrada.getJuego().getEdadMinima()){ 
                request.getSession().setAttribute("txtSituacion","Edad no permitida!");}
            else{edad=true;
             entrada.setEdad(edadN);
            }
            
            if((horaN<entrada.getJuego().getAbre()) |  (horaN> entrada.getJuego().getCierra())){
                request.getSession().setAttribute("txtSituacion","Horario no permitido!");}
            else{hora=true;
            entrada.setHora(horaN);
            }
            
            if(hora&edad){
            entrada.setDia(fechaN);
            entrada.setCliente(clienteN);
            
            control.modEntrada(entrada);
            
            request.getSession().setAttribute("txtSituacion","Modificacion exitosa!");
            
            
            request.getSession().setAttribute("id",entrada.getId_entrada());
            request.getSession().setAttribute("juego",entrada.getJuego().getNombre() );
            request.getSession().setAttribute("fecha", entrada.getDia());
            request.getSession().setAttribute("hora",entrada.getHora() );
            request.getSession().setAttribute("nombre", entrada.getCliente());
            request.getSession().setAttribute("edad",entrada.getEdad() );
            }

        } catch (NonexistentEntityException | NumberFormatException a) {
            request.getSession().setAttribute("id","No exite la entrada");
        }
        
        response.sendRedirect("Consultas.jsp");
            
           

        
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
