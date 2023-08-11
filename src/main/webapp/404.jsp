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

                    <!-- 404 Error Text -->
                    <div class="text-center">
                        <div class="error mx-auto" data-text="404">404</div>
                        <p class="lead text-gray-800 mb-5">Página no encontrada</p>
                            <p class="text-gray-500 mb-0">La página deseada no existe.</p>
                        <a href="/Farmaciav1">&larr; Volver al Dashboard</a>
                    </div>

                </div>
                <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <%@include file="/componentes/footer.jsp" %>
        
        <%@include file="/componentes/scripts.jsp" %>
        
        <%@include file="/componentes/endPage.jsp" %>

