package coupon.web.app.tests;

/**
 * Created by Kubal on 11/19/2016.
 */



import com.couponsystem.beans.Company;
import com.couponsystem.beans.Coupon;
import com.couponsystem.beans.Customer;
import com.couponsystem.exceptions.DAOException;
import coupon.web.app.services.Login;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestJson{

    @GET
    @Path("/companies")
    public Collection<Company> getAllCompanies(@Context HttpServletRequest req) throws Exception{
        return Login.couponSys.comapnyDao.getAll();
    }

    @POST
    @Path("/customers")
    public void creatCustomer(Customer customer) throws DAOException
        {
        Login.couponSys.customerDao.create(customer);
    }

    @DELETE
    @Path("/customers/{customerId}")
    public void removeCustomer(@PathParam("customerId")long customerId) throws DAOException {
        Login.couponSys.customerDao.delete(customerId);
    }

    @PUT
    @Path("/customers/{customerId}/{password}")
    public void updateCustomer(long customerId, String password) throws DAOException {
        Login.couponSys.customerDao.updateCustomer(customerId, password);
    }

    @PUT
    @Path("/customers/{customerId}")
    public Customer updateCustomer(Customer customer) throws Exception {
        Login.couponSys.customerDao.updateCustomer(customer.getId(), customer.getPassword());
        return Login.couponSys.customerDao.getCustomer(customer.getId());
    }

    @GET
    @Path("/customers/{customerId}")
    public Customer getCustomer(@PathParam("customerId")long customerId) throws DAOException {
        return Login.couponSys.customerDao.getCustomer(customerId);
    }

    @GET
    @Path("/customers/{name}/{password}")
    public Customer getCustomerByNamePassword(@PathParam("name")String name,@PathParam("password") String password) throws DAOException {
        return Login.couponSys.customerDao.getCustomerByNamePassword(name, password);
    }

    @GET
    @Path("/customers")
    public Collection<Customer> getAllCustomers() throws DAOException {
        return Login.couponSys.customerDao.getAllCustomers();
    }

    @GET
    @Path("/coupons/{couponId}")
    public Coupon getCoupon(@PathParam("couponId")long id) throws DAOException {
        return Login.couponSys.couponDao.getCoupon(id);
    }

    @GET
    @Path("/coupons")
    public Collection<Coupon> getAllCoupon() throws DAOException {
        return Login.couponSys.couponDao.getAllCoupon();
    }

    @POST
    @Path("/companies")
    public void creatCompany(Company company) throws DAOException {
        Login.couponSys.comapnyDao.creatCompany(company);
    }

    @POST
    @Path("/coupons")
    public void creatCoupon(Coupon coupon) throws DAOException {
        Login.couponSys.couponDao.creatCoupon(coupon);;
    }

    @DELETE
    @Path("/companies/{companyId}")
    public void removeCompany(@PathParam("companyId")long companyId) throws DAOException {
        Login.couponSys.comapnyDao.removeCompany(companyId);
    }

    @PUT
    @Path("/companies")
    public void updateCompany(Company company) throws DAOException {
        Login.couponSys.comapnyDao.updateCompany(company.getId(), company.getPassword(), company.getEmail());;
    }

    @GET
    @Path("/companies/{companyId}")
    public Company getCompany(@PathParam("companyId")long companyId) throws DAOException {

        return Login.couponSys.comapnyDao.getCompany(companyId);
    }

    @GET
    @Path("/coupons/purchase/{couponId}")
    public String purchaseCoupon(@Context HttpServletRequest request, @PathParam("couponId") long couponId) throws Exception {
        Login.couponSys.customerDao.purchaseCoupon(couponId,1);
        return "successfully!";
    }


}