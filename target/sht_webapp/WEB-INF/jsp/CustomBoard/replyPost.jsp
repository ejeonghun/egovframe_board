<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>답변 작성</title>
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
            background-color: #007bff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
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
<body>
<!-- 게시글 답변 작성 View 페이지 -->
    <div class="container">
        <h1>답변 작성</h1>
        <form action="insertReplyPost.do" method="post" enctype="multipart/form-data">
            <input type="hidden" name="parentId" value="${param.parentId}">
            
            <label for="author">작성자:</label>
            <input type="text" id="author" name="author" value="${username}" readonly>
            
            <label for="title">제목:</label>
            <input type="text" id="title" name="title">
            
            <label for="content">내용:</label><br>
            <textarea id="content" name="content" rows="10"></textarea>
            
        	<input name="file_1" id="egovComFileUploader" type="file" />
						                        <div id="egovComFileList"></div>
						                        <br/>
            
            <div class="button-container">
                <input type="submit" value="작성">
            </div>
            
        </form>
        
        <div class="link-container">
            <a href="postList.do">게시글 목록으로 돌아가기</a>
        </div>
    </div>
</body>
</html>
