package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Fernando
 */
public class Conexao {

    //METODO RESPONSAVEL DE ESTABELECER A CONEXAO COM O BANCO DBUSUARIO
    public static Connection conector() {
        java.sql.Connection conexao = null;
        //A LINHA ABAIXO CHAMA O DRIVER 
        String driver = "com.mysql.jdbc.Driver";
        // ARMAZENANDO INFORMAÇÕES REFERENTES AO BANCO DE DADOS
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "";
        
        //ESTABELECENDO A CONEXAO COM O BANCO DE DADOS
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user, password);
            return conexao;
            
        } catch (Exception e) {
            System.out.println("erro "+e);
           return null; 
        }
    }
    
    
    
        //METODO RESPONSAVEL DE ESTABELECER A CONEXAO COM O BANCO DBMSG
        public static Connection conector2() {
            java.sql.Connection conexao = null;
            //A LINHA ABAIXO CHAMA O DRIVER 
            String driver = "com.mysql.jdbc.Driver";
            // ARMAZENANDO INFORMAÇÕES REFERENTES AO BANCO DE DADOS
            String url = "jdbc:mysql://localhost:3306/dbmsg";
            String user = "root";
            String password = "";
            
            //ESTABELECENDO A CONEXAO COM O BANCO DE DADOS
            try {
                Class.forName(driver);
                conexao = DriverManager.getConnection(url,user, password);
                return conexao;
                
            } catch (Exception e) {
                System.out.println("erro "+e);
               return null; 
            }
        
    }

}
