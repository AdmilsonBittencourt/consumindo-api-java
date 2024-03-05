package services;

import java.io.BufferedReader;
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

}
