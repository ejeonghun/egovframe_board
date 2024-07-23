package egovframework.let.uat.uia.service.impl;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.RegisterVO;

@Repository("registerDAO")
public class RegisterDAO extends EgovAbstractMapper {

    /**
     * 회원가입을 처리한다
     * @param vo RegisterVO
     * @return int - 삽입된 행의 수
     * @exception Exception
     */
    public int registerUser(RegisterVO vo) throws Exception {
        return insert("registerDAO.registerUser", vo);
    }
    
    /**
     * 현재 멤버 시퀀스 조회
     * 
     */
    public String getNextEmplyrId() throws Exception {
        return selectOne("registerDAO.getNextEmplyrId");
    }
}
