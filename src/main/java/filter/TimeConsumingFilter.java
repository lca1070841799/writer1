package filter;

import dao.impl.HttpRequestDaoImpl;
import utils.Log;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class TimeConsumingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest=(HttpServletRequest)request;
        HttpServletResponse httpServletResponse=(HttpServletResponse)response;

        //过滤掉静态资源请求
        String spath=httpServletRequest.getServletPath();
//        System.out.println(spath);
        String[] urls={"/js/","/css/"};
        boolean flag=true;
        for(String str:urls){
            if(spath.indexOf(str)!=-1){
                flag=false;
                break;
            }
        }
        if(flag) {
            long t1 = System.currentTimeMillis();
            filterChain.doFilter(request, response);
            long t2 = System.currentTimeMillis();

//        System.out.println("请求 "+httpServletRequest.getRequestURL()+" 耗时 "+(t2-t1)+" ms");

            HttpRequestDaoImpl hrdi = new HttpRequestDaoImpl();
            hrdi.insert(Log.httpRequestLog(httpServletRequest, httpServletResponse, t2 - t1));
        }else{
            filterChain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}