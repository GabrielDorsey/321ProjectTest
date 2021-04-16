package frontend;

import backend.FileManager;
import backend.Game;
import backend.GameList;
import backend.User;
import org.json.simple.parser.ParseException;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class userpage {

    private JPanel rootPanel;
    private JTable m_gameTable;
    private JComboBox genreCombo;
    private JTextField searchBox;
    private JButton searchButton;
    private JButton Logout;
    private JButton deleteButton;
    private JLabel titleLabel;
    ArrayList<ChangeListener> searchListener = new ArrayList<>();
    ArrayList< ChangeListener> logoutListener = new ArrayList<>();
    ArrayList<ChangeListener> deleteListener  = new ArrayList<>();
    User m_currentUser = new User();
    Game m_testGame = new Game();
    GameList m_searchResult = new GameList();
    FileManager m_fileManager = new FileManager();


    public userpage(User user) throws IOException, ParseException {

        createTable(user);
        createGenreCombo();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    m_testGame.setTitle(searchBox.getText());
                    if(m_fileManager.isGameInList(m_testGame)){
                        m_searchResult = m_fileManager.gamesSearchResult(m_testGame);

                        ChangeEvent event = new ChangeEvent(this);
                        for (ChangeListener listener : searchListener ) {
                            listener.stateChanged(event);
                        }

                    }
                    else{ JOptionPane.showMessageDialog(null, "GAME NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);}
                } catch (IOException | ParseException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener : logoutListener ) {
                    listener.stateChanged(event);
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener : deleteListener ) {
                    listener.stateChanged(event);
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void createTable(User user) {
        // will make it look better later
        String[][] m_data = new String[user.getGameLists().get(0).getLength()][3];

        int m_counter = 0;

        for (Game g : (Iterable<Game>)user.getGameLists().get(0)) {

            //temporarily replacing commas with spaces to test sorting genres
            String genreL = g.getGenre().replace(",", " ");
            m_data[m_counter][0] = g.getTitle();
            m_data[m_counter][1] = genreL; //TEMPORARY
            m_data[m_counter++][2] = g.getPublisher();
        }

        m_gameTable.setModel( new DefaultTableModel(
                m_data,
                new String[]{"Title", "Genre", "Publisher", "Status", "Rating" }
        ));
      TableColumnModel columns = m_gameTable.getColumnModel();
      columns.getColumn(0).setMinWidth(0);
       m_gameTable.setAutoCreateRowSorter(true); // THIS WILL SORT ALPHABETICALLY AUTOMATICALLY

    }


    public void createGenreCombo(){

        //this arraylist  populates the combobox; definitely needs a better solution
        ArrayList<String> genres  = new ArrayList<>(Arrays.asList("Role Playing", "Action", "Indie", "Active", "Sports", "Massively Multiplayer Online", "Multiplayer Online Battle Arena", "Adventure", "Strategy", "Simulation", "Racing", "First-Person Shooter", "Free To Play", "Puzzle", "Casual", "Platformer", "Arcade", "Family"));
        String genreSelect = (String)genreCombo.getSelectedItem();


        genreCombo.setModel(new DefaultComboBoxModel<String>(genres.toArray(new String[0]))); //sets comboBox labels

        genreCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    String genreSelect = (String)genreCombo.getSelectedItem(); //grab genre selection
                    System.out.print(genreSelect);
                        //sort here
                }
            }
        });


    }



    public void addSearchListener(ChangeListener listener) {
        searchListener.add(listener);
    }
    public void addlogoutListener(ChangeListener listener) {logoutListener.add(listener);}
    public void deleteGameListener(ChangeListener listener){ deleteListener.add(listener);}
    public void setUserTitle(String Name){
        titleLabel.setText(Name);
    }
}
