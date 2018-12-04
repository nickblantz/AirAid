/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airaid.managers;

import java.io.Serializable;
import twitter4j.*;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import twitter4j.conf.ConfigurationBuilder;

@Named(value = "twitterApi")
@SessionScoped

/**
 *
 * @author Andrew
 */
public class TwitterApi implements Serializable {
    private static final String TWITTER_CONSUMER_KEY = "23yMHBinIkfCWDXjEY9NfZbPM";
    private static final String TWITTER_SECRET_KEY = "AYoRIahv7lfnDUL9IT6rWzwxofJFmY5AezLUKGfdgkzX8EFB4w";
    private static final String TWITTER_ACCESS_TOKEN = "412782295-3fiidvfVN1IOHjq6724smrVVkdQqmWxCCYfa324k";
    private static final String TWITTER_ACCESS_TOKEN_SECRET = "lLePSmDPQeBJ8dpn9VdrRwrZzNQ27wo6AOpkmQrtvuLKb";
    
    
    public TwitterApi() {

    }
    
    public ResponseList<Status> getTweets() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
            .setOAuthConsumerSecret(TWITTER_SECRET_KEY)
            .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
            .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET)
            .setTweetModeExtended(true);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            Paging page = new Paging(1, 100);
            ResponseList<Status> tweets = twitter.getUserTimeline("TSA", page);
            //tweets.forEach(tweet -> {
                //System.out.println(tweet.getText());
               //System.out.println(tweet.getUser().getProfileImageURL());
            //});
            return tweets;
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
        return null;
    }
}
