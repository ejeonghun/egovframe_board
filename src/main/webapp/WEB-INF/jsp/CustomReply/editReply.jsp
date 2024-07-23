<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>댓글 수정</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 40px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
            margin-bottom: 20px;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }
        input[type="text"], textarea {
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 10px;
            margin-bottom: 15px;
            font-size: 16px;
        }
        textarea {
            resize: vertical;
            min-height: 100px;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .action-links a {
            text-decoration: none;
            color: #007bff;
            font-size: 16px;
        }
        .action-links a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<!-- 댓글 수정 View 페이지 -->
<body>
    <div class="container">
        <h1>댓글 수정</h1>
        <form action="updateReply.do" method="post">
            <input type="hidden" name="id" value="${reply.id}">
            <input type="hidden" name="postId" value="${reply.postId}">
            <label for="author">작성자:</label>
            <input type="text" id="author" name="author" value="${reply.author}" readonly>
            <label for="content">내용:</label>
            <textarea id="content" name="content">${reply.content}</textarea>
            <input type="submit" value="수정">
        </form>
        <br>
        <div class="action-links">
            <a href="replyList.do?postId=${reply.postId}">댓글 목록</a>
        </div>
    </div>
</body>
</html>
