<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #f2f2f2;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .header span, .header a {
            font-size: 16px;
        }
        .header a {
            text-decoration: none;
            color: #007bff;
        }
        .header a:hover {
            text-decoration: underline;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        .reply {
            margin-left: 40px; /* 들여쓰기 */
            color: #666; /* 답변 글에 대한 스타일 */
            background-color: #f9f9f9; /* 답변의 배경 색상 */
        }
        .reply td {
            border-left: 3px solid #ddd; /* 답변 구분을 위한 선 */
        }
    </style>
    <script>
        function confirmDelete(url) {
            if (confirm("정말로 삭제하시겠습니까?")) {
                window.location.href = url;
            }
        }
    </script>
</head>
<body>
<!-- 게시글 리스트 View 페이지 -->
<!-- 로그인 정보 -->
    <div class="header">
        <div>
            <h1>게시글 목록</h1>
        </div>
        <div>
            <c:choose>
                <c:when test="${username == 'anonymousUser'}">
                    <a href="/sht_webapp/uat/uia/egovLoginUsr.do">로그인</a>
                </c:when>
                <c:otherwise>
                    <span>${username}님 환영합니다.</span>
                    <a href="/sht_webapp/uat/uia/actionLogout.do">로그아웃</a>
                </c:otherwise>
            </c:choose>
        </div>
        <a href="<c:url value='/cop/bbs${prefix}/writePost.do'/>">게시글 작성</a><br><br>
    </div>
    <table>
        <thead>
            <tr>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>내용</th>
                <th>조회수</th>
                <th>첨부파일 존재</th>
                <th>답변 게시글 여부</th>
                <th>수정/삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="post" items="${postList}">
                <tr>
                    <td><a href="viewPost.do?boardId=${post.boardId}">${post.title}</a></td>
                    <td>${post.author}</td>
                    <td>${post.createdAt}</td>
                    <td>${post.content}</td>
                                        <td>${post.postCnt }</td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty post.atchFileId}">Y</c:when>
                            <c:otherwise>N</c:otherwise>
                        </c:choose>
                    </td>

                    <td>N</td>
                    <td>
                        <a href="editPost.do?boardId=${post.boardId}">수정</a> / 
                        <a href="javascript:void(0);" onclick="confirmDelete('deletePost.do?boardId=${post.boardId}')">삭제</a>
                    </td>
                </tr>
                <!-- 답변 게시글들 표시 -->
                <c:forEach var="replyPost" items="${replyPostList}">
                    <c:if test="${replyPost.parentId == post.boardId}">
                        <tr class="reply">
                            <td>&nbsp<img src="/sht_webapp/images/reply_arrow.gif" alt="reply arrow"><a href="viewPost.do?boardId=${replyPost.parentId}">  답변 : ${post.title} | ${replyPost.title}</a></td>
                            <td>${replyPost.author}</td>
                            <td>${replyPost.createdAt}</td>
                            <td>${replyPost.content}</td>
                            <td></td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty replyPost.atchFileId}">Y</c:when>
                                    <c:otherwise>N</c:otherwise>
                                </c:choose>
                            </td>                            
                            <td>Y</td>
                            <td>
                                <a href="editPost.do?boardId=${replyPost.boardId}">수정</a> / 
                                <a href="javascript:void(0);" onclick="confirmDelete('deletePost.do?boardId=${replyPost.boardId}')">삭제</a>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
