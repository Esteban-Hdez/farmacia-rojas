<%@page import="Dominio.Usuario"%>
<%@include file="/componentes/encabezado.jsp" %>

<body id="page-top">

    <%@include file="/componentes/menu.jsp" %>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <%@include file="/componentes/encabezado_usuario.jsp" %>


            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
                </div>

                <!-- Profile Row -->
                <div class="row">

                    <div class="col">
                        <div class="card shadow mb-4">

                            <div class="row no-gutters" style="padding: 2%; display: flex; align-items: center;">
                                <div class="col-md-2">
                                    <img src="img/undraw_profile.svg" alt="profile_photo">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <%
                                        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
                                        String nombre = usuario.getNombre() +" "+ usuario.getApellidoPaterno() +" "+ usuario.getApellidoMaterno();
                                        %>
                                        <h3 class="card-title">¡Bienvenido!</h3>
                                        <h1 class="card-text"><%=nombre %></h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                
                <div class="row row-cols-4">
                    <%@include file="/componentes/status.jsp" %>
                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <%@include file="/componentes/footer.jsp" %>

        <%@include file="/componentes/scripts.jsp" %>

        <%@include file="/componentes/endPage.jsp" %>

