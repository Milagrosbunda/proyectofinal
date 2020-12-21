/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Empleado;
import Logica.Entrada;
import Logica.Juego;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Delfina
 */
public class ControladoraPersistencia {
    
    EmpleadoJpaController empleadojpa = new EmpleadoJpaController();
    JuegoJpaController juegojpa = new JuegoJpaController();
    EntradaJpaController entradajpa = new EntradaJpaController();
    
    
    public void crearEntrada(Entrada nueva){
        
        entradajpa.create(nueva);
    }
    
      public void crearEmpleado(Empleado empleado){
        
        empleadojpa.create(empleado);
    }
      
        public void crearJuego(Juego nuevo){
        
        try{
            juegojpa.create(nuevo);}
        catch(Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    public List<Empleado> traerEmpleados() {
        List<Empleado> resultado = empleadojpa.findEmpleadoEntities();
        return resultado;
    }
    
     public List<Juego> traerJuegos() {

        List<Juego> resultado = juegojpa.findJuegoEntities();
        return resultado;
    }
     
     public List<Entrada> traerEntradas() {

        List<Entrada> resultado = entradajpa.findEntradaEntities();
        return resultado;
    }
    

    
    public Entrada traerEntrada(int id) throws NonexistentEntityException{
     
         Entrada entrada = entradajpa.findEntrada(id);
         return entrada;
    }
    
     public Juego traerJuego(int id) throws NonexistentEntityException{
     
         Juego juego = juegojpa.findJuego(id);
         return juego;
    }
     
      public Empleado traerEmpleado(int id) throws NonexistentEntityException{
     
         Empleado empleado = empleadojpa.findEmpleado(id);
         return empleado;
    }
    
      public void modEntrada( Entrada entrada ){
      
        try {
            entradajpa.edit(entrada);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }

      }
      
    public void eliminarEntrada (int id) throws NonexistentEntityException{ 
        
        entradajpa.destroy(id);
    
    }
    
     public Boolean validarEmpleado(int ID_emple, int contra){
         
        Boolean ok=false;
        
            List<Empleado> empleados = traerEmpleados();
            for (Empleado each : empleados){
                if ((each.getId_empleado()== ID_emple)&(each.getContraseña()== contra)){
                    
                    ok= true;
                } }


            
        return ok;

}
    


    
}
