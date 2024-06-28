package projeto.jasperreport.report;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;

import projeto.jasperreport.Application;
import projeto.jasperreport.ReportUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.MOCK)
public class TesteReportImpressao {
	
	@Autowired
	private ReportUtil reportUtil;
	
	@Test
	public void testeImpressaoRelUsuario1() throws Exception {
		
		HashMap params = new HashMap();
		MockHttpServletRequest request = new MockHttpServletRequest();
		
		params.put("SALARIO_INI", 1.00);
		params.put("SALARIO_FIM" , 500.00);
		String local = reportUtil.gerarRelatorioStringPath(params, request.getServletContext(), "relatorio-usuario");
		
		System.out.println(local);
		
	}

}
