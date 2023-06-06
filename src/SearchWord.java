import javax.swing.*;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchWord extends JFrame {
    private JTextField word;
    private JTextArea definition;
    private JButton searchButton;

    public SearchWord() {
        super("Search Word");

        GridBagLayout gbl  = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        word = new JTextField(20);
        definition = new JTextArea();
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (word.getText().length() > 0) {
                    String def = Dictionary.searchWord(word.getText());
                    if (def != null) {
                        definition.setText(def);
                    } else {
                        JOptionPane.showMessageDialog(SearchWord.this, "Could not find word.", "Search Word", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(SearchWord.this, "Could not find word.", "Search Word", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        Container container = getContentPane();
        container.setLayout(gbl);

        gbc.anchor = GridBagConstraints.EAST;
        container.add(new JLabel("Search Word:"));
        gbc.anchor = GridBagConstraints.WEST;
        container.add(word);
        gbc.anchor = GridBagConstraints.EAST;
        container.add(searchButton);

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        container.add(new JLabel("Definition:"));
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        definition.setRows(3);
        definition.setColumns(30);
        JScrollPane sp = new JScrollPane(definition, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        container.add(sp, gbc);

        pack();
    }
}
