package chat.hyc.com.joke;

/**
 * Created by 1 on 2016/11/24.
 */

public class Joke {
    private String jokeContent;
    private String jokeImage;
    private String imageType;
    private String jokeTitle;
    private String gifImage;


    public String getJokeContent() {
        return jokeContent;
    }

    public void setJokeContent(String jokeContent) {
        this.jokeContent = jokeContent;
    }

    public String getJokeImage() {
        return jokeImage;
    }

    public void setJokeImage(String jokeImage) {
        this.jokeImage = jokeImage;
    }

    public String getJokeTitle() {
        return jokeTitle;
    }

    public void setJokeTitle(String jokeTitle) {
        this.jokeTitle = jokeTitle;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getGifImage() {
        return gifImage;
    }

    public void setGifImage(String gifImage) {
        this.gifImage = gifImage;
    }
}
