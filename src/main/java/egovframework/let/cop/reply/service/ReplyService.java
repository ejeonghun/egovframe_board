package egovframework.let.cop.reply.service;

import java.util.List;

import egovframework.com.cmm.ReplyVO;

public interface ReplyService {

	public List<ReplyVO> selectReplies(Long nttId) throws Exception;
	
	
	public void insertReply(ReplyVO vo) throws Exception;
}
