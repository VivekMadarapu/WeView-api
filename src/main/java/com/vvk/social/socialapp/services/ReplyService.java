package com.vvk.social.socialapp.services;

import com.vvk.social.socialapp.models.Reply;
import com.vvk.social.socialapp.models.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public void addReply(Reply reply) {
        replyRepository.save(reply);
    }

    public Reply deleteReply(Long id) {
        Reply reply = replyRepository.getReferenceById(id);
        reply.setDeleted(true);
        return replyRepository.save(reply);
    }

    public Reply restoreReply(Long id) {
        Reply reply = replyRepository.getReferenceById(id);
        reply.setDeleted(false);
        return replyRepository.save(reply);
    }

    public Reply updateReply(Long id, String content) {
        Reply reply = replyRepository.getReferenceById(id);
        if (!content.isEmpty())
            reply.setContent(content);
        return replyRepository.save(reply);
    }

}
