package com.btasdemir.coderetreat.game;

import java.util.Arrays;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class GameOfLifeTest {

  @Autowired
  private GameOfLife gameOfLife;

  @Test
  public void it_should_return_correct_state_for_dying_for_loneliness() {
    // Given
    var state = new int[][] {
        { 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
    };

    // When
    var nextState = gameOfLife.getNextState(state);

    // Then
    var nextValidState = new int[][] {
      { 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
  };
    Assertions.assertThat(nextState).isEqualTo(nextValidState);
  }

  @Test
  public void test() {
    var state = new int[][] {
      { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1 },
      { 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 1, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0 },
      { 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1 },
      { 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0 },
      { 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0 },
      { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0 },
      { 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
      { 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0 },
      { 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0 },
      { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 },
    };

    for (var i = 0; i < 100; i++) {
      System.out.print(String.format("\033[2J"));
      for (var row = 0; row < state.length; row++) {
        var strVal = Arrays.toString(state[row])
          .replaceAll("1", "⬛")
          .replaceAll("[,\\[\\]]", "")
          .replaceAll("0", "⬜");
        System.out.println(strVal);
      }
      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      state = gameOfLife.getNextState(state);
    }
  }

}
