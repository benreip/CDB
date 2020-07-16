<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="ressources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="ressources/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="ressources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <c:if test="${not empty success}"><c:out value="${success}" /></c:if>
                    <springForm:form action="addComputer" method="POST" modelAttribute="cdto">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <springForm:input type="text" class="form-control" id="computerName" path="computerDtoName" name="computerName" placeholder="Computer name" value="${newComputer.computerDtoName}" />
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <springForm:input type="date" class="form-control" id="introduced" path="computerdtointroductedin"  name="introduced" placeholder="Introduced date" value="${newComputer.computerdtointroductedin}"/>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <springForm:input type="date" class="form-control" id="discontinued" path="computerdtodiscontinuedin" name="discontinued" placeholder="Discontinued date" value="${newComputer.computerdtodiscontinuedin}"/>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <springForm:select class="form-control" id="companyId" path="computerdtocompanieid" name="companyId" >
                                    <option value="0">--</option>
                                    <c:forEach items="${companies}" var="company">
                                		
                                		<option value="${company.companiedtoid}" ><c:out value="${company.companiedtoname}"/></option>
                                	</c:forEach>
                                </springForm:select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </springForm:form>
                </div>
            </div>
        </div>
    </section>
    <script src="ressources/js/jquery.min.js"></script>
    <script src="ressources/js/addComputer.js"></script>
</body>
</html>