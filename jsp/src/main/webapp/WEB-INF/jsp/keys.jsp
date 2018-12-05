<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Core crud.</title>
</head>
<body>
	<br>
	<div style="text-align: center">
		<h2>
			Core Input crud<br/>

		</h2>
		<p><table style="width:100%">
            <tr>
                <th>Key</th>
                <th>Action name</th>
            </tr>
              <c:forEach items ="${keys}" var = "key">
                 <tr>
                    <td><c:out value = "${key.key}"/></td>
                    <td><c:out value = "${key.actionName}"/></td>
                    <td><a href="/${key.id}/delete">delete</a></td>
                 </tr>
              </c:forEach>
        </table>
        <p><form:form action = "/${cr.id}/keys/add" method = "POST" modelAttribute="key">
        <table>
        <tr>
            <td><form:label path="key">Key num:</form:label></td>
            <td><form:input path="key"/></td>
        </tr>
        <tr>
            <td><form:label path="actionName">Action name:</form:label></td>
            <td><form:input path="actionName"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
        </table>
        </form:form>
        <a href="/">back</a>
	</div>
</body>
</html>