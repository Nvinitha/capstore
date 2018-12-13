<%@page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
<h2>Insert Image Here!!!</h2>

<form action="InsertImage" method="post" enctype="multipart/form-data">

<pre>
		
	Name: <input type="text" name="name">
				
	Age: <input type="number" name="age">
				
	Photo: <input type="file" name="photo">
				
	<input type="submit" value="Submit">

</pre>

</form>

<p>${msg}</p>

<table border="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Age</th>
			<th>Photo</th>
		</tr>
		<c:forEach var="student" items="${listStu}">
			<tr>
				<td>${student.id}</td>
				<td>${student.name}</td>
				<td>${student.age}</td>
				<td><img width="100" height="100" src="getStudentPhoto/<c:out value='${student.id}'/>"></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>
