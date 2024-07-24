<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="egovc" uri="/WEB-INF/tlds/egovc.tld" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세보기</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
        h1, h2 {
            color: #333;
            margin-bottom: 20px;
        }
        .post-table, .reply-table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .post-table th, .post-table td, .reply-table th, .reply-table td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        .post-table th {
            background-color: #f4f4f4;
            color: #555;
            width: 15%;
        }
        .reply-table th {
            background-color: #f4f4f4;
            color: #555;
        }
        .action-links {
            margin-top: 20px;
            text-align: right;
        }
        .action-links a {
            display: inline-block;
            margin-right: 10px;
            padding: 10px 20px;
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
        .action-links a:hover {
            background-color: #0056b3;
        }
        .back-link {
            display: inline-block;
            margin-bottom: 20px;
            color: #007bff;
            text-decoration: none;
        }
        .back-link:hover {
            text-decoration: underline;
        }
        .reply-form {
            margin-top: 20px;
            border-top: 1px solid #ddd;
            padding-top: 20px;
        }
        .reply-form input, .reply-form textarea {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .reply-form button {
            display: block;
            width: 100px;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .reply-form button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<!-- 게시글 상세 View 페이지 -->
    <div class="container">
        <h1>게시글 상세보기</h1>
        <a class="back-link" href="postList.do">게시글 목록</a>
        <!-- 부모 게시글 상세 정보 -->
        <table class="post-table">
            <tr>
                <th>제목</th>
                <td>${post.title}</td>
                <c:choose>
                <%-- 만약 수정일이 존재한다면 수정일 표시 --%>
                <c:when test="${not empty post.updatedDt }">
                	                <th>수정일</th>
                <td>${post.updatedDt}</td>
                </c:when>
                <%-- 만약 수정일이 존재하지 않는다면 작성일 표시 --%>
                <c:otherwise>
                                <th>작성일</th>
                <td>${post.createdAt}</td>
                </c:otherwise>
                </c:choose>
            </tr>
            <tr>
                <th>조회수</th>
                <td>${post.postCnt}</td>
                <th>글쓴이</th>
                <td>${post.author}</td>
            </tr>
            <tr>
            </tr>
            <tr>
                <th colspan="4">내용</th>
            </tr>
            <tr>
                <td colspan="4">${post.content}</td>
            </tr>
            <c:if test="${not empty post.atchFileId}">
                <tr> 
                    <th colspan="4">첨부파일 목록</th>
                </tr>
                <tr>
                    <td colspan="4">
                    	<!-- 첨부파일은 게시글의 atchFileId를 주면 내부적으로 파일명 암호화를 하여 다운로드 경로 제공 -->
                        <c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
                            <c:param name="param_atchFileId" value="${egovc:encrypt(post.atchFileId)}" />
                        </c:import>
                    </td>
                </tr>
            </c:if>
        </table>

        <!-- 답변이 존재한다면 표시 -->
        <c:if test="${not empty replyPost}">
            <h2>답변</h2>
            <table class="reply-table">
                <tr>
                    <th>작성자</th>
                    <td>${replyPost.author}</td>
                    <th>작성일</th>
                    <td>${replyPost.createdAt}</td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td colspan="3">${replyPost.title}</td>
                </tr>
                <tr>
                    <th colspan="4">내용</th>
                </tr>
                <tr>
                    <td colspan="4">${replyPost.content}</td>
                </tr>
                <c:if test="${not empty replyPost.atchFileId}">
                    <tr> 
                        <th colspan="4">첨부파일 목록</th>
                    </tr>
                    <tr>
                        <td colspan="4">
                        <!-- 답변에도 첨부파일이 존재한다면 표시 -->
                            <c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
                                <c:param name="param_atchFileId" value="${egovc:encrypt(replyPost.atchFileId)}" />
                            </c:import>
                        </td>
                    </tr>
                </c:if>
            </table>
        </c:if>

        <c:if test="${empty replyPost}">
            <p>답변이 없습니다.</p>
        </c:if>
        
        <!-- 댓글 목록 표시 -->
        <c:import url="/cop/bbs/replyList.do?postId=${post.boardId}" charEncoding="utf-8">
        </c:import>
    </div>
</body>
</html>
