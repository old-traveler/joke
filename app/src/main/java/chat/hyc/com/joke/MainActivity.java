package chat.hyc.com.joke;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.rv_joke)
    RecyclerView rv_Joke;

    @BindView(R.id.sl_joke)
    SwipeRefreshLayout sl_Joke;

    private JokeAdapter jokeAdapter;

    private SwipeRefreshLayout sl_joke;

    private JokeHttpHandler jokeHttpHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        rv_Joke.setLayoutManager(new LinearLayoutManager(this));
        rv_Joke.setItemAnimator(new DefaultItemAnimator());
        sl_joke = (SwipeRefreshLayout) findViewById(R.id.sl_joke);
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
        if (jokeHttpHandler == null) {
            jokeHttpHandler = JokeHttpHandler.getInstance();
        }
        jokeHttpHandler.getJokeHttpData(5, new JokeHttpHandler.JokeHttpListener() {
            @Override
            public void complete(List<Joke> list) {
                jokeAdapter = new JokeAdapter(MainActivity.this, list);
                rv_Joke.setAdapter(jokeAdapter);
                sl_joke.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
