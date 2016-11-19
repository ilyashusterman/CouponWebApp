package coupon.web.app.services;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Kubal on 11/19/2016.
 */
@XmlRootElement
public class Token
    {
        private String username;
        private String type;
        private String id;

        public Token()
            {
                super();
            }

        public Token(String username, String type, String id)
            {
                this.username = username;
                this.type = type;
                this.id = id;
            }

        public String getUsername()
            {
                return username;
            }

        public void setUsername(String username)
            {
                this.username = username;
            }

        public String getType()
            {
                return type;
            }

        public void setType(String type)
            {
                this.type = type;
            }

        public String getId()
            {
                return id;
            }

        public void setId(String id)
            {
                this.id = id;
            }

        @Override
        public String toString()
            {
                return "Token{" +
                        "username='" + username + '\'' +
                        ", type='" + type + '\'' +
                        ", id='" + id + '\'' +
                        '}';
            }

    }
