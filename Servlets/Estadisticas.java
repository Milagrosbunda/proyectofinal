/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import Logica.Entrada;
import Logica.Juego;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
@WebServlet(name = "Estadisticas", urlPatterns = {"/Estadisticas"})
public class Estadisticas extends HttpServlet {

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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    
            Controladora controlP = new Controladora();
            int juego = Integer.parseInt(request.getParameter("listaJuegos"));
            
            int totalEntradas = 0; //total de ventas para el dia
            int totalPersonas =0;   //total de empleados en el juego
            int totalEntradasxDia =0;   //total de entradas para el juego ene esa fecha
            int a=0;
            int b=0;  
            String topJuego="";
            
            
            String fecha = request.getParameter("fecha");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
            
            Date dia = new Date();
            
        try {
            dia = formato.parse(fecha);
            
        } catch (ParseException ex) {
            Logger.getLogger(Estadisticas.class.getName()).log(Level.SEVERE, null, ex);
        }
            dia= controlP.resetHora(dia);
            dia = controlP.resetMes(dia);
            if(dia.getMonth()==11){
                int añoOk = dia.getYear()+1;
                dia.setYear(añoOk);}

            List<Juego> juegos = controlP.traerJuegos();
            List<Entrada> entradas = controlP.traerEntradas();
            List <Empleado> empleados = controlP.traerEmpleados();
            
            //si elijo todos los juegos
            if(juego == 7){
                
                totalEntradas = 0;
                totalPersonas =0;
                totalEntradasxDia =0;
                
                for(int i =0;i<entradas.size();i++){
                    
                    if(controlP.resetHora(entradas.get(i).getDia()).equals(dia)){
                    
                        totalEntradas+=1;
                    }
                }
                totalPersonas =empleados.size();
                
            request.getSession().setAttribute("empleados", totalPersonas);
            request.getSession().setAttribute("topDia", totalEntradasxDia);
            request.getSession().setAttribute("topJuegoDia", totalEntradas);
                
            }
            
            //un juegp en particular
            else{
                
                for (Juego each : juegos){
                    
                    if(each.getId_juego()==juego){
                        
                        for(int i =0;i<entradas.size();i++){   //recorro toda entrada
                            
                            if((juego == entradas.get(i).getJuego().getId_juego()) &
                                    (controlP.resetHora(entradas.get(i).getDia()).equals(dia))
                                    )
                                    {   //si matcheo juego y dia
                                totalEntradas++;
                            }
                        }
                        
                        //todo empleado en el juego
                        for(int i =0;i<empleados.size();i++){
                            
                            if((empleados.get(i).getJuego().getId_juego())== juego){
                                totalPersonas++;
                            }
                        }
                        
                    }//if match nombre
                    
                }//fin for juegos
                
                for(int i =0;i<entradas.size();i++){  
                    //recorro toda entrada
                    if(controlP.resetHora(entradas.get(i).getDia()).equals(dia)){   //si  dia
                        totalEntradasxDia++;
                    }}
            }
            
            
            for (Juego eachJ : juegos){
                for (Entrada eachE : entradas){
                    if((eachE.getJuego().getId_juego()==eachJ.getId_juego())&(controlP.resetHora(eachE.getDia()).equals(dia))){
                        a+=1;
                    }}
                if (a>b){ topJuego = eachJ.getNombre();
                b=a;}
                a=0;
            }

            
            request.getSession().setAttribute("empleados", totalPersonas);
            request.getSession().setAttribute("topDia", totalEntradasxDia);
            request.getSession().setAttribute("topJuegoDia", totalEntradas);
            request.getSession().setAttribute("topJuego", topJuego);

              response.sendRedirect("Estadisticas.jsp");
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
