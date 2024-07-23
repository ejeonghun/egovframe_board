package egovframework.customreply.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.customreply.service.CustomReplyService;
import egovframework.customreply.service.CustomReplyVO;

@Service("customReplyService")
public class CustomReplyServiceImpl implements CustomReplyService {

    @Resource(name = "customReplyDAO")
    private CustomReplyDAO customReplyDAO;

    @Override
    public void insertCustomReply(CustomReplyVO reply) throws Exception {
        customReplyDAO.insertCustomReply(reply);
    }

    @Override
    public CustomReplyVO selectReply(Long id) throws Exception {
        return customReplyDAO.selectReply(id);
    }

    @Override
    public void updateReply(CustomReplyVO reply) throws Exception {
        customReplyDAO.updateReply(reply);
    }

    @Override
    public void deleteReply(Long id) throws Exception {
        customReplyDAO.deleteReply(id);
    }

    @Override
    public List<CustomReplyVO> selectRepliesByPostId(Long postId) throws Exception {
        return customReplyDAO.selectRepliesByPostId(postId);
    }

    @Override
    public List<CustomReplyVO> selectRepliesByParentReplyId(Long parentId) throws Exception {
        return customReplyDAO.selectRepliesByParentReplyId(parentId);
    }
    
    @Override
    public void deleteParentReplyLogic(Long id) throws Exception {
    	customReplyDAO.deleteParentReplyLogic(id);
    }
    
    @Override
    public void deleteReplyPostId(Long post_id) throws Exception {
    	customReplyDAO.deleteReplyPostId(post_id);
    }
    
}