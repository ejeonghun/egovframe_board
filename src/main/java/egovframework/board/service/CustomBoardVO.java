package egovframework.board.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 게시판 관련 VO
 * */
public class CustomBoardVO implements Serializable {

    /**
	 *  serialVersion UID
	 */
	private static final long serialVersionUID = -3779821913760046011L;
	
	private Long boardId; // 게시글 ID

	private String author; // 작성자
	
	private Timestamp createdAt; // 만든날짜
	
	private String title; // 제목
	
	private String content; // 내용
	
	private String isReply; // 답변 여부
	
	private Long parentId; // 부모 게시글 또는 댓글의 ID
	
	private String atchFileId; // 첨부파일 ID
	
	private Timestamp updatedDt; // 수정 날짜
	
	private Long postCnt; // 조회수
	

	public Long getPostCnt() {
		return postCnt;
	}

	public void setPostCnt(Long postCnt) {
		this.postCnt = postCnt;
	}

	public Timestamp getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Timestamp updatedDt) {
		this.updatedDt = updatedDt;
	}

	public Long getBoardId() {
		return boardId;
	}

	public void setBoardId(Long boardId) {
		this.boardId = boardId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsReply() {
		return isReply;
	}

	public void setIsReply(String isReply) {
		this.isReply = isReply;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	
	
}