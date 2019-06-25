package tripShareObjects;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private long m_ID;
	private long m_userWhichCommentedID;
	private String m_comment;
	
	public long getID() { return m_ID; }
	
	public long getUserWhichCommentedID() { return m_userWhichCommentedID; }

	public String getComment() { return m_comment; } 
	
	public void setUserWhichCommentedID(long i_ID) { m_userWhichCommentedID = i_ID; }
	
	public void setComment( String i_comment ) { m_comment = i_comment; }
}
