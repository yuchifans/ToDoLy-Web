<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Showing date</title>
    </head>
    <body>
        <p>
            The chosen date is <%= request.getParameter("selDate1") %>
            The chosen date is <%= request.getParameter("selDate2") %>
        </p>
    </body>
</html>