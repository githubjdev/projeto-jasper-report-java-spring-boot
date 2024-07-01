package projeto.jasperreport.controllers;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import projeto.jasperreport.ReportUtil;
import projeto.repository.UsuarioRepository;
import projeto.service.UsuarioService;

@SuppressWarnings("rawtypes")
@Controller
public class ImpressaoReportController {
	
	
	@Autowired
	private ReportUtil reportUtil;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
    /*Para download em aquivo pdf*/
	@RequestMapping(value = "**/imprimirreport/nome/{nome}/salario_ini/{salario_ini}/salario_fim/{salario_fim}", method = RequestMethod.GET)
	public void imprimirReport(@PathVariable("nome") String nome,
			                  @PathVariable("salario_ini") Double salario_ini,
			                  @PathVariable("salario_fim") Double salario_fim,
			                  HttpServletRequest request,
			                  HttpServletResponse response) throws Exception {
		
		ServletContext context = request.getServletContext();
		HashMap params = new HashMap();
		List listDados = new ArrayList();
		
		listDados = montaDados(nome, salario_ini, salario_fim, listDados);
		
		byte[] reportByte = reportUtil.gerarRelatorioByte(params, context, nome, listDados);
		
		response.setContentLength(reportByte.length);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=report.pdf");
		
		OutputStream outputStream = response.getOutputStream();
		outputStream.write(reportByte);
		outputStream.flush();
		outputStream.close();
		
	}
	
	
    /*Para download em aquivo pdf em base 64 para API REST*/
	@ResponseBody
	@RequestMapping(value = "**/imprimirReportBase64/nome/{nome}/salario_ini/{salario_ini}/salario_fim/{salario_fim}", method = RequestMethod.GET)
	public ResponseEntity<String> imprimirReportBase64(@PathVariable("nome") String nome,
			                  @PathVariable("salario_ini") Double salario_ini,
			                  @PathVariable("salario_fim") Double salario_fim,
			                  HttpServletRequest request,
			                  HttpServletResponse response) throws Exception {
		
		ServletContext context = request.getServletContext();
		HashMap params = new HashMap();
		List listDados = new ArrayList();
		
		listDados = montaDados(nome, salario_ini, salario_fim, listDados);
		
		byte[] reportByte = reportUtil.gerarRelatorioByte(params, context, nome, listDados);
		
		String base64 = "data:application/text;base64," + DatatypeConverter.printBase64Binary(reportByte);
		
		return new ResponseEntity<String>(base64, HttpStatus.OK);
		
	}


	private List montaDados(String nome, Double salario_ini, Double salario_fim, List listDados) {
		
		if (nome.equals("relatorio-usuario-jrbcds") 
				|| nome.equals("relatorio-usuario-agrupamento-jrbcds") 
				|| nome.equals("relatorio-usuario-com-subreport-jrbcds")) {
			
			listDados = usuarioRepository.listbySalario(salario_ini, salario_fim);
			
		}else if (nome.equals("relatorio-usuario-grafico-pizza-jrbcds") 
				|| nome.equals("relatorio_usuario-barra-chart-jrbcds")
				|| nome.equals("relatorio_usuario_tabela-jrbcds")){
			
			listDados = usuarioService.listUserGraficoPizzaAndBar();
			
		}else if (nome.equals("relatorio_usuario_crosstable-jrbcds")) {
			
			listDados = usuarioService.listUserCrossBar();
			
		}
		return listDados;
	}

}
