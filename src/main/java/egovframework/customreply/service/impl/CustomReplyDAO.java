package egovframework.customreply.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.customreply.service.CustomReplyVO;


@Repository("customReplyDAO")
public class CustomReplyDAO extends EgovAbstractMapper {

	/**
	 * 댓글을 생성한다.
	 * @param CustomReplyVO
	 *  */
    public void insertCustomReply(CustomReplyVO reply) throws Exception {
        insert("insertCustomReply", reply);
    }

    /**
	 * 댓글을 조회한다.
	 * @param id (댓글ID)
	 * @return CustomReplyVO
	 *  */
    public CustomReplyVO selectReply(Long id) throws Exception {
        return selectOne("selectReply", id);
    }

    /**
	 * 게시글의 댓글 목록을 조회한다.
	 * @param postId(게시글 ID)
	 * @return List<CustomReplyVO>
	 *  */
    public List<CustomReplyVO> selectRepliesByPostId(Long postId) throws Exception {
        return selectList("selectRepliesByPostId", postId);
    }

    /**
	 * 부모 댓글의 답글 목록을 생성한다.
	 * @param parentId (부모 댓글의 ID)
	 * @return CustomReplyVO
	 *  */
    public List<CustomReplyVO> selectRepliesByParentReplyId(Long parentId) throws Exception {
        return selectList("selectRepliesByParentReplyId", parentId);
    }

    /**
	 * 댓글을 갱신한다.
	 * @param CustomReplyVO
	 *  */
    public void updateReply(CustomReplyVO reply) throws Exception {
        update("updateReply", reply);
    }

    /**
	 * 댓글을 삭제한다.
	 * @param id(댓글 ID)
	 *  */
    public void deleteReply(Long id) throws Exception {
        delete("deleteReply", id);
    }
    
    /**
	 * 자식 댓글들을 삭제한다.
	 * @param parent_id(부모 댓글 ID)
	 *  */
    public void deleteParentReplyLogic(Long id) throws Exception {
    	delete("deleteParentReplyLogic", id);
    }
    
    /**
	 * 게시글의 댓글을 삭제한다.
	 * @param post_id (게시글 ID)
	 *  */
    public void deleteReplyPostId(Long post_id) throws Exception {
    	delete("deleteReplyPostId", post_id);
    }
    
}

