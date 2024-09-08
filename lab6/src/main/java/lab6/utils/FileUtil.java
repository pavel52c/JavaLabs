package lab6.utils;

import lab6.entities.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileUtil implements Serializable {
    private final String PATH = System.getProperty("user.dir") + "\\output\\";

    public Student getStudent(String fileName) {
        Student student = null;
        try {
            File file = new File(PATH + fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String name = reader.readLine();
            String surname = reader.readLine();
            String middleName = reader.readLine();
            String subject = reader.readLine();
            String mark = reader.readLine();
            student = new Student(name, surname, middleName, subject, mark);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }

    public boolean writeStudent(Student student) throws IOException {
        String fileName = student.getSurname() + "_" +
                student.getName() + "_" +
                student.getMiddleName();
        List<File> fileList = getFileList();
        for (File tmpFile : fileList) {
            if (fileName.equals(tmpFile.getName())) {
                Student tmpStudent = getStudent(fileName);
                if (!checkMark(tmpStudent.getMark())) {
                    writeFile(fileName, student);
                    return true;
                } else {
                    return false;
                }
            }
        }
        writeFile(fileName, student);
        return true;
    }

    public List<File> getFileList() {
        File dir = new File(PATH);
        return Arrays.asList(Objects.requireNonNull(dir.listFiles()));
    }

    public List<Student> getStudents() {
        List<File> files = getFileList();
        List<Student> students = new ArrayList<>();
        for (File f : files) {
            Student student = getStudent(f.getName());
            students.add(student);
        }
        return students;
    }

    private void writeFile(String fileName, Student student) throws IOException {
        File file = new File(PATH + fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(student.getName());
        fileWriter.write("\n");
        fileWriter.write(student.getSurname());
        fileWriter.write("\n");
        fileWriter.write(student.getMiddleName());
        fileWriter.write("\n");
        fileWriter.write(student.getSubject());
        fileWriter.write("\n");
        fileWriter.write(student.getMark());
        fileWriter.write("\n");
        fileWriter.flush();
        fileWriter.close();
    }

    private boolean checkMark(String mark) {
        String[] markArray = {"3", "4", "5", "удовлетворительно", "хорошо", "отлично", "удовл", "хор", "отл", "удв"};
        for (String s : markArray) {
            if (s.equals(mark)) {
                return true;
            }
        }
        return false;
    }
}
