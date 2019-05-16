package tripShareObjects;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Coordinate implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private long m_ID;
	private String m_latitude;
	private String m_longitude;
	private Addition m_additionToCoordinate;

	public Coordinate() {
	    }

	Coordinate(String i_Latitude, String i_Longitude) {
	        this.m_latitude = new String(i_Latitude);
	        this.m_longitude = new String(i_Longitude);
	    }
	
	public String getLatitude()
	{
		return m_latitude;
	}
	
	public String getLongitude()
	{
		return m_longitude;
	}
	
	public Addition getCoordinateAddition()
	{
		return m_additionToCoordinate;
	}
	
	public void setAddition(Addition i_additionToSet)
	{
		// TODO finish this function.
	}
	
	@Override
    public String toString() {
        return String.format("%d, %d", this.m_latitude, this.m_longitude);
    }
}