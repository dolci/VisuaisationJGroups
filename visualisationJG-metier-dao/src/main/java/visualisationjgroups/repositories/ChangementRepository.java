package visualisationjgroups.repositories;

import java.util.Date;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import visualisationjgroups.entities.Changement;;

public interface ChangementRepository extends CrudRepository<Changement, Long>{
	@Query("select c from Changement c where c.graphe.id=?1 order by c.dateModif desc")
	Iterable<Changement> getAllChangementByIdGraphe(long idGraphe);
//	@Query("select c from Changement c where c.graphe.id=?1 order by v.dateModif desc")
	@Query("select c from Changement c where c.graphe.id=?1  and c.dateModif between ?2 and ?3 order by c.dateModif desc")
	Iterable<Changement> findByDateModifBetween (long idGraphe,Date from, Date to);
	@Query("select c from Changement c where c.graphe.id=?1  and c.dateModif=?2 and c.heureModif between ?3 and ?4 order by c.heureModif desc")
	Iterable<Changement> findByheureCreationBetween (long idGraphe,Date date,String from, String to);
}
