package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Formatter;

/**
 * Один отражающийся шарик в прямоугольной коробке.
 * Весь код в одном файле. Это неграмотный дизайн кода!
 */


// Расширяем поведение(добавляем) на основе базовой JPanel, для того что бы изменить отрисовку этого компонента 
public class BouncingBallSimple extends JPanel {
    // Зададим ширину и высоту контейнера для шарика
    private static final int BOX_WIDTH = 640;
    private static final int BOX_HEIGHT = 480;

    // Зададим характеристики шарика
    private float ballRadius = 50; // Радиус шарика
    // Координаты центра (x, y)
    private float ballX = ballRadius + 50;
    private float ballY = ballRadius + 20;
    private float ballSpeedX = 3;   // Скорость шарика по различным осям
    private float ballSpeedY = 2;

    private static final int UPDATE_RATE = 30; // Число отвечающее за количество обновлений экрана за единицу времени

    /**
     * Создадим конструктор для создания UI и инициализации объектов
     */
    public BouncingBallSimple() {
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));

        // Дадим толчок нашему шарику (из отдельного потока)
        Thread gameThread = new Thread() {
            public void run() {
                while (true) { // вечный цикл обновления
                    // Модифицируем координаты шарика по осям на некоторую дельту
                    ballX += ballSpeedX;
                    ballY += ballSpeedY;
                    // Проверка на пересечение границ
                    // Если пересекли, то изменяем вектор скорости и координаты границ
                    if (ballX - ballRadius < 0) {
                        ballSpeedX = -ballSpeedX; // Инвертация вектора движения (обычное отражение)
                        ballX = ballRadius; // Релокация шарика относительно границы
                    } else if (ballX + ballRadius > BOX_WIDTH) {
                        ballSpeedX = -ballSpeedX;
                        ballX = BOX_WIDTH - ballRadius;
                    }
                    // Проверим так же для двух других
                    if (ballY - ballRadius < 0) {
                        ballSpeedY = -ballSpeedY;
                        ballY = ballRadius;
                    } else if (ballY + ballRadius > BOX_HEIGHT) {
                        ballSpeedY = -ballSpeedY;
                        ballY = BOX_HEIGHT - ballRadius;
                    }
                    // вызываем перерисовку компонента
                    repaint(); // Callback paintComponent()
                    // Задержка между вызовами перерисовки компонента
                    try {
                        Thread.sleep(1000 / UPDATE_RATE);  // миллисекунды
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        gameThread.start();  // вызываем исполнение потока
    }

    /**
     * Переопределяем поведение для отрисовки компонента
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);    // Вызываем базовую отрисовку компонента

        // Рисуем контейнер
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

        // Рисуем шарик
        g.setColor(Color.BLUE);
        g.fillOval((int) (ballX - ballRadius), (int) (ballY - ballRadius),
                (int) (2 * ballRadius), (int) (2 * ballRadius));

        // Выводим информацию о шарике
        g.setColor(Color.WHITE);
        g.setFont(new Font("Courier New", Font.PLAIN, 12));
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("Ball @(%3.0f,%3.0f) Speed=(%2.0f,%2.0f)", ballX, ballY,
                ballSpeedX, ballSpeedY);
        g.drawString(sb.toString(), 20, 30);
    }

    /**
     * точка входа нашей программы
     */
    public static void main(String[] args) {
        // вызываем отрисовку через композитный менеджер в новом потоке
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Задаем основное окно программы
                JFrame frame = new JFrame("A Bouncing Ball");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(new BouncingBallSimple());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}