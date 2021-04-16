package frontend;

import backend.FileManager;
import backend.User;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RegisterUserDisplay extends JPanel{

    FileManager m_fileManager = new FileManager();
    User m_tmpUser = new User();

    final JPanel m_registerDisplay = new JPanel();
    final JLabel l_createUser = new JLabel("<html><B>Create User</B>");
    final JLabel l_userNameLabel = new JLabel("Create UserName:");
    final JTextField l_userNameBlank = new JTextField(30);
    final JLabel l_passwordLabel = new JLabel("Create Password:");
    final JTextField l_passwordBlank = new JTextField(30);
    final JButton l_submitButton = new JButton("Create User");
    final JButton l_returnToLogin = new JButton("Return to Login Page");
    final JLabel l_spaceField = new JLabel("<html><br><br>");
    final JLabel l_errorMessage = new JLabel();

    String m_inputStringUserName;
    String m_inputStringPassword;

    final ArrayList<ChangeListener> listeners = new ArrayList<>();
    final ArrayList<ChangeListener> returnToLoginListener = new ArrayList<>();
    public RegisterUserDisplay() throws IOException, ParseException {
        if(m_tmpUser.getName() == null || m_tmpUser.getName().equals("")){
            l_userNameLabel.setText("Create Username: ");
        }

        if(m_tmpUser.getPassword() == null || m_tmpUser.getPassword().equals("")){
            l_passwordLabel.setText("Create Password:  ");
        }
        l_userNameBlank.setPreferredSize(new Dimension(150, 20));
        l_passwordBlank.setPreferredSize(new Dimension(150, 20));
        l_userNameBlank.setMaximumSize(new Dimension(200, 20));
        l_passwordBlank.setMaximumSize(new Dimension(200, 20));

        l_createUser.setPreferredSize(new Dimension(450, 40));
        l_userNameLabel.setPreferredSize(new Dimension(450, 40));
        l_passwordLabel.setPreferredSize(new Dimension(450, 40));
        l_spaceField.setPreferredSize(new Dimension(400, 50));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        m_registerDisplay.add(l_createUser);
        m_registerDisplay.add(l_spaceField);
        m_registerDisplay.add(l_userNameLabel);
        m_registerDisplay.add(l_userNameBlank);
        m_registerDisplay.add(l_spaceField);
        m_registerDisplay.add(l_passwordLabel);
        m_registerDisplay.add(l_passwordBlank);
        m_registerDisplay.add(l_spaceField);
        m_registerDisplay.add(l_submitButton);
        m_registerDisplay.add(l_returnToLogin);
        m_registerDisplay.add(l_errorMessage);

        m_registerDisplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_createUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_userNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        l_userNameBlank.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        l_passwordBlank.setAlignmentX(Component.CENTER_ALIGNMENT);
        l_submitButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        l_returnToLogin.setAlignmentX(Component.RIGHT_ALIGNMENT);
        l_errorMessage.setAlignmentX(Component.CENTER_ALIGNMENT);


        m_inputStringUserName = "";
        m_inputStringPassword = "";

        l_submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                m_inputStringUserName = l_userNameBlank.getText();
                m_inputStringPassword = l_passwordBlank.getText();

                m_tmpUser.setInfo(m_inputStringUserName, m_inputStringPassword);
                try {
                    m_fileManager.addUser(m_tmpUser);

                    ChangeEvent event = new ChangeEvent(this);
                    for(ChangeListener listener : returnToLoginListener){
                        listener.stateChanged(event);
                    }
                } catch (IOException | ParseException e) {
                    JOptionPane.showMessageDialog(null, "Issue", "ERROR", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }


            }
        });

        l_returnToLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChangeEvent event = new ChangeEvent(this);
                for(ChangeListener listener : returnToLoginListener){
                    listener.stateChanged(event);
                }
            }
        });


    }

    public JPanel getRegisterDisplay(){
        return m_registerDisplay;
    }

    public void addListenerReturntoLogin(ChangeListener listener) {
        returnToLoginListener.add(listener);
    }

    public void addListenerLogin(ChangeListener listener) {
        listeners.add(listener);
    }
}
