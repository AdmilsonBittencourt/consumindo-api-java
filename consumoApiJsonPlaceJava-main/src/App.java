import java.util.List;

import interfaces.IPostService;
import model.Post;

public class App {
    public static void main(String[] args) throws Exception {
        IPostService postService = new services.PostsServices();
        List<Post> posts = postService.getPosts();

        posts.forEach(post -> {
            System.out.println("-----------------------");
            System.out.println("Title: - " + post.getTitle());
            System.out.println("Body:" + post.getBody());
            System.out.println("----------------------");
        });

    }
}
