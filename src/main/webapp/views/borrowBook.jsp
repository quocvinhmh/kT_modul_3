<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Mượn sách</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script>
    function confirmReturn() {
      return confirm('Bạn có chắc chắn muốn trở về danh sách?');
    }
  </script>
</head>
<body>
<div class="container mt-4">
  <h2>Mượn sách</h2>
  <!-- Hiển thị thông báo lỗi nếu có -->
  <c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">${errorMessage}</div>
  </c:if>
  <form action="${pageContext.request.contextPath}/books" method="post">
    <!-- Xác định action xử lý là processBorrow -->
    <input type="hidden" name="action" value="processBorrow"/>
    <!-- Gửi bookId ẩn -->
    <input type="hidden" name="bookId" value="${book.id}"/>

    <div class="form-group">
      <label>Mã mượn sách</label>
      <input type="text" name="borrowCode" class="form-control" placeholder="MS-XXXX" required>
    </div>
    <div class="form-group">
      <label>Tên sách</label>
      <!-- Hiển thị tên sách, không cho sửa -->
      <input type="text" name="bookName" class="form-control" value="${book.name}" readonly>
    </div>
    <div class="form-group">
      <label>Họ tên học sinh</label>
      <select name="studentId" class="form-control" required>
        <option value="">Chọn học sinh</option>
        <c:forEach var="student" items="${students}">
          <option value="${student.id}">${student.getName()}</option>
        </c:forEach>
      </select>
    </div>
    <div class="form-group">
      <label>Ngày mượn</label>

      <input type="text" name="borrowDate" class="form-control" value="<%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date()) %>" readonly>
    </div>
    <div class="form-group">
      <label>Ngày trả</label>
      <input type="text" name="returnDate" class="form-control" placeholder="dd/MM/yyyy" required>
    </div>
    <button type="submit" class="btn btn-primary">Mượn sách</button>
    <a href="${pageContext.request.contextPath}/books?action=list" class="btn btn-secondary" onclick="return confirmReturn();">Trở về danh sách</a>
  </form>
</div>
</body>
</html>
