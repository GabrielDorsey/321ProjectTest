package backend;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class UserList {
    String m_listName;
    ArrayList<User> m_userList = new ArrayList<>();
    int m_length;

    public UserList(){
        m_length = 0;
    }

    public void setListName(String listName){m_listName = listName;}
    public String getListName(){return m_listName;}

    public void addUser(User user){
        m_userList.add(user);
        m_length++;
    }

    public boolean deleteUser(User user){
        for(int i =0; i<m_length; i++){
            if(ifExists(user)){
                m_userList.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean ifExists(User user){
        for(int i =0; i<m_length; i++){
            if(m_userList.get(i).toString().equals(user.toString())){
                return true;
            }
        }
        return false;
    }

}
