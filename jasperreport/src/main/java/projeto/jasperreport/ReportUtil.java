package projeto.jasperreport;

import java.io.File;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@Component
public class ReportUtil {
	
	private static final String FOLDER_RELATORIOS = "/relatorios";
	private static final String PARAMETRO_PASTA_REPORT = "PARAMETRO_PASTA_REPORT";
	private static final String SEPARETOR = File.separator;
	
	
	public String gerarRelatorio(HashMap parametroRelatorio, ServletContext servletContext) {
		
		String caminhoPastaImagensReport = servletContext.getRealPath(FOLDER_RELATORIOS);
		parametroRelatorio.put(PARAMETRO_PASTA_REPORT, caminhoPastaImagensReport + SEPARETOR);
		
		
		
		return "";
	}
	

}
