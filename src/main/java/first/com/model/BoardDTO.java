package first.com.model;

import java.util.Date;

public class BoardDTO {
	
	private int board_id; //pk������
	private int member_id; //ȸ�� ������
	private String board_title; //�Խñ� ����
	private String board_content; //�Խñ� ����
	private Date board_date; //�Խñ� �ۼ���
	private String board_nickname; //ȸ���г���
	private int board_hit; //��ȸ��
	private int board_like; //���ƿ�
	private int board_bad; //�Ⱦ��
	private int board_comment_count; //�ڸ�Ʈ ����
	private String board_password; 
	private int board_ip; //ȸ�� ip
	private int board_filecount; //���ϰ���
	private int bgroup_id;	//ī�װ� ���� ��ȣ
	private String member_name; //ȸ�� �̸�
	private String scrap_member_id;

	public String getScrap_member_id() {
		return scrap_member_id;
	}
	public void setScrap_member_id(String scrap_member_id) {
		this.scrap_member_id = scrap_member_id;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	public String getBoard_nickname() {
		return board_nickname;
	}
	public void setBoard_nickname(String board_nickname) {
		this.board_nickname = board_nickname;
	}
	public int getBoard_hit() {
		return board_hit;
	}
	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
	}
	public int getBoard_like() {
		return board_like;
	}
	public void setBoard_like(int board_like) {
		this.board_like = board_like;
	}
	public int getBoard_bad() {
		return board_bad;
	}
	public void setBoard_bad(int board_bad) {
		this.board_bad = board_bad;
	}
	public int getBoard_comment_count() {
		return board_comment_count;
	}
	public void setBoard_comment_count(int board_comment_count) {
		this.board_comment_count = board_comment_count;
	}
	public String getBoard_password() {
		return board_password;
	}
	public void setBoard_password(String board_password) {
		this.board_password = board_password;
	}
	public int getBoard_ip() {
		return board_ip;
	}
	public void setBoard_ip(int board_ip) {
		this.board_ip = board_ip;
	}
	public int getBoard_filecount() {
		return board_filecount;
	}
	public void setBoard_filecount(int board_filecount) {
		this.board_filecount = board_filecount;
	}
	public int getBgroup_id() {
		return bgroup_id;
	}
	public void setBgroup_id(int bgroup_id) {
		this.bgroup_id = bgroup_id;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	
	
}
