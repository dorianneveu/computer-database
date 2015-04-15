<!DOCTYPE html>
<html>
	<head>
		<title>Computer Database</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta charset="utf-8">
		<!-- Bootstrap -->
		<link href="${request.getContextPath()}css/bootstrap.min.css"
			rel="stylesheet" media="screen">
		<link href="${request.getContextPath()}css/font-awesome.css"
			rel="stylesheet" media="screen">
		<link href="${request.getContextPath()}css/main.css" rel="stylesheet"
			media="screen">
	</head>
	<body>
		<header class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<a class="navbar-brand" href="dashboard?page=50&offset=0&order=cmp.name&search=&sort=ASC"> Application -
					Computer Database </a>    
					<div style="float:right"><a href="?lang=_en&id=${computerDTO.id }"><img src="${request.getContextPath()}img/uk.png"></a><a href="?lang=_fr&id=${computerDTO.id }"><img src="${request.getContextPath()}img/fr.png"></a>
					</div>
			</div>
		</header>