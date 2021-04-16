package frontend;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

import backend.FileManager;
import backend.User;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoginScreenDisplay  extends JPanel{
    private JPanel loginPanel;
    private JLabel WelcomeLabel;
    private JTextField usernameTextField;
    private JLabel usernameLabel;
    private JTextField passwordTextField;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JLabel loginLabel;
    private JLabel registerLabel;
    private JButton registerButton;
    ArrayList<ChangeListener> listeners = new ArrayList<>();
    ArrayList<ChangeListener> listeners2 = new ArrayList<>();

    FileManager m_fileManager = new FileManager();


    public LoginScreenDisplay(User user) throws IOException, ParseException {;

        /**
         * Action Button Listener that will search a JSON file for a username and try  and match it with the inputted username and passowrd on the login button lick
         *
         */
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                user.setName(usernameTextField.getText());
                user.setPassword(passwordTextField.getText());

                //if the the login function is true.
                try{
                    if(m_fileManager.isRegisteredUser(user)){
                        ChangeEvent event = new ChangeEvent(this);
                        m_fileManager.loadUser(user);
                        for (ChangeListener listener : listeners)
                            listener.stateChanged(event);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "USER NOT FOUND", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException | ParseException ioException) {
                    ioException.printStackTrace();
                }


            }
        });


        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangeEvent event = new ChangeEvent(this);
                for (ChangeListener listener : listeners2)
                    listener.stateChanged(event);
            }
        });
    }

    /**
     * Function: getLoginPanel
     * Description: function that returns the Jpanel of the Login view
     * @return the Jpanel of the LoginView
     */
    public JPanel getLoginPanel (){
        return loginPanel;
    }


    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public void addChangeListener2(ChangeListener listener) {
        listeners2.add(listener);
    }

}

