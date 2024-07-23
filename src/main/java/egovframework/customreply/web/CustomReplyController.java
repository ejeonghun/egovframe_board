package egovframework.customreply.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.customreply.service.CustomReplyService;
import egovframework.customreply.service.CustomReplyVO;

@Controller
public class CustomReplyController {
    
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
    

    /** 댓글 목록 로직 */
    @RequestMapping("/cop/bbs/replyList.do")
    public String selectReplyList(@RequestParam("postId") Long postId, Model model) throws Exception {
        List<CustomReplyVO> replyList = customReplyService.selectRepliesByPostId(postId);
        model.addAttribute("replyList", replyList);
        model.addAttribute("postId", postId);
        return "/CustomReply/replyList";
    }

    /** 댓글 생성 View 핸들러 */
    @RequestMapping("/cop/bbs/writeReply.do")
    public String writeReplyPage(@RequestParam("postId") Long postId, @RequestParam(value = "parentId", required = false) Long parentId, Model model) throws Exception {
        String username = getUsername();
        
        if (username.equals("anonymousUser")) { // 로그인이 안되어 있으면 로그인창으로
            return "cmm/uat/uia/EgovLoginUsr";
        }

        model.addAttribute("username", username);
        model.addAttribute("postId", postId);
        model.addAttribute("parentId", parentId); // parentId는 null일 수 있음
        return "/CustomReply/writeReply";
    }
    

    /** 댓글 생성 로직 */
    @RequestMapping("/cop/bbs/insertReply.do")
    public String insertCustomReply(CustomReplyVO reply) throws Exception {
        customReplyService.insertCustomReply(reply);
        return "redirect:/cop/bbs/viewPost.do?boardId=" + reply.getPostId();
    }

    /** 댓글 수정 View 핸들러 */
    @RequestMapping("/cop/bbs/editReply.do")
    public String editReplyPage(@RequestParam("id") Long id, Model model) throws Exception {
        CustomReplyVO reply = customReplyService.selectReply(id);
        String username = getUsername();

        // 현재 사용자가 댓글 작성자이거나 ROLE_ADMIN 권한을 가지고 있는지 확인
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        if (!username.equals(reply.getAuthor()) && !isAdmin) {
            return "errorPage"; // 권한이 없으면 errorPage로 리디렉션
        }

        model.addAttribute("reply", reply);
        return "/CustomReply/editReply";
    }

    /** 댓글 수정 로직 */
    @RequestMapping("/cop/bbs/updateReply.do")
    public String updateReply(CustomReplyVO reply) throws Exception {
        String username = getUsername();

        // 현재 사용자가 ROLE_ADMIN 권한을 가지고 있는지 확인
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        CustomReplyVO existingReply = customReplyService.selectReply(reply.getId());

        // 댓글이 존재하고, 현재 사용자가 댓글 작성자이거나 관리자일 경우 수정 허용
        if (existingReply != null && (username.equals(existingReply.getAuthor()) || isAdmin)) {
            customReplyService.updateReply(reply);
            return "redirect:/cop/bbs/viewPost.do?boardId=" + reply.getPostId();
        }

        return "errorPage"; // 권한이 없거나 댓글이 존재하지 않는 경우 errorPage로 리디렉션
    }

    /** 댓글 삭제 */
    @RequestMapping("/cop/bbs/deleteReply.do")
    public String deleteReply(@RequestParam("id") Long id, @RequestParam("postId") Long postId) throws Exception {
        CustomReplyVO reply = customReplyService.selectReply(id);
        String username = getUsername();

        // 현재 사용자가 ROLE_ADMIN 권한을 가지고 있는지 확인
        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        if (!username.equals(reply.getAuthor()) && !isAdmin) {
            return "errorPage"; // 권한이 없으면 errorPage로 리디렉션
        }
        
        customReplyService.deleteParentReplyLogic(id);
        customReplyService.deleteReply(id);
        return "redirect:/cop/bbs/viewPost.do?boardId=" + postId;
    }

}