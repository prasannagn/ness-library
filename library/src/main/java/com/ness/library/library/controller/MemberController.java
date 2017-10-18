package com.ness.library.library.controller;

import com.ness.library.api.model.Member;
import com.ness.library.library.resource.MemberResource;
import com.ness.library.library.resource.PageResource;
import com.ness.library.library.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin(origins = "*")
@RestController
public class MemberController {

    @Resource
    private MemberService memberService;

    //Create
    @RequestMapping(value = "/members", method = POST, produces = "application/hal+json")
    public ResponseEntity<MemberResource> create(@RequestBody Member member) {
        return ResponseEntity.ok(new MemberResource(memberService.save(member)));
    }

    //Update
    @RequestMapping(value = "/members/{id}", produces = "application/hal+json", method = PUT)
    public ResponseEntity<MemberResource> update(@PathVariable("id") int memberId, @RequestBody Member source) {
        return ResponseEntity.ok(new MemberResource(memberService.update(memberId, source)));
    }

    //Read
    @RequestMapping(value = "/members", produces = "application/hal+json", method = GET)
    public ResponseEntity<PageResource<MemberResource>> list(@PageableDefault(size = 1, page = 0) Pageable pageable) {
        Page<Member> members = memberService.findAll(pageable);
        Page<MemberResource> memberResource = members.map(MemberResource::new);
        return ResponseEntity.ok(new PageResource<>(memberResource, "page", "size"));
    }

    @RequestMapping(value = "/members/{id}", produces = "application/hal+json", method = GET)
    public ResponseEntity<MemberResource> findOne(@PathVariable("id") int id) {
        return ResponseEntity.ok(new MemberResource(memberService.findOne(id)));
    }

    //Delete
    @RequestMapping(value = "/members/{id}", produces = "application/hal+json", method = DELETE)
    public HttpEntity<String> delete(@PathVariable("id") int id) {
        memberService.delete(id);
        return new HttpEntity<String>("Deleted");
    }


    //Issue
    @RequestMapping(value = "/members/{id}/take/{bookId}", produces = "application/hal+json", method = PUT)
    public ResponseEntity<MemberResource> takeBook(@PathVariable("id") int id, @PathVariable("bookId") int bookId) {
        return ResponseEntity.ok(new MemberResource(memberService.takeBook(id, bookId)));
    }

    //return
    @RequestMapping(value = "/members/{id}/return/{bookId}", produces = "application/hal+json", method = PUT)
    public ResponseEntity<MemberResource> returnBook(@PathVariable("id") int id, @PathVariable("bookId") int bookId) {
        return ResponseEntity.ok(new MemberResource(memberService.returnBook(id, bookId)));
    }

}
