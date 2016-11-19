package coupon.web.app.services;

import com.couponsystem.facadedao.ClientType;
import com.couponsystem.facadedao.CouponClientFacade;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Kubal on 11/19/2016.
 */
@XmlRootElement
public class User
    {
        private String username;
        private String password;
        private ClientType type;


        public User()
            {
                super();
            }

        public User(String username, String password, ClientType type)
            {
                this.username = username;
                this.password = password;
                this.type = type;
            }

        @Override
        public String toString()
            {
                return "User{" +
                        "username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", type=" + type +
                        '}';
            }

        public String getUsername()
            {
                return username;
            }

        public void setUsername(String username)
            {
                this.username = username;
            }

        public String getPassword()
            {
                return password;
            }

        public void setPassword(String password)
            {
                this.password = password;
            }

        public ClientType getType()
            {
                return type;
            }

        public void setType(ClientType type)
            {
                this.type = type;
            }
    }
