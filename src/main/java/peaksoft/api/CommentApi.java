package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.CommentRequest;
import peaksoft.dto.CommentResponse;
import peaksoft.entity.Comment;
import peaksoft.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentApi {
    private final CommentService commentService;
//    @PostMapping("/{id}/comment")
//    public String addCommentToProduct(@PathVariable Long id, @RequestBody CommentRequest commentRequest) {
//        return commentService.commentToProduct(id, commentRequest);
//    }
}
