package com.example.que_bang.modules.post;

import com.example.que_bang.modules.account.Account;
import com.example.que_bang.modules.account.CurrentAccount;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class PostController {
  private final PostService postService;
  private final ModelMapper modelMapper;

  @GetMapping("/posts")
  public String postIndex(@CurrentAccount Account account, Model model) {
    model.addAttribute(account);
    model.addAttribute("posts", postService.findAll());
    return "post/index";
  }

  @GetMapping("/new-post")
  public String newPostForm(@CurrentAccount Account account, Model model) {
    model.addAttribute(account);
    model.addAttribute(new PostForm());
    return "post/form";
  }

  @PostMapping("/new-post")
  public String newPostSubmit(@CurrentAccount Account account, @Valid PostForm postForm, Errors errors, Model model) {
    if (errors.hasErrors()) {
      model.addAttribute(account);
      return "post/form";
    }

    Long newPostId = postService.add(modelMapper.map(postForm, Post.class));
    return "redirect:/post/" + newPostId.toString();
  }

  @GetMapping("/post/{id}")
  public String viewPost(@CurrentAccount Account account, @PathVariable Long id, Model model) {
    Post post = postService.findOne(id);
    model.addAttribute(account);
    model.addAttribute(post);
    return "post/view";
  }


  @GetMapping("/post/{id}/edit")
  public String editPost(@CurrentAccount Account account, @PathVariable Long id, Model model) {
    Post post = postService.findOne(id);
    model.addAttribute(account);
    model.addAttribute(post);
    model.addAttribute(modelMapper.map(post, PostForm.class));
    return "post/update-form";
  }

  @PostMapping("/post/{id}/edit")
  public String editPostSubmit(@CurrentAccount Account account, @PathVariable Long id, @Valid PostForm postForm, Errors errors, Model model) {
    Post post = postService.findOne(id);
    if (errors.hasErrors()) {
      model.addAttribute(account);
      model.addAttribute(post);
      return "post/update-form";
    }
    postService.updatePost(id, postForm);
    return "redirect:/post/" + id.toString();
  }

  @GetMapping("/post/{id}/delete")
  public String deletePost(@CurrentAccount Account account, @PathVariable Long id, Model model) {
    postService.deleteOne(id);
    return "redirect:/posts";

  }
}
