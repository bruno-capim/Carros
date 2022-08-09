
package classesJava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class CarrosDAO {
    String sql;
    Connection conexao;
    PreparedStatement ps;
    ResultSet rs;
    
    public void mensagem(String msg){
        FacesContext.getCurrentInstance().addMessage
                        (null, new FacesMessage(FacesMessage.SEVERITY_INFO, "",msg));
    }
    
    public void salvar(Carros carro){
        try {
            conexao = ConectaBD.getConexao();
            sql = "insert into tbCarros (marca, modelo, cor, ano) values (?,?,?,?)";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, carro.getMarca());
            ps.setString(2, carro.getModelo());
            ps.setString(3, carro.getCor());
            ps.setInt(4, carro.getAno());
            ps.setInt(4, carro.getIdCarro());
            ps.execute();
            ConectaBD.fechaConexao();
            mensagem("Carro cadastrado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(CarrosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            mensagem("Erro ao cadastrar!");
            
        }
    }
    
    public void editar(Carros carro){
        try {
            conexao = ConectaBD.getConexao();
            sql = "update tbCarros set marca = tbid ?, modelo = ?, cor = ?, ano = ? where idCarro = tbid ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, carro.getMarca());
            ps.setString(2, carro.getModelo());
            ps.setString(3, carro.getCor());
            ps.setInt(4, carro.getAno());
            ps.setInt(4, carro.getIdCarro());
            ps.execute();
            ConectaBD.fechaConexao();
            mensagem("Carro alterado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(CarrosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            mensagem("Erro ao editar");
        }
    }
    
    public void excluir(int id){
        try {
            conexao = ConectaBD.getConexao();
            sql = "delete from tbCarros where idCarro = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ConectaBD.fechaConexao();
            mensagem("Carro excluído com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(CarrosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            mensagem("Erro ao excluir!");
        }
    }
    
    public List<Carros> listar(){
        try {
            conexao = ConectaBD.getConexao();
            sql = "select * from tbCarros";
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            List<Carros> listaCarros = new ArrayList<>();
            while (rs.next()){
                Carros car = new Carros();
                car.setIdCarro(rs.getInt("idCarro"));
                car.setMarca(rs.getString("Marca"));
                car.setModelo(rs.getString("Modelo"));
                car.setCor(rs.getString("Cor"));
                car.setAno(rs.getInt("Ano"));
                listaCarros.add(car);
            }
            return listaCarros;
        } catch (SQLException ex) {
            Logger.getLogger(CarrosDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }
    }
}
