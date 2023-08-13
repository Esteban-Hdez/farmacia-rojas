<%@include file="/componentes/encabezado.jsp" %>

<body id="page-top">


    <!-- Begin Page Content -->
    <div class="container" style="padding-top: 10%;">
        <div class="row justify-content-md-center">
            <div class="col-md-6">

                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <div class="sidebar-brand d-flex align-items-center justify-content-center text-danger h3 text-center" style="margin: 0;">
                            <div class="sidebar-brand-icon rotate-n-15">
                                <i class="fas fa-regular fa-pills"></i>&nbsp;&nbsp;
                            </div>
                            <div class="sidebar-brand-text mx-3 text-uppercase font-weight-bold" style="margin: 0 !important;">Farmacia Rojas</div>
                        </div>
                    </div>
                    <div class="card-body">
                        
                        <h4 class="text-center">Iniciar Sesión</h4>

                        <%@include file="/componentes/mensaje.jsp" %>

                        <form action="SvLogin" method="POST">

                            <div class="form-group">
                                <label for="userLabel">Usuario</label>
                                <div class="input-group mb-2">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text"><i class="bi bi-person-fill"></i></div>
                                    </div>
                                    <input type="email" class="form-control" id="userLabel" name="usuario" placeholder="" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="passLabel">Contraseña</label>
                                <div class="input-group mb-2">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text"><i class="bi bi-key-fill"></i></div>
                                    </div>
                                    <input type="password" class="form-control" id="passLabel" name="contrasenna" placeholder="" required>
                                </div>
                            </div>

                            <div class="text-center">
                                <button type="submit" class="btn btn-success" style="margin-top: 7%;">Iniciar sesión</button>
                            </div>
                        </form>



                    </div>
                </div>

            </div>
        </div>
    </div>

    <%@include file="/componentes/scripts.jsp" %>

    <%@include file="/componentes/endPage.jsp" %>