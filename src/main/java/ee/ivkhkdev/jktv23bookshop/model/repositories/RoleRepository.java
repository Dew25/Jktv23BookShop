package ee.ivkhkdev.jktv23bookshop.model.repositories;

import ee.ivkhkdev.jktv23bookshop.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findRoleByName(String name);
}
