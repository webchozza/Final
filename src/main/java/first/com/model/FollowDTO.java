package first.com.model;

public class FollowDTO {
	
	private int member_id;
	private int follow_member_id;
	private int following_count;//�ڽ��� �ȷ����� ��
	private int follower_count;//�ڽ��� �ȷο��� �ȷο��� ��
	private int followCheck;//�ȷο��� ������� üũ
	private String member_name;
	
	
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public int getFollowCheck() {
		return followCheck;
	}
	public void setFollowCheck(int followCheck) {
		this.followCheck = followCheck;
	}
	public int getFollowing_count() {
		return following_count;
	}
	public void setFollowing_count(int following_count) {
		this.following_count = following_count;
	}
	public int getFollower_count() {
		return follower_count;
	}
	public void setFollower_count(int follower_count) {
		this.follower_count = follower_count;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public int getFollow_member_id() {
		return follow_member_id;
	}
	public void setFollow_member_id(int follow_member_id) {
		this.follow_member_id = follow_member_id;
	}

}
