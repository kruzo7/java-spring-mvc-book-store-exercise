<#macro mainLayout title>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Spring MVC DEMO - ${title}</title>
<link rel='stylesheet'
	href='/webjars/bootstrap/3.3.6/css/bootstrap.min.css'>
<script type='text/javascript' src='/webjars/jquery/2.2.3/jquery.min.js'></script>
<script type='text/javascript'
	src='/webjars/bootstrap/3.3.6/js/bootstrap.min.js'></script>
</head>

<body>
	<nav class="navbar navbar-default">
		<div class="container">
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/">Index</a></li>
					<li><a href="/book">Books</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<h3>${title}</h3>
		<#nested>
	</div>
</body>
</html>
</#macro>
