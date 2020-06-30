<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard.html"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                ${nbComputers} Computers found
            </h1>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="#" method="GET" class="form-inline">

                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" />
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                    <a class="btn btn-success" id="addComputer" href="addComputer">Add Computer</a> 
                    <a class="btn btn-default" id="editComputer" href="editComputer" onclick="$.fn.toggleEditMode();">Edit</a>
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
                            Computer name
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
                	<c:forEach items="${computers}" var="cdto">
                    <tr>
                        <td class="editMode">
                            <input type="checkbox" name="cb" class="cb" value="0">
                        </td>
                        <td>
                            <a href="editComputer" onclick="">${cdto.computerDtoName} </a>
                        </td>
                        <td><c:if test = "${not empty cdto.computerdtointroductedin}">${cdto.computerdtointroductedin}</c:if></td>
                    	<td><c:if test = "${not empty cdto.computerdtodiscontinuedin}">${cdto.computerdtodiscontinuedin}</c:if></td>
                    	<td><c:if test = "${not empty cdto.computerdtocompanieid}">${cdto.computerdtocompanieid}</c:if></td>
                    	</tr>
                    	</c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
                <li> <c:if test="${currentpage > 1}"> 
                    <a href="/cdb/dashboard?page=${currentpage-1}" aria-label="Previous">
                      <span aria-hidden="true">&laquo;</span>
                  </a>
                  </c:if>
              </li>
              <li> 
              <a href="/cdb/dashboard?page=${currentpage}">${currentpage}</a></li>
              <li><a href="/cdb/dashboard?page=${currentpage+1}">${currentpage+1}</a></li>
              <li><a href="/cdb/dashboard?page=${currentpage+2}">${currentpage+2}</a></li>
              <li><a href="/cdb/dashboard?page=${currentpage+3}">${currentpage+3}</a></li>
              <li><a href="/cdb/dashboard?page=${currentpage+4}">${currentpage+4}</a></li>
              <li>
              	<c:if test="${currentpage < nbPageMax}">
                <a href="/cdb/dashboard?page=${currentpage+1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
                </c:if>
            </li>
        </ul>

        <div class="btn-group btn-group-sm pull-right" role="group" >
        	<c:set var="active" value=""/>
            <c:if test = "${nb_entries_per_page == 10}">
               <c:set var="active" value="active"/>
            </c:if>
            <a href="/Computer-DataBase/dashboard?nbByPage=10"><button type="button" class="btn btn-default ${active}">10</button></a>
            <c:set var="active" value=""/>
            <c:if test = "${nb_entries_per_page == 50}">
               <c:set var="active" value="active"/>
            </c:if>
            <a href="/Computer-DataBase/dashboard?nbByPage=50"><button type="button" class="btn btn-default ${active}">50</button></a>
            <c:set var="active" value=""/>
            <c:if test = "${nb_entries_per_page == 100}">
               <c:set var="active" value="active"/>
            </c:if>
            <a href="/Computer-DataBase/dashboard?nbByPage=100"><button type="button" class="btn btn-default ${active}">100</button></a>
        	</div>

    </footer>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/dashboard.js"></script>

</body>
</html>