package com.petparade.api.controller;

import com.petparade.api.PetParadeApplication;
import com.petparade.api.dto.UserDto;
import com.petparade.api.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.Mockito.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PetParadeApplication.class)
@WebAppConfiguration
public class UserControllerTest {
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @MockBean
  private UserService userService;

  private UserDto userDto;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    this.userDto = new UserDto("test", "test@email.com");
  }

  @Test
  public void getAllUsers() throws Exception {
    String uri = "/users";
    List<UserDto> userDtoList = Collections.singletonList(userDto);

    when(userService.findAll()).thenReturn(userDtoList);

    mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(greaterThan(0))))
            .andReturn();
  }

  @Test
  public void login() throws Exception {
    String uri = "/users/login";

    when(userService.findByEmail(anyString())).thenReturn(userDto);

    mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"email\": \"test@email.com\"," +
                " \"password\": \"password\"" +
                "}")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.username").exists())
            .andExpect(jsonPath("$.username").value("test"))
            .andExpect(jsonPath("$.email").exists())
            .andExpect(jsonPath("$.email").value("test@email.com"))
            .andReturn();
  }

  @Test
  public void signup() throws Exception {
    String uri = "/users/signup";

    when(userService.save(any())).thenReturn(userDto);

    mockMvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\": \"test\", " +
                    "\"email\": \"test@email.com\"," +
                    " \"password\": \"password\"}")
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.username").exists())
            .andExpect(jsonPath("$.username").value("test"))
            .andExpect(jsonPath("$.email").exists())
            .andExpect(jsonPath("$.email").value("test@email.com"))
            .andReturn();
  }



}
