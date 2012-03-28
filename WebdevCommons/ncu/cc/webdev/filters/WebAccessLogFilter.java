package ncu.cc.webdev.filters;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebAccessLogFilter implements Filter {
	
	
	private PrintWriter	pw = new PrintWriter(System.out);
	
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		SimpleDateFormat		dateFormate = new SimpleDateFormat("[dd/MMM/yyyy:HH:mm:ss Z]");
		
		String		requestURI = req.getRequestURI();
		if (pw != null) {
			String	user = "-";
		
			String referer = req.getHeader("referer");
			String useragent = req.getHeader("user-agent");
			if (referer == null) referer = "";
			if (useragent == null) useragent = "";
		
			pw.println(req.getRemoteAddr() + " " + user + " - " +	
					dateFormate.format(Calendar.getInstance().getTime()) + " \"" +
					req.getMethod() + " " + requestURI + " " +
					req.getProtocol() + "\" \"" + referer + "\" \"" + useragent + "\"");
			pw.flush();
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
