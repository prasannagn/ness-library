package com.ness.library.library.service;


import com.ness.library.api.model.Member;
import com.ness.library.api.model.Status;
import com.ness.library.library.model.BookModel;
import com.ness.library.library.model.MemberModel;
import com.ness.library.library.repository.BookRepository;
import com.ness.library.library.repository.MemberRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.annotation.Resource;

public class MemberService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private MemberRepository repository;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private BookRepository bookRepository;

    public Member save(Member member) {
        return map(repository.save(map(member)));
    }

    public Member findOne(int memberId) {
        return map(repository.findOne(memberId));
    }

    public Page<Member> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::map);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    public Member update(int memberId, Member source) {
        MemberModel target = repository.findOne(memberId);
        BeanUtils.copyProperties(source, target, "id");
        return map(repository.save(target));
    }

    public Member takeBook(int id, int bookId) {
        MemberModel memberModel = repository.findOne(id);
        BookModel bookModel = bookRepository.findOne(bookId);

        if (bookModel.getStatus() == Status.AVAILABLE) {
            memberModel.getBookModels().add(bookModel);
            bookModel.setStatus(Status.ISSUED);
            bookRepository.save(bookModel);
            return map(repository.save(memberModel));
        }
        throw new RuntimeException();
    }

    public Member returnBook(int id, int bookId) {
        MemberModel memberModel = repository.findOne(id);
        BookModel bookModel = bookRepository.findOne(bookId);
        if (bookModel.getStatus() == Status.ISSUED && memberModel.getBookModels().contains(bookModel)) {
            memberModel.getBookModels().remove(bookModel);
            bookModel.setStatus(Status.AVAILABLE);
            bookRepository.save(bookModel);
            return map(repository.save(memberModel));
        }
        throw new RuntimeException();
    }

    private Member map(MemberModel memberModelModel) {
        Member member = new Member();
        BeanUtils.copyProperties(memberModelModel, member);
        return member;
    }

    private MemberModel map(Member member) {
        MemberModel memberModelModel = new MemberModel();
        BeanUtils.copyProperties(member, memberModelModel);
        return memberModelModel;
    }
}
