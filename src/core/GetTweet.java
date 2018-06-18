package core;

import java.text.SimpleDateFormat;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class GetTweet {

	Twitter twitter;
	Query query;

	private int GET_TWEET_SIZE = 100;

	public GetTweet(){
		twitter = new TwitterFactory().getInstance();
		query = new Query();
		query.setLang("ja");
		query.setCount(GET_TWEET_SIZE);
		query.setSince("2018-06-10");
		query.setUntil("2018-06-16");
	}

	public void searchTweet() throws TwitterException {
		// 検索語指定
		query.setQuery("遅延");

		// 検索実行
		QueryResult result = twitter.search(query);

		// 検索結果を見てみる
		int page = 0;
		while(true) {
			int tweetCount =0;
			System.out.println("ページ数 : " + ++page);
			System.out.println("ヒット数 : " + result.getTweets().size());

			for (Status tweet : result.getTweets()) {
				System.out.println("T "+String.format("%3s", Integer.toString(++tweetCount+(page-1)*GET_TWEET_SIZE)));
				System.out.println("U "+tweet.getUser().getScreenName());
				//System.out.println("C "+tweet.getText());
				System.out.println("D "+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(tweet.getCreatedAt())+" ["+tweet.getCreatedAt().getTime()+"]");
				System.out.println();
			}

			if (result.hasNext()) {
				query = result.nextQuery();
			} else {
				break;
			}
		}
	}
}
