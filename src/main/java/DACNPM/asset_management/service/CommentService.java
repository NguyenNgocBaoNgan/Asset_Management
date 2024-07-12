package DACNPM.asset_management.service;

import DACNPM.asset_management.mapper.MapperGenerate;
import DACNPM.asset_management.model.Account;
import DACNPM.asset_management.model.Comment;
import DACNPM.asset_management.model.dto.CommentDTO;
import DACNPM.asset_management.model.response.CommentResponse;
import DACNPM.asset_management.repository.AccountRepository;
import DACNPM.asset_management.repository.CommentRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Resource
    AccountRepository accountRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MapperGenerate mapperGenerate;
    @Autowired
    private HttpServletRequest request;
    public static String MESS = "SUCCESSFULLY";

    public List<Comment> findCommentByAssetId(int id) {
        return commentRepository.findCommentByAssetId(id);
    }

    public String findAccountNameById(int id) {
        return commentRepository.getFullNameAccountById(id);
    }

    public void saveComment(Comment comment) {
        commentRepository.addComment(comment.getAccountId(), comment.getAssetId(),
                comment.getContent(), comment.getCreatedAt());
    }

    public List<CommentResponse> getAllByAssetId(Integer id) {
        List<Comment> listEntity = commentRepository.findAllByAssetIdOrderByCreatedAtDesc(id);
        List<CommentResponse> rs = mapperGenerate.toListConvert(listEntity, CommentResponse.class);
        // call api python, after set attribute isGood, if good = true, else = false
        return rs;
    }

    public boolean create(CommentDTO commentDTO) {
        HttpSession session = request.getSession(); // lấy session
        Account account = (Account) session.getAttribute("loggedInAccount"); // lấy session với giá trị loggedInAccount
        if (!accountRepository.existsById(account.getId_account())) { // kiểm tra id có tồn tại trong bảng account
            MESS = "ACCOUNT NOT EXISTS";
            return false;
        }
        commentDTO.setAccountId(account.getId_account());
        Comment entity = mapperGenerate.convert(commentDTO, Comment.class);
        commentRepository.save(entity);
        return true;
    }

    public boolean delete(Integer id) {
        if (!commentRepository.existsById(id)) { // kiểm tra id có tồn tại trong bảng account
            MESS = "COMMENT NOT EXISTS";
            return false;
        }
        commentRepository.deleteById(id);
        return true;
    }
    public void deleteComment(int id){
        commentRepository.deleteById(id);
    }

}
