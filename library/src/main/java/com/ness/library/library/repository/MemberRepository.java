package com.ness.library.library.repository;

import com.ness.library.library.model.MemberModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberModel, Integer> {
}
