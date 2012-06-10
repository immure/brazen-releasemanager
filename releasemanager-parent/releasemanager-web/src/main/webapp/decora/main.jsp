<%@ taglib prefix="joy" uri="/jodd-joy" %>
<%@ taglib prefix="j" uri="/jodd" %>
<%@ taglib prefix="joyfn" uri="/joddfn-joy" %>
${joyfn:initPage(pageContext)}
<!DOCTYPE html>
<html>
<head>
	<title><decora:title>BRAZEN - Release Manager</decora:title></title>
	<link rel="Shortcut Icon" href="/uphea.ico" />
	<link rel="icon" href="/uphea.ico" />
	<meta name="description" content="brazen release management"/>
	<meta name="keywords" content="brazen, release management"/>
	<meta property="og:site_name" content="brazen"/>
	<link href="${CTX}/reset.css" rel="stylesheet" type="text/css" />
	<link href="${CTX}/960_24_col.css" rel="stylesheet" type="text/css" />
	<link href="${CTX}/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${CTX}/jquery.js"></script>
	<script type="text/javascript" src="${CTX}/jsext.js"></script>
	<script type="text/javascript" src="${CTX}/app.js"></script>
	<decora:head/>
</head>
<body>
<div id="wait">Please wait</div>
<div id="main">

		<div id="head" class="cls">
			<div id="logo">Brazen Releaser</div>
			<div id="title"><a href="${CTX}/index.html">Home</a></div>
		</div>

	<div id="body" class="c24">
	<decora:body/>
	</div>

	<div id="foot">
		<b class="c-ora">brazen</b> is open-source Java platform for performing release management built on <a href="http://jodd.org" class="c-ora"><b>Jodd framework</b></a>.
	</div>
</div>

</body>
</html>