package visualisationjgroups.repositories;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import visualisationjgroups.entities.Member;

public interface MemberRepository extends CrudRepository<Member, Long>{
	@Query("select m from Member m where m.graphe.id=?1 and m.status=actif")
	Iterable<Member> getAllMemberActif(long idGrpahe);
	@Query("select m from Member m where m.graphe.id=?1")
	Iterable<Member> getAllMember(long idGrpahe);
	@Query("select m from Member m where m.graphe.id=?1 and m.logicalName=?2")
	Member findMemberByIdGrapheAndlogicalName(long idgraphe,String logicalName);
}
