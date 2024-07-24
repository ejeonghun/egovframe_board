package egovframework.board.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import egovframework.board.service.CustomBoardService;
import egovframework.board.service.CustomBoardVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.customreply.service.CustomReplyService;

@Controller
public class CustomBoardController {

    private static final Logger LOGGER = LogManager.getLogger(CustomBoardController.class);

    @Resource(name = "customBoardService") // 게시판 관련 리소스
    private CustomBoardService customBoardService;
    
    @Resource(name = "EgovFileMngUtil") // 파일 업로드 관련 리소스
    private EgovFileMngUtil fileUtil;
    
    @Resource(name = "EgovFileMngService") // 파일 업로드 관련 리소스
    private EgovFileMngService fileMngService;
    
    @Resource(name = "customReplyService") // 댓글 관련 리소스
    private CustomReplyService customReplyService;

    
    /** 시큐리티에 저장된 정보 가져오는 메소드 */
    private String getUsername() {
    	// 시큐리티 컨텍스트에 저장된 사용자의 인증 정보를 가져온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String username;
        if (principal instanceof UserDetails) { // 로그인이 되어있다면
            username = ((UserDetails) principal).getUsername(); // 사용자 이름
        } else if (principal instanceof String) {
            username = (String) principal;
        } else { // 만약 로그인을 안한 사용자라면
            username = "anonymousUser";
        }
        return username;
    }

    
    /** 게시글 목록 View */
    @RequestMapping("/cop/bbs/postList.do")
    public String selectPostList(Model model) throws Exception {
    	String username = getUsername(); 
        List<CustomBoardVO> postList = customBoardService.selectPostList();
        List<CustomBoardVO> replyPostList = customBoardService.selectRepliesByParentId();
        model.addAttribute("username", username); // 사용자 유저네임
        model.addAttribute("postList", postList); // 게시글 목록 List
        model.addAttribute("replyPostList", replyPostList); // 댓글 목록 List
        return "/CustomBoard/postList";
    }
    
    /** 게시글 작성 View */
    @RequestMapping("/cop/bbs/writePost.do")
    public String writePostPage(Model model) throws Exception {
        String username = getUsername();
        if (username.equals("anonymousUser")) { // 로그인을 하지 않았다면 
            return "cmm/uat/uia/EgovLoginUsr";  // 로그인 화면으로
        }
        model.addAttribute("username", username); // 게시글에 들어갈 유저정보를 주입 -> input 태그로 다시 들어옴
        return "/CustomBoard/writePost";
    }
    
    /** 게시글 수정 View */
    @RequestMapping("/cop/bbs/editPost.do")
    public String editPostPage(Model model, @RequestParam("boardId") Long boardId) throws Exception {
        CustomBoardVO post = customBoardService.selectPost(boardId); // 수정할 게시글을 불러옴
        String currentUsername = getUsername(); // 현재 유저의 정보를 가져옴
        
        // 현재 사용자가 ROLE_ADMIN 권한을 가지고 있는지 확인
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        
        // 게시글이 존재하고, 현재 사용자가 게시글 작성자이거나 관리자일 때만 수정 페이지로 이동
        if (post != null && !(currentUsername.equals(post.getAuthor()) || isAdmin)) {
            // 사용자 권한이 없으면 게시글 목록으로 리다이렉트
            LOGGER.debug("게시글 작성자가 아님 ! (수정) : " + currentUsername);
            LOGGER.debug( " 어드민 여부 " + isAdmin);
            return "errorPage";
        }
        
        model.addAttribute("post", post); // 수정할 게시글을 보냄 
        return "/CustomBoard/editPost";
    }
    
