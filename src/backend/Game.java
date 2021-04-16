package backend;

// This class depends on what data is in our data file
// Subject to change (the data file that we picked earlier has these attributes)
public class Game {
   String m_ID;
    public String m_Title;
   String m_highlights, m_optimized;
   String m_SteamUrl;
   String m_Publisher;
   String m_Genre;
   String m_Status;
   String m_rating;
   String m_PathToPic;

   public Game(){
      m_ID = "1";
      m_Title = "N/A";
      m_highlights = "N/A";
      m_optimized = "N/A";
      m_SteamUrl = "N/A";
      m_Publisher= "N/A";
      m_Genre= "N/A";
      m_Status= "N/A";
      m_rating= "N/A";

      m_PathToPic = "./gamePictures/"; // Will determine layout later
   }
   public Game(String id, String title, String highlights, String optimized, String url, String publisher, String genre, String status, String rating){
      m_ID = id;
      m_Title = title;
      m_highlights = highlights;
      m_optimized = optimized;
      m_SteamUrl = url;
      m_Publisher= publisher;
      m_Genre= genre;
      m_Status= status;
      m_rating= rating;
   }

   public void setId(String id){m_ID=id;}
   public void setTitle(String title){m_Title=title;}
   public void setHighlights(String highlights){m_highlights=highlights;}
   public void setOptimized(String optimized){m_optimized=optimized;}
   public void setURL(String url){m_SteamUrl=url;}
   public void setPublisher(String publisher){m_Publisher=publisher;}
   public void setGenre(String genre){m_Genre=genre;}
   public void setStatus(String status){m_Status=status;}
   public void setRating(String rating){m_rating=rating;}

   public String getID() {
      return m_ID;
   }

   public String getTitle() {
      return m_Title;
   }

   public String getHighlights() {
      return m_highlights;
   }

   public String getOptimized() {
      return m_optimized;
   }

   public String getSteamUrl() {
      return m_SteamUrl;
   }

   public String getPublisher() {
      return m_Publisher;
   }

   public String getGenre() {
      return m_Genre;
   }

   public String getStatus() {
      return m_Status;
   }

   public boolean compareNames(Game game){// dumb code, but it could have looked a lot messier.
       // this is handling spaces through recurrsion: it's a mess, don't ask
      if(game.getTitle().indexOf(" ") > -1){
         int m_tmpInt0 = game.getTitle().indexOf(" ");

         Game game2 = new Game();
         Game game3 = new Game();
         game2.setTitle(game.getTitle().substring(0,m_tmpInt0));
         game3.setTitle(game.getTitle().substring(m_tmpInt0+1));

         boolean m_tmpRetVal1 = compareNames(game2);
         boolean m_tmpRetVal2 = compareNames(game3);

         return (m_tmpRetVal1 && m_tmpRetVal2);
      }
      else{
         return (this.getTitle().toLowerCase().indexOf(game.getTitle().toLowerCase()) > -1);
      }
   }

   @Override
   public String toString() {
      return "Game{" +
              "m_ID='" + m_ID + '\'' +
              ", m_Title='" + m_Title + '\'' +
              ", m_highlights='" + m_highlights + '\'' +
              ", m_optimized='" + m_optimized + '\'' +
              ", m_SteamUrl='" + m_SteamUrl + '\'' +
              ", m_Publisher='" + m_Publisher + '\'' +
              ", m_Genre='" + m_Genre + '\'' +
              ", m_Status='" + m_Status + '\'' +
              ", m_rating='" + m_rating + '\'' +
              ", m_PathToPic='" + m_PathToPic + '\'' +
              '}';
   }
}
