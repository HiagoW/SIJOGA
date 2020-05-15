<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="erro.jsp" %>
<c:if test="${(empty sessionScope.bean)}">
    <jsp:forward page="../login.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema" />
    </jsp:forward>
</c:if>
<c:if test="${(sessionScope.bean.tipo.id!=3)}">
    <jsp:forward page="../login.jsp">
        <jsp:param name="msg" value="Você não tem acesso à essa página!" />
    </jsp:forward>
</c:if>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="../plugins/images/favicon.png">
    <title>SIJOGA - Processos</title>
    <!-- Bootstrap Core CSS -->
    <link href="../bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="../plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- toast CSS -->
    <link href="../plugins/bower_components/toast-master/css/jquery.toast.css" rel="stylesheet">
    <!-- morris CSS -->
    <link href="../plugins/bower_components/morrisjs/morris.css" rel="stylesheet">
    <!-- animation CSS -->
    <link href="../css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/estilo.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="../css/colors/blue-dark.css" id="theme" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
    <!-- Preloader -->
    <div class="preloader">
        <div class="cssload-speeding-wheel"></div>
    </div>
    <div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top m-b-0">
            <div class="navbar-header"> <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse"><i class="fa fa-bars"></i></a>
                <div class="top-left-part"><a class="logo" href="home.html"><b><img src="../plugins/images/logo.png" alt="home" /></b><span class="hidden-xs logo-word">SIJOGA</span></a></div>
                <ul class="nav navbar-top-links navbar-right pull-left">
                    <li>
                        <a class="profile-pic" href="#"><b class="hidden-xs">${sessionScope.bean.nome}</b> </a>
                    </li>
                </ul>
                <ul class="nav navbar-top-links navbar-right pull-right">
                    <li>
                        <a class="profile-pic" href="../LogoutServlet"><b class="hidden-xs">Sair</b> </a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-header -->
            <!-- /.navbar-top-links -->
            <!-- /.navbar-static-side -->
        </nav>
        <!-- Left navbar-header -->
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse slimscrollsidebar">
                <ul class="nav" id="side-menu">
                    <li style="padding: 10px 0 0;">
                        <a href="home.html" class="waves-effect"><i class="fa fa-clock-o fa-fw" aria-hidden="true"></i><span class="hide-menu">Processos</span></a>
                    </li>
                    <li>
                        <a href="../juiz/home.html" class="waves-effect"><i class="fa fa-user fa-fw" aria-hidden="true"></i><span class="hide-menu">Home Juiz</span></a>
                    </li>
                    <li>
                        <a href="../advogado/home.html" class="waves-effect"><i class="fa fa-table fa-fw" aria-hidden="true"></i><span class="hide-menu">Home Advogado</span></a>
                    </li>
                    <li>
                        <a href="fontawesome.html" class="waves-effect"><i class="fa fa-font fa-fw" aria-hidden="true"></i><span class="hide-menu">Icons</span></a>
                    </li>
                    <li>
                        <a href="map-google.html" class="waves-effect"><i class="fa fa-globe fa-fw" aria-hidden="true"></i><span class="hide-menu">Google Map</span></a>
                    </li>
                    <li>
                        <a href="blank.html" class="waves-effect"><i class="fa fa-columns fa-fw" aria-hidden="true"></i><span class="hide-menu">Blank Page</span></a>
                    </li>
                    <li>
                        <a href="404.html" class="waves-effect"><i class="fa fa-info-circle fa-fw" aria-hidden="true"></i><span class="hide-menu">Error 404</span></a>
                    </li>
                </ul>
            </div>
        </div>
        <!-- Left navbar-header end -->
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <!--row -->
                <div class="row">
                    <div class="col-sm-12">
                        <div class="white-box" style="margin-top: 20px;">
                            <h3 class="box-title">PROCESSOS
                                <div class="col-md-3 col-sm-5 col-xs-12 pull-right">
                                    <select class="form-control pull-right row b-none">
                                        <option>Todos</option>
                                        <option>Abertos</option>
                                        <option>Encerrados</option>
                                        <option>Como Promovente</option>
                                        <option>Como Promovido</option>
                                    </select>
                                </div>
                            </h3>
                            <div class="table-responsive">
                                <table class="table ">
                                    <thead>
                                        <tr>
                                            <th>NÂº</th>
                                            <th>PAPEL</th>
                                            <th>DATA</th>
                                            <th>SITUAÃÃO</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <a href="#"><tr class="deliberacao">
                                            <td>123</td>                                            
                                            <td>Promovente</td>
                                            <td class="txt-oflo">18/03/2019</td>
                                            <td>AGUARDANDO DELIBERAÃÃO</td>
                                            <td><a href="detalhesProcesso.html" class="btn btn-danger btn-block btn-rounded waves-effect waves-light">Acessar</a></td>
                                        </tr></a>
                                        <tr>
                                            <td>124</td>                                            
                                            <td>Promovido</td>
                                            <td class="txt-oflo">18/03/2019</td>
                                            <td>AGUARDANDO INTIMAÃÃO</td>
                                            <td><a href="detalhesProcesso.html" class="btn btn-danger btn-block btn-rounded waves-effect waves-light">Acessar</a></td>
                                        </tr>
                                        <tr>
                                            <td>125</td>                                            
                                            <td>Promovente</td>
                                            <td class="txt-oflo">18/03/2019</td>
                                            <td>EM ANDAMENTO</td>
                                            <td><a href="detalhesProcesso.html" class="btn btn-danger btn-block btn-rounded waves-effect waves-light">Acessar</a></td>
                                        </tr>
                                        <tr>
                                            <td>126</td>                                            
                                            <td>Promovente</td>
                                            <td class="txt-oflo">18/03/2019</td>
                                            <td>EM ANDAMENTO</td>
                                            <td><a href="detalhesProcesso.html" class="btn btn-danger btn-block btn-rounded waves-effect waves-light">Acessar</a></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
                <div class="row">
                   <div class="col-sm-12">
                       <div class="white-box">                           
                           <div class="row">
                               <div class="col-md-4"></div>
                               <div class="form-group col-md-4">
                                    <h4 class="box-title text-center">RelatÃ³rio de Processos</h4>
                                    <a role="button" class="btn btn-warning btn-block btn-rounded waves-effect waves-light" >Emitir RelatÃ³rio</a>
                               </div>
                           </div>
                       </div>
                   </div> 
                </div>
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2017 &copy; Pixel Admin brought to you by wrappixel.com </footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="../plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="../bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="../plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="../js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="../js/waves.js"></script>
    <!--Counter js -->
    <script src="../plugins/bower_components/waypoints/lib/jquery.waypoints.js"></script>
    <script src="../plugins/bower_components/counterup/jquery.counterup.min.js"></script>
    <!--Morris JavaScript -->
    <script src="../plugins/bower_components/raphael/raphael-min.js"></script>
    <script src="../plugins/bower_components/morrisjs/morris.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="../js/custom.min.js"></script>
    <script src="../js/dashboard1.js"></script>
    <script src="../plugins/bower_components/toast-master/js/jquery.toast.js"></script>
</body>

</html>