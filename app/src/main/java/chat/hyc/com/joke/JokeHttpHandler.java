package chat.hyc.com.joke;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2016/11/24.
 */

public class JokeHttpHandler {

    private static String nextUri=null;

    public JokeHttpListener jokeListener;

    private int currentCount;

    private List<Joke> list;

    private int count=0;

    public interface JokeHttpListener{
        void complete(List<Joke> list);
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (list==null){
                list=new ArrayList<Joke>();
            }
            currentCount++;
            if (currentCount==count){
                jokeListener.complete(list);
            }else {
                Joke j=(Joke) msg.obj;
                Log.d("TAG","j 是"+j.getJokeContent());
                Log.d("TAG","下个网址"+nextUri);
                list.add(j);
                getJokeHttpData(count,null);
            }

        }
    };
    private final static String address = "http://www.yikedou.com/wenzi/201611/73833.html?uchz161122";

    private static JokeHttpHandler jokeHttpHandler;

    public static synchronized JokeHttpHandler getInstance(){
        if (jokeHttpHandler==null){
            jokeHttpHandler=new JokeHttpHandler();
        }
        return jokeHttpHandler;
    }

    public void getJokeHttpData(int count, final JokeHttpListener jokeHttpListener){
        this.count=count;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc=null;
                if (nextUri==null){
                    try {
                        doc = Jsoup.connect(address).get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        doc = Jsoup.connect(nextUri).get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Element tipsP=doc.select("p").get(1);
                Element tipsA= tipsP.select("a").first();
                Element img=tipsA.select("img").first();
                String uri=tipsA.attr("href");
                String imageUri=img.attr("src");
                String content=doc.select("p").first().text();
                String title=tipsP.text();
                final Joke joke=new Joke();
                joke.setJokeTitle(title);
                joke.setJokeContent(content);
                joke.setJokeImage("http://www.yikedou.com"+imageUri);
                nextUri="http://www.yikedou.com"+uri;
                if (jokeHttpListener!=null){
                    jokeListener=jokeHttpListener;
                }
                Message m=new Message();
                m.obj=joke;
                handler.sendMessage(m);
            }
        }).start();

    }
}
