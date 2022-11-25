package tests.testmockito;

import com.zuev.entities.Post;
import com.zuev.repositories.PostRepository;
import com.zuev.services.ServiceForPost;
import com.zuev.services.impl.ServiceForPostsImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.when;

public class TestServiceForPost {

    @Mock
    PostRepository postRepository;

    ServiceForPost serviceForPost;

    public TestServiceForPost(){
        MockitoAnnotations.openMocks(this);
        this.serviceForPost = new ServiceForPostsImpl(postRepository);
    }



    @Test
    void TestGetPostById(){
        Post newPost = new Post(1L,"hello my friend", new Date(), new Date(), Collections.emptyList());

        when(postRepository.getByld(1L)).thenReturn(newPost);
        Post post = serviceForPost.getByld(1L);
        Assertions.assertEquals(newPost, post);

    }

    @Test
    void TestGetAllPosts(){
        Post firstPost = new Post(1L, "hello my friend", new Date(), new Date(), Collections.emptyList());
        Post secondPost = new Post(2L, "i am the best writer", new Date(), new Date(), Collections.emptyList());
        Post thirdPost = new Post(3L, "i wrote my first post", new Date(), new Date(), Collections.emptyList());

        List<Post> newPosts = new ArrayList<>(Arrays.asList(
                firstPost, secondPost, thirdPost
        ));

        when(postRepository.getAll()).thenReturn(newPosts);
        List<Post> posts = serviceForPost.getAll();
        Assertions.assertEquals(newPosts, posts);
    }

    @Test
    void TestSavePost(){
        Post newPost = new Post(1L, "hello my friend", new Date(), new Date(), Collections.emptyList());

        when(postRepository.save(newPost)).thenReturn(newPost);
        Post post = serviceForPost.save(newPost);
        Assertions.assertEquals(newPost, post);
    }

    @Test
    void TestUpdatePost(){
        Post newPost = new Post(1L, "hello my friend", new Date(), new Date(), Collections.emptyList());

        when(postRepository.update(newPost)).thenReturn(newPost);
        Post post = serviceForPost.update(newPost);
        Assertions.assertEquals(newPost, post);
    }
}
