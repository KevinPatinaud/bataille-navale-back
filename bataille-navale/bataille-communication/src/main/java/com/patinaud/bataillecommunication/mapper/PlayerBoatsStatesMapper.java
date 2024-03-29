package com.patinaud.bataillecommunication.mapper;

import com.patinaud.bataillecommunication.responsedata.BoatState;
import com.patinaud.bataillecommunication.responsedata.PlayerBoatsStates;
import com.patinaud.bataillemodel.constants.IdPlayer;
import com.patinaud.bataillemodel.dto.BoatDTO;

import java.util.ArrayList;
import java.util.List;

public class PlayerBoatsStatesMapper {
    private PlayerBoatsStatesMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static PlayerBoatsStates fromDtoToResponse(IdPlayer idPlayer, List<BoatDTO> boatsDtos) {
        PlayerBoatsStates playerBoatsStates = new PlayerBoatsStates();
        playerBoatsStates.setIdPlayer(idPlayer);

        ArrayList<BoatState> boatsStates = new ArrayList<>();

        for (int i = 0; i < boatsDtos.size(); i++) {
            BoatState boatState = new BoatState();
            boatState.setType(boatsDtos.get(i).getBoatType());
            boatState.setDestroyed(boatsDtos.get(i).isDestroyed());
            boatsStates.add(boatState);
        }

        playerBoatsStates.setBoatsStates(boatsStates);
        return playerBoatsStates;
    }
}
