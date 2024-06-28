package projeto.jasperreport.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.JRException;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	

	
    /**
     *
     * @param name the name to greet
     * @return greeting text
     * @throws SQLException 
     * @throws JRException 
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name, HttpServletRequest request) throws Exception {
    	
    	
        return "Hello " + name + "!";
    }
}
