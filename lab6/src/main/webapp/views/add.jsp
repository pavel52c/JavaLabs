<%@ page import="lab6.entities.Student" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    String title = "Добавить студента";
    Student student = null;
    String studentName = "";
    String studentSurname = "";
    String studentMiddleName = "";
    String subject = "";
    String mark = "";
    if (request.getAttribute("student") != null) {
        title = "Изменить студента";
        student = (Student) request.getAttribute("student");
        studentName = student.getName();
        studentSurname = student.getSurname();
        studentMiddleName = student.getMiddleName();
        subject = student.getSubject();
        mark = student.getMark();
    }
    if (request.getAttribute("updated") != null) {
        if (request.getAttribute("updated") != null) {
            if (request.getAttribute("studentName") != null) {
                if (request.getAttribute("studentName").equals("")) {
                    out.println("<p>Поля должны быть заполнены!</p>");
                } else {
                    out.println("<p>Студент '" + request.getAttribute("studentName") + "' Добавлен!</p>");
                }
            }
        } else {
            throw new ServletException("Mandatory Parameter missing");
        }
    }

%>
<html>
<head>
    <title>
        <%
            out.println("<title>" + title + "</title>");
        %>
    </title>
</head>
<body>
<div>
    <h1>Лабораторная 6</h1>
</div>
<div>
    <div>
        <div>
            <%
                out.println("<h2>" + title + "</h2>");
            %>
        </div>
        <form method="post">
            <label>Имя:
                <%
                    out.println("<input type=\"text\" name=\"name\" value=\"" + studentName + "\"><br />");
                %>
            </label>
            <label>Фаминия:
                <%
                    out.println("<input type=\"text\" name=\"surname\" value=\"" + studentSurname + "\"><br />");
                %>
            </label>
            <label>Отчество:
                <%
                    out.println("<input type=\"text\" name=\"middleName\" value=\"" + studentMiddleName + "\"><br />");
                %>
            </label>
            <label>Дисциплина:
                <%
                    out.println("<input type=\"text\" name=\"subject\" value=\"" + subject + "\"><br />");
                %>
            </label>
            <label>Оценка:
                <%
                    out.println("<input type=\"text\" name=\"mark\" value=\"" + mark + "\"><br />");
                %>
            </label>
            <button type="submit">Добавить</button>
        </form>
    </div>
</div>
<div>
    <button onclick="location.href='/lab6'">Вернуться назад</button>
</div>
</body>
</html>
