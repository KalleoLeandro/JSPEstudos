/**
 * 
 */
package util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Classe para armazenar os métodos de manipulação de Relatórios Jasper
 * @author Kalleo Leandro dos Santos Leal
 * @since 21/02/2023
 * @version 1.0
 */

@SuppressWarnings({"rawtypes", "unchecked"})
public class ReportUtil implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public byte[] geraRelatorioPdf(List listaDados, String nomeRelatorio, HashMap<String, Object> params ,ServletContext servletContext) throws JRException {
		
		
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDados);
		
		String caminhoJasper = servletContext.getRealPath("WEB-INF\\classes\\relatorio") + File.separator + nomeRelatorio + ".jasper";
		
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, params, jrbcds);
						
		return JasperExportManager.exportReportToPdf(impressoraJasper);		
	}
	
public byte[] geraRelatorioPdf(List listaDados, String nomeRelatorio, ServletContext servletContext) throws JRException {
		
		
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDados);
		
		String caminhoJasper = servletContext.getRealPath("WEB-INF\\classes\\relatorio") + File.separator + nomeRelatorio + ".jasper";
		
		JasperPrint impressoraJasper = JasperFillManager.fillReport(caminhoJasper, new HashMap(),jrbcds);
						
		return JasperExportManager.exportReportToPdf(impressoraJasper);		
	}
}
