package coupon.web.app.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.couponsystem.facadedao.CouponClientFacade;
import coupon.web.app.services.Login;


public class AuthenticationFilter implements Filter {

    public AuthenticationFilter() {
        System.out.println("ServerAuthenticationFilter initialization");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;
        String uriCurrentPath = request.getRequestURI().toString();
        String urlCurrentPath = request.getRequestURL().toString();
        System.out.println("DEBUG : Current URI is "+uriCurrentPath);
        System.out.println("DEBUG : Current URL is "+urlCurrentPath);
        System.out.println("DEBUG : filter() on ServerAuthenticationFilter");
        CouponClientFacade facade = (CouponClientFacade) Login.getFacade(request);
        boolean NotloggedIn=facade==null&&((!uriCurrentPath.equals("/CouponWebAppPhase2/")&&
                !uriCurrentPath.equals("/CouponWebAppPhase2/index.html"))&&!uriCurrentPath.equals("/CouponWebAppPhase2/rest/login"));
        if (NotloggedIn) {
            System.out.println("Authentication Filter Blocked! facade= " + facade);
            redirectPage(response,request);
            return;
        } else {

            if(facade!=null){
                System.out.println("Facade get name "+facade.getClass().getName());
                switch (facade.getClass().getName()) {

                    case "facade.CustomerFacade":

                        if (uriCheckCoupon("cust", uriCurrentPath)){
                            redirectPage(response,request);
                            return ;

                        }
                        break;
                    case "facade.AdminFacade":
                        System.out.println("uri is"+uriCurrentPath);
                        if (uriCheckCoupon("admin", uriCurrentPath)){
                            System.out.println("bad request uri!!!!");
                            redirectPage(response,request);
                            return ;
                        }
                        break;
                    case "facade.CompanyFacade":
                        if (uriCheckCoupon("company", uriCurrentPath)){
                            redirectPage(response,request);
                            return ;
                        }
                        break;
                    default:
                        break;
                }
            }
            System.out.println("Authentication Filter Passed; Facade is " + facade);
            filterChain.doFilter(request,response);
        }

    }
    private boolean uriCheckCoupon(String regex,String uriPath){
        return !uriPath.contains(regex)&&!uriPath.equals("/coupon_2/")&&
                !uriPath.equals("/CouponWebAppPhase2/index.html")&&!uriPath.equals("/CouponWebAppPhase2/rest/login");

    }
    private void redirectPage(HttpServletResponse response,HttpServletRequest request) throws IOException {
        String url="http://localhost"+
                ":"+request.getServerPort()+request.getServletContext().getContextPath();
        response.sendRedirect(response.encodeRedirectURL(url));
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }


    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("ServerAuthenticationRequestFilter initialization");

    }

}
