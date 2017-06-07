package first.com.model;

import java.util.Date;

public class BcommentDTO {
	
	private int bcomment_id;
	private int board_id;
	private int bgroup_id;//�Խ��� �� ���� ���̵�
	private String bcomment_content;
	private Date bcomment_date;
	private int bcomment_password;
	private int member_id;
	private String member_name;
	private int answer;
	private int answer_id;
	
	
	public int getAnswer_id() {
		return answer_id;
	}
	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public int getBcomment_id() {
		return bcomment_id;
	}
	public void setBcomment_id(int bcomment_id) {
		this.bcomment_id = bcomment_id;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public int getBgroup_id() {
		return bgroup_id;
	}
	public void setBgroup_id(int bgroup_id) {
		this.bgroup_id = bgroup_id;
	}
	public String getBcomment_content() {
		return bcomment_content;
	}
	public void setBcomment_content(String bcomment_content) {
		this.bcomment_content = bcomment_content;
	}
	public Date getBcomment_date() {
		return bcomment_date;
	}
	public void setBcomment_date(Date bcomment_date) {
		this.bcomment_date = bcomment_date;
	}
	public int getBcomment_password() {
		return bcomment_password;
	}
	public void setBcomment_password(int bcomment_password) {
		this.bcomment_password = bcomment_password;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	
	

}
