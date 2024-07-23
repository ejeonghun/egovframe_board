package egovframework.let.uat.uia.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.RegisterVO;
import egovframework.let.uat.uia.service.EgovRegisterService;

@Controller
public class EgovRegisterController {

    /** EgovRegisterService */
    @Resource(name = "registerService")
    private EgovRegisterService registerService;

    @RequestMapping(value = "/uat/uia/egovRegisterUsr.do")
    public String RegisterUsrView() throws Exception {
        return "cmm/uat/uia/EgovRegisterUsr";
    }

    @RequestMapping(value = "/uat/uia/actionRegister.do")
    public String actionRegister(@ModelAttribute("registerVO") RegisterVO registerVO, HttpServletResponse response, HttpServletRequest request, ModelMap model) throws Exception {
        String message;
        try {
            // 회원가입 서비스 호출
            int isRegistered = registerService.actionRegister(registerVO);
            if (isRegistered == 1) {
                message = "회원가입이 완료되었습니다.";
            } else {
                message = "회원가입에 실패했습니다. 다시 시도해주세요.";
            }
        } catch (Exception e) {
            message = "오류가 발생했습니다: " + e.getMessage();
        }

        // JSP로 메시지 전달
        model.addAttribute("message", message);
        return "cmm/uat/uia/EgovRegisterUsr";
    }
}
