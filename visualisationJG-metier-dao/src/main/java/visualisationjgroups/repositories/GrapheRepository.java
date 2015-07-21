package visualisationjgroups.repositories;


import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import visualisationjgroups.entities.Graphe;

public interface GrapheRepository extends CrudRepository<Graphe, Long>{
	
	List <Graphe>findBydateCreationBefore(Date date);
	Graphe findBydescription(String description );
}
