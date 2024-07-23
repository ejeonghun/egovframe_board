package egovframework.customreply.service;

import java.io.Serializable;
import java.sql.Timestamp;

public class CustomReplyVO implements Serializable {
	
	private static final long serialVersionUID = -3779821913760046011L;
	
    private Long id; // 댓글 ID
    private Long parentId; // 부모 댓글 ID
    private Long postId;  // 게시글 ID
    private String author; // 작성자
    private String content; // 내용
    private Timestamp createdAt; // 생성일
    private Timestamp updatedAt; // 수정일
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
    
    
    

}