package tripShareObjects;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Addition implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private long m_ID;
	private String m_imageDescription;
	// private image; // TODO
	
	public String getImageDescription() { return m_imageDescription; }
	public void setImageDescription(String i_imageDescription) { m_imageDescription= new String(i_imageDescription);}
}
