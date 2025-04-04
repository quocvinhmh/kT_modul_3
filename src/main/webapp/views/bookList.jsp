<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Danh sách sách</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
  <h2>Danh sách sách</h2>
  <table class="table table-bordered table-hover mt-3">
    <thead class="thead-dark">
    <tr>
      <th>ID</th>
      <th>Mã sách</th>
      <th>Tên sách</th>
      <th>Tác giả</th>
      <th>Mô tả</th>
      <th>Số lượng</th>
      <th>Hành động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${books}">
      <tr>
        <td>${book.id}</td>
        <td>${book.code}</td>
        <td>${book.name}</td>
        <td>${book.author}</td>
        <td>${book.description}</td>
        <td>${book.quantity}</td>
        <td>
          <a href="${pageContext.request.contextPath}/books?action=borrow&bookId=${book.id}" class="btn btn-primary btn-sm">Mượn sách</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <a href="http://localhost:8080/Gradle___com_example___THi_Module_3_1_0_SNAPSHOT_war/home" class="btn btn-primary btn-sm">Home</a>
</div>
</body>
</html>
