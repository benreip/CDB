<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="ressources/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="ressources/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="ressources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="cdb/dashboard"> <spring:message code="title"/>  </a>
        	<div class="pull-right">
            	<a class="navbar-brand" href="<spring:message code="lang.url"/>"><spring:message code="lang"/></a>
            </div>
        </div>
    </header>
    
     <c:set var="searchValue" value="" />
	<c:if test="${search != null && search != ''}">
		<c:set var="searchValue" value="&search=${search}" />
	</c:if>
	
	<c:set var="nbByPageValue" value="" />
	<c:if test="${page.nb_entries_per_page != null && page.nb_entries_per_page != ''}">
		<c:set var="nbByPageValue" value="&nbByPage=${page.nb_entries_per_page}" />
	</c:if>
	
	<c:set var="colonneValue" value="" />
	<c:if test="${page.colonne != null && page.colonne != ''}">
		<c:set var="colonneValue" value="&colonne=${page.colonne}" />
	</c:if>
	
	<c:set var="ascendingValue" value="" />
	<c:if test="${page.ascending != null }">
		<c:set var="ascendingValue" value="&ascending=${page.ascending}" />
	</c:if>
	
	<c:set var="askedPageValue" value="" />
	<c:if test="${askedPage != null }">
		<c:set var="askedPageValue" value="&askedPage=${askedPage}"/> 
	</c:if>
    

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                ${nbComputers} <spring:message code="dashboard.computer.found"/>
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="<spring:message code="dashboard.search.placeholder"/>" value="${search}" />
                        <input type="submit" id="searchsubmit" value=<spring:message code="dashboard.filter.button"/>
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer"><spring:message code="dashboard.addComputer.button"/></a> 
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="dashboard.edit.button"/></a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="#" method="POST">
            <input type="hidden" name="selection" value="">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>  
                        	<c:if test="${page.colonne == 'name' && page.ascending == 'ASC'}"><a href="dashboard?colonne=computer.name&ascending=DESC${nbByPageValue}${searchValue}" ><spring:message code="dashboard.computerName"/></a></c:if>
                         	<c:if test="${page.colonne == 'name' && page.ascending == 'DESC'}"><a href="dashboard?colonne=computer.name&ascending=ASC${nbByPageValue}${searchValue}" ><spring:message code="dashboard.computerName"/></a></c:if>
                        	<c:if test="${page.colonne != 'name'}"><a href="dashboard?colonne=computer.name&ascending=ASC${nbByPageValue}${searchValue}" ><spring:message code="dashboard.computerName"/></a></c:if>
                        </th>
                        <th>
                           <c:if test="${page.colonne == 'introduced' && page.ascending == 'ASC'}"><a href="dashboard?colonne=introduced&ascending=DESC${nbByPageValue}${searchValue}" ><spring:message code="dashboard.introduced"/></a></c:if>
                         	<c:if test="${page.colonne == 'introduced' && page.ascending == 'DESC'}"><a href="dashboard?colonne=introduced&ascending=ASC${nbByPageValue}${searchValue}" ><spring:message code="dashboard.introduced"/></a></c:if>
                        	<c:if test="${page.colonne != 'introduced'}"><a href="dashboard?colonne=introduced&ascending=ASC${nbByPageValue}${searchValue}" ><spring:message code="dashboard.introduced"/></a></c:if>
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            <c:if test="${page.colonne == 'discontinued' && page.ascending == 'ASC'}"><a href="dashboard?colonne=discontinued&ascending=DESC${nbByPageValue}${searchValue}" ><spring:message code="dashboard.discontinued"/></a></c:if>
                         	<c:if test="${page.colonne == 'discontinued' && page.ascending == 'DESC'}"><a href="dashboard?colonne=discontinued&ascending=ASC${nbByPageValue}${searchValue}" ><spring:message code="dashboard.discontinued"/></a></c:if>
                        	<c:if test="${page.colonne != 'discontinued'}"><a href="dashboard?colonne=discontinued&ascending=ASC${nbByPageValue}${searchValue}" ><spring:message code="dashboard.discontinued"/></a></c:if>
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            <c:if test="${page.colonne == 'company' && page.ascending == 'ASC'}"><a href="dashboard?colonne=company&ascending=DESC${nbByPageValue}${searchValue}" ><spring:message code="dashboard.company"/></a></c:if>
                         	<c:if test="${page.colonne == 'company' && page.ascending == 'DESC'}"><a href="dashboard?colonne=company&ascending=ASC${nbByPageValue}${searchValue}" ><spring:message code="dashboard.company"/></a></c:if>
                        	<c:if test="${page.colonne != 'company'}"><a href="dashboard?colonne=company&ascending=ASC${nbByPageValue}${searchValue}" ><spring:message code="dashboard.company"/></a></c:if>
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                	<c:forEach items="${computers}" var="cdto">
                    <tr>
                        <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="${cdto.computerdtoid }">
                        </td>
                        <td>
                            <a href="editComputer?id=${cdto.computerdtoid }" onclick="">${cdto.computerDtoName} </a>
                        </td>
                        <td><c:if test = "${not empty cdto.computerdtointroductedin}">${cdto.computerdtointroductedin}</c:if></td>
                    	<td><c:if test = "${not empty cdto.computerdtodiscontinuedin}">${cdto.computerdtodiscontinuedin}</c:if></td>
                    	<td><c:if test = "${not empty cdto.company.companiedtoname}">${cdto.company.companiedtoname}</c:if></td>
                    	</tr>
                    	</c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                <li> <c:if test="${page.currentPage > 1}"> 
                    <a href="/cdb/dashboard?page=${page.currentPage-1}${searchValue}${colonneValue}${ascendingValue}${nbByPageValue}" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
                  </c:if>
              </li>
              <li> 
              <a href="/cdb/dashboard?page=${page.currentPage}${searchValue}${colonneValue}${ascendingValue}${nbByPageValue}">${page.currentPage}</a></li>
              <li><a href="/cdb/dashboard?page=${page.currentPage+1}${searchValue}${colonneValue}${ascendingValue}${nbByPageValue}">${page.currentPage+1}</a></li>
              <li><a href="/cdb/dashboard?page=${page.currentPage+2}${searchValue}${colonneValue}${ascendingValue}${nbByPageValue}">${page.currentPage+2}</a></li>
              <li><a href="/cdb/dashboard?page=${page.currentPage+3}${searchValue}${colonneValue}${ascendingValue}${nbByPageValue}">${page.currentPage+3}</a></li>
              <li><a href="/cdb/dashboard?page=${page.currentPage+4}${searchValue}${colonneValue}${ascendingValue}${nbByPageValue}">${page.currentPage+4}</a></li>
              <li> <c:if test="${page.currentPage < control_page }">
                <a href="/cdb/dashboard?page=${page.currentPage+1}${searchValue}${colonneValue}${ascendingValue}${nbByPageValue}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
                </c:if>
            </li>
        </ul>

        <div class="btn-group btn-group-sm pull-right" role="group" >
        	<c:set var="active" value=""/>
            <c:if test = "${nbByPage == 10}">
               <c:set var="active" value="active"/>
            </c:if>
            <a href="/cdb/dashboard?nbByPage=10${searchValue}${colonneValue}${ascendingValue}"><button type="button" class="btn btn-default ${active}">10</button></a>
            <c:set var="active" value=""/>
            <c:if test = "${nbByPage == 50}">
               <c:set var="active" value="active"/>
            </c:if>
            <a href="/cdb/dashboard?nbByPage=50${searchValue}${colonneValue}${ascendingValue}"><button type="button" class="btn btn-default ${active}">50</button></a>
            <c:set var="active" value=""/>
            <c:if test = "${nbByPage == 100}">
               <c:set var="active" value="active"/>
            </c:if>
            <a href="/cdb/dashboard?nbByPage=100${searchValue}${colonneValue}${ascendingValue}"><button type="button" class="btn btn-default ${active}">100</button></a>
        	</div>

    </footer>
<script src="ressources/js/jquery.min.js"></script>
<script src="ressources/js/bootstrap.min.js"></script>
<script src="ressources/js/dashboard.js"></script>

</body>
</html>