package first.com.model;

import java.util.Date;

public class MessageDTO {
	
	private int message_id;
	
	private String email_from;
	
	private String email_to;
	
	private String name_from;
	
	private String name_to;
	
	private String message_subject;
	
	private String message_content;
	
	private Date message_date;

	private String search;
	
	
	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public String getEmail_from() {
		return email_from;
	}

	public void setEmail_from(String email_from) {
		this.email_from = email_from;
	}

	public String getEmail_to() {
		return email_to;
	}

	public void setEmail_to(String email_to) {
		this.email_to = email_to;
	}
	public String getName_from() {
		return name_from;
	}

	public void setName_from(String name_from) {
		this.name_from = name_from;
	}

	public String getName_to() {
		return name_to;
	}

	public void setName_to(String name_to) {
		this.name_to = name_to;
	}

	public String getMessage_subject() {
		return message_subject;
	}

	public void setMessage_subject(String message_subject) {
		this.message_subject = message_subject;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public Date getMessage_date() {
		return message_date;
	}

	public void setMessage_date(Date message_date) {
		this.message_date = message_date;
	}
	
	
}
