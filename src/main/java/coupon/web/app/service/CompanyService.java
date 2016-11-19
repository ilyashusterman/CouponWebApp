package coupon.web.app.service;

import facade.ClientType;
import facade.CompanyFacade;
import servicebeans.Company;
import servicebeans.Coupon;
import servicebeans.CouponType;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.Collection;

@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyService{
	private final ClientType clientType=ClientType.COMPANY;
	@GET
	@Path("/coupons")
	public Collection<Coupon> getCouponsOfCompany(@Context HttpServletRequest request) throws Exception {
		return ((CompanyFacade)Login.getFacade(request,this.clientType)).getCouponsOfCompany();
	}
	@GET
	@Path("/coupons/date/{date}")
	public Collection<Coupon> getCouponByDate(@Context HttpServletRequest request,@PathParam("date")String Date) throws Exception {
		return ((CompanyFacade)Login.getFacade(request,this.clientType)).getCouponByDate(Timestamp.valueOf(Date));
	}
	@GET
	@Path("/coupons/type/{type}")
	public Collection<Coupon> getCouponByType(@Context HttpServletRequest request,@PathParam("type")String couponType) throws Exception {
		return ((CompanyFacade)Login.getFacade(request,this.clientType)).getCouponByType(CouponType.valueOf(couponType));
	}
	@GET
	@Path("/coupons/price/{price}")
	public Collection<Coupon> getCouponUpToPrice(@Context HttpServletRequest request,@PathParam("price")double price) throws Exception {
		return ((CompanyFacade)Login.getFacade(request,this.clientType)).getCouponUpToPrice(price);
	}
    @POST
    @Path("/coupons")
	public void creatCoupon(@Context HttpServletRequest request,Coupon coupon) throws Exception {
    	((CompanyFacade)Login.getFacade(request,this.clientType)).creatCoupon(coupon);
	}

    @DELETE
    @Path("/coupons/{couponId}")
	public void removeCoupon(@Context HttpServletRequest request, @PathParam("couponId")long couponId) throws Exception {
    	((CompanyFacade)Login.getFacade(request,this.clientType)).removeCoupon(couponId);
		
	}
    @PUT
    @Path("/coupons/{couponId}")
	public void updateCoupon(@Context HttpServletRequest request,Coupon coupon,@PathParam("couponId")long couponId) throws Exception {
    	((CompanyFacade)Login.getFacade(request,this.clientType)).updateCoupon(couponId, coupon.getEndDate(), coupon.getPrice());	
	}
	@GET
	@Path("/company")
	public Company getTheCompany(@Context HttpServletRequest request) throws Exception {
		return ((CompanyFacade)Login.getFacade(request,this.clientType)).getTheCompany();
	}


}
