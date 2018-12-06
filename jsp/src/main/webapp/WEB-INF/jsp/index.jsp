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
			Core crud<br/>

		</h2>
		<p><table style="width:100%">
            <tr>
                <th>Core name</th>
                <th>Core path</th>
            </tr>
              <c:forEach items ="${list}" var = "core">
                 <tr>
                    <td><c:out value = "${core.coreName}"/></td>
                    <td><c:out value = "${core.corePath}"/></td>
                    <td><a href="${core.id}/edit">edit</a></td>
                    <td><a href="${core.id}/delete">delete</a></td>
                    <td><a href="${core.id}/keys">Keys</a></td>
                 </tr>
              </c:forEach>
        </table>
        <p><form:form action = "add" method = "POST" modelAttribute="cr">
        <table>
        <tr>
            <td><form:label path="coreName">Core Name</form:label></td>
            <td><form:input path="coreName"/></td>
        </tr>
        <tr>
            <td><form:label path="corePath">Core Name</form:label></td>
            <td><form:input path="corePath"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
        </table>
        </form:form>

	</div>
</body>
</html>