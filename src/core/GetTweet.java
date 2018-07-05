package core;

import java.text.SimpleDateFormat;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class GetTweet {

	private Twitter twitter;
	private Query query;
	private RemoveUser removeUser;

	private int GET_TWEET_SIZE = 100;
	private String[] KEYWORDS = {"運転", "影響", "再開", "復旧", "復活", "遅延", "遅", "点検", "見合わせ", "停", "止", "死", "人身", "事故"};

	public GetTweet(){
		twitter = new TwitterFactory().getInstance();
		query = new Query();
		query.setLang("ja");
		query.setCount(GET_TWEET_SIZE);
		query.setSince("2018-07-01");
		query.setUntil("2018-07-06");
		removeUser = new RemoveUser();
	}

	public void searchTweet() throws TwitterException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String message = new String();

		// 検索語指定
		query.setQuery("日比谷線 -rt -bot");

		// 検索実行
		QueryResult result = twitter.search(query);

		// 検索結果を見てみる
		int tweetCount =0;

		for (Status tweet : result.getTweets()) {
			if(removeUser.isEnterUser(tweet.getUser().getScreenName())) continue;

			System.out.println("No"+String.format("%3s", Integer.toString(++tweetCount))+" daytime: "+sdf.format(tweet.getCreatedAt()));
			message = tweet.getText().replaceAll("[\r\n]", "<br>");
			System.out.println(containKeywordsCheck(message));
			System.out.println(message);
			System.out.println();
		}
	}

	private String containKeywordsCheck(String mes){
		String ret = new String();

		for(String key : KEYWORDS){
			if(mes.contains(key)){
				ret += key+" * ";
			}else{
				ret += key+"   ";
			}
		}

		return ret;
	}
}
