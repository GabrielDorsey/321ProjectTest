package backend;

import backend.Game;
import backend.GameList;

import java.util.ArrayList;
import java.util.Iterator;

public class User implements Iterable, Cloneable{
    public String m_Name;
    ArrayList<GameList> m_GameLists = new ArrayList<>(); // Game list placeholder
    GameList m_activeGameList;

    String m_Password;

    public User(){
        m_Name = "";
        m_Password = "";
        m_activeGameList = new GameList();
        m_GameLists.add(m_activeGameList);
    }

    public User(String name, String password){
        m_Name = name;
        m_Password = password;
        m_activeGameList = new GameList();
        m_GameLists.add(m_activeGameList);
    }

    public ArrayList<GameList> getGameLists() {
        return m_GameLists;
    }

    public void setPassword(String password){m_Password = password;}
    public void setName(String name){m_Name = name;}
    public boolean setInfo(String name, String password){
        m_Name = name;
        m_Password = password;
        return true;
    }

    public String getName(){return m_Name;} //Changed the gets for right now so that it does not require a String for a parameter for login (3/28/21)
    public String getPassword(){return m_Password;} //Changedthe gets for right now so that it does not require the String for a parameter for login (3/28/21)

    @Override
    public String toString() {
        return "User{" +
                "m_Name='" + m_Name + '\'' +
                ", m_Password='" + m_Password + '\'' +
                '}';
    }


    public String toJSON(){
        return "{\"ID\":\""+ "User{" +
                "m_Name='" + m_Name + '\'' +
                ", m_Password='" + m_Password + '\'' +
                "}\"}";
    }


    public void createGameList(GameList gameList){
        m_GameLists.add(gameList);
    }

    public void removeGameList(String gameListName){
    }

    public void addGame(String gameListName, Game game){

    }

    public Iterator<GameList> iterator() {
        return this.m_GameLists.iterator();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

