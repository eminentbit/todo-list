package view;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import model.Task;
import model.TaskManager;

public class MainWindow extends JFrame {

    private final TaskManager taskManager;
    private TaskFormPanel taskFormPanel;

    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;

    public MainWindow(TaskManager taskManager) {
        this.taskManager = taskManager;
        initUI();
    }

    private void initUI() {
        setTitle("To-Do List");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Layout
        setLayout(new BorderLayout(10, 10));

        // Task List Panel
        taskListModel = new DefaultListModel<>();
        taskList = new JList<Task>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Task selectedTask = taskList.getSelectedValue();
                if (selectedTask != null) {
                    // Handle task selection here
                    System.out.println("Selected task: " + selectedTask);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(taskList);
        add(scrollPane, BorderLayout.CENTER);

        taskFormPanel = new TaskFormPanel();
        taskFormPanel.setTaskFormListener(task -> {
            taskManager.addTask(task);
            refreshTaskList();
        });

        add(taskFormPanel, BorderLayout.SOUTH);

        refreshTaskList();
    }

    private void refreshTaskList() {
        taskListModel.clear();
        for (Task task : taskManager.getAllTasks()) {
            taskListModel.addElement(task);
        }
    }
}