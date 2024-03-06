import java.util.List;

import interfaces.IPostService;
import model.Post;

public class App {
    public static void main(String[] args) throws Exception {
        IPostService postService = new services.PostsServices();
        List<Post> posts = postService.getPosts();

        // posts.forEach(post -> {
        // System.out.println("-----------------------");
        // System.out.println("Title: - " + post.getTitle());
        // System.out.println("Body:" + post.getBody());
        // System.out.println("----------------------");
        // });

        // System.out.println(postService.getPostById(3));

        // Post post = new Post(3, "Harry porra", "A vara que haaaa");
        // System.out.println(postService.createPost(post));

        // System.out.println(postService.updatePost(post, 3));

        postService.deletePost(3);
    }
}
