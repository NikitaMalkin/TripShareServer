package tripShareObjects;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private long m_ID;
	private String m_userName;
	private	String m_password;
	
	public long getID() { return m_ID; }
	
	public String getStringUserName() { return m_userName; }
	
	public String getPassword() 
	{ 
		return m_password; 	
	}
	
	public void setUserName(String i_userName) { m_userName = i_userName; }
	
	public void setPassword(String i_password)
	{
		m_password = i_password;
	}
	
}
