package egovframework.let.uat.uia.service;

import egovframework.com.cmm.RegisterVO;

public interface EgovRegisterService {
    /**
     * 회원가입을 처리한다
     * @param vo RegisterVO
     * @return int - 삽입된 행의 수
     * @exception Exception
     */
    public int actionRegister(RegisterVO vo) throws Exception;
}
