<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<<jsp:include page="header.jsp"></jsp:include>

<div class="container">

<h3>Core Registration</h3>
<form action='/index/update' method='post'>

    <table class='table table-hover table-responsive table-bordered'>

        <tr>
            <td><b>Name</b></td>
            <td><input type='text' name='core_name' class='form-control' value="${cr.name}" /></td>
        </tr>

        <tr>
            <td><b>Path</b></td>
            <td><input type='text' name='path' class='form-control' value="${cr.path}" /></td>
        </tr>

    </table>
</form>


</div>

<jsp:include page="footer.jsp"></jsp:include>