package frontend;

import backend.FileManager;
import backend.User;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.IOException;

public class ScreenManager {

    public ScreenManager(){}

    public static void main(String[] args) throws IOException, ParseException {
        JFrame testFrame = new JFrame(); //creates a new Jframe
        FileManager m_fileManager = new FileManager();
        User m_user = new User();

        testFrame.setSize(1250,750); //sets the Jframe size
        testFrame.setLocationRelativeTo(null); // sets the location to null so that the Jframe does not open in the top left corner of the screen
        final LoginScreenDisplay userLogin = new LoginScreenDisplay(m_user); //creates a new loginDisplay for the card Layout
        final RegisterUserDisplay userRegister = new RegisterUserDisplay(); // creates a new Register user display for the card Layout;
        final searchResultsDisplay searchResults = new searchResultsDisplay(m_user); //   creates a new testDisplay (CAN  BE CHANGED TO WHATEVER YOU ARE USING TO ADD THE GAME )
        final userpage userListPage = new userpage(m_user); // Creates a new userpage display
        final JPanel cardSet= new JPanel(new CardLayout());       //creates a card set which will accept Jpanels into its cardset;

        cardSet.add(userLogin.getLoginPanel(), "LoginView"); //adds the login display Panel
        cardSet.add(userRegister.getRegisterDisplay(), "RegisterView"); //adds the Regiser Display
        cardSet.add(userListPage.getRootPanel(),"UserView"); //adds the userpage display
        cardSet.add(searchResults.getTest(),"Search Results"); //adds the testDisplay
        testFrame.add(cardSet);  //adds  cardset to the JFrame


        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setVisible(true);
    /*
      Change listener that listens for a change to happen in the userLogin(LoginScreenDisplay) panel, which will be when the Log in button is clicked
     */
        CardLayout cl = (CardLayout) (cardSet.getLayout());

        userLogin.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                userListPage.createTable(m_user);
                userListPage.setUserTitle(m_user.m_Name + "'s Game Diary");
                cl.show(cardSet,  "UserView");// shows the userpage card
            }

        });

        userLogin.addChangeListener2(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent  e2){
                cl.show(cardSet,  "RegisterView"); //shows the the registerUser Card
            }

        });


        userRegister.addListenerReturntoLogin(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                String event = changeEvent.getSource().toString();
                cl.show(cardSet, "LoginView"); //  shows the login card once the user finishes registering
            }
        });
        // don't really now what this does yet
        userRegister.addListenerLogin(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                cl.show(cardSet, "UserView");
            }
        });


        userListPage.addSearchListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                //sets the testDisplay with the information from searching the game ( CAN BE CLEANED UP )
                searchResults.setGameDisplay(userListPage.m_searchResult);
                searchResults.setSearchResultLabel("Search Results for:  " + userListPage.m_testGame.m_Title);
                cl.show(cardSet, "Search Results"); //dispays the Testdisplay with the game information (CAN BE CHANGED TO WHAT YOU NEED)
            }
        });
        userListPage.deleteGameListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
             userListPage.createTable(m_user);
            }
        });


        userListPage.addlogoutListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                m_user.getGameLists().get(0).clearList();
                cl.show(cardSet,  "LoginView");
            }
        });


        searchResults.addReturntoUserPage(new ChangeListener() {
            @Override

            public void stateChanged(ChangeEvent e) {
                userListPage.createTable(m_user);
                cl.show(cardSet,"UserView");
            }
        });
        searchResults.addAnotherGame(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                searchResults.setSearchResultLabel("Search Results for:  " + searchResults.m_testGame.m_Title);
                searchResults.setGameDisplay(searchResults.m_NewSearchResult);
            }
        });

    }

    }

    // Unsure but on exit(), I want to just run save

