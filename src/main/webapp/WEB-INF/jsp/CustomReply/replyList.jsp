<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>댓글 목록</title>
    <style>
        .reply-container {
            margin-top: 20px;
        }
        .reply-item {
            border-bottom: 1px solid #ddd;
            padding: 10px 0;
        }
        .reply-item:last-child {
            border-bottom: none;
        }
        .reply-item .reply {
            margin-left: 20px;
        }
        .reply-item .reply p {
            margin: 0;
        }
        .reply-item .reply a {
            margin-right: 10px;
        }
    </style>
</head>
<body>
<!-- 댓글 목록 View 페이지 -->
    <div class="reply-container">
        <h2>댓글 목록</h2>

        <!-- 댓글을 출력합니다. -->
        <c:forEach var="comment" items="${replyList}">
            <c:if test="${comment.parentId == null}">
                <div class="reply-item">
                    <p><strong>${comment.author}</strong> ${comment.createdAt}</p>
                    <p>${comment.content}</p>
                    <a href="writeReply.do?postId=${comment.postId}&parentId=${comment.id}">답글</a> | 
                    <a href="editReply.do?id=${comment.id}">수정</a> | 
                    <a href="deleteReply.do?id=${comment.id}&postId=${comment.postId}">삭제</a>

                    <!-- 댓글의 답글을 출력합니다. -->
                    <c:forEach var="reply" items="${replyList}">
                        <c:if test="${reply.parentId == comment.id}">
                            <div class="reply-item reply">
                                &nbsp<img src="/sht_webapp/images/reply_arrow.gif" alt="reply arrow"><p><strong>${reply.author}</strong> ${reply.createdAt}</p></img>
                                <p>${reply.content}</p>
                                <a href="editReply.do?id=${reply.id}">수정</a> | 
                                <a href="deleteReply.do?id=${reply.id}&postId=${reply.postId}">삭제</a>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </c:if>
        </c:forEach>

        <!-- 새 댓글 작성 링크 -->
        <a href="writeReply.do?postId=${postId}">새 댓글 작성</a>
    </div>
</body>
</html>
