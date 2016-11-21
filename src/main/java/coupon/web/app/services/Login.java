package coupon.web.app.services;

import com.couponsystem.beans.Customer;
import com.couponsystem.facadedao.ClientType;
import com.couponsystem.facadedao.CouponClientFacade;
import com.couponsystem.system.CouponSystem;
import coupon.web.app.exceptions.CouponSystemExceptionResource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Root resource (exposed at "login" path)
 */
@Path("login")
public class Login
    {

            public static CouponSystem couponSys;
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getIt() {
        return new Customer(1,"ilya","123123");
    }

        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response login(@Context HttpServletRequest request, User user) {
            CouponClientFacade facade;
            ClientType theType = null;
            Token token=null;
            if (user.equals(null)) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            if ((theType = ClientType.valueOf((user.getType().toString()))) == null) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            String facadeClientType=theType.toString();

            try {
                facade = authenticate(user.getUsername(), user.getPassword(), theType);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                CouponSystemExceptionResource exception=new CouponSystemExceptionResource(e.getMessage());
                return exception.toResponse(exception);
            }
            if (facade != null) {
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.invalidate();
                    System.out.println("session invalidated!");
                }
                session = request.getSession(true);
                token = new Token(user.getUsername(),user.getType().toString(),session.getId());
                session.setAttribute(facadeClientType, facade);
                session.setAttribute("token", token);
                session.setAttribute("facade", facade);
                Token theToken=(Token)session.getAttribute("token");
                System.out.println("set session att seccessfully and token = "+theToken);
                System.out.println("is session "+session.getId()+" is new ? "+session.isNew());
                System.out.println("requested session id "+request.getRequestedSessionId());
            }

            return Response.ok(token).entity(token).type(MediaType.APPLICATION_JSON).build();
        }

        private CouponClientFacade authenticate(String username, String password,ClientType clientType) throws Exception {
            return couponSys.login(username, password, clientType);
        }

        public static CouponClientFacade getFacade(HttpServletRequest request,ClientType facadeClientType) {
            HttpSession session=request.getSession(false);
            if(session==null)return null;
            return (CouponClientFacade) session.getAttribute(facadeClientType.toString());
        }
        public static CouponClientFacade getFacade(HttpServletRequest request) {
            HttpSession session=request.getSession(false);
            if(session==null)return null;
            return (CouponClientFacade) session.getAttribute("facade");
        }

    }

