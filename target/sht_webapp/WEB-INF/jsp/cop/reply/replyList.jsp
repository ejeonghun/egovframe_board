<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String nttId = request.getParameter("nttId");
 %>
<c:forEach var="reply" items="${replyList}">
    <div class="reply">
        <p><strong>${reply.createdId}</strong>: ${reply.content}</p>
        <p><small>${reply.replyDate}</small></p>
    </div>
</c:forEach>
