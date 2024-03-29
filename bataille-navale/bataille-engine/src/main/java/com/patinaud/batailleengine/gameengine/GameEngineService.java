package com.patinaud.batailleengine.gameengine;

import com.patinaud.bataillemodel.dto.BoatDTO;
import com.patinaud.bataillemodel.dto.CoordinateDTO;
import com.patinaud.bataillemodel.dto.GameDTO;


import java.util.List;

public interface GameEngineService {

    GameDTO generateNewGame();

    void positionHumanPlayerBoat(String idGame, List<BoatDTO> boats);

    void playerAttack(String idGame, String idPlayerAttackerStr, CoordinateDTO coordinateTargeted);

}
