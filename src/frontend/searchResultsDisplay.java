package frontend;

import backend.FileManager;
import backend.Game;
import backend.GameList;
import backend.User;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class searchResultsDisplay extends JPanel{
    private JPanel test;
    private JTable m_gameTable;
    private JButton returntoUserPageButton;
    private JButton searchButton;
    private JButton m_addGameButton;
    private JTextField searchGameText;
    private FileManager m_fileManager;
    private FileManager m_fileManager1 = new FileManager();
    private JLabel searchResultLabel;
    private JTextField scoreTextField;
    private JTextField textField1;
    private JLabel StatusLabel;
    Game m_testGame = new Game();
    private int m_row = 0;
    private int m_column = 0;
    GameList m_searchResult = new GameList();
    GameList m_NewSearchResult = new GameList();
    ArrayList <ChangeListener> returntoUser = new ArrayList<>();
    ArrayList <ChangeListener> m_addGame = new ArrayList<>();
    ArrayList <ChangeListener> m_addAnotherGame = new ArrayList<>();


    public searchResultsDisplay(User user) throws IOException, ParseException {
        returntoUserPageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener : returntoUser) {
                    listener.stateChanged(event);
                }
            }
        });
        m_addGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    m_fileManager = new FileManager();
                } catch (IOException | ParseException e) {
                    e.printStackTrace();
                }
                m_row = m_gameTable.getSelectedRow();
                String m_title = (String) m_gameTable.getValueAt(m_row, 0);

                for(Game g : (Iterable<Game>) m_searchResult){
                    if(g.getTitle().equals(m_title)){
                        user.getGameLists().get(0).addGame(g);
                        try {
                            m_fileManager.saveUserData(user);
                            JOptionPane.showMessageDialog(null, "Game Added!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                            ChangeEvent event = new ChangeEvent(this);
                            for (ChangeListener listener : m_addGame) {
                                listener.stateChanged(event);
                            }
                            break;
                        } catch (IOException | ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                m_testGame.setTitle(searchGameText.getText());
                try {
                    if(m_fileManager1.isGameInList(m_testGame)){
                       m_NewSearchResult = m_fileManager1.gamesSearchResult(m_testGame);
                        ChangeEvent event = new ChangeEvent(this);
                        for (ChangeListener listener : m_addAnotherGame) {
                            listener.stateChanged(event);
                        }
                    }else { JOptionPane.showMessageDialog(null, "GAME NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);}
                } catch (IOException | ParseException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public void setGameDisplay(GameList gameList){
         String[][] m_data = new String[gameList.getLength()][3];
         int m_counter = 0;

         for (Game g : (Iterable<Game>)gameList) {
             m_data[m_counter][0] = g.getTitle();
             m_data[m_counter][1] = g.getGenre();
             m_data[m_counter++][2] = g.getPublisher();

             Game m_tmpGame = new Game();
             m_tmpGame = g;
             m_searchResult.addGame(g);
         }

         m_gameTable.setModel( new DefaultTableModel(
                 m_data,
                 new String[]{"Title", "Genre", "Publisher"}
         ));
         TableColumnModel columns = m_gameTable.getColumnModel();
         columns.getColumn(0).setMinWidth(0);
        m_gameTable.setAutoCreateRowSorter(true);
    }



    public JPanel getTest(){return test;}

    public void addReturntoUserPage(ChangeListener listener) {
        returntoUser.add(listener);
    }
    public void addAnotherGame(ChangeListener listener){
        m_addAnotherGame.add(listener);
    }
    public void setSearchResultLabel(String resultLabel){ searchResultLabel.setText(resultLabel);}
}

