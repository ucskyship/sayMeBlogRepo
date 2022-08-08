package africa.ucj.sayMeBlog.services;

import africa.ucj.sayMeBlog.data.models.Blog;
import africa.ucj.sayMeBlog.data.repositories.BlogRepository;
import africa.ucj.sayMeBlog.dtos.requests.BlogRequest;
import africa.ucj.sayMeBlog.exceptions.BlogNotFoundException;
import africa.ucj.sayMeBlog.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Blog saveBlog(BlogRequest request) {
        Blog blog = new Blog();
        Mapper.mapRequestToBlog(request, blog);
        return blogRepository.save(blog);
    }

    @Override
    public Blog getBlog(String id) {
        var foundBlog = blogRepository.findById(id);
        if(foundBlog.isPresent()){
            return foundBlog.get();
        }
        throw new BlogNotFoundException("blog with " + id + " not found");
    }

    @Override
    public String updateBlog(String id, BlogRequest blogRequest) {
        var updateBlog = getBlog(id);
        Mapper.mapRequestToBlog(blogRequest, updateBlog);
        return "updated successful";
    }

    @Override
    public String deleteBlog(String id) {
        var deleteBlog = getBlog(id);
        blogRepository.delete(deleteBlog);
        return "delete successful";
    }

    @Override
    public void reSave(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void deleteAll() {
        blogRepository.deleteAll();
    }
}
