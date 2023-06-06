import javax.swing.*;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWord extends JFrame {
    private JTextField word;
    private JTextArea definition;
    private JButton newButton;

    public AddWord() {
        super("Add Word");

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        word = new JTextField(30);
        definition = new JTextArea();
        newButton = new JButton("Add Word");
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (word.getText().length() > 0 && definition.getText().length() > 0) {
                    Dictionary.addWord(word.getText(), definition.getText());
                    JOptionPane.showMessageDialog(AddWord.this,  "Word added successfully", "Add Word", JOptionPane.INFORMATION_MESSAGE);
                    word.setText("");
                    definition.setText("");
                    word.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(AddWord.this,  "Word or definition missing", "Add Word", JOptionPane.ERROR_MESSAGE);
                    word.requestFocus();
                }
            }
        });

        Container container = getContentPane();
        container.setLayout(gbl);

        gbc.anchor = GridBagConstraints.EAST;
        container.add(new JLabel("Enter word:"), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        container.add(word);

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        container.add(new JLabel("Enter definition:"), gbc);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        definition.setRows(3);
        definition.setColumns(30);
        JScrollPane sp = new JScrollPane(definition, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        container.add(sp, gbc);

        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        container.add(newButton, gbc);

        pack();
    }
}
