package com.tournment.cricket.controller;

/**
 * Created by DESIREDDY JAYASYAM
 */import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tournment.cricket.model.Match;
import com.tournment.cricket.model.Role;
import com.tournment.cricket.model.User;
import com.tournment.cricket.service.MatchService;
import com.tournment.cricket.service.RoleService;
import com.tournment.cricket.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private MatchService matchService;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private UserController userController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowLoginPage_WithCurrentUser() {
        User user = new User();
        user.setRole(new Role("ADMIN"));
        when(session.getAttribute("currentUser")).thenReturn(user);
        assertThat(userController.showLoginPage(session), equalTo("redirect:/adminDashboard"));
    }

    @Test
    public void testShowLoginPage_WithoutCurrentUser() {
        when(session.getAttribute("currentUser")).thenReturn(null);
        assertThat(userController.showLoginPage(session), equalTo("login"));
    }

    @Test
    public void testLogout() {
        assertThat(userController.logout(session), equalTo("login"));
    }

    @Test
    public void testLogin_ValidCredentials_Admin() {
        User user = new User();
        user.setRole(new Role("ADMIN"));
        when(userService.getUserByEmailAndPassword("admin@example.com", "password")).thenReturn(Optional.of(user));
        assertThat(userController.login("admin@example.com", "password", session, redirectAttributes, model), equalTo("redirect:/adminDashboard"));
    }

    @Test
    public void testLogin_ValidCredentials_User() {
        User user = new User();
        user.setRole(new Role("USER"));
        when(userService.getUserByEmailAndPassword("user@example.com", "password")).thenReturn(Optional.of(user));
        assertThat(userController.login("user@example.com", "password", session, redirectAttributes, model), equalTo("redirect:/userDashboard"));
    }

    @Test
    public void testLogin_InvalidCredentials() {
        when(userService.getUserByEmailAndPassword("invalid@example.com", "password")).thenReturn(Optional.empty());
        assertThat(userController.login("invalid@example.com", "password", session, redirectAttributes, model), equalTo("redirect:/"));
    }

    @Test
    public void testShowAdminDashboardView() {
        Match match1 = new Match();
        Match match2 = new Match();
        when(matchService.getAllMatches()).thenReturn(Arrays.asList(match1, match2));
        assertThat(userController.showAdminDashboardView(model), equalTo("adminDashboard"));
    }
}