<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thống kê sách đang mượn</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script>
        function confirmReturn() {
            return confirm('Bạn có chắc chắn muốn trả sách?');
        }
    </script>
</head>
<body>
<div class="container mt-4">
    <h2>Thống kê sách đang mượn</h2>
    <form method="get" action="${pageContext.request.contextPath}/books">
        <input type="hidden" name="action" value="statistics"/>
        <div class="form-row">
            <div class="col">
                <input type="text" name="searchBook" class="form-control" placeholder="Tìm theo tên sách">
            </div>
            <div class="col">
                <input type="text" name="searchStudent" class="form-control" placeholder="Tìm theo tên học sinh">
            </div>
            <div class="col">
                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            </div>
        </div>
    </form>
    <table class="table table-bordered table-hover mt-3">
        <thead class="thead-dark">
        <tr>
            <th>Mã mượn</th>
            <th>Tên sách</th>
            <th>Tên học sinh</th>
            <th>Ngày mượn</th>
            <th>Ngày trả</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="card" items="${borrowCards}">
            <tr>
                <td>${card.borrowCode}</td>
                <td>${card.bookName}</td>
                <td>${card.studentName}</td>
                <td>${card.borrowDate}</td>
                <td>${card.returnDate}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/books?action=return&borrowCardId=${card.id}&bookId=${card.bookId}"
                       class="btn btn-warning btn-sm"
                       onclick="return confirm('Bạn có chắc chắn muốn trả sách?');">
                        Trả sách
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
</div>
</body>
</html>
