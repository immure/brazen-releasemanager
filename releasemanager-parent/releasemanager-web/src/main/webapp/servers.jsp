<%@ taglib prefix="j" uri="/jodd" %>
<%-- <%@ taglib prefix="joy" uri="/jodd-joy" %> --%>
<%-- <%@ taglib prefix="jfn" uri="/joddfn" %> --%>
<%-- <%@ taglib prefix="appfn" uri="/appfn" %> --%>


<html>
<head>
<title>
BRAZEN
</title>
</head>

<body>

<div id="pagetitle">Servers</div>

<table id="servers" class="newspaper-a">
<thead>
<thead>
<tr>
<th scope="col">Name</th>
<th scope="col">Address</th>
<th scope="col">Environment</th>
<th scope="col">Deployment</th>
<th scope="col">Operational</th>
</tr>
<tbody>
<j:iter items="${servers}" var="server" status="s">
	<tr>
	<td>${server.name }</td>
	<td>${server.address }</td>
	<td>${server.environment }</td>
	<td></td>
	<td></td>
	</tr>
</j:iter>
</tbody>
</table>

<j:form>
<form action="<j:url _="/servers.post.html"/>" id="server" method="post" autocomplete="off">
	<div class="frow">
	<label for="name" class="g4">Name: </label><input type="text" name="serverName" id="name" class="g6" maxlength="50"/>
	</div>
	
	<div class="frow">
	<label for="address" class="g4">Address: </label><input type="text" name="serverAddress" id="address" class="g6" maxlength="50"/>
	</div>
	
	<div class="frow">
	<label for="environment" class="g4">Environment: </label>
	</div>
	
	<div class="frow" style="margin:40px 0 0 0">
		<div class="g10 push4">
			<input type="submit" class="g4 submit" value="Add" />
		</div>
	</div>
	
</form>
</j:form>

</body>
</html>