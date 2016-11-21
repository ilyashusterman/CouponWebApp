package coupon.web.app.services;

import com.couponsystem.beans.Company;
import com.couponsystem.beans.Customer;
import com.couponsystem.exceptions.AdminFacadeException;
import com.couponsystem.exceptions.DAOException;
import com.couponsystem.facadedbdao.AdminFacade;
import com.couponsystem.system.CouponSystem;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by Kubal on 11/19/2016.
 */
@Path("/admin")
public class AdminService
    {
        public AdminService()
            {
                super();
            }

        private AdminFacade admin;
        private CouponSystem couponSys;
        @POST
        @Produces(MediaType.APPLICATION_JSON)

        public void createCompany(Company company) throws AdminFacadeException
            {
            }

        public Company getCompany(long id) throws AdminFacadeException
            {
                return null;
            }

        public Company getCompanyName(String companyName) throws AdminFacadeException
            {
                return null;

            }

        public Collection<Company> getAllCompanies() throws AdminFacadeException
            {
                return null;

            }

        public void updateCompany(Company company) throws AdminFacadeException
            {
            }

        public void deleteCompany(long companyId) throws AdminFacadeException
            {
            }

        public void createCustomer(Customer customer) throws AdminFacadeException
            {
            }

        public Customer getCustomer(long id) throws AdminFacadeException
            {
                return null;

            }

        public Collection<Customer> getAllCustomers() throws AdminFacadeException
            {
                return null;

            }

        public void updateCustomer(Customer customer) throws AdminFacadeException
            {
            }

        public void deleteCustomer(long customerId) throws AdminFacadeException
            {
            }

    }
