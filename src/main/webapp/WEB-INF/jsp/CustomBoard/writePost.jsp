<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f9f9f9;
        }
        h1 {
            color: #333;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 0 auto;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }
        input[type="text"], textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 16px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        textarea {
            resize: vertical;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            padding: 15px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .navigation {
            text-align: center;
            margin-top: 20px;
        }
        .navigation a {
            color: #007BFF;
            text-decoration: none;
            font-size: 16px;
        }
        .navigation a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<!-- 게시글 작성 View 페이지 -->
    <h1>게시글 작성</h1>
    <form action="insertPost.do" method="post" enctype="multipart/form-data">
        <label for="author">작성자:</label>
        <input type="text" id="author" name="author" value="${username}" readonly><br>
        <label for="title">제목:</label>
        <input type="text" id="title" name="title" required><br>
        <label for="content">내용:</label><br>
        <textarea id="content" name="content" rows="10" required></textarea><br>
        <input type="hidden" id="atchFileId" name="atchFileId"><br>
        <input name="file_1" id="egovComFileUploader" type="file" />
						                        <div id="egovComFileList"></div>
						                        <br/>
        <input type="submit" value="작성">
    </form>
    <div class="navigation">
        <a href="postList.do">게시글 목록</a>
    </div>
</body>
</html>
