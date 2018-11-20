/*
Projeto Esamc
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Conexao {

   static Connection conn = null;
   private static Statement stm = null;
   private static final PreparedStatement Pst = null;
   static ResultSet rs = null;
   private static final String DRIVER = "org.postgresql.Driver";
   private static final String URL = "jdbc:postgresql://localhost:5432/test";
   private static final String USER = "postgres";
   private static final String SENHA = "1234";
   
   public static Connection getConexao(){
 
       if (conn == null){
           try {
               Class.forName(DRIVER);
               conn = DriverManager.getConnection(URL, USER,SENHA);
           } catch (ClassNotFoundException ex) {
               System.out.println("ERRO!!");
               
           } catch (SQLException ex) {
               System.out.println("ERRO ao Conectar!!");
               Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
           }
       
       }
       return conn; 
 //      try {
 //          System.setProperty("jdbc.Drivers", DRIVER);
//           conn = DriverManager.getConnection(URL, USER, SENHA);
           //JOptionPane.showMessageDialog(null, "Conectado!");
//       } catch (SQLException ex) {
 //          JOptionPane.showMessageDialog(null, "Erro não está conectado!\n" +ex.getMessage());
//       }
   
   }
    public static PreparedStatement getPreparedStatement(String sql){
        // testo se a conexão já foi criada
        if (conn == null){
            // cria a conexão
            conn = getConexao();
        }
        try {
            // retorna um objeto java.sql.PreparedStatement
            return conn.prepareStatement(sql);
        } catch (SQLException e){
            System.out.println("Erro de sql: "+
                    e.getMessage());
        }
        return null;
    }
   public void desconecta(){
       try {
           conn.close();
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão");
       }
   
   }
   public void executaSql(String sql){
       try {
           stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
           rs = stm.executeQuery(sql);
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao consultar!!"+ex);
       }
   } 
}
 