package com.libr.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CharacterFilter
 */
@WebFilter(urlPatterns={"/test/unknow"},filterName="CharacterFilter",
initParams= {@WebInitParam(name="encoding",value="UTF-8"),@WebInitParam(name="count",value="999")})
public class TestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TestFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	private String encoding=null;
	private int count = 99;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse rep = (HttpServletResponse)response;
		Map<String, String[]> map = request.getParameterMap(); //获取request里面所有请求参数。已Key-value形式返回
		Set<String> keys = map.keySet();
		Collection <String[]> v = map.values();
//		for(String key : keys) {
//			String[] value = map.get(key);
//			System.out.println(key + " = " + value[0]);
//	}
		PrintWriter out = response.getWriter();
//		HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(request);
		if(map!=null||map.size()>0){   //有参数时才处理
			Set<Entry<String, String[]>> set = map.entrySet();
			Iterator<Entry<String, String[]>> iter = set.iterator();
			while(iter.hasNext()) {
				Map.Entry<String, String[]>entry = (Map.Entry<String,String[]>)iter.next();
//				String[] entryValue = entry.getValue();
//				if(entry.getValue() instanceof String[]) {
					String values[] = (String[])entry.getValue();
					for(int i=0;i<values.length;i++) {
						values[i]=StringFilter(values[i]);
						out.print("<h2>"+values[i]+"<h2>");
						
					}
					entry.setValue(values);
				}
				
			}
		chain.doFilter(request, response);
	
	}
//		HttpServletRequest request = (HttpServletRequest)arg0;
//		Map map = request.getParameterMap(); //获取request里面所有请求参数。已Key-value形式返回
//		if(map!=null||map.size()<1){   //有参数时才处理
//			Set set = map.entrySet();
//			Iterator ite = set.iterator(); //返回迭代器
//			while(ite.hasNext()){   //迭代替换参数中的特殊字符
//				Map.Entry entry = (Entry) ite.next();
//				if(entry.getValue() instanceof String[]){
//					   String[] values = (String[]) entry.getValue();  
//			      for(int i = 0 ; i < values.length ; i++){  
//			       values[i] = StringFilter(values[i]);  
//			      }
//			      entry.setValue(values);  //存进参数集合中
//				}
//			}
//	
//		}
//		
//		arg2.doFilter(arg0, arg1);	  //放行


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public  String StringFilter(String   str){     
		  String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"\"]";  
		  Pattern   p   =   Pattern.compile(regEx);     
		  Matcher   m   =   p.matcher(str);     
		  return   m.replaceAll("").trim();     
		  }  

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		encoding = fConfig.getInitParameter("encoding");
		count = Integer.parseInt(fConfig.getInitParameter("count"));
	}

}
