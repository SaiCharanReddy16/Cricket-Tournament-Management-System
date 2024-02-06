package com.tournment.cricket.service;

/**
 * Created by DESIREDDY JAYASYAM
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.tournment.cricket.model.Role;
import com.tournment.cricket.repository.RoleRepository;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private Role createMockRole(Long id, String name) {
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        return role;
    }

    @Test
    public void testGetAllRoles() {
        Role role1 = createMockRole(1L, "Role 1");
        Role role2 = createMockRole(2L, "Role 2");
        List<Role> roleList = Arrays.asList(role1, role2);

        when(roleRepository.findAll()).thenReturn(roleList);

        List<Role> result = roleService.getAllRoles();

        assertEquals(roleList, result);
    }

    @Test
    public void testGetRoleById() {
        Role role = createMockRole(1L, "Role 1");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Role result = roleService.getRoleById(1L);

        assertEquals(role, result);
    }

    @Test
    public void testGetRoleById_ReturnsNull() {
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        Role result = roleService.getRoleById(1L);

        assertNull(result);
    }

    @Test
    public void testCreateRole() {
        Role role = createMockRole(null, "Role 1");

        when(roleRepository.save(any(Role.class))).thenReturn(role);

        Role result = roleService.createRole(role);

        assertEquals(role.getName(), result.getName());
    }

    @Test
    public void testUpdateRole() {
        Role existingRole = createMockRole(1L, "Role 1");
        Role updatedRole = createMockRole(1L, "Role 1 Updated");

        when(roleRepository.findById(1L)).thenReturn(Optional.of(existingRole));
        when(roleRepository.save(existingRole)).thenReturn(updatedRole);

        Role result = roleService.updateRole(1L, updatedRole);

        assertEquals(updatedRole, result);
    }

    @Test
    public void testUpdateRole_ReturnsNull() {
        Role updatedRole = createMockRole(1L, "Role 1 Updated");

        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        Role result = roleService.updateRole(1L, updatedRole);

        assertNull(result);
    }

    @Test
    public void testDeleteRole() {
        roleService.deleteRole(1L);
    }

    @Test
    public void testGetRoleByName() {
        Role role = createMockRole(1L, "Role 1");

        when(roleRepository.findByName("Role 1")).thenReturn(Optional.of(role));

        Optional<Role> result = roleService.getRoleByName("Role 1");

        assertEquals(role, result.get());
    }
}
