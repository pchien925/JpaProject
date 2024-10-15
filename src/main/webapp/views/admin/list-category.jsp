<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <h1>Category List</h1>

    <a href="/admin/category/create">Add New Category</a>

    <!-- Check if cateList exists and is not empty -->
    <c:if test="${not empty cateList}">
        <table border="1" cellpadding="5" cellspacing="0">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Category Name</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <!-- Loop through cateList -->
                <c:forEach var="category" items="${cateList}">
                    <tr>
                        <td>${category.categoryId}</td>
                        <td>${category.categoryname}</td>
                        <td>${category.status == 1 ? 'Active' : 'Inactive'}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- Show message if cateList is empty -->
    <c:if test="${empty cateList}">
        <p>No categories found.</p>
    </c:if>