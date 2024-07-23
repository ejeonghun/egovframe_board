package egovframework.let.uat.uia.web;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.let.uat.uia.service.EgovLoginService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.egovframe.rte.fdl.cmmn.trace.LeaveaTrace;
import org.egovframe.rte.fdl.property.EgovPropertyService;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 일반 로그인을 처리하는 컨트롤러 클래스
 * @author 공통서비스 개발팀 박지욱
 * @since 2009.03.06
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *  수정일      수정자      수정내용
 *  -------            --------        ---------------------------
 *  2009.03.06  박지욱     최초 생성
 *  2011.08.31  JJY            경량환경 템플릿 커스터마이징버전 생성
 *
 *  </pre>
 */
@Controller
public class EgovLoginController {

	/** EgovLoginService */
	@Resource(name = "loginService")
	private EgovLoginService loginService;

	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** TRACE */
	@Resource(name = "leaveaTrace")
	LeaveaTrace leaveaTrace;
	
	
	private static final Logger LOGGER = LogManager.getLogger(EgovLoginController.class);

	/**
	 * 로그인 화면으로 들어간다
	 * @param vo - 로그인후 이동할 URL이 담긴 LoginVO
	 * @return 로그인 페이지
	 * @exception Exception
	 */
	@RequestMapping(value = "/uat/uia/egovLoginUsr.do")
	public String loginUsrView(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		return "cmm/uat/uia/EgovLoginUsr";
	}

	/**
	 * 일반 로그인을 처리한다
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 로그인결과(세션정보)
	 * @exception Exception
	 */
	@RequestMapping(value = "/uat/uia/actionLogin.do")
	public String actionLogin(@ModelAttribute("loginVO") LoginVO loginVO, HttpServletResponse response, HttpServletRequest request, ModelMap model) throws Exception {

		// 접속IP
		/* String userIp = request.getRemoteAddr(); */
		
		// 1. 일반 로그인 처리
		LoginVO resultVO = loginService.actionLogin(loginVO);

		boolean loginPolicyYn = true;
		

		if (resultVO != null && resultVO.getId() != null && !resultVO.getId().equals("") && loginPolicyYn) {
			// 2. spring security 연동
						request.getSession().setAttribute("LoginVO", resultVO);
						
						// User roles 설정
				        List<GrantedAuthority> authorities = new ArrayList<>();
				        if (resultVO.getUserSe().equals("USR") || resultVO.getId().equals("admin")) {
				        	authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 기본 역할 설정
				        } else {
				        	authorities.add(new SimpleGrantedAuthority("ROLE_USER")); // 기본 역할 설정
				        }
				        authorities.add(new SimpleGrantedAuthority("ROLE_ANONYMOUS")); // 기본 역할 설정
				        
				        // resultVO에 역할 정보가 있다면 추가적으로 설정
				        // authorities.add(new SimpleGrantedAuthority(resultVO.getRole()));

				        // 사용자의 id와 password를 스프링 시큐리티에 저장함
				        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(resultVO.getId(), resultVO.getPassword(), authorities);

				        // Set authentication in context
				        SecurityContext securityContext = SecurityContextHolder.getContext();
				        securityContext.setAuthentication(authentication);
				        request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

				        LOGGER.debug(SecurityContextHolder.getContext().getAuthentication() + " 시큐리티 유저 정보 ");
			return "redirect:/cmm/main/mainPage.do"; // 성공 시 페이지 forward 사용 시 브라우저에서 뒤로가기 시 양식 제출뜸 
		} else {

			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "cmm/uat/uia/EgovLoginUsr";
		}

	}

	/**
	 * 로그인 후 메인화면으로 들어간다
	 * @param
	 * @return 로그인 페이지
	 * @exception Exception
	 */
	@RequestMapping(value = "/uat/uia/actionMain.do")
	public String actionMain(ModelMap model) throws Exception {

		// 1. 사용자 인증 처리
		Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
		if (!isAuthenticated) {
			model.addAttribute("message", egovMessageSource.getMessage("fail.common.login"));
			return "cmm/uat/uia/EgovLoginUsr"; // 로그인이 정상적으로 처리되지 않았을 때나 인증정보가 없을 때 다시 로그인 창으로
		}

		// 2. 메인 페이지 이동
		return "forward:/cmm/main/mainPage.do";
	}

	/**
	 * 로그아웃한다.
	 * @return String
	 * @exception Exception
	 */
	@RequestMapping(value = "/uat/uia/actionLogout.do")
	public String actionLogout(HttpServletRequest request, ModelMap model) throws Exception {
		request.getSession().setAttribute("LoginVO", null); // 세션의 정보를 삭제
		RequestContextHolder.getRequestAttributes().removeAttribute("LoginVO", RequestAttributes.SCOPE_SESSION);
		SecurityContextHolder.getContext().setAuthentication(null); // 스프링 시큐리티 인증 정보 삭제
		LOGGER.debug(SecurityContextHolder.getContext().getAuthentication() + " 시큐리티 유저 정보 "); // 유저 정보 한번 확인 -> 정상 삭제됨
		return "forward:/cmm/main/mainPage.do";
	}

	
	class RequestWrapperForSecurity extends HttpServletRequestWrapper {
		private String username = null;
		private String password = null;
		
		public RequestWrapperForSecurity(HttpServletRequest request, String username, String password) {
			super(request);

			this.username = username;
			this.password = password;
		}
		
		@Override
		public String getServletPath() {		
			return ((HttpServletRequest) super.getRequest()).getContextPath() + "/egov_security_login";
		}

		@Override
		public String getRequestURI() {		
			return ((HttpServletRequest) super.getRequest()).getContextPath() + "/egov_security_login";
		}

		@Override
		public String getParameter(String name) {
			if (name.equals("egov_security_username")) {
				return username;
			}

			if (name.equals("egov_security_password")) {
				return password;
			}

			return super.getParameter(name);
		}
}
}