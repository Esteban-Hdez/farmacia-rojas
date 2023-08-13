<%@page import="java.util.List"%>
<%@page import="Dominio.Producto"%>
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
                <h1 class="h3 mb-4 text-gray-800">Stock</h1>

                <div class="row">

                    <div class="col-md-8">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Productos en stock</h6>
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


                                <div class="table-responsive">

                                    <table id="my-table" class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">Código</th>
                                                <th scope="col">Nombre</th>
                                                <th scope="col">Tipo de producto</th>
                                                <th scope="col">Fecha de ingreso</th>
                                                <th scope="col">Fecha de vencimiento</th>
                                                <th scope="col">Precio (Q)</th>
                                                <th scope="col">Cantidad</th>
                                            </tr>
                                        </thead>
                                        <%List<Producto> productos = (List) request.getSession().getAttribute("productos"); %>
                                        <tbody>
                                            <% for (Producto p : productos) {%>
                                            <tr>
                                                <th scope="row"><%=p.getCodigoBarras()%></th>
                                                <td><%=p.getNombre()%></td>
                                                <td><%=p.getTipo() %></td>
                                                <td><%=p.getFechaIngreso()%></td>
                                                <td><%=p.getFechaVencimiento()%></td>
                                                <td><%=p.getPrecio()%></td>
                                                <td><%=p.getCantidad()%></td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>

                                </div>

                            </div>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <%@include file="/componentes/status.jsp" %>
                    </div>

                </div>

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- End of Main Content -->

        <%@include file="/componentes/footer.jsp" %>


        <script src="https://code.jquery.com/jquery-3.7.0.js"></script>
        <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
        <script>
            new DataTable('#my-table', {
                lengthMenu: [
                    [10, 20, -1],
                    [10, 20, 'Todos']
                ],
                language: {
                    url: 'https://cdn.datatables.net/plug-ins/1.11.5/i18n/es-ES.json',
                }
            });
        </script>

        <%@include file="/componentes/scripts.jsp" %>

        <%@include file="/componentes/endPage.jsp" %>

