package egovframework.board.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.board.service.CustomBoardVO;

/**
 *  
 * */
@Repository("customBoardDAO")
public class CustomBoardDAO extends EgovAbstractMapper {

	/**
	 * 게시글을 생성한다.
	 * @param CustomBoardVO
	 *  */
    public void insertPost(CustomBoardVO post) throws Exception {
        insert("insertPost", post);
    }

    /**
     * 게시글을 조회한다.
     * @param boardId (게시글의 ID)
     * @return CustomBoardVO
     * */
    public CustomBoardVO selectPost(Long boardId) throws Exception {
        return selectOne("selectPost", boardId);
    }

    /** 
     * 게시글 목록을 조회한다.
     * @return List<CustomBoardVO>
     * */
    public List<CustomBoardVO> selectPostList() throws Exception {
        return selectList("selectPostList");
    }

    /**
     * 답변 게시글을 생성한다.
     * @param CustomBoardVO
     * */
    public void insertReplyPost(CustomBoardVO reply) throws Exception {
        insert("insertReplyPost", reply);
    }

    /** 
     * 게시글을 갱신한다.
     * @param CustomBoardVO
     * */
    public void updatePost(CustomBoardVO post) throws Exception {
        update("updatePost", post);
    }

    /** 
     * 게시글을 삭제한다.
     * @param boardId (게시글의 ID)
     * */
    public void deletePost(Long boardId) throws Exception {
        delete("deletePost", boardId);
    }
    
    /** 
     * 답변 게시글 목록을 조회한다.
     * @param boardId
     * @return List<CustomBoardVO>
     * */
    public List<CustomBoardVO> selectRepliesByParentId() throws Exception {
        return selectList("selectRepliesByParentId");
    }
    

    /** 
     * 답변 게시글을 조회한다.
     * @param boardId
     * @return CustomBoardVO
     * */
    public CustomBoardVO SelectReplyPost(Long boardId) throws Exception {
        return selectOne("SelectReplyPost", boardId);
    }
    
    /** 
     * 게시글의 조회수를 +1 증감한다.
     * @param boardId
     * */
    public void postCntUpdate(Long boardId) throws Exception {
    	update("postCntUpdate", boardId);
    }
}
