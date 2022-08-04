package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
/**
 * Servlet implementation class TwitterServlet
 */
@WebServlet("/twitter")
public class TwitterServlet extends HttpServlet {

	public void doGet (HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException
			{
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			}

protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{


	//エンコーディング方式の設定
	request.setCharacterEncoding("UTF-8");

	//検索対象キーワードの取得
	String searchTarget = "#" + request.getParameter("searchTarget");

	//遷移先画面
	String forwardpage = "/WEB-INF/TwitterToolResult.jsp";

	//設定の取り込み
	/**
	 * インスタンス生成
	 */
	ConfigurationBuilder cb = new ConfigurationBuilder();

	//キー情報の設定
	cb.setDebugEnabled(true)
	.setOAuthConsumerKey("[G27Ei035yUMI38QZrRxa34C7d]")
	.setOAuthConsumerSecret("[eHrgxmvE39QphtXNiNvRFDSr4qL8]")
	.setOAuthAccessToken("[1328559522558799872-uM2q76VqdMdG3Llg58IxSwZ1fZkFNpSGzMgytD3dDJkEYF4DgWh4]")
	.setOAuthAccessTokenSecret("[egUXJHS9xryRmxRqjaZzYmPcNEHJjPmlgAMIPEZlXKakH]");

	//各種Twitterインスタンスの生成
	/**
	 * ファクトリクラスのインスタンス生成
	 */
	TwitterFactory tf = new TwitterFactory(cb.build());

	/*Twitterクラスのインスタンス生成
	 *
	 */
	Twitter twitter = tf.getInstance();

	//ハッシュタグに夜検索
	/*
	 * ハッシュタグ検索用クラス
	 */
	Query query = new Query();
	/**
	/*クエリ生成
	 */
	query.setQuery(searchTarget);
	/**
	 * 検索結果の格納用
	 */
	 QueryResult queryResult = null;
	 if(queryResult == null) {
		 return;
		 }

	/*クエリの発行・結果格納
	 *
	 */
	 try {
		 queryResult = twitter.search(query);
	 }catch(TwitterException e1) {
		 e1.printStackTrace();
	 }
	 {
	 RequestDispatcher dispatcher =
			request.getRequestDispatcher("./TwitterToolResult");
			dispatcher.forward(request, response);
			}

	System.out.println("Hit Num:" +queryResult.getTweets().size());

	/*最終結果の格納用
	 */
	List result = new ArrayList();

	/*検索結果から1Statusずつツイート内容を最終結果に格納
	 *
	 */
	for(Status tweet:queryResult.getTweets()) {
		result.add(tweet.getText());
	}
	//結果格納・画面遷移
	/**
	 * 結果をリクエストオブジェクトにセット
	 */
	request.setAttribute("result", result);

	/**
	 * 画面遷移
	 */
	//遷移先画面

	RequestDispatcher dispatch = request.getRequestDispatcher(forwardpage);
	dispatch.forward(request, response);
}

}
