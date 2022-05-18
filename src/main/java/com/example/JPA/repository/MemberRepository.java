package com.example.JPA.repository;

import com.example.JPA.dto.MemberDto;
import com.example.JPA.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 파라미터가 길어지면 문제가 됨
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);


    /** !아래는 주석 처리를 해도 실행이 됨.
     * @Query(name = "Member.findByUsername")
     * "Member.findByUsername" 에서 @Param(:"username") 을 우선적으로 찾음
     * 없으면 만들어진 메소드중에서 찾음.
     * @Param 은 :username 처럼 JPQL을 명확히 작성했을 때
    **/

    List<Member> findByUsername(@Param("username") String username);

    /**
     * 장점 : @Query 에서 정의되는 것은 NamedQuery 라고 보면 됨
     * 애플리케이션 로딩 시점에 파싱하므로 오류를 잡아낼 수 있음.
     * 실무에서는 많이 사용됨, 정적쿼리는 아래와 같이 사용 but 복잡한 동적쿼리는 결국 Querydsl 을 사용
    **/
    @Query("select m from Member m where m.username= :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new com.example.JPA.dto.MemberDto(m.id, m.username, t.name) " +
            "from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);




}