    /** 게시글 답변 작성 View */
    @RequestMapping("/cop/bbs/replyPost.do")
    public String replyPostPage(@RequestParam("parentId") Long parentId, Model model) throws Exception {
        String username = getUsername();
        if (username.equals("anonymousUser")) { // 만약 로그인을 하지 않았다면
            return "cmm/uat/uia/EgovLoginUsr";  // 로그인 페이지로 
        }
        model.addAttribute("username", username); // 유저 정보를 보냄 -> 게시글 작성에 필요
        return "/CustomBoard/replyPost";
    }

    
    /** 게시글 작성 로직 */
    @RequestMapping("/cop/bbs/insertPost.do")
    public String insertPost(CustomBoardVO post, final MultipartHttpServletRequest multiRequest) throws Exception {
        List<FileVO> result = null;
        String atchFileId = "";
        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (!files.isEmpty()) {  // 만약 파일이 있다면
            result = fileUtil.parseFileInf(files, "BBS_", 0, "", ""); // 받은 파일의 정보를 가공함
            atchFileId = fileMngService.insertFileInfs(result);       // 가공한 파일의 데이터를 서버에 업로드하고, FileId를 반환함 
        }
        post.setAtchFileId(atchFileId);      // 게시글의 파일ID 컬럼에 추가함
        customBoardService.insertPost(post); // 게시글을 DB에 저장함 
        return "redirect:/cop/bbs/postList.do";
    }

    /** 게시글 조회 로직 */
    @RequestMapping("/cop/bbs/viewPost.do")
    public String selectPost(@RequestParam("boardId") Long boardId, Model model) throws Exception {
    	customBoardService.postCntUpdate(boardId);    // 조회수를 +1 증가시킴
        CustomBoardVO post = customBoardService.selectPost(boardId);  // 현재 게시물을 DB에서 불러온다.
        CustomBoardVO replyPost = customBoardService.SelectReplyPost(boardId); // 게시물의 답변을 불러온다.
        model.addAttribute("post", post);             
        model.addAttribute("replyPost", replyPost);
        return "/CustomBoard/viewPost";
    }

    /** 게시글 답변 작성 로직 */
    @RequestMapping("/cop/bbs/insertReplyPost.do")
    public String insertReplyPost(CustomBoardVO reply, final MultipartHttpServletRequest multiRequest) throws Exception {
        List<FileVO> result = null;
        String atchFileId = "";
        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (!files.isEmpty()) { // 첨부파일이 있으면 서버에 업로드 
            result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
            atchFileId = fileMngService.insertFileInfs(result);
        }
        reply.setAtchFileId(atchFileId);
        customBoardService.insertReplyPost(reply);
        return "redirect:/cop/bbs/postList.do";
    }

    /** 게시글 수정 로직 */
    @RequestMapping("/cop/bbs/updatePost.do")
    public String updatePost(CustomBoardVO post) throws Exception {
        String currentUsername = getUsername();
        
        // 현재 사용자가 ROLE_ADMIN 권한을 가지고 있는지 확인
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        
        CustomBoardVO existingPost = customBoardService.selectPost(post.getBoardId());
        
        // 게시글이 존재하고, 현재 사용자가 게시글 작성자이거나 관리자일 경우 수정 허용
        if (existingPost != null && (currentUsername.equals(existingPost.getAuthor()) || isAdmin)) {
            customBoardService.updatePost(post);
            return "redirect:/cop/bbs/viewPost.do?boardId=" + post.getBoardId();
        }
        
        // 권한이 없거나 게시글이 존재하지 않는 경우 errorPage로 리디렉션
        return "errorPage";
    }


    /** 게시글 삭제 로직 */
    @RequestMapping("/cop/bbs/deletePost.do")
    public String deletePost(@RequestParam("boardId") Long boardId) throws Exception {
        String currentUsername = getUsername();
        
        // 현재 사용자가 ROLE_ADMIN 권한을 가지고 있는지 확인
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        
        CustomBoardVO post = customBoardService.selectPost(boardId);
        
        // 게시글 작성자와, 어드민 권한을 가진 사용자만이 삭제가 가능하다. 
        if ((post != null && currentUsername.equals(post.getAuthor()) )|| isAdmin) { // post가 null이 아니고 사용자이름과 현재 시큐리티에 저장된 인증정보가 같을 때
            customReplyService.deleteReplyPostId(boardId);
            customBoardService.deletePost(boardId);
        } else {
        	return "errorPage";
        }
        return "redirect:/cop/bbs/postList.do";
    }
   
}
