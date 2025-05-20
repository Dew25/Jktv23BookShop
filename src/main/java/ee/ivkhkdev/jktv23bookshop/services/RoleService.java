package ee.ivkhkdev.jktv23bookshop.services;

import ee.ivkhkdev.jktv23bookshop.model.entity.Role;
import ee.ivkhkdev.jktv23bookshop.model.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public void save(Role role){
        roleRepository.save(role);
    }

    public Role findRole(String name) {
        return roleRepository.findRoleByName(name);
    }
}
