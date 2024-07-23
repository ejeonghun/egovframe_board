<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        label {
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"],
        textarea {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            width: calc(100% - 22px);
        }
        textarea {
            resize: vertical;
        }
        .button-container {
            text-align: center;
        }
        input[type="submit"] {
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #28a745;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #218838;
        }
        .link-container {
            text-align: center;
            margin-top: 20px;
        }
        .link-container a {
            color: #007bff;
            text-decoration: none;
            font-size: 16px;
        }
        .link-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<!-- 게시글 수정 View 페이지 -->
<body>
    <div class="container">
        <h1>게시글 수정</h1>
        <form action="updatePost.do" method="post">
            <input type="hidden" name="boardId" value="${post.boardId}">
            
            <label for="author">작성자:</label>
            <input type="text" id="author" name="author" value="${post.author}" readonly>
            
            <label for="title">제목:</label>
            <input type="text" id="title" name="title" value="${post.title}">
            
            <label for="content">내용:</label><br>
            <textarea id="content" name="content" rows="10">${post.content}</textarea>
            
            <div class="button-container">
                <input type="submit" value="수정">
            </div>
        </form>
        
        <div class="link-container">
            <a href="postList.do">게시글 목록으로 돌아가기</a>
        </div>
    </div>
</body>
</html>
