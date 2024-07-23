package egovframework.customreply.service;

import java.util.List;

public interface CustomReplyService {
    void insertCustomReply(CustomReplyVO reply) throws Exception;
    CustomReplyVO selectReply(Long id) throws Exception;
    void updateReply(CustomReplyVO reply) throws Exception;
    void deleteReply(Long id) throws Exception;
    List<CustomReplyVO> selectRepliesByPostId(Long postId) throws Exception;
    List<CustomReplyVO> selectRepliesByParentReplyId(Long parentId) throws Exception;
    void deleteParentReplyLogic(Long id) throws Exception;
    void deleteReplyPostId(Long post_id) throws Exception;
}
