package hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("simple-jpa-application");

    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();

    try {
      // 저장
//      Member member = new Member();
//      member.setId(1L);
//      member.setName("HelloA");
//      em.persist(member);
      // 조회
      Member findMember = em.find(Member.class, 1L);
      System.out.println(findMember.getId());

      //JPQL : 엔티티 객체를 대상으로 쿼리를 함.
      List<Member> query = em.createQuery("select m from Member as m", Member.class)
              .setFirstResult(5)
              .setMaxResults(8)
              .getResultList();


      for (Member member : query) {
        // soutv
        System.out.println("member.name = " + member.getName());
      }
      // 수정
      findMember.setName("test");
      // 삭제
//      em.remove(findMember);

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();

  }
}
