package services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import interfaces.IPostService;
import model.Post;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PostsServices implements IPostService {
  private final Gson gson = new Gson();

  public List<Post> getPosts() {
    try {
      URL url = new URL("https://jsonplaceholder.typicode.com/posts");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      int responseCode = con.getResponseCode();

      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
          response.append(line);
        }

        Type listType = new TypeToken<ArrayList<Post>>() {
        }.getType();
        return gson.fromJson(response.toString(), listType);

      }

    } catch (Exception e) {
      System.out.println(e.getMessage());
      // TODO: handle exception
    }
    return new ArrayList<>();
  }

  public Post getPostById(Integer id) {
    try {
      URL url = new URL("https://jsonplaceholder.typicode.com/posts" + "/" + id);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      int responseCode = con.getResponseCode();

      if (!(responseCode == HttpURLConnection.HTTP_OK))
        throw new IOException();

      BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;

      while ((line = reader.readLine()) != null) {
        response.append(line);
      }

      return gson.fromJson(response.toString(), Post.class);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    return new Post(null, null, null);

  }

  public Post createPost(Post post) {
    try {
      URL url = new URL("https://jsonplaceholder.typicode.com/posts");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setRequestProperty("Content-Type", "application/json");
      con.setDoOutput(true);

      String postJson = gson.toJson(post);

      DataOutputStream sendPost = new DataOutputStream(con.getOutputStream());
      sendPost.writeBytes(postJson);
      sendPost.flush();
      sendPost.close();

      int responseCode = con.getResponseCode();

      if (!(responseCode == HttpURLConnection.HTTP_CREATED))
        throw new IOException();

      BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;

      while ((line = reader.readLine()) != null) {
        response.append(line);
      }

      return gson.fromJson(response.toString(), Post.class);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    return new Post(null, null, null);

  }

  public Post updatePost(Post post, Integer id) {

    try {
      URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + id);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("PUT");
      con.setRequestProperty("Content-Type", "application/json");
      con.setDoOutput(true);

      String postJson = gson.toJson(post);

      DataOutputStream sendPost = new DataOutputStream(con.getOutputStream());
      sendPost.writeBytes(postJson);
      sendPost.flush();
      sendPost.close();

      int responseCode = con.getResponseCode();

      if (!(responseCode == HttpURLConnection.HTTP_OK))
        throw new IOException();

      BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;

      while ((line = reader.readLine()) != null) {
        response.append(line);
      }

      return gson.fromJson(response.toString(), Post.class);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    return new Post(null, null, null);

  }

  public void deletePost(Integer id) {

    try {
      URL url = new URL("https://jsonplaceholder.typicode.com/posts/" + id);
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("DELETE");

      int responseCode = con.getResponseCode();

      if (!(responseCode == HttpURLConnection.HTTP_OK))
        throw new IOException();

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}
