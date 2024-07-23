package egovframework.com.cmm;

import java.io.Serializable;

public class ReplyVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;

	/** 댓글Id */
	private Long replyId;
	
	/** 생성자 *//** 작성자 uniqid값 */
	private String createdId;
	
	/** 내용 */
	private String content;
	
	/** 게시글 Id */
	private Long nttId;
	

	public Long getReplyId() {
		return replyId;
	}

	public void setReplyId(Long replyId) {
		this.replyId = replyId;
	}


	public Long getNttId() {
		return nttId;
	}

	public void setNttId(Long nttId) {
		this.nttId = nttId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getCreatedId() {
		return createdId;
	}
	
	public void setCreatedId(String createdId) {
		this.createdId = createdId ;
	}
	/** */
}
