/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procealmacenado;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Procealmacenado {
private static Scanner leo=new Scanner(System.in);
        private static String longitud;
        private static String nombreCam;
        private static String descripcion;
        private static int opcion;
         public static void main(String[] args) throws SQLException {
                 
                   do{
                           System.out.println("Elija una opcion:");
                           System.out.println("1.Insertar");
                           System.out.println("2.Modificar");
                           System.out.println("3.Borar");
                           System.out.println("4.Mostrar Datos");
                           System.out.println("5.Salir");
                           opcion=leo.nextInt();
                           switch (opcion) {
                        case 1:
                                insertarProcedimiento() ;
                                break;
                        case 2:
                                editarProcedimiento() ;
                                break;
                        case 3:
                                eliminarProcedimiento();
                                break;
                        case 4:
                                mostrarProcedimiento();
                                break;
                        case 5:
                                System.out.println("sali (-__-)");
                                System.exit(0);
                        }
                           
                   }while(opcion!=5);
                
        }
         
            private static void mostrarProcedimiento() throws SQLException {
             
                   Connection conectar = null;
                   ResultSet rs;
                        try{
                                // creamos la Conexion
                                conectar = conexion.conectar();
                                
                                
                                conectar.setAutoCommit(false);
                                
                                /*instanciamos el objeto callable donde mandamos a pedir la cantidad
                                 *  parametros en este caso MostrarCampo no tiene
                                 *  y se coloca como se muestra*/
                                CallableStatement prcProcedimientoAlmacenado = conectar.prepareCall("{call MostrarCampo()}");
                              rs=prcProcedimientoAlmacenado.executeQuery();
                              
                              
                           while(rs.next()){
                                  System.out.println("ID"+"->"+rs.getInt(1));
                                  System.out.println("Longitud"+"->"+rs.getString(2));
                                  System.out.println("Nombre Campo"+"->"+rs.getString(3));
                                  System.out.println("Descripcion"+"->"+rs.getString(4));
                                  
                           }
                                  
                                conectar.commit();
                                
                             
                                
                        } catch (Exception e) {
                                
                                conectar.rollback();
                                
                                e.printStackTrace();
                        } finally {
                                // cerrar la Conexion
                                conectar.close();
                        }
        }
                public static void insertarProcedimiento() throws SQLException{
                   Connection conectar = null;
        try{
                
                conectar = conexion.conectar();
        
                conectar.setAutoCommit(false);
                
                System.out.println("Introdusca la longitud");
                longitud=leo.next();
                System.out.println("Introdusca el Nombre de Campo");
                nombreCam=leo.next();
                System.out.println("Introdusca una descripcion");
                descripcion=leo.next();
                /* en este caso si mandamos a pedir tres parametros a insertar como se muestra en el ejemplo*/
                CallableStatement prcProcedimientoAlmacenado = conectar.prepareCall("{call insertarCampo(?,?,?)}");
                // cargar parametros
                prcProcedimientoAlmacenado.setString(1, longitud);
                prcProcedimientoAlmacenado.setString(2, nombreCam);
                prcProcedimientoAlmacenado.setString(3, descripcion);
              
                
                // se ejecuta el procedimiento
                prcProcedimientoAlmacenado.execute();
                conectar.commit();
                System.out.println("Insertado con exito");  
        } catch (Exception e) {
        
                conectar.rollback();
        
                e.printStackTrace();
        } finally {
                // cerrar la Conexion
                conectar.close();
        }
            }
            
            public static void eliminarProcedimiento() throws SQLException{
                  Connection conectar = null;
        try{
                conectar = conexion.conectar();
                conectar.setAutoCommit(false);
                System.out.println("Introdusca el id de la fila a eliminar");
                int n=leo.nextInt();
                CallableStatement prcProcedimientoAlmacenado = conectar.prepareCall("{call EliminarCampo(?)}");   
                prcProcedimientoAlmacenado.setInt(1, n);
                prcProcedimientoAlmacenado.execute();
                conectar.commit();
                System.out.println("eliminado con exito");     
        } catch (Exception e) {   
                conectar.rollback();
                e.printStackTrace();
        } finally {
                conectar.close();
        }    
            }
            public static void editarProcedimiento() throws SQLException{
                  Connection conectar = null;
        try{  
                conectar = conexion.conectar();
                conectar.setAutoCommit(false); 
                System.out.println("Introdusca el Id a modificar");
                int n=leo.nextInt();
                System.out.println("Introdusca una nueva Longitud");
                longitud=leo.next();
                System.out.println("Introdusca un nuevo Campo");
                nombreCam=leo.next();
                System.out.println("Introdusca una descripcion");
                descripcion=leo.next();
                CallableStatement prcProcedimientoAlmacenado = conectar.prepareCall("{call ActualizarCampo(?,?,?,?)}");
                prcProcedimientoAlmacenado.setInt(1, n);
                prcProcedimientoAlmacenado.setString(2, longitud);
                prcProcedimientoAlmacenado.setString(3, nombreCam);
                prcProcedimientoAlmacenado.setString(4, descripcion);
                prcProcedimientoAlmacenado.execute();
                conectar.commit();
             System.out.println("Actualizado con exito");
                
        } catch (Exception e) {
                conectar.rollback();
                e.printStackTrace();
        } finally {
                // cerrar la Conexion
                conectar.close();
        }  
            }
}
