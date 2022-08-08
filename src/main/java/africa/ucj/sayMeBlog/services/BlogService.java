package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.Blog;
import africa.ucj.sayMeBlog.dtos.requests.BlogRequest;

public interface BlogService {
    Blog saveBlog(BlogRequest request);
    Blog getBlog(String id);
    String updateBlog(String id, BlogRequest blogRequest);
    String deleteBlog(String id);

    void reSave(Blog blog);

    void deleteAll();
}
