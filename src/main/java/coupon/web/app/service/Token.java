package coupon.web.app.service;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token {
	
private String username;
private String id;
private String clientType;

public Token() {
	super();
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getClientType() {
	return clientType;
}

public void setClientType(String clientType) {
	this.clientType = clientType;
}

@Override
public String toString() {
	return "Token [username=" + username + ", id=" + id + ", clientType=" + clientType + "]";
}

public Token(String username, String clientType,String id) {
	super();
	this.username = username;
//    Random random = new SecureRandom();
//    this.id = new BigInteger(130, random).toString(32);
	this.id=id;
	this.clientType = clientType;
}


}
