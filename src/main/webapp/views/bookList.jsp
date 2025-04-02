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
        <td>${book.nameBook}</td>
        <td>${book.nameAuthor}</td>
        <td>${book.describe}</td>
        <td>${book.quantity}</td>
        <td>
          <!-- Nút mượn sách: chuyển sang trang borrowBook.jsp -->
          <a href="${pageContext.request.contextPath}/books?action=borrow&bookId=${book.id}" class="btn btn-primary btn-sm">
            Mượn sách
          </a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
