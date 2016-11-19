/**
 *
 */
package coupon.web.app.exceptions;

/**
 * @author ilya shusterman & or kowalsky
 *
 */
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import com.couponsystem.exceptions.ErrorType;

@Provider
public class CouponSystemExceptionResource extends Exception implements ExceptionMapper<Throwable>
    {
        private static final long serialVersionUID = 1L;

        public CouponSystemExceptionResource() {
            super("Unknown error!");
        }

        public CouponSystemExceptionResource(String string) {
            super(string);
        }
        public CouponSystemExceptionResource(ErrorTypeResource errorType, Throwable e) {
            super(errorType.toString(),e);
        }
        public CouponSystemExceptionResource(ErrorType errorType,Throwable e) {
            super(errorType.toString(),e);
        }

        @Override
        public Response toResponse(Throwable exception)
            {
                return Response.status(Response.Status.UNAUTHORIZED).entity(exception.getMessage())
                        .type("text/plain").build();
            }
    /*
    @Override
    public Response toResponse(Throwable exception)
    {
    	System.out.println("Just entered into the exceptions mapper");
    	if (exception instanceof ApplicationException){
    		ApplicationException e = (ApplicationException) exception;

    		int internalErrorCode = e.getErrorType().getInternalErrorCode();
    		String message = e.getMessage();
    		ErrorBean errorBean = new ErrorBean(internalErrorCode, message);
    		return Response.status(700).entity(errorBean).build();
    	}

    	System.out.println("Returning 500 as the http status");
        return Response.status(500).entity("General failure").build();
    }
    */
    }