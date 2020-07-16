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
                    <div class="label label-default pull-right">
                        <c:out value="${computer.computerdtoid}"/>
                    </div>
                    <h1>Edit Computer</h1>
                    	<c:if test="${success != null}"><div class="alert alert-success"><c:out value="${success}"/></div></c:if>

                    <springForm:form action="editComputer?id=${computer.computerdtoid}" method="POST" modelAttribute="cdto">
                        <input type="hidden" value="0" id="id"/> <!-- TODO: Change this value with the computer id -->
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <springForm:input type="text" class="form-control" id="computerName" path="computerDtoName" name="computerName" placeholder="Computer name" value="${computer.computerDtoName}"/>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <springForm:input type="date" class="form-control" id="introduced" name="introduced" path="computerdtointroductedin" placeholder="Date de sortie" value="${computer.computerdtointroductedin}"/>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <springForm:input type="date" class="form-control" id="discontinued" name="discontinued" path="computerdtodiscontinuedin" placeholder="Date d'arrêt" value="${computer.computerdtodiscontinuedin}"/>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <springForm:select class="form-control" id="companyId" name="companyId" path="computerdtocompanieid" >
                                   <c:forEach items="${companies}" var="company">
                                		
                                		<option value="${company.companiedtoid}" ><c:out value="${company.companiedtoname}"/></option>
                                	</c:forEach>
                                </springForm:select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </springForm:form>
                </div>
            </div>
        </div>
    </section>
    <script src="ressources/js/jquery.min.js"></script>
    <script src="ressources/js/editComputer.js"></script>
</body>
</html>