package com.petparade.api;

import com.petparade.api.controller.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SmokeTest {
  @Autowired
  private IndexController indexController;
  @Autowired
  private LikeController likeController;
  @Autowired
  private PetController petController;
  @Autowired
  private RatingController ratingController;
  @Autowired
  private SpeciesController speciesController;
  @Autowired
  private UserController userController;

  @Test
  public void contextLoads() throws Exception {
    assertThat(indexController).isNotNull();
    assertThat(likeController).isNotNull();
    assertThat(petController).isNotNull();
    assertThat(ratingController).isNotNull();
    assertThat(speciesController).isNotNull();
    assertThat(userController).isNotNull();
  }
}
