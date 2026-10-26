package dev.sojunx.owed.repository;

import dev.sojunx.owed.domain.GroupMember;
import dev.sojunx.owed.domain.GroupMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberId> {}
