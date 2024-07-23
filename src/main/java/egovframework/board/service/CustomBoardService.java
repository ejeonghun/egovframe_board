package egovframework.board.service;

import java.util.List;

public interface CustomBoardService {

    void insertPost(CustomBoardVO post) throws Exception;

    CustomBoardVO selectPost(Long boardId) throws Exception;

    List<CustomBoardVO> selectPostList() throws Exception;

    void insertReplyPost(CustomBoardVO reply) throws Exception;

    void updatePost(CustomBoardVO post) throws Exception;

    void deletePost(Long boardId) throws Exception;
    
    List<CustomBoardVO> selectRepliesByParentId() throws Exception;
    
    CustomBoardVO SelectReplyPost(Long boardId) throws Exception;
}
