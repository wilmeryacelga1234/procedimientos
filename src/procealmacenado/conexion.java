/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procealmacenado;

import java.sql.Connection;

public class conexion {
    public static Connection sql;
    public static String user="root";
    public static String pas="";
              
        public static Connection conectar() throws Exception {
                try {
                        String databaseURL ="C:\\wamp\\bin\\mysql\\mysql5.6.17\\data\\procedimiento";
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        sql = java.sql.DriverManager.getConnection(databaseURL, user, pas);
                        //System.out.println("Conexion Establecida..");
                } catch (Exception e) {
                        e.printStackTrace();
                        throw new Exception(e);
                }
                return sql;
        }
    
}
