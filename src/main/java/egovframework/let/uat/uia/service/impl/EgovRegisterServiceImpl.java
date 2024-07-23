package egovframework.let.uat.uia.service.impl;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.RegisterVO;
import egovframework.let.uat.uia.service.EgovRegisterService;
import egovframework.let.utl.sim.service.EgovFileScrty;

@Service("registerService")
public class EgovRegisterServiceImpl extends EgovAbstractServiceImpl implements EgovRegisterService {

    @Resource(name = "registerDAO")
    private RegisterDAO registerDAO;

    /**
     * 회원 가입을 처리한다.
     * @param vo RegisterVO
     * @return int - 삽입된 행의 수
     * @exception Exception
     */
    @Override
    public int actionRegister(RegisterVO vo) throws Exception {
        // 1. 입력한 비밀번호를 암호화한다.
        String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId());
        vo.setPassword(enpassword);
        String esntlId = registerDAO.getNextEmplyrId();
        
        vo.setOrgnztId("ORGNZT_0000000000000");
        vo.setEsntlId(esntlId);
        vo.setPasswordCnsr("none");
        vo.setPasswordHint("nome");
        vo.setGroudId("GROUP_00000000000000");
        
        // 2. 회원가입을 처리한다.
        int result = registerDAO.registerUser(vo);

        // 3. 결과를 리턴한다.
        return result;  // 삽입된 행의 수를 반환합니다.
    }
}
