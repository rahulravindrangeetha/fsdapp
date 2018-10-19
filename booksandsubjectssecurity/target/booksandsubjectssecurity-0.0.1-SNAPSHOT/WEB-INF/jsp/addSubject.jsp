<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Subject</title>
</head>
<body>
<security:authorize access="hasAuthority('PRINCIPAL')">
<form:form method="post" action="/booksandsubjectssecurity/login/addSubject.html" modelAttribute="subjectObject">      
        <table >  
         <tr>    
         	<td><form:label path = "subjectId">Subject Id</form:label></td>
         	<td><form:input path = "subjectId"></form:input></td>
         </tr>
         <tr>    
         	<td><form:label path = "subTitle">Sub Title</form:label></td>
         	<td><form:input path = "subTitle" ></form:input></td>
         </tr>
         <tr>    
         	<td><form:label path = "durationInHours">Duration In Hours</form:label></td>
         	<td><form:input path = "durationInHours"></form:input></td>
         </tr>
         <tr>
         <td>
         Add the reference bookids separated by commas
         </td>
         <td>
         <input type="text" name="bookIds"/>
         </td>
         </tr>
         <tr>
         	<td><input type="submit" value="submit" /></td>  
         </tr>
    
        </table>    
</form:form>
</security:authorize>

<security:authorize access="!hasAuthority('PRINCIPAL')">
Sorry !!! You cannot access this page
</security:authorize>

<form:form method="post" action="/booksandsubjectssecurity/login/optionRedirect.html">      
<input type="submit" value="Main Menu" />
</form:form>
</body>
</html>