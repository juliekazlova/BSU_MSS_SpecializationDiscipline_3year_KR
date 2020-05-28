
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!--Change locale to prove i18n-->
<c:if test="${not empty param.language}">
    <c:set var="language" value="${param.language}" scope="session"/>
</c:if>
<fmt:setLocale value="${language}" />
<fmt:setLocale value="ru" />
<fmt:setBundle basename="messages" />
<!DOCTYPE html>

<html>
<head>
    <title><fmt:message key="label.welcome" /></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row col-md-12">
        <c:if test="${not empty requestScope.status && requestScope.status != '200'}">
            <div class="col-md-4 alert alert-danger alert-dismissible error-block" role="alert">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Error!</strong><br>
                <code><c:out value="${requestScope.status}"/></code>
            </div>
        </c:if>
    </div>

    <div class="row col-md-12">
        <div class="form-group">
            <a class="btn btn-default" href="${pageContext.request.contextPath}/NewServlet?action=getFees"><fmt:message key="label.fees" /></a>
            <a class="btn btn-default" href="${pageContext.request.contextPath}/NewServlet?action=getIvanovSum"><fmt:message key="label.ivanov" /></a>
        </div>
    </div>

    <div class="col-md-10 col-md-offset-1">
        <c:if test="${not empty requestScope.fees}">
            <h2>Fees:</h2>
            <table class="table table-striped">
                <tr>
                    <th scope="col"><fmt:message key="label.type" /></th>
                    <th scope="col"><fmt:message key="label.title" /></th>
                    <th scope="col"><fmt:message key="label.payer" /></th>
                    <th scope="col"><fmt:message key="label.sum" /></th>
                </tr>

                <c:forEach items="${requestScope.fees}" var="fee">
                    <tr>
                        <td>${fee.type}</td>
                        <td>${fee.title}</td>
                        <td>${fee.payer}</td>
                        <td>${fee.sum}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <c:if test="${not empty requestScope.sum}">
            <p class="text"><c:out value="${requestScope.sum}"/></p>
        </c:if>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
        integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"></script>
</html>
