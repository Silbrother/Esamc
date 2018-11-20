
package control;

import static control.Conexao.rs;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.model;

public class ControleCadastro {
 
    Conexao conexao = new Conexao();
    model mod = new model();
    
    public void Salvar(model mod){
    Conexao.getConexao();
        try {
            PreparedStatement pst = Conexao.conn.prepareStatement("insert into funcionarios (Nome, Rg, Email,foto) values (?,?,?,?)");
            pst.setString(1, mod.getNome());
            pst.setString(2, mod.getRg());
            pst.setString(3, mod.getEmail());
            pst.setBytes(4, mod.getFoto());
            pst.executeUpdate();
          
            JOptionPane.showMessageDialog(null, "Dados Inseridos!");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro! Dados não Inseridos!"+ex);
         }   
    // conexao.desconecta();
    }
   public void consulta (model model){
 /*   conexao.getConexao();
    model mod = null;
    String sql = "SELECT id, imagem from test where id=?";
    PreparedStatement pst = Conexao.getPreparedStatement(sql);
    
    conexao.executaSql("select * from funcionarios where Nome like '%"+model.getPesquisa()+"%'");
        try {
            Conexao.rs.first();
            mod.setId(conexao.rs.getInt("id"));
            mod.setNome(conexao.rs.getString("Nome"));
            mod.setRg(conexao.rs.getString("Cpf"));
            mod.setEmail(conexao.rs.getString("Telefone"));
            mod.setFoto(conexao.rs.getBytes("foto"));
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro!!"+ex);
        }      
    conexao.desconecta();
    return model; */ 
   }

    /**
     *
     * @param id
     * @return
     */
    public model Buscar(Integer id) {
        model retorno = null;
        String sql = "SELECT id, foto from funcionarios where id=? ";
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                retorno = new model();
                retorno.setId(rs.getInt("id"));
                retorno.setFoto(rs.getBytes("foto"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            retorno = null;
        }
        return retorno;

    }

    public model Pesquisa(model mod) {
              conexao.executaSql("SELECT * from funcionarios where  nome like '%" + mod.getPesquisa() + "%' ");
               try {
                  conexao.rs.next();
                mod.setId(conexao.rs.getInt("id"));
                mod.setNome(conexao.rs.getString("nome"));
                mod.setRg(conexao.rs.getString("rg"));
                mod.setEmail(conexao.rs.getString("email"));
                mod.setFoto(conexao.rs.getBytes("foto"));
               
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERRO!!");
                
}   
   //     conexao.desconecta();
        return mod;

    }
}