package chat.hyc.com.joke;

import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2016/11/24.
 */

public class JokeHttpHandler {

    private List<Joke> list;

    public interface JokeHttpListener{
        void complete(List<Joke> list);
    }

    private final static String address = "http://www.pengfu.com/index_";

    private static JokeHttpHandler jokeHttpHandler;

    public static synchronized JokeHttpHandler getInstance(){
        if (jokeHttpHandler==null){
            jokeHttpHandler=new JokeHttpHandler();
        }
        return jokeHttpHandler;
    }

    /**
     * 根据传入的页码请求对应网页上的HTML文档数据
     * @param page
     * @param jokeHttpListener
     */
    public void getJokeHttpData(final int page, final JokeHttpListener jokeHttpListener){

        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc=null;
                try {
                    doc = Jsoup.connect(address+page+".html").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                jokeHttpListener.complete(analysisHttpData(doc));
            }

        }).start();
    }

    /**
     * 解析从网页上获取的HTML文档
     */
    private List<Joke> analysisHttpData(Document document){
        list=new ArrayList<Joke>();
        Element body=document.body();
        Elements items=body.select("dl");
        for(Element item:items){
            Joke joke=new Joke();
            if (item.select("h1").first()==null){
                Log.d("TAG","h1是一个空对象");
            }else {
                String title=item.select("h1").first().text();
                joke.setJokeTitle(title);
            }
            if (item.select("div").select("img").first()==null){
                joke.setImageType("noImage");
                if(item.attr("class").equals("clearfix dl-con")){
                    joke.setJokeContent(item.select("div").text());
                }
            }else {
                Element image=item.select("div").select("img").first();
                String  imageUri=image.attr("src");
                String  gifUri=image.attr("gifsrc");
                if (!gifUri.equals("")){
                    joke.setImageType("gif");
                    joke.setJokeImage(imageUri);
                    joke.setGifImage(gifUri);
                }else {
                    joke.setImageType("image");
                    joke.setJokeImage(imageUri);
                }
            }
            list.add(joke);
        }
        return list;
    }
}
