package coupon.web.app.service;


import coupon.web.app.exception.CouponSystemExceptionResource;
import coupon.web.app.logger.AppLogger;
import coupon.web.app.tasks.*;
import coupon.web.app.tasks.Process;

import facade.ClientType;
import facade.CouponClientFacade;
import org.apache.log4j.Logger;
import system.CouponSystem;
import test.TestDB;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;


@Path("/login")
public class Login {
	public volatile static CouponSystem couponSys;
	private volatile static boolean didStartDataBase;
	final static Logger logger = AppLogger.getLogger(Login.class);
	@Context 
	private HttpServletResponse response;
	
	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHello() {  
	    return "Hello guy! \n Im not your guy friend! \n Im not your friend buddy!";
	  }
	
    @PostConstruct
    public void init() throws Exception {

    	if(!didStartDataBase){
    		logger.info("The application has been initiated!");
			TrayIconTask.getInstance().execute(new Process("CouponSystem", "ON", couponSys+" "));
			Class.forName("org.apache.derby.jdbc.ClientDriver");
    		couponSys=CouponSystem.getInstance();
    		new TestDB(CouponSystem.getInstance());
    		didStartDataBase=true;
			logger.info("The application initiation has been completed!");
    	}

    }
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(@Context HttpServletRequest request, User user) {
		CouponClientFacade facade;
		ClientType theType = null;
		Token token=null;
		System.out.println("User is " + user);
		if (user.equals(null)) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		
		if ((theType = ClientType.valueOf((user.getClientType().toUpperCase()))) == null) {
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
			token = new Token(user.getUsername(),user.getClientType(),session.getId());
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
    
	@GET
	@Path("/checkToken/{tokenId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkLogin(@Context HttpServletRequest request,@PathParam ("tokenId") String id ) {
		Token token=null;
		HttpSession session = request.getSession();
		System.out.println("from checkToken is session "+session.getId()+" is new ? "+session.isNew());
		if ((request.getSession(false)==null) || (request.getSession(false).getAttribute("token") == null)) {

			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		else
			{
			String tokenId=((Token)request.getSession(false).getAttribute("token")).getId();
			System.out.println("tokenId = "+tokenId+" id ="+id);
			if(!tokenId.equals(id))return Response.status(Response.Status.UNAUTHORIZED).build();
			token =((Token)request.getSession(false).getAttribute("token"));
			}
		return Response.ok(token).entity(token).type(MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/logout")
	public Response logout(@Context HttpServletRequest request) {
		
		if (request.getSession(false) != null) {
			request.getSession(false).invalidate();
		}				
		URI loc;
		//redirect to login page
		try {
			loc = new URI("../#/login");
			return Response.seeOther(loc).build();
		} catch (URISyntaxException e) {
			return null;
		}		
	}	
    
	
	public static Response getErrorResp(String message){
		CouponSystemExceptionResource exception=new CouponSystemExceptionResource(message);
		return exception.toResponse(exception);
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
