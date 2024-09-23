package org.example.Threads;

import org.example.dbworker.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DirtyRead {
    private final DBConnector dbConnector = new DBConnector();
    private final Connection connection = dbConnector.getConnection();

    public DirtyRead() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void makeDirtyRead() {
        Thread updateThread = new Thread(() -> {
            try {
                // Отключаем авто-коммит
                connection.setAutoCommit(false);
                // Обновляем данные
                String queryString = "UPDATE student SET studentName = 'Jack' WHERE id = 10";
                PreparedStatement statement = connection.prepareStatement(queryString);
                statement.executeUpdate();

                // Просто ждем перед коммитом, чтобы дать время потоку B произвести чтение
                System.out.println("updateThread: Updated students but has not committed yet...");
                Thread.sleep(5000); // Задержка 5 секунд

                // Коммитим изменения
                connection.commit();
                System.out.println("UpdateThreads: Changes committed.");
            } catch (SQLException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread readThread = new Thread(() -> {
            try {
                // Устанавливаем уровень изоляции, чтобы разрешить грязное чтение
                connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

                // Читаем данные до их коммита
                String readString = "SELECT studentName, studentSurname, numberOfRecordBook, subjectName, countOfHours, mark" +
                        " FROM exam, student where exam.student_id=student.id UNION " +
                        "SELECT studentName, studentSurname, numberOfRecordBook, subjectName, countOfHours, mark" +
                        " FROM credit, student where credit.student_id=student.id order by numberOfRecordBook";
                PreparedStatement statement = connection.prepareStatement(readString);
                ResultSet resultSet = null;
                try {
                    resultSet = statement.executeQuery();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Getting data from database...");
                while (true) {
                    try {
                        assert resultSet != null;
                        if (!resultSet.next()) break;
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    String studentName = resultSet.getString(1);
                    String studentSurname = resultSet.getString(2);
                    int numberOfRecordBook = resultSet.getInt(3);
                    String subjectName = resultSet.getString(4);
                    int countOfHours = resultSet.getInt(5);
                    String mark = resultSet.getString(6);

                    System.out.println("studentName = " + studentName + " studentSurname = " + studentSurname +
                            " numberOfRecordBook = " + numberOfRecordBook
                            + " subjectName = " + subjectName + " countOfHours = " + countOfHours + " mark = " + mark);
                }
                System.out.println("Read thread end");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        updateThread.start();
        readThread.start();
    }
}
