package chat.hyc.com.joke;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.List;


public class MainActivity extends Activity  {

    private RecyclerView rv_joke;

    private JokeAdapter jokeAdapter;

    private SwipeRefreshLayout sl_joke;

    private JokeHttpHandler jokeHttpHandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        rv_joke= (RecyclerView) findViewById(R.id.rv_joke);
        rv_joke.setLayoutManager(new LinearLayoutManager(this));
        rv_joke.setItemAnimator(new DefaultItemAnimator());
        sl_joke= (SwipeRefreshLayout) findViewById(R.id.sl_joke);
        sl_joke.setRefreshing(true);
        initData();
        sl_joke.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sl_joke.setRefreshing(false);
            }
        });
    }

    private void initData() {
        if (jokeHttpHandler==null){
            jokeHttpHandler=JokeHttpHandler.getInstance();
        }
        jokeHttpHandler.getJokeHttpData(5,new JokeHttpHandler.JokeHttpListener() {
            @Override
            public void complete(List<Joke> list) {
                jokeAdapter=new JokeAdapter(MainActivity.this,list);
                rv_joke.setAdapter(jokeAdapter);
                sl_joke.setRefreshing(false);
            }
        });
    }
}
