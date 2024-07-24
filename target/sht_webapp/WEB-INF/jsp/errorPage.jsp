<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>오류</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }
        .container {
            text-align: center;
            background: #fff;
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #ff4d4d; /* 오류를 강조하기 위해 빨간색 사용 */
        }
        h2 {
            color: #666;
        }
        p {
            color: #333;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
    <script type="text/javascript">
        function showAlertAndGoBack(message) {
            if (message) {
                alert(message);
                window.history.back();
            }
        }

        // 페이지가 로드될 때 서버에서 전달한 메시지를 확인합니다.
        window.onload = function() {
            var message = '${message}';
            showAlertAndGoBack(message);
        };
    </script>
</head>
<body>
    <div class="container">
        <h1>권한이 없습니다.</h1>
        <h2>작성자가 아닙니다!</h2>
        <p>${message}</p>
        <button onclick="window.history.back();">이전 페이지로</button>
    </div>
</body>
</html>
