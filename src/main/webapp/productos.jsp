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
                <h1 class="h3 mb-4 text-gray-800">Productos</h1>

                <div class="row">

                    <div class="col-md-8">
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

                                <%@include file="/componentes/mensaje.jsp" %>

                                <div class="table-responsive">

                                    <table id="my-table" class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">Id</th>
                                                <th scope="col">Código</th>
                                                <th scope="col">Nombre</th>
                                                <th scope="col">Fecha de ingreso</th>
                                                <th scope="col">Fecha de vencimiento</th>
                                                <th scope="col">Precio (Q)</th>
                                                <th scope="col">Cantidad</th>
                                                <th scope="col">Acciones</th>
                                            </tr>
                                        </thead>
                                        <%List<Producto> productos = (List) request.getSession().getAttribute("productos"); %>
                                        <tbody>
                                            <% for (Producto p : productos) {%>
                                            <tr>
                                                <th class="prod-id" scope="row"><%=p.getIdProducto()%></th>
                                                <td><%=p.getCodigoBarras()%></td>
                                                <td class="prod-nom"><%=p.getNombre()%></td>
                                                <td><%=p.getFechaIngreso()%></td>
                                                <td><%=p.getFechaVencimiento()%></td>
                                                <td><%=p.getPrecio()%></td>
                                                <td><%=p.getCantidad()%></td>
                                                <td>
                                                    <div class="row">
                                                        <a href="SvEditar?id_producto=<%=p.getIdProducto()%>" class="btn btn-warning btn-sm btn-circle" role="button" aria-pressed="true"><i class="bi bi-pencil"></i></a>
                                                        &nbsp;
                                                        <button type="button" class="btn btn-danger btn-sm btn-delete btn-circle"><i class="bi bi-trash"></i>
                                                    </div>
                                                </td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>

                                    <!-- Modal -->
                                    <div class="modal fade" id="deleteProduct" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="deleteProduct" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="deleteProduct"><i class="bi bi-trash text-danger"></i>&nbsp;&nbsp;Eliminar Producto</h5>
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <form action="SvEliminar" method="POST">
                                                    <div class="modal-body">
                                                        <input type="hidden" name="idProducto" id="idProductoDlt">
                                                        <h5>¿Estás seguro de que deseas eliminar este producto?</h5>
                                                        <br>
                                                        <h6 id="idProductMsg">Número de producto: <strong><span></span></strong></h6>
                                                        <h6 id="nomProductoMsg">Nombre del producto: <strong><span></span></strong></h6>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                        <button type="submit" class="btn btn-danger">Eliminar</button>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

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

        <script>
            $(document).ready(function () {
                $('.btn-delete').click(function (e) {
                    e.preventDefault();
                    const idProd = $(this).closest('tr').find('.prod-id').text();
                    const nombreProducto = $(this).closest('tr').find('.prod-nom').text();
                    $('#idProductoDlt').val(idProd);
                    $('#idProductMsg span').html(idProd);
                    $('#nomProductoMsg span').html(nombreProducto);
                    $('#deleteProduct').modal('show');
                });
            });
        </script>

        <%@include file="/componentes/endPage.jsp" %>

