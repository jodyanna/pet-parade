package com.petparade.api.controller;

import com.petparade.api.PetParadeApplication;
import com.petparade.api.dto.PetDto;
import com.petparade.api.service.PetService;
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
public class PetControllerTest {
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @MockBean
  private PetService petService;

  private PetDto petDto;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    this.petDto = new PetDto();
    this.petDto.setName("Violet");
    this.petDto.setBio("My childhood pet and best friend. RIP");
    this.petDto.setIsFlagged(true);
    this.petDto.setSpecies(1L);
    this.petDto.setOwner(1L);
  }

  @Test
  public void getPetById() throws Exception {
    String uri = "/pets/1";
    when(petService.findById(anyLong())).thenReturn(petDto);

    mockMvc.perform(MockMvcRequestBuilders.get(uri)
          .accept(MediaType.APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk())
          .andExpect(content().contentType(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$.name").exists())
          .andExpect(jsonPath("$.name").value("Violet"))
          .andExpect(jsonPath("$.bio").exists())
          .andExpect(jsonPath("$.bio").value("My childhood pet and best friend. RIP"))
          .andExpect(jsonPath("$.isFlagged").exists())
          .andExpect(jsonPath("$.isFlagged").value(true))
          .andExpect(jsonPath("$.species").exists())
          .andExpect(jsonPath("$.species").value(1L))
          .andExpect(jsonPath("$.owner").exists())
          .andExpect(jsonPath("$.owner").value(1L))
          .andReturn();
  }

  @Test
  public void getFlaggedPets() throws Exception {
    String uri = "/pets/flagged";
    List<PetDto> petDtoList = Collections.singletonList(petDto);

    when(petService.findAllFlaggedPets()).thenReturn(petDtoList);

    mockMvc.perform(MockMvcRequestBuilders.get(uri)
        .accept(MediaType.APPLICATION_JSON_VALUE))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(greaterThan(0))))
        .andReturn();
  }

  @Test
  public void save() throws Exception {
    String uri = "/pets";

    when(petService.save(any())).thenReturn(petDto);

    mockMvc.perform(MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\": \"Violet\"," +
            " \"bio\": \"My childhood pet and best friend. RIP\"," +
            " \"isFlagged\": \"true\"," +
            " \"birthday\": \"null\"," +
            " \"species\": \"1\"," +
            " \"id\": \"null\"," +
            " \"owner\": \"1\"" +
            "}")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").exists())
        .andExpect(jsonPath("$.name").value("Violet"))
        .andExpect(jsonPath("$.bio").exists())
        .andExpect(jsonPath("$.bio").value("My childhood pet and best friend. RIP"))
        .andExpect(jsonPath("$.isFlagged").exists())
        .andExpect(jsonPath("$.isFlagged").value(true))
        .andExpect(jsonPath("$.species").exists())
        .andExpect(jsonPath("$.species").value(1L))
        .andExpect(jsonPath("$.owner").exists())
        .andExpect(jsonPath("$.owner").value(1L))
        .andReturn();
  }
}
