<%
    request.getSession().removeAttribute("user");
    response.sendRedirect(request.getContextPath()+ "/home");
    request.getSession().removeAttribute("cart");
%>