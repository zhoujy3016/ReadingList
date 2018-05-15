package readingList.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="publish")
public class Publish implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String publish_id;
	private String publish_name;
	
	public String getPublish_id() {
		return publish_id;
	}
	public void setPublish_id(String publish_id) {
		this.publish_id = publish_id;
	}
	public String getPublish_name() {
		return publish_name;
	}
	public void setPublish_name(String publish_name) {
		this.publish_name = publish_name;
	}


}
