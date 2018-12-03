<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp"></jsp:include>

<h3>Core Registration</h3>
<br>
<form action='/index/add' method='post'>

    <table class='table table-hover table-responsive table-bordered'>

        <tr>
            <td><b>Core</b></td>
            <td><input type='text' name='core_name' class='form-control'  required/></td>
        </tr>

        <tr>
            <td><b>Path</b></td>
            <td><input type='text' name='path' class='form-control' required /></td>
        </tr>

        <tr>
            <td></td>
            <td>
                <button type="submit" class="btn btn-primary">Register</button>
            </td>
        </tr>

    </table>
    <b><c:out value="${danger}"></c:out></b>
</form>



<h3>List Of Cores</h3>
<br>
<table class="table table-hover">

    <thead>
      <tr>
        <th><b>Core Name</b></th>
        <th><b>Core Path</b></th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="cr">
      <tr>
        <td><c:out value="${cr.name}"></c:out></td>
        <td><c:out value="${cr.path}"></c:out></td>
        <td>
              <a href="/index/${cr.id}/edit">
                  <button type="submit" class="btn btn-primary">Edit Core</button>
                  </a>
                    </td>
                    <td>
                     <a href="/index/${cr.id}/delete">
                        <button type="submit" class="btn btn-primary">Delete Core</button>
                        </a>
                    </td>
              </tr>
          </c:forEach>
    </tbody>
  </table>
</div>

<jsp:include page="footer.jsp"></jsp:include>