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
            max-width: 900px;
            margin: 20px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1, h2 {
            color: #333;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        tr:nth-child(even) {
            background-color: #fafafa;
        }
        .reply {
            margin-top: 20px;
            padding: 10px;
            background-color: #f9f9f9;
            border-left: 4px solid #007bff;
        }
        .action-links a {
            margin-right: 15px;
            text-decoration: none;
            color: #007bff;
        }
        .action-links a:hover {
            text-decoration: underline;
        }
        .file-info {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<!-- 게시글 상세 View 페이지 -->
    <div class="container">
        <h1>게시글 상세보기</h1>
		<a href="postList.do"> 게시글 목록</a>
        <!-- 부모 게시글 상세 정보 -->
        <h2>게시글</h2>
        <table>
<%--             <tr>
                <th>ID</th>
                <td>${post.boardId}</td>
            </tr> --%>
            <tr>
                <th>작성자</th>
                <td>${post.author}</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td>${post.createdAt}</td>
            </tr>
            <tr>
                <th>제목</th>
                <td>${post.title}</td>
            </tr>
            <tr>
                <th>내용</th>
                <td>${post.content}</td>
            </tr>
<%--             <tr>
                <th>첨부파일 ID</th>
                <td>${post.atchFileId}</td>
            </tr> --%>
            <c:if test="${not empty post.atchFileId}">
                
                <tr> 
                    <th>첨부파일 목록</th>
                    <td colspan="2">
                        <c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
                            <c:param name="param_atchFileId" value="${egovc:encrypt(post.atchFileId)}" />
                        </c:import>
                    </td>
                </tr>
            </c:if>
            <tr>
                <th>답변 여부</th>
                <td><c:if test="${not empty replyPost}">완료</c:if><c:if test="${empty replyPost}">대기중</c:if></td>
            </tr>
<%--             <tr>
                <th>부모 ID</th>
                <td>${post.parentId}</td>
            </tr> --%>
        </table>

        <div class="action-links">
            <c:if test="${empty replyPost}">
                <a href="replyPost.do?parentId=${post.boardId}">답변 작성</a>
            </c:if>
        </div>

        <!-- 답변 표시 -->
        <c:if test="${not empty replyPost}">
            <h2>답변</h2>
            <div class="reply">
                <table>
<%--                     <tr>
                        <th>ID</th>
                        <td>${replyPost.boardId}</td>
                    </tr> --%>
                    <tr>
                        <th>작성자</th>
                        <td>${replyPost.author}</td>
                    </tr>
                    <tr>
                        <th>작성일</th>
                        <td>${replyPost.createdAt}</td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td>${replyPost.title}</td>
                    </tr>
                    <tr>
                        <th>내용</th>
                        <td>${replyPost.content}</td>
                    </tr>
<%--                     <tr>
                        <th>첨부파일 ID</th>
                        <td>${replyPost.atchFileId}</td>
                    </tr> --%>
<%--                     <tr>
                        <th>부모 ID</th>
                        <td>${replyPost.parentId}</td>
                    </tr> --%>
                     <c:if test="${not empty replyPost.atchFileId}">
                <tr> 
                    <th>첨부파일 목록</th>
                    <td colspan="2">
                        <c:import url="/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
                            <c:param name="param_atchFileId" value="${egovc:encrypt(replyPost.atchFileId)}" />
                        </c:import>
                    </td>
                </tr>
            </c:if>
                </table>
            </div>
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
