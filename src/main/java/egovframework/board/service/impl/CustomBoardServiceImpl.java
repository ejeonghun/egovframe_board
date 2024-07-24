package egovframework.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.board.service.CustomBoardService;
import egovframework.board.service.CustomBoardVO;

@Service("customBoardService")
public class CustomBoardServiceImpl extends EgovAbstractServiceImpl implements CustomBoardService {

    @Resource(name = "customBoardDAO")
    private CustomBoardDAO customBoardDAO;

    @Override
    public void insertPost(CustomBoardVO post) throws Exception {
        customBoardDAO.insertPost(post);
    }

    @Override
    public CustomBoardVO selectPost(Long boardId) throws Exception {
        return customBoardDAO.selectPost(boardId);
    }

    @Override
    public List<CustomBoardVO> selectPostList() throws Exception {
        return customBoardDAO.selectPostList();
    }

    @Override
    public void insertReplyPost(CustomBoardVO reply) throws Exception {
        customBoardDAO.insertReplyPost(reply);
    }

    @Override
    public void updatePost(CustomBoardVO post) throws Exception {
        customBoardDAO.updatePost(post);
    }

    @Override
    public void deletePost(Long boardId) throws Exception {
        customBoardDAO.deletePost(boardId);
    }
    
    @Override
    public List<CustomBoardVO> selectRepliesByParentId() throws Exception {
        return customBoardDAO.selectRepliesByParentId();
    }
    
    @Override
    public CustomBoardVO SelectReplyPost(Long boardId) throws Exception {
    	return customBoardDAO.SelectReplyPost(boardId);
    }
    
    @Override
    public void postCntUpdate(Long boardId) throws Exception {
    	customBoardDAO.postCntUpdate(boardId);
    }
    
}
