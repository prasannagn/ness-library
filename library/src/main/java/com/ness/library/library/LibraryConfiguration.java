package com.ness.library.library;

import com.ness.library.library.service.BookService;
import com.ness.library.library.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LibraryConfiguration {

    @Bean
    BookService bookService() {
        return new BookService();
    }

    @Bean
    MemberService memberService(){
        return new MemberService();
    }
}
