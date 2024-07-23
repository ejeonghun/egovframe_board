package egovframework.customreply.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.customreply.service.CustomReplyVO;


@Repository("customReplyDAO")
public class CustomReplyDAO extends EgovAbstractMapper {

    public void insertCustomReply(CustomReplyVO reply) throws Exception {
        insert("insertCustomReply", reply);
    }

    public CustomReplyVO selectReply(Long id) throws Exception {
        return selectOne("selectReply", id);
    }

    public List<CustomReplyVO> selectRepliesByPostId(Long postId) throws Exception {
        return selectList("selectRepliesByPostId", postId);
    }

    public List<CustomReplyVO> selectRepliesByParentReplyId(Long parentId) throws Exception {
        return selectList("selectRepliesByParentReplyId", parentId);
    }

    public void updateReply(CustomReplyVO reply) throws Exception {
        update("updateReply", reply);
    }

    public void deleteReply(Long id) throws Exception {
        delete("deleteReply", id);
    }
    
    public void deleteParentReplyLogic(Long id) throws Exception {
    	delete("deleteParentReplyLogic", id);
    }
    
    public void deleteReplyPostId(Long post_id) throws Exception {
    	delete("deleteReplyPostId", post_id);
    }
    
}

