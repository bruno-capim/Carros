/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesJava;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author rafaelcergoli
 */

@ManagedBean
@SessionScoped
public class CarrosBean {
    private Carros objCarro = new Carros();
    private List<Carros> listaCarros = new ArrayList<>();
    private CarrosDAO objCarrosDAO = new CarrosDAO();
    
    public void listar(){
        listaCarros = objCarrosDAO.listar();
    }
    
    public void adicionar(){
        objCarrosDAO.salvar(objCarro);
        objCarro = new Carros();
        listar();
    }
    
    public void editar(){
        objCarrosDAO.editar(objCarro);
        objCarro = new Carros();
        listar();
    }
    
    public void excluir(){
        objCarrosDAO.excluir(objCarro.getIdCarro());
        objCarro = new Carros();
        listar();
    }
    
    public void limpar(){
        objCarro = new Carros();
    }
    
    public void selecionar(Carros c){
        objCarro = c;
    }

    public Carros getObjCarro() {
        return objCarro;
    }

    public void setObjCarro(Carros objCarro) {
        this.objCarro = objCarro;
    }

    public List<Carros> getListaCarros() {
        return listaCarros;
    }

    public void setListaCarros(List<Carros> listaCarros) {
        this.listaCarros = listaCarros;
    }

    public CarrosDAO getObjCarrosDAO() {
        return objCarrosDAO;
    }

    public void setObjCarrosDAO(CarrosDAO objCarrosDAO) {
        this.objCarrosDAO = objCarrosDAO;
    }
    
    
}
