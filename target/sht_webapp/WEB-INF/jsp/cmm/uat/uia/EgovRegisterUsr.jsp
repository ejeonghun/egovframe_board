<%--
  Class Name : Register.jsp
  Description : User registration page
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
.form-items {
    list-style: none;
    padding: 0;
    margin: 0;
}

.form-item {
    margin-bottom: 15px; /* 항목 간의 간격 조정 */
    display: flex;
    flex-direction: column;
}

.input_style {
    width: 100%; /* 입력 필드 너비 조정 */
    max-width: 300px; /* 입력 필드 최대 너비 설정 */
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    margin-top: 5px; /* 라벨과 입력 필드 사이 간격 */
}

.btn_style {
    margin-top: 20px; /* 버튼 상단 간격 조정 */
    padding: 10px 20px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.btn_style:hover {
    background-color: #45a049;
}

.message_area {
    margin-top: 20px;
    color: red;
}

.text_area ul {
    list-style-type: disc;
    margin-left: 20px;
}

.text_area ul li {
    margin-bottom: 5px;
}

#content_img_div {
    margin-bottom: 20px; /* 이미지와 폼 간격 조정 */
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="content-language" content="ko">
<title>경량환경 단순홈페이지 템플릿 - 회원가입</title>
<link href="<c:url value='/'/>css/common.css" rel="stylesheet" type="text/css">
<link href="<c:url value='/'/>css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function validateRegistration() {
    if (document.registerForm.id.value == "") {
        alert("아이디를 입력하세요");
        return false;
    } else if (document.registerForm.password.value == "") {
        alert("비밀번호를 입력하세요");
        return false;
    } else if (document.registerForm.confirmPassword.value == "") {
        alert("비밀번호 확인을 입력하세요");
        return false;
    } else if (document.registerForm.password.value != document.registerForm.confirmPassword.value) {
        alert("비밀번호가 일치하지 않습니다");
        return false;
    } else if (document.registerForm.email.value == "") {
        alert("이메일을 입력하세요");
        return false;
    } else if (document.registerForm.userNm.value == "") {
        alert("닉네임을 입력하세요");
        return false;
    }  else {
            document.registerForm.action = "<c:url value='/uat/uia/actionRegister.do'/>";
            document.registerForm.submit();
        }
}

</script>
</head>
<body>
    <noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
    <!-- 전체 레이어 시작 -->
    <div id="wrap">
        <!-- header 시작 -->
        <div id="header_mainsize">
            <c:import url="/EgovPageLink.do?linkIndex=0" />
        </div>
        <div id="topnavi">
            <c:import url="/EgovPageLink.do?linkIndex=1" />
        </div>
        <!-- //header 끝 -->
        <!-- container 시작 -->
        <div id="container">
            <!-- 좌측메뉴 시작 -->
            <div id="leftmenu">
                <c:import url="/EgovPageLink.do?linkIndex=2" />
            </div>
            <!-- //좌측메뉴 끝 -->
            <!-- content 시작 -->
            <div id="content">
                <div id="cur_loc">
                    <div id="cur_loc_align">
                        <ul>
                            <li>HOME</li>
                            <li>&gt;</li>
                            <li><strong>회원가입</strong></li>
                        </ul>
                    </div>
                </div>
                <!-- 타이틀 이미지 -->
                <br/>
                <h3>회원가입 페이지 모든 정보를 입력해주세요.</h3>
                <div class="user_register">
                    <form:form name="registerForm" method="post" action="actionRegister.do">
                        <div class="user_register_ultop">
                            <ul class="form-items">

                                <li class="form-item">
                                    <label for="id">아이디</label>
                                    <input type="text" class="input_style" title="아이디를 입력하세요." id="id" name="id" maxlength="10" />
                                </li>
                                <li class="form-item">
                                    <label for="password">비밀번호</label>
                                    <input type="password" class="input_style" maxlength="25" title="비밀번호를 입력하세요." id="password" name="password" />
                                </li>
                                <li class="form-item">
                                    <label for="confirmPassword">비밀번호 확인</label>
                                    <input type="password" class="input_style" maxlength="25" title="비밀번호 확인을 입력하세요." id="confirmPassword"/>
                                </li>
                                <li class="form-item">
                                    <label for="email">닉네임</label>
                                    <input type="text" class="input_style" title="닉네임을 입력하세요." id="userNm" name="userNm" />
                                </li>
                                <li class="form-item">
                                    <label for="email">이메일</label>
                                    <input type="text" class="input_style" title="이메일을 입력하세요." id="email" name="email" />
                                </li>
                            </ul>
                            <button type="button" class="btn_style" onclick="validateRegistration()">회원가입</button>
                        </div>
                        <input type="hidden" name="message" value="${message}" />
                    </form:form>
                    <div class="text_area">
                        <ul>
                            <li>비밀번호는 6~12자의 영문 대/소문자, 숫자, 특수문자를 혼합해서 사용하실 수 있습니다.</li>
                            <li>쉬운 비밀번호나 자주 쓰는 사이트의 비밀번호가 같을 경우, 도용되기 쉬우므로 주기적으로 변경하셔서 사용하는 것이 좋습니다.</li>
                        </ul>
                    </div>
                    <!-- 메시지 표시 영역 -->
                    <div class="message_area">
                        <c:if test="${not empty message}">
                            <p>${message}</p>
                        </c:if>
                    </div>
                </div>
            </div>
            <!-- //content 끝 -->
        </div>
        <!-- footer 시작 -->
        <div id="footer">
            <c:import url="/EgovPageLink.do?linkIndex=3" />
        </div>
        <!-- //footer 끝 -->
    </div>
    <!-- //전체 레이어 끝 -->
</body>
</html>
