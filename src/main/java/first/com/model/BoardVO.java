package first.com.model;

public class BoardVO {
	
	private int code = 1;//���¼ҽ� �Խ���
	private int free = 2;//�����Խ���(Ŀ�´�Ƽ)
	private int job = 3;//���α��� �Խ���
	private int qna = 4;//Qna �Խ���
	
	public int getQna() {
		return qna;
	}
	public int getFree() {
		return free;
	}
	public int getCode() {
		return code;
	}
	public int getJob() {
		return job;
	}
}
