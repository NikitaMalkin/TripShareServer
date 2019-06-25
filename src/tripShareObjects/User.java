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
	private	byte[] m_password;
	
	public long getID() { return m_ID; }
	
	public String getStringUserName() { return m_userName; }
	
	public byte[] password() 
	{ 
		//TODO make an encryption here 
		return m_password; 	
	}
	
	public void setUserName(String i_userName) { m_userName = i_userName; }
	
	public void setPassword(byte[] i_password)
	{
		// TODO make a decryption here
		m_password = i_password;
	}
	
}
