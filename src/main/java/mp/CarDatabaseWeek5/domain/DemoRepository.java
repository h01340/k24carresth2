package mp.CarDatabaseWeek5.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DemoRepository extends CrudRepository<Demo, Long> {

    //http://localhost:8080/api/demoes/search/findByName?name=Minna
    List<Demo> findByName(@Param("name") String name);

}
