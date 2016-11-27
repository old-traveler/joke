package chat.hyc.com.joke;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
                if (joke.getImageType().equals("gif")){
                    Glide
                            .with(context)
                            .load(joke.getJokeImage())
                            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                            .into((ImageView) baseViewHolder.getView(R.id.iv_joke_image));
                    baseViewHolder.getView(R.id.iv_joke_image).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Glide
                                    .with(context)
                                    .load(joke.getGifImage())
                                    .asGif()
                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                    .into((ImageView) baseViewHolder.getView(R.id.iv_joke_image));
                        }
                    });
                }else if (joke.getImageType().equals("image")){
                    Glide
                            .with(context)
                            .load(joke.getJokeImage())
                            .into((ImageView) baseViewHolder.getView(R.id.iv_joke_image));
                }else {
                    baseViewHolder.getView(R.id.cv).setVisibility(View.GONE);
                }
                Log.d("TAG","加载的图片地址"+joke.getJokeImage());

    }

}
