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

    public void insertPost(CustomBoardVO post) throws Exception {
        insert("insertPost", post);
    }

    public CustomBoardVO selectPost(Long boardId) throws Exception {
        return selectOne("selectPost", boardId);
    }

    public List<CustomBoardVO> selectPostList() throws Exception {
        return selectList("selectPostList");
    }

    public void insertReplyPost(CustomBoardVO reply) throws Exception {
        insert("insertReplyPost", reply);
    }

    public void updatePost(CustomBoardVO post) throws Exception {
        update("updatePost", post);
    }

    public void deletePost(Long boardId) throws Exception {
        delete("deletePost", boardId);
    }
    
    public List<CustomBoardVO> selectRepliesByParentId() throws Exception {
        return selectList("selectRepliesByParentId");
    }
    
    public CustomBoardVO SelectReplyPost(Long boardId) throws Exception {
        return selectOne("SelectReplyPost", boardId);
    }
}
