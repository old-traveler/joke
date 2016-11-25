package chat.hyc.com.joke;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by 1 on 2016/11/24.
 */

public class JokeAdapter extends BaseQuickAdapter<Joke>{

    private Context context;

    private List<Joke> jokes;

    public JokeAdapter(Context context,List<Joke> jokes){
        super(R.layout.item_joke,jokes);
        this.context=context;
        this.jokes=jokes;
    }
    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final Joke joke) {

                baseViewHolder.setText(R.id.tv_joke_title,joke.getJokeTitle());
                baseViewHolder.setText(R.id.tv_joke_content,joke.getJokeContent());
                Glide
                        .with(context)
                        .load(joke.getJokeImage())
                        .into((ImageView) baseViewHolder.getView(R.id.iv_joke_image));

    }


}
