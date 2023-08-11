<%

    String mensaje = (String) session.getAttribute("mensaje");

    if (mensaje != null) {
        String estiloAlert = (String) session.getAttribute("style");
%>

<div class="alert alert-<%=estiloAlert%> alert-dismissible fade show" role="alert">
    <%=mensaje%>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<%
        session.removeAttribute("style");
        session.removeAttribute("mensaje");
    }
%>
