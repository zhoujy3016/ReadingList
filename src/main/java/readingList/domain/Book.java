package readingList.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.Mapping;


@Entity
@Table(name="book")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String reader;
	@NotEmpty(message="ISBN不能为空！")
	private String isbn;
	@NotEmpty(message="书名不能为空！")
	private String title;
	private String author;
	private String description;
	
	private String publish_id;
	
	@Transient
	private String publish_name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="publish_id", insertable=false, updatable=false)
	private Publish publish;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReader() {
		return reader;
	}
	public void setReader(String reader) {
		this.reader = reader;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public void setPublish(Publish publish) {
		this.publish = publish;
	}
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
