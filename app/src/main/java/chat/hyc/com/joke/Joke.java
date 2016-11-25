package chat.hyc.com.joke;

/**
 * Created by 1 on 2016/11/24.
 */

public class Joke {
    private String jokeContent;
    private String jokeImage;
    private String nextJoke;
    private String jokeTitle;
    private int position;

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

    public String getNextJoke() {
        return nextJoke;
    }

    public void setNextJoke(String nextJoke) {
        this.nextJoke = nextJoke;
    }

    public String getJokeTitle() {
        return jokeTitle;
    }

    public void setJokeTitle(String jokeTitle) {
        this.jokeTitle = jokeTitle;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
