package lab6.servlets;

import lab6.entities.Student;
import lab6.utils.FileUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddServlet extends HttpServlet {
    private final FileUtil fileUtil = new FileUtil();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fileName");
        Student student = new Student("", "", "", "", "");
        if (fileName != null) {
            student = fileUtil.getStudent(fileName);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
        req.setAttribute("student", student);
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String middleName = req.getParameter("middleName");
        String subject = req.getParameter("subject");
        String mark = req.getParameter("mark");
        if (!name.equals("") || !surname.equals("") || !middleName.equals("") || !subject.equals("") || !mark.equals("")) {
            Student student = new Student(name, surname, middleName, subject, mark);
            if (fileUtil.writeStudent(student)) {
                req.setAttribute("updated", true);
            } else {
                req.setAttribute("updated", false);
            }
        }
        req.setAttribute("studentName", name);
        doGet(req, resp);
    }
}
