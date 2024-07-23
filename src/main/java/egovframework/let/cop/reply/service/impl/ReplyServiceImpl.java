package egovframework.let.cop.reply.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.ReplyVO;
import egovframework.let.cop.reply.service.ReplyService;

@Service("replyService")
public class ReplyServiceImpl extends EgovAbstractServiceImpl implements ReplyService {
	
	@Resource(name="replyDAO")
	private ReplyDAO replyDAO;
	

	/** 
	 * 해당 게시글의 댓글 목록을 불러온다.
	 * */
	@Override
	public List<ReplyVO> selectReplies(Long nttId) throws Exception {
		
		// 윗 단 Controller 단에서 ntt_id -> 게시글의 id 값을 가져옴 그걸로 조회함
		List<ReplyVO> replyVO = replyDAO.selectReplies(nttId);
		return replyVO;
	}
	
    @Override
    public void insertReply(ReplyVO reply) throws Exception {
        replyDAO.insertReply(reply);
    }
}
