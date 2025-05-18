package app;

import javax.swing.SwingUtilities;

import model.TaskManager;
import view.MainWindow;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow(taskManager);
            window.setVisible(true);
        });
    }
}