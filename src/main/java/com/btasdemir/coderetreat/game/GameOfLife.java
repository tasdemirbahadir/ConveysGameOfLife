package com.btasdemir.coderetreat.game;

import org.springframework.stereotype.Component;

@Component
public class GameOfLife {

  public int[][] getNextState(int[][] map) {
    var nextState = new int[map.length][map[0].length];
    for (var row = 0; row < map.length; row++) {
      for (var col = 0; col < map[row].length; col++) {
        nextState[row][col] = getNextStateVal(map, row, col);
      }
    }
    return nextState;
  }

  private int getNextStateVal(int[][] map, int row, int col) {
    var curLiveNeighboursCount = getLiveNeighoursCount(map, row, col);
    if (map[row][col] == 1) {
      if (curLiveNeighboursCount < 2 || curLiveNeighboursCount > 3) {
        return 0;
      } else {
        return 1;
      }
    } else if (curLiveNeighboursCount == 3) {
      return 1;
    } else {
      return map[row][col];
    }
  }

  private int getLiveNeighoursCount(int[][] map, int row, int col) {
    var liveNeighbourCount = 0;
    for (int curRow = row - 1; curRow <= row + 1; curRow++) {
      for (int curCol = col - 1; curCol <= col + 1; curCol++) {
        if (curRow == row && curCol == col) {
          continue;
        }
        if (curRow >= 0 && curRow < map.length &&
          curCol >= 0 && curCol < map[curRow].length &&
            map[curRow][curCol] == 1) {
              liveNeighbourCount++;
          }
      }
    }
    return liveNeighbourCount;
  }

}
