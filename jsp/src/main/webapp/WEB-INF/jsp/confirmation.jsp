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
			Are you sure?<br/>
		</h2>

		<p><form:form action = "delete" method = "DELETE">
            <table>
                <tr>
                    <td><input type="submit" value="Yes"/></td>
                </tr>
            </table>
        </form:form>
        <p><form:form action = "/" method = "GET">
            <table>
                <tr>
                    <td><input type="submit" value="No"/></td>
                </tr>
            </table>
        </form:form>
    </div>
</body>
</html>