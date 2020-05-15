<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="erro.jsp" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/plugins/images/favicon.png">
    <title>SIJOGA</title>
    <!-- Bootstrap Core CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!-- Menu CSS -->
    <link href="${pageContext.request.contextPath}/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- animation CSS -->
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/estilo.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="${pageContext.request.contextPath}/css/colors/blue-dark.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body style="height: 744px;">
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top m-b-0 p-0">
        <div class="navbar-header">
            <div class="top-left-part"><a class="logo"><b><img src="${pageContext.request.contextPath}/plugins/images/logo.png" alt="home" /></b><span class="hidden-xs logo-word">SIJOGA</span></a></div>
            <ul class="nav navbar-top-links navbar-right pull-left">
                <li>
                    <a class="profile-pic" href="${pageContext.request.contextPath}/login.jsp"><b class="hidden-xs">Seja Bem-Vindo</b> </a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-header -->
        <!-- /.navbar-top-links -->
        <!-- /.navbar-static-side -->
    </nav>
    <!-- Page Content -->
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4 col-xs-4"></div>
            <div class="col-md-4 col-xs-4">
                <div class="white-box align-middle" style="margin-top: 50px;">
                    <form class="form-horizontal form-material" method="post" action="${pageContext.request.contextPath}/LoginServlet">
                        <div class="form-group">
                            <label for="example-email" class="col-md-12">Email</label>
                            <div class="col-md-12">
                                <input type="email" class="form-control form-control-line" name="email" id="example-email"> </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-12">Senha</label>
                            <div class="col-md-12">
                                <input type="password" class="form-control form-control-line" name="senha"> </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <button type="submit" class="btn btn-success">Entrar</button>
                            </div>  
                        </div>
                        <p>
                            <c:choose>
                                <c:when test="${!(empty msg)}">
                                                ${msg}
                                </c:when>
                                <c:when test="${!(empty param.msg)}">
                                        ${param.msg}
                                </c:when>
                            </c:choose>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <footer class="footer text-center" style="left: 0;"> 2017 &copy; Pixel Admin brought to you by wrappixel.com </footer>

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="${pageContext.request.contextPath}/js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="${pageContext.request.contextPath}/js/waves.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/js/custom.min.js"></script>
</body>

</html>
