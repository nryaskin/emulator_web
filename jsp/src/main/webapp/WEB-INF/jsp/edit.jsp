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
		<p><form:form action = "/update" method = "POST" modelAttribute="cr">
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
                <a href="/">back</a>
    </div>
</body>
</html>