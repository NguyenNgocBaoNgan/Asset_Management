package DACNPM.asset_management.service;

import DACNPM.asset_management.model.Comment;
import DACNPM.asset_management.repository.AssetRepository;
import DACNPM.asset_management.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepositoryRepository;

    public List<Comment> findCommentByAssetId(int id){
        return commentRepositoryRepository.findCommentByAssetId(id);
    }

    public String findAccoutNameById(int id){
        return commentRepositoryRepository.getFullNameAccountById(id);
    }
    public void saveComment(Comment comment){
        commentRepositoryRepository.addComment(comment.getAccountId(),comment.getAssetId(),
                comment.getContent(),comment.getCreatedAt());
    }
}
