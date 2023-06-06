import javax.swing.*;

import java.util.Iterator;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private WordList ListPanel;

    public Menu() {
        super("Dictionary Manager");

        getContentPane().setBackground(new Color(33, 19, 13));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(175, 175, 175));
        JMenu dictionaryMenu = new JMenu("Dictionary");
        menuBar.add(dictionaryMenu);

        JMenuItem option = new JMenuItem("Add Word");
        dictionaryMenu.add(option);
        option.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                addWord();
            }
        });

        option = new JMenuItem("Delete Word");
        dictionaryMenu.add(option);
        option.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                deleteWord();
            }
        });

        dictionaryMenu.addSeparator();

        option = new JMenuItem("Search Word");
        dictionaryMenu.add(option);
        option.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                searchWord();
            }
        });

        option = new JMenuItem("List Words");
        dictionaryMenu.add(option);
        option.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                WordList.updateList();
                updateWordsList();
            }
        });

        dictionaryMenu.addSeparator();

        option = new JMenuItem("Exit");
        dictionaryMenu.add(option);
        option.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        
        Dictionary.loadFile();

        WordList.updateList();
        loadWordsList();
        addDataMenu(menuBar);
        generateToolBar();
        setJMenuBar(menuBar);

    }

    public void exit() {
        Dictionary.saveFile();
        System.exit(0);
    }

    public void centerToParent(JFrame parent, JFrame child) {
        Dimension parentDimension = parent.getSize();
        Dimension childDimension = child.getSize();
        int x = (int) (parentDimension.getWidth() - childDimension.getWidth()) / 2;
        int y = (int) (parentDimension.getHeight() - childDimension.getHeight()) / 2;
        child.setLocation(x, y);
    }

    public void addWord() {
        AddWord window = new AddWord();
        centerToParent(Menu.this, window);
        window.setVisible(true);
    }
    
    public void deleteWord() {
        DeleteWord window = new DeleteWord();
        centerToParent(Menu.this, window);
        window.setVisible(true);
    }

    public void searchWord() {
        SearchWord window = new SearchWord();
        centerToParent(Menu.this, window);
        window.setVisible(true);
    }

    public void loadWordsList() {
        ListPanel = new WordList();
        ListPanel.setBackground(new Color(33, 19, 13));
        ListPanel.setVisible(true);

        getContentPane().add(ListPanel, BorderLayout.WEST);
    }

    public void updateWordsList() {
        getContentPane().remove(ListPanel);
        loadWordsList();
        
        update(getGraphics());
        revalidate();
        repaint();
    }

    public void bucketSort() {
        WordList.updateListByBucketSort();
        updateWordsList();
    }

    public void quickSort() {
        WordList.updateListByQuickSort();
        updateWordsList();
    }

    public void radixSort() {
        WordList.updateListByRadixSort();
        updateWordsList();
    }
    
    public void deleteAll() {
        Map<String, String> wordsList = Dictionary.getWords();
        Iterator<Map.Entry<String, String>> entries = wordsList.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>)entries.next();
            entries.remove();
            //Dictionary.deleteWord(entry.getKey());
        }
        Dictionary.saveFile();
        updateWordsList();
    }

    public void generateToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(new Color(173, 166, 163));
        JButton button = new JButton("Add");
        button.setPreferredSize(new Dimension(32, 32));
        toolBar.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addWord();
            }
        });

        button = new JButton("Delete");
        button.setPreferredSize(new Dimension(32, 32));
        toolBar.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteWord();
            }
        });

        button = new JButton("Delete All");
        button.setPreferredSize(new Dimension(32, 32));
        toolBar.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAll();
            }
        });

        button = new JButton("Search");
        button.setPreferredSize(new Dimension(32, 32));
        toolBar.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchWord();
            }
        });

        button = new JButton("List Words");
        button.setPreferredSize(new Dimension(32, 32));
        toolBar.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WordList.updateList();
                updateWordsList();
            }
        });

        toolBar.addSeparator();

        button = new JButton("Save");
        button.setPreferredSize(new Dimension(32, 32));
        toolBar.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dictionary.saveFile();
            }
        });

        button = new JButton("Load");
        button.setPreferredSize(new Dimension(32, 32));
        toolBar.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dictionary.loadFile();
            }
        });

        getContentPane().add(toolBar, BorderLayout.NORTH);

        toolBar.addSeparator();

        button = new JButton("Sort by Bucket Sort");
        button.setPreferredSize(new Dimension(32, 32));
        toolBar.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bucketSort();
            }
        });

        button = new JButton("Sort by Quick Sort");
        button.setPreferredSize(new Dimension(32, 32));
        toolBar.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quickSort();
            }
        });

        button = new JButton("Sort by Radix Sort");
        button.setPreferredSize(new Dimension(32, 32));
        toolBar.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radixSort();
            }
        });
    }

    public void addDataMenu(JMenuBar menuBar) {
        JMenu dataMenu = new JMenu("File");

        JMenuItem option = new JMenuItem("Save");
        option.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dictionary.saveFile();
            }
        });

        option = new JMenuItem("Load");
        dataMenu.add(option);
        option.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dictionary.loadFile();
            }
        });

        menuBar.add(dataMenu);
    }
}
