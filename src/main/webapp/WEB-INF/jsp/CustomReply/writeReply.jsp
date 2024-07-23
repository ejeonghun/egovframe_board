<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>댓글 작성</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            color: #555;
        }
        .form-group input[type="text"],
        .form-group textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .form-group textarea {
            resize: vertical;
        }
        .form-group input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
        }
        .form-group input[type="submit"]:hover {
            background-color: #45a049;
        }
        .link {
            display: block;
            text-align: center;
            margin-top: 20px;
        }
        .link a {
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
        }
        .link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<!-- 댓글 작성 View 페이지 -->
    <div class="container">
        <h1>댓글 작성</h1>
        <form action="insertReply.do" method="post">
            <input type="hidden" name="postId" value="${param.postId}">
            <input type="hidden" name="parentId" value="${param.parentId}">
            
            <div class="form-group">
                <label for="author">작성자:</label>
                <input type="text" id="author" name="author" value="${username}" readonly>
            </div>
            
            <div class="form-group">
                <label for="content">내용:</label>
                <textarea id="content" name="content" rows="10" placeholder="댓글 내용을 입력하세요..."></textarea>
            </div>
            
            <div class="form-group">
                <input type="submit" value="작성">
            </div>
        </form>
        <div class="link">
            <a href="javascript:window.history.back();">뒤로 가기</a>
        </div>
    </div>
</body>
</html>
