import javax.swing.*;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteWord extends JFrame {
    private JTextField word;
    private JButton deleteButton;

    public DeleteWord() {
        super("Delete Word");

        word = new JTextField(20);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (word.getText().length() > 0) {
                    Dictionary.deleteWord(word.getText());
                    JOptionPane.showMessageDialog(DeleteWord.this,  "Word deleted successfully", "Delete Word", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(DeleteWord.this, "Invalid word", "Delete Word", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(new JLabel("Word To Delete"));
        container.add(word);
        container.add(deleteButton);

        pack();
    }
}
