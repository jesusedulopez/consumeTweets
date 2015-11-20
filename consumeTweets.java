/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumetweets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

/**
 *
 * @author jesus
 */
public class ConsumeTweets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws TwitterException, IOException, InterruptedException 
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(true)
         .setOAuthConsumerKey("tBvIn00ttXR2H0dLYUc0vMdf2")
         .setOAuthConsumerSecret("bawl0xIVvSXPpCjodlEMHEB5OP26mXVg5Y6EjHNKD7WQztymFV")
         .setOAuthAccessToken("2230172132-mdvnLX6YuE9u2RNyiDFWtJ4LyqzGQsCPSGfzSqB")
         .setOAuthAccessTokenSecret("q9vWp9IDOqvSwFOHVyFILF1z4eSn4QflSgpwM9BcXLbVm");
        TwitterFactory tf = new TwitterFactory(cb.build());
        
        Twitter twitter = tf.getInstance();
        Query query = new Query("HuracanPatricia");
        QueryResult result;
        
        String json="";
        File f = new File("/home/jesus/tweets/HuracanPatricia.txt");
        FileWriter w = new FileWriter(f,true);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw); 
        
        File fjson = new File("/home/jesus/tweets/HuracanPatricia.json");
        FileWriter wjson = new FileWriter(fjson,true);
        BufferedWriter bwjson = new BufferedWriter(wjson);
        PrintWriter wrjson = new PrintWriter(bwjson); 
                
        int x = 0;
        try 
        {
          while (x<10000)
          {
             result = twitter.search(query);
             for (Status tweet : result.getTweets()) 
             {
               
                json = DataObjectFactory.getRawJSON(tweet);
                System.out.println("Tweet:"+x+"\n"+json+"\n\n");
                wr.write("Tweet:"+x+"\n"+json+"\n\n");
                wrjson.write(json+"\n\n");
                x++;
             }
             Thread.sleep (30*1000);
          }
         wr.close();
         wrjson.close();
         bw.close();
         bwjson.close();
        } catch (TwitterException ex) {} 
    }//Main
    
}
