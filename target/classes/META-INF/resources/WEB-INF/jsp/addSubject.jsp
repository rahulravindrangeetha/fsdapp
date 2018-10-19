<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form method="post" action="/booksandsubjectsmvchibernatedataboot/addSubject.html" modelAttribute="subjectObject">      
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

<form:form method="post" action="/booksandsubjectsmvchibernatedataboot/optionRedirect.html">      
<input type="submit" value="Main Menu" />
</form:form>
</body>
</html>