package com.mikelcuenca.app.utilidades;



import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.stereotype.Component;
/**
 * Clase de utilidad para obtener la traza de un error en formato string. Ej de uso:
 * 
 * ...
 * catch(Excepcion ex){
 * 		String traza=StackTraceManager.getStackTrace(ex);
 * 		system.outprintln(traza);
 * }
 * 
 * ...
 * 
 * @author BICUGUAL
 */
@Component
public class StackTraceManager {

	private StackTraceManager(){
		
	}
	public static String getStackTrace(Throwable t)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        t.printStackTrace(pw);
        pw.flush();
        sw.flush();
        
        return sw.toString();
    }
}