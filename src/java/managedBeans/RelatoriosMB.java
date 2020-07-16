/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import beans.Processo;
import beans.Usuario;
import facade.CadastroFacade;
import facade.LoginFacade;
import facade.ProcessoFacade;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import util.SessionContext;

/**
 *
 * @author hiago
 */
@Named
@RequestScoped
public class RelatoriosMB implements Serializable {
   
    private Usuario usuario;
    private String dataInicial;
    private String dataFinal;
    
    //Troca para o seu usuario do banco:
    private final String usuarioBD = "postgres";
    private final String senhaBD = "159951";
    
    public RelatoriosMB() {
    }
    
    @PostConstruct
    public void init(){
        usuario = (Usuario) SessionContext.getInstance().getAttribute("usuarioLogado");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String parte(){
        Connection con = null;
            try {
                // ConexÃ£o com o banco
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/sijoga",
                       usuarioBD, senhaBD);
                
                String relativePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/parte.jasper");
                File file = new File(relativePath);
                    
                // ParÃ¢metros do relatÃ³rio
                HashMap params = new HashMap();
                params.put("id", usuario.getId());
                
                JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, con);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.addHeader("Content-Disposition", "attachment; filename=relatorio.pdf");
                response.setContentType("application/pdf");
                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(print, stream);
                FacesContext.getCurrentInstance().responseComplete();
            } catch (Exception e){
                FacesContext.getCurrentInstance().validationFailed();
                FacesMessage mensagem = new FacesMessage("Erro na geração do relatório","");
                    mensagem.setSeverity(FacesMessage.SEVERITY_INFO);
                       FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return "/parte/home.xhtml";
            }
    }
    
    public String encerrados(){
        Connection con = null;
            try {
                // ConexÃ£o com o banco
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/sijoga",
                        usuarioBD, senhaBD);
                
                String relativePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/encerrados.jasper");
                File file = new File(relativePath);
                    
                // ParÃ¢metros do relatÃ³rio
                HashMap params = new HashMap();
                params.put("id", usuario.getId());
                System.out.println(usuario.getId());
                
                JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, con);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.addHeader("Content-Disposition", "attachment; filename=relatorio.pdf");
                response.setContentType("application/pdf");
                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(print, stream);
                FacesContext.getCurrentInstance().responseComplete();
            } catch (ClassNotFoundException e) {
                System.out.println("Driver BD nÃ£o encontrado : "
                        + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Erro de conexÃ£o ou query: "
                        + e.getMessage());
            } catch (JRException e) {
                System.out.println("Erro no Jasper : "
                        + e.getMessage());
            } catch (Exception e){
                System.out.println("Outro erro : "
                        + e.getMessage());
            }finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return "/advogado/home.xhtml";
            }
    }
    
    public String porData(){
        Connection con = null;
            try {
                // ConexÃ£o com o banco
                Class.forName("org.postgresql.Driver");
                con = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/sijoga",
                        usuarioBD, senhaBD);
                
                String relativePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/porData.jasper");
                File file = new File(relativePath);
                    
                // ParÃ¢metros do relatÃ³rio
                HashMap params = new HashMap();
                params.put("id", usuario.getId());
                SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                Date datai = fmt.parse(dataInicial);
                params.put("dataInicial", datai);
                Date dataf = fmt.parse(dataFinal);
                params.put("dataFinal", dataf);
                JasperPrint print = JasperFillManager.fillReport(file.getPath(), params, con);
                HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                response.addHeader("Content-Disposition", "attachment; filename=relatorio.pdf");
                response.setContentType("application/pdf");
                ServletOutputStream stream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(print, stream);
                FacesContext.getCurrentInstance().responseComplete();
            } catch (ClassNotFoundException e) {
                System.out.println("Driver BD nÃ£o encontrado : "
                        + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Erro de conexÃ£o ou query: "
                        + e.getMessage());
            } catch (JRException e) {
                System.out.println("Erro no Jasper : "
                        + e.getMessage());
            } catch (Exception e){
                System.out.println("Outro erro : "
                        + e.getMessage());
            }finally {
                if (con != null) {
                    try {
                        con.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return "/advogado/home.xhtml";
            }
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }
    
    
}
