package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.dto.CommentDTO;
import DACNPM.asset_management.service.CommentService;
import DACNPM.asset_management.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/comment")
    public ResponseEntity<?> create(@RequestBody CommentDTO commentDTO) {
        if (!commentService.create(commentDTO)) {
            return ResponseEntity.badRequest().body(CommentService.MESS);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/comment/{assetId}")
    public ResponseEntity<?> create(@PathVariable Integer assetId) {
        return ResponseEntity.ok(commentService.getAllByAssetId(assetId));
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (!commentService.delete(id)) {
            return ResponseEntity.badRequest().body(CommentService.MESS);
        }
        return ResponseEntity.ok().build();
    }
}
