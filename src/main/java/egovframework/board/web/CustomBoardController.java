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

    @Resource(name = "customBoardService")
    private CustomBoardService customBoardService;
    
    @Resource(name = "EgovFileMngUtil") // 파일 업로드 관련
    private EgovFileMngUtil fileUtil;
    
    @Resource(name = "EgovFileMngService") // 파일 업로드 관련
    private EgovFileMngService fileMngService;
    
    @Resource(name = "customReplyService")
    private CustomReplyService customReplyService;

    
    /** 시큐리티에 저장된 정보 가져오는 메소드 */
    private String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername(); // 사용자 이름
        } else if (principal instanceof String) {
            username = (String) principal;
        } else {
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
        model.addAttribute("username", username);
        model.addAttribute("postList", postList);
        model.addAttribute("replyPostList", replyPostList);
        return "/CustomBoard/postList";
    }
    
    /** 게시글 작성 View */
    @RequestMapping("/cop/bbs/writePost.do")
    public String writePostPage(Model model) throws Exception {
        String username = getUsername();
        if (username.equals("anonymousUser")) {
            return "cmm/uat/uia/EgovLoginUsr";
        }
        model.addAttribute("username", username);
        return "/CustomBoard/writePost";
    }
    
    /** 게시글 수정 View */
    @RequestMapping("/cop/bbs/editPost.do")
    public String editPostPage(Model model, @RequestParam("boardId") Long boardId) throws Exception {
        CustomBoardVO post = customBoardService.selectPost(boardId);
        String currentUsername = getUsername();
        
        // 현재 사용자가 ROLE_ADMIN 권한을 가지고 있는지 확인
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        
        // 게시글이 존재하고, 현재 사용자가 게시글 작성자이거나 관리자일 때만 수정 페이지로 이동
        if (post != null && !(currentUsername.equals(post.getAuthor()) || isAdmin)) {
            // 사용자 권한이 없으면 게시글 목록으로 리다이렉트
            LOGGER.debug("게시글 작성자가 아님 ! (수정) : " + currentUsername);
            LOGGER.debug(isAdmin + " 어드민 여부 ");
            return "errorPage";
        }
        
        model.addAttribute("post", post);
        return "/CustomBoard/editPost";
    }
    
    /** 게시글 답변 작성 View */
    @RequestMapping("/cop/bbs/replyPost.do")
    public String replyPostPage(@RequestParam("parentId") Long parentId, Model model) throws Exception {
        String username = getUsername();
        if (username.equals("anonymousUser")) {
            return "cmm/uat/uia/EgovLoginUsr";
        }
        model.addAttribute("username", username);
        return "/CustomBoard/replyPost";
    }

    
    /** 게시글 작성 로직 */
    @RequestMapping("/cop/bbs/insertPost.do")
    public String insertPost(CustomBoardVO post, final MultipartHttpServletRequest multiRequest) throws Exception {
        List<FileVO> result = null;
        String atchFileId = "";
        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (!files.isEmpty()) {
            result = fileUtil.parseFileInf(files, "BBS_", 0, "", "");
            atchFileId = fileMngService.insertFileInfs(result);
        }
        post.setAtchFileId(atchFileId);
        customBoardService.insertPost(post);
        return "redirect:/cop/bbs/postList.do";
    }

    /** 게시글 조회 로직 */
    @RequestMapping("/cop/bbs/viewPost.do")
    public String selectPost(@RequestParam("boardId") Long boardId, Model model) throws Exception {
        CustomBoardVO post = customBoardService.selectPost(boardId);
        CustomBoardVO replyPost = customBoardService.SelectReplyPost(boardId);
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
        if ((post != null && currentUsername.equals(post.getAuthor()) )|| isAdmin) { // post가 null이 아니고 사용자이름과 현재 시큐리티에 저장된 인증정보가 같을 때
            customReplyService.deleteReplyPostId(boardId);
            customBoardService.deletePost(boardId);
        } else {
        	return "errorPage";
        }
        return "redirect:/cop/bbs/postList.do";
    }
   
}
