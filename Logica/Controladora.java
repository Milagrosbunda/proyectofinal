
package Logica;

import Persistencia.ControladoraPersistencia;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Delfina
 */
public class Controladora {
    
    ControladoraPersistencia controlP = new ControladoraPersistencia();
    
      public void crearEntrada(Entrada entrada){
        
        controlP.crearEntrada(entrada);
    }
       public void crearEmpleado(Empleado empleado){
        
        controlP.crearEmpleado(empleado);
    }
       
        public void crearJuego(Juego juego){
        
        controlP.crearJuego(juego);
    }
        
      
                
    public List<Empleado> traerEmpleados() {
        List<Empleado> resultado = controlP.traerEmpleados();
        return resultado;
    }
    
     public List<Juego> traerJuegos() {

        List<Juego> resultado = controlP.traerJuegos();
        return resultado;
    }
     
     public List<Entrada> traerEntradas() {

        List<Entrada> resultado = controlP.traerEntradas();
        return resultado;
    }
    
    
    public Entrada traerEntrada(int id) throws NonexistentEntityException{
     
         Entrada entrada = controlP.traerEntrada(id);
         return entrada;
    }
    
     public Juego traerJuego(int id) throws NonexistentEntityException{
     
         Juego juego = controlP.traerJuego(id);
         return juego;
    }
     
      public Empleado traerEmpleado(int id) throws NonexistentEntityException{
     
         Empleado empleado = controlP.traerEmpleado(id);
         return empleado;
    }
    
      public void modEntrada( Entrada entrada ){
      
        controlP.modEntrada(entrada);

      }
      
    public void eliminarEntrada (int id) throws NonexistentEntityException{ 
        
        controlP.eliminarEntrada(id);
    
    }
    
    public Boolean validarEmpleado(int ID_emple, int contra){
    
        Boolean ok=controlP.validarEmpleado(ID_emple, contra);
        return ok;

}
    public Date resetHora (Date date){
    
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        
        return calendar.getTime();
    }
    
    public Date resetMes (Date date){
    
        Calendar calendar = Calendar.getInstance();
        
         int mesOk = date.getMonth()-1;
         date.setMonth(mesOk);
         if(mesOk==12){
         date.setYear(date.getYear()+1);}

        return date;
    }
    
}


