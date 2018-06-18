package core;

import twitter4j.TwitterException;

public class Main {

	public static void main(String[] args) {
		GetTweet getTweet = new GetTweet();
		try {
			getTweet.searchTweet();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

}
