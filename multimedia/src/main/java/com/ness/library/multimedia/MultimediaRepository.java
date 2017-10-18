package com.ness.library.multimedia;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MultimediaRepository extends MongoRepository<Multimedia, Long> {
    Multimedia findBy_id(String id);
}