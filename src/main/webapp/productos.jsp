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
                <h1 class="h3 mb-4 text-gray-800">Productos</h1>

                <div class="row">

                    <div class="col">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Tabla de inventario</h6>
                                <div class="dropdown no-arrow">
                                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                         aria-labelledby="dropdownMenuLink">
                                        <div class="dropdown-header">Acciones:</div>
                                        <a class="dropdown-item" href="/Farmaciav1/agregarProducto.jsp">Agregar producto</a>

                                    </div>
                                </div>
                            </div>
                            <div class="card-body">

                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th scope="col">id</th>
                                            <th scope="col">Código</th>
                                            <th scope="col">Nombre</th>
                                            <th scope="col">Fecha de ingreso</th>
                                            <th scope="col">Fecha de vencimiento</th>
                                            <th scope="col">Precio</th>
                                            <th scope="col">Cantidad</th>
                                            <th scope="col">Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th scope="row">1</th>
                                            <td>44545</td>
                                            <td>Paracetamol</td>
                                            <td>02/08/2023</td>
                                            <td>05/06/2025</td>
                                            <td>20.50</td>
                                            <td>65</td>
                                            <td>
                                                <div class="row">
                                                    <button type="button" class="btn btn-warning btn-sm"><i class="bi bi-pencil"></i></button>
                                                    &nbsp;
                                                    <button type="button" class="btn btn-danger btn-sm"><i class="bi bi-trash"></i></button>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>

                    <div class="col">

                        <%@include file="/componentes/status.jsp" %>
                    </div>

                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <%@include file="/componentes/footer.jsp" %>

