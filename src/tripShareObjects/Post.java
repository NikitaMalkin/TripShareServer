package tripShareObjects;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private long m_ID;
	private int m_userID;
	private String m_title;
	private String m_description;
	@OneToOne
	private long m_routeID;
	
	public Post(int i_userID, String i_title, String i_description)
	{
		m_userID = i_userID;
		setTitle(i_title);
		setDescription(i_description);
	}
	
	public long getID() { return m_ID; }
	
	public int getUserID() { return m_userID; }
	public void setUserID(int i_userID) { m_userID = i_userID; }
	
	public String getTitle() { return m_title; }
	public void setTitle(String i_title) { m_title = new String(i_title); }
	
	public String getDescription() { return m_description; }
	public void setDescription(String i_description) { m_description = new String(i_description); }
	
	public long getPostRoute() { return m_routeID; }
}
