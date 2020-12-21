/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.Entrada;
import Logica.Juego;
import Persistencia.exceptions.NonexistentEntityException;
import java.io.IOException;
import static java.lang.System.out;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "generarEntrada", urlPatterns = {"/generarEntrada"})
public class generarEntrada extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getSession().setAttribute("txtIDE","Situacion:  ");
            try {
                Controladora control = new Controladora();
                
                int opcion = Integer.parseInt( request.getParameter("listaJuegos"));
                
                //
                String fecha = request.getParameter("fecha");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                Date fechaN = format.parse(fecha);
                control.resetMes(fechaN);
                if(fechaN.getMonth()==11){
                    fechaN.setYear(fechaN.getYear()+1);}
                
                int hora =Integer.parseInt(request.getParameter("hora"));
                String nombre =request.getParameter("cliente");
                int edad =Integer.parseInt(request.getParameter("edad"));
                
                Entrada nueva = new Entrada();
                nueva.setDia(fechaN);
                nueva.setCliente(nombre);
                nueva.setEdad(edad);
                nueva.setHora(hora);
                
                try {
                    nueva.setJuego(control.traerJuego(opcion));
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(generarEntrada.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Boolean horaok = false;
                Boolean edadok = false;
                
                List<Juego> juegos = control.traerJuegos();
                
                for (Juego each : juegos){
                    if((each.getId_juego()) == nueva.juego.getId_juego()){
                        
                        if (nueva.getHora()>= each.getAbre() & nueva.getHora()<=each.getCierra()){
                            horaok=true;} 
                        else{request.getSession().setAttribute("txtError","Error! Horario no habilitado");
                        }
                        
                        if (nueva.getEdad()>= each.getEdadMinima()){
                            edadok=true;}
                        else{request.getSession().setAttribute("txtError","Error! Edad no permitida");} 
                    }
                }
                
                if(horaok&edadok){
                    control.crearEntrada(nueva);
                   request.getSession().setAttribute("txtError","          Se pudo generar\n la entrada con exito!");
                   request.getSession().setAttribute("txtIDE","El ID de la ultima entrada vendida es: "+Integer.toString(nueva.getId_entrada()));
                }
                response.sendRedirect("Entradas.jsp");
                
            } catch (ParseException ex) {
                Logger.getLogger(generarEntrada.class.getName()).log(Level.SEVERE, null, ex);
            }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
