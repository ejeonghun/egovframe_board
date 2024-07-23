package egovframework.let.cop.reply.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import egovframework.com.cmm.ReplyVO;
import egovframework.let.cop.reply.service.ReplyService;


@Controller
public class ReplyController {

	@Resource(name="replyService")
    private ReplyService replyService;

//    @RequestMapping(value = "/cop/reply/selectReplies.do", method = RequestMethod.GET)
//    public String selectReplies(@RequestParam("nttId") Long nttId, Model model) throws Exception {
//        // nttId를 사용하여 필요한 데이터를 조회
//        // List<Reply> replies = replyService.getRepliesByNttId(nttId);
//        
//        model.addAttribute("nttId", nttId);
//        // model.addAttribute("replies", replies);
//    }
	

	/*
	 * public List<ReplyVO> selectReplies(@RequestParam("nttId") Long nttId, Model
	 * model) throws Exception { ReplyVO vo = new ReplyVO(); vo.setNttId(nttId);
	 * List<ReplyVO> replyList = replyService.selectReplies(nttId);
	 * model.addAttribute("replyList", replyList); return replyList; }
	 */
    
    @RequestMapping("/cop/reply/writeReply.do")
    public String writeReply(
        @RequestParam("nttId") Long nttId,
        @RequestParam("content") String content,
        HttpServletRequest request,
        Model model) {
        try {
            ReplyVO reply = new ReplyVO();
            reply.setNttId(nttId);
         // 현재 인증 정보를 가져옵니다.
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            // Authentication에서 Principal을 가져옵니다.
            Object principal = authentication.getPrincipal();
            
            String username;
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                username = userDetails.getUsername(); // 사용자 이름
            } else if (principal instanceof String) {
                username = (String) principal;
            } else {
                throw new RuntimeException("Unknown principal type: " + principal.getClass().getName());
            }
            reply.setCreatedId(username);
            reply.setContent(content);
            replyService.insertReply(reply);
            model.addAttribute("message", "댓글 작성 성공");
            return "redirect:/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_BBBBBBBBBBBB&menuNo=43";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error writing reply: " + e.getMessage());
            return "replyForm";
        }
    }
}