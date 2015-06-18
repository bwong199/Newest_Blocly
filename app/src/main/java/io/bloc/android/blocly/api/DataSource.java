package io.bloc.android.blocly.api;

import java.util.ArrayList;
import java.util.List;

import io.bloc.android.blocly.api.model.RssFeed;
import io.bloc.android.blocly.api.model.RssItem;
import io.bloc.android.blocly.api.network.GetFeedsNetworkRequest;

/**
 * Created by benwong on 2015-05-05.
 */
public class DataSource {

    private List<RssFeed> feeds;
    private List<RssItem> items;

    public DataSource() {
        feeds = new ArrayList<RssFeed>();
        items = new ArrayList<RssItem>();
//        createFakeData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new GetFeedsNetworkRequest("http://feeds.feedburner.com/androidcentral?format=xml").performRequest();
//               Dog goldenretrieve = new Dog();

        GetFeedsNetworkRequest getFeedsNetworkRequest = new GetFeedsNetworkRequest("http://feeds.feedburner.com/androidcentral?format=xml");
                List<GetFeedsNetworkRequest.FeedResponse> feedresponses = getFeedsNetworkRequest.performRequest();
                for (int x = 0; x < feedresponses.size() ; x ++){
                    RssFeed rssFeed = new RssFeed(feedresponses.get(x).channelTitle, feedresponses.get(x).channelDescription,feedresponses.get(x).channelURL,  feedresponses.get(x).channelFeedURL);
                    feeds.add(rssFeed);

                    for (int y = 0; y < feedresponses.get(x).channelItems.size(); y++){
                        RssItem rssitem = new RssItem(feedresponses.get(x).channelItems.get(y).itemGUID,feedresponses.get(x).channelItems.get(y).itemTitle,feedresponses.get(x).channelItems.get(y).itemDescription,feedresponses.get(x).channelItems.get(y).itemURL,   feedresponses.get(x).channelItems.get(y).itemPubDate,  Long.parseLong(feedresponses.get(x).channelItems.get(y).itemEnclosureURL),  Long.parseLong(feedresponses.get(x).channelItems.get(y).itemEnclosureMIMEType), false, false );
                    items.add(rssitem);
                    }

                }
//                public RssFeed(String title, String description, String siteUrl, String feedUrl)
//                public RssItem(String guid, String title, String description, String url, String imageUrl, long rssFeedId, long datePublished, boolean favorite, boolean archived)
            }}).start();



    }

    public List<RssFeed> getFeeds() {
        return feeds;
    }

    public List<RssItem> getItems() {
        return items;
    }

//    void createFakeData() {
//        feeds.add(new RssFeed("My Favorite Feed",
//                "This feed is just incredible, I can't even begin to tell you…",
//                "http://favoritefeed.net", "http://feeds.feedburner.com/favorite_feed?format=xml"));
//        for (int i = 0; i < 10; i++) {
//            items.add(new RssItem(String.valueOf(i),
//                    BloclyApplication.getSharedInstance().getString(R.string.placeholder_headline) + " " + i,
//                    BloclyApplication.getSharedInstance().getString(R.string.placeholder_content),
//                    "http://favoritefeed.net?story_id=an-incredible-news-story",
//                    "http://rs1img.memecdn.com/silly-dog_o_511213.jpg",
//                    0, System.currentTimeMillis(), false, false));
//        }
//    }
}
