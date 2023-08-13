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
                <h1 class="h3 mb-4 text-gray-800">Historial de Ventas</h1>

                <div class="row">

                    <div class="col-md-8">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">Tabla de ventas realizadas</h6>
                                
                            </div>
                            <div class="card-body">

                                <%@include file="/componentes/mensaje.jsp" %>

                                <div class="table-responsive">

                                    <table id="my-table" class="table">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Producto</th>
                                                <th scope="col">Fecha de venta</th>
                                                <th scope="col">Cantidad vendida</th>
                                                <th scope="col">Total (Q)</th>
                                                <th scope="col">Acciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th class="prod-id" scope="row">4545</th>
                                                <td>Paracetamol</td>
                                                <td>2023/08/11</td>
                                                <td>2</td>
                                                <td>60.26</td>
                                                <td>
                                                    <div class="row">
                                                        <button type="button" class="btn btn-danger btn-sm btn-delete btn-circle"><i class="bi bi-trash"></i></button>
                                                        &nbsp;
                                                        <button type="button" class="btn btn-info btn-sm btn-circle"><i class="bi bi-eye-fill"></i></button>
                                                    </div>
                                                </td>
                                            </tr>
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
                                                        ¿Estas seguro que deseas eliminar este producto?
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
        
         <script>
            $(document).ready(function() {
                
                $('.btn-delete').click(function(e) {
                    e.preventDefault();
                    const idProd = $(this).closest('tr').find('.prod-id').text();
                    $('#idProductoDlt').val(idProd);
                    $('#deleteProduct').modal('show');
                })
                
            })
        </script>

        <%@include file="/componentes/scripts.jsp" %>

        <%@include file="/componentes/endPage.jsp" %>

