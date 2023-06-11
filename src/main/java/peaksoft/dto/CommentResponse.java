package peaksoft.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.entity.User;

@Data
@Builder
@AllArgsConstructor
public class CommentResponse {
    private User userName;
    private String comment;

}
