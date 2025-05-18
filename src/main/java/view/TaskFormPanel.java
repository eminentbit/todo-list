package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Task;

public class TaskFormPanel extends JPanel {
    private JTextField titleField;
    private JTextField descriptionField;
    private JComboBox<String> priorityCombo;
    private JTextField dueDateField;

    private JButton addButton;

    public interface TaskFormListener {
        void onTaskFormSubmit(Task task);
    }

    private TaskFormListener listener;

    public TaskFormPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel titleLabel = new JLabel("Title:");
        JLabel descLabel = new JLabel("Description:");
        JLabel priorityLabel = new JLabel("Priority:");
        JLabel dueDateLabel = new JLabel("Due Date (YYYY-MM-DD):");

        titleField = new JTextField(15);
        descriptionField = new JTextField(15);
        priorityCombo = new JComboBox<>(new String[] { "Low", "Medium", "High" });
        dueDateField = new JTextField(10);

        addButton = new JButton("Add Task");
        addButton.addActionListener(e -> submitForm());

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);
        gbc.gridx = 1;
        add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(descLabel, gbc);
        gbc.gridx = 1;
        add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(priorityLabel, gbc);
        gbc.gridx = 1;
        add(priorityCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(dueDateLabel, gbc);
        gbc.gridx = 1;
        add(dueDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(addButton, gbc);
    }

    private void submitForm() {
        String title = titleField.getText().trim();
        String description = descriptionField.getText().trim();
        String priority = (String) priorityCombo.getSelectedItem();
        String dueDateStr = dueDateField.getText().trim();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate dueDate;
        if (dueDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Due Date must be provided", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            dueDate = dueDateStr.isEmpty() ? LocalDate.now() : LocalDate.parse(dueDateStr);
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid due date format. Use YYYY-MM-DD.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            return;

        }

        Task newTask = new Task(title, description, priority, dueDate);

        if (listener != null) {
            listener.onTaskFormSubmit(newTask);
        }

        clearForm();
    }

    private void clearForm() {
        titleField.setText("");
        descriptionField.setText("");
        priorityCombo.setSelectedIndex(1); // Default to Medium
        dueDateField.setText("");
    }

    public void setTaskFormListener(TaskFormListener listener) {
        this.listener = listener;
    }
}