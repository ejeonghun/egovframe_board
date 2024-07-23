package egovframework.let.cop.reply.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.ReplyVO;

@Repository("replyDAO")
public class ReplyDAO extends EgovAbstractMapper {
	
	public List<ReplyVO> selectReplies(Long nttId) throws Exception {
		return selectList("replyDAO.selectReplies", nttId);
		
	}
	
    public void insertReply(ReplyVO reply) throws Exception {
        insert("replyDAO.insertReply", reply);
    }
}
