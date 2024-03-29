package com.patinaud.batailleservice;

import com.patinaud.bataillemodel.constants.BoatType;
import com.patinaud.bataillemodel.dto.BoatDTO;
import com.patinaud.bataillemodel.dto.CellDTO;
import com.patinaud.bataillemodel.dto.CoordinateDTO;
import com.patinaud.bataillemodel.dto.GridDTO;
import com.patinaud.batailleservice.service.GridService;
import com.patinaud.batailleservice.service.GridServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GridServiceImplTest {

    @Test
    void theBoatCanBePositionHereHOutLeft() {
        GridService gridService = new GridServiceImpl();

        BoatDTO boat = new BoatDTO();
        boat.setBoatType(BoatType.CROISEUR);
        boat.setxHead(-1);
        boat.setyHead(4);
        boat.setHorizontal(true);

        GridDTO grid = gridService.generateEmptyGrid(10, 10);

        Assertions.assertFalse(gridService.theBoatCanBePositionHere(boat, null, grid));

    }


    @Test
    void theBoatCanBePositionHereHIn() {
        GridService gridService = new GridServiceImpl();

        BoatDTO boat = new BoatDTO();
        boat.setBoatType(BoatType.CROISEUR);
        boat.setxHead(1);
        boat.setyHead(4);
        boat.setHorizontal(true);

        GridDTO grid = gridService.generateEmptyGrid(10, 10);

        Assertions.assertTrue(gridService.theBoatCanBePositionHere(boat, null, grid));

    }


    @Test
    void theBoatCanBePositionHereHOutRight() {
        GridService gridService = new GridServiceImpl();

        BoatDTO boat = new BoatDTO();
        boat.setBoatType(BoatType.CROISEUR);
        boat.setxHead(8);
        boat.setyHead(4);
        boat.setHorizontal(true);

        GridDTO grid = gridService.generateEmptyGrid(10, 10);

        Assertions.assertFalse(gridService.theBoatCanBePositionHere(boat, null, grid));

    }

    @Test
    void theBoatCanBePositionHereVOutTop() {
        GridService gridService = new GridServiceImpl();

        BoatDTO boat = new BoatDTO();
        boat.setBoatType(BoatType.CROISEUR);
        boat.setxHead(3);
        boat.setyHead(-1);
        boat.setHorizontal(false);

        GridDTO grid = gridService.generateEmptyGrid(10, 10);

        Assertions.assertFalse(gridService.theBoatCanBePositionHere(boat, null, grid));

    }


    @Test
    void theBoatCanBePositionHereVIn() {
        GridService gridService = new GridServiceImpl();

        BoatDTO boat = new BoatDTO();
        boat.setBoatType(BoatType.CROISEUR);
        boat.setxHead(3);
        boat.setyHead(2);
        boat.setHorizontal(false);

        GridDTO grid = gridService.generateEmptyGrid(10, 10);

        Assertions.assertTrue(gridService.theBoatCanBePositionHere(boat, null, grid));

    }


    @Test
    void theBoatCanBePositionHereVOutBottom() {
        GridService gridService = new GridServiceImpl();

        BoatDTO boat = new BoatDTO();
        boat.setBoatType(BoatType.CROISEUR);
        boat.setxHead(3);
        boat.setyHead(9);
        boat.setHorizontal(false);

        GridDTO grid = gridService.generateEmptyGrid(10, 10);

        Assertions.assertFalse(gridService.theBoatCanBePositionHere(boat, null, grid));

    }


    @Test
    void boatOccupiesTheCellHoriz() {
        GridService gridService = new GridServiceImpl();
        BoatDTO boat = new BoatDTO();
        boat.setBoatType(BoatType.CROISEUR);
        boat.setxHead(5);
        boat.setyHead(5);
        boat.setHorizontal(true);

        Assertions.assertTrue(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(5, 5)));
        Assertions.assertTrue(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(4, 4)));
        Assertions.assertTrue(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(4, 6)));
        Assertions.assertTrue(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(9, 4)));
        Assertions.assertTrue(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(9, 6)));
        Assertions.assertFalse(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(9, 7)));
        Assertions.assertFalse(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(9, 3)));
    }


    @Test
    void boatOccupiesTheCellVerti() {
        GridService gridService = new GridServiceImpl();
        BoatDTO boat = new BoatDTO();
        boat.setBoatType(BoatType.TORPILLEUR);
        boat.setxHead(5);
        boat.setyHead(5);
        boat.setHorizontal(false);

        Assertions.assertTrue(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(5, 5)));
        Assertions.assertTrue(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(5, 6)));
        Assertions.assertTrue(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(4, 4)));
        Assertions.assertTrue(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(6, 4)));
        Assertions.assertTrue(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(4, 7)));
        Assertions.assertTrue(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(6, 7)));

        Assertions.assertFalse(gridService.theBoatOccupiesTheCell(boat, new CoordinateDTO(8, 7)));
    }

    @Test
    void thePositionIsFree() {
        GridService gridService = new GridServiceImpl();

        BoatDTO boatToPosition = new BoatDTO();
        boatToPosition.setBoatType(BoatType.PORTE_AVIONS);
        boatToPosition.setxHead(4);
        boatToPosition.setyHead(3);
        boatToPosition.setHorizontal(true);

        BoatDTO boatAlreadyPositioned = new BoatDTO();
        boatAlreadyPositioned.setBoatType(BoatType.TORPILLEUR);
        boatAlreadyPositioned.setxHead(7);
        boatAlreadyPositioned.setyHead(5);
        boatAlreadyPositioned.setHorizontal(true);

        ArrayList<BoatDTO> boatsAlreadyPositioned = new ArrayList<>();
        boatsAlreadyPositioned.add(boatAlreadyPositioned);

        Assertions.assertTrue(gridService.thePositionIsFreeOfOtherBoats(boatToPosition, boatsAlreadyPositioned));
    }


    @Test
    void thePositionIsNotFree() {
        GridService gridService = new GridServiceImpl();

        BoatDTO boatToPosition = new BoatDTO();
        boatToPosition.setBoatType(BoatType.PORTE_AVIONS);
        boatToPosition.setxHead(6);
        boatToPosition.setyHead(3);
        boatToPosition.setHorizontal(true);

        BoatDTO boatAlreadyPositioned = new BoatDTO();
        boatAlreadyPositioned.setBoatType(BoatType.TORPILLEUR);
        boatAlreadyPositioned.setxHead(3);
        boatAlreadyPositioned.setyHead(3);
        boatAlreadyPositioned.setHorizontal(false);

        ArrayList<BoatDTO> boatsAlreadyPositioned = new ArrayList<>();
        boatsAlreadyPositioned.add(boatAlreadyPositioned);

        Assertions.assertTrue(gridService.thePositionIsFreeOfOtherBoats(boatToPosition, boatsAlreadyPositioned));
    }


    @Test
    void countNumberOfRevealedCellWhichContainsABoatFromThisCell() {

        GridService gridService = new GridServiceImpl();
        GridDTO grid = gridService.generateEmptyGrid(10, 10);

        CellDTO cell = new CellDTO();
        cell.setX(3);
        cell.setY(6);

        Assertions.assertEquals(0,
                gridService.countNumberOfRevealedCellWhichContainsABoatFromThisCoordinate(grid, new CoordinateDTO(3, 6), -1, 0)
        );
    }


    @Test
    void countNumberOfRevealedCellWhichContainsABoatFromThisCellToLeftBorder() {

        GridService gridService = new GridServiceImpl();
        GridDTO grid = gridService.generateEmptyGrid(10, 10);


        grid.getCell(1, 6).setRevealed(true);
        grid.getCell(1, 6).setOccupied(true);

        grid.getCell(0, 6).setRevealed(true);
        grid.getCell(0, 6).setOccupied(true);

        CellDTO cell = new CellDTO();
        cell.setX(2);
        cell.setY(6);

        Assertions.assertEquals(2,
                gridService.countNumberOfRevealedCellWhichContainsABoatFromThisCoordinate(grid, new CoordinateDTO(2, 6), -1, 0)
        );
    }


    @Test
    void countNumberOfRevealedCellWhichContainsABoatFromThisCellToTopWater() {

        GridService gridService = new GridServiceImpl();
        GridDTO grid = gridService.generateEmptyGrid(10, 10);

        grid.getCell(2, 4).setRevealed(true);
        grid.getCell(2, 4).setOccupied(false);

        grid.getCell(2, 5).setRevealed(true);
        grid.getCell(2, 5).setOccupied(true);


        Assertions.assertEquals(1,
                gridService.countNumberOfRevealedCellWhichContainsABoatFromThisCoordinate(grid, new CoordinateDTO(2, 6), 0, -1)
        );
    }


    @Test
    void countNumberOfUnrevealedCellFromThisCellToBorder() {

        GridService gridService = new GridServiceImpl();
        GridDTO grid = gridService.generateEmptyGrid(10, 10);


        CellDTO cell = new CellDTO();
        cell.setX(4);
        cell.setY(6);

        Assertions.assertEquals(4,
                gridService.countNumberOfUnrevealedCellFromThisCoordinate(grid, new CoordinateDTO(4, 6), -1, 0)
        );
    }


    @Test
    void countNumberOfUnrevealedCellFromThisCellToRevealed() {

        GridService gridService = new GridServiceImpl();
        GridDTO grid = gridService.generateEmptyGrid(10, 10);

        grid.getCell(4, 2).setRevealed(true);

        Assertions.assertEquals(3,
                gridService.countNumberOfUnrevealedCellFromThisCoordinate(grid, new CoordinateDTO(4, 6), 0, -1)
        );
    }

    @Test
    void countNumberOfPositionTheBoatCanTakeInTheCoordinate() {

        GridService gridService = new GridServiceImpl();
        GridDTO grid = gridService.generateEmptyGrid(10, 10);

        Assertions.assertEquals(6,
                gridService.countNumberOfPositionTheBoatCanTakeInTheCoordinate(grid, BoatType.SOUS_MARIN_1, new CoordinateDTO(5, 5))
        );
    }


    @Test
    void countNumberOfPositionTheBoatCanTakeInTheCoordinateCurrentCellRevealed() {

        GridService gridService = new GridServiceImpl();
        GridDTO grid = gridService.generateEmptyGrid(10, 10);


        grid.getCell(5, 5).setRevealed(true);

        Assertions.assertEquals(6,
                gridService.countNumberOfPositionTheBoatCanTakeInTheCoordinate(grid, BoatType.SOUS_MARIN_1, new CoordinateDTO(5, 5))
        );
    }


    @Test
    void countNumberOfPositionTheBoatCanTakeInTheCoordinateAlreadyRevealedCell() {

        GridService gridService = new GridServiceImpl();
        GridDTO grid = gridService.generateEmptyGrid(10, 10);

        grid.getCell(5, 3).setRevealed(true);

        Assertions.assertEquals(7,
                gridService.countNumberOfPositionTheBoatCanTakeInTheCoordinate(grid, BoatType.PORTE_AVIONS, new CoordinateDTO(5, 5))
        );
    }


    @Test
    void countNumberOfPositionTheBoatCanTakeInTheCoordinateBorder() {

        GridService gridService = new GridServiceImpl();
        GridDTO grid = gridService.generateEmptyGrid(10, 10);

        Assertions.assertEquals(6,
                gridService.countNumberOfPositionTheBoatCanTakeInTheCoordinate(grid, BoatType.PORTE_AVIONS, new CoordinateDTO(0, 5))
        );
    }


    @Test
    void countNumberOfPositionTheBoatCanTakeInTheCoordinateNoSolution() {

        GridService gridService = new GridServiceImpl();
        GridDTO grid = gridService.generateEmptyGrid(10, 10);


        grid.getCell(0, 3).setRevealed(true);
        grid.getCell(0, 6).setRevealed(true);
        grid.getCell(2, 5).setRevealed(true);


        Assertions.assertEquals(0,
                gridService.countNumberOfPositionTheBoatCanTakeInTheCoordinate(grid, BoatType.PORTE_AVIONS, new CoordinateDTO(0, 5))
        );
    }


    @Test
    void countNumberOfPositionBoatsCanTakeInTheCoordinate() {
        GridService gridService = new GridServiceImpl();
        GridDTO grid = gridService.generateEmptyGrid(10, 10);


        Assertions.assertEquals(11,
                gridService.countNumberOfPositionBoatsCanTakeInTheCoordinate(grid, List.of(BoatType.PORTE_AVIONS, BoatType.CROISEUR), new CoordinateDTO(0, 5))
        );
    }


    @Test
    void countNumberOfPositionTheBoatCanTakeInTheCoordinateFullGrid() {


        GridService gridService = new GridServiceImpl();

        GridDTO grid = gridService.generateEmptyGrid(3, 3);
        grid.getCell(0, 0).setRevealed(true);
        grid.getCell(1, 0).setRevealed(true);
        grid.getCell(2, 0).setRevealed(true);
        grid.getCell(0, 1).setRevealed(true);
        grid.getCell(0, 2).setRevealed(true);
        grid.getCell(1, 2).setRevealed(true);
        grid.getCell(2, 2).setRevealed(true);


        Assertions.assertEquals(1,
                gridService.countNumberOfPositionTheBoatCanTakeInTheCoordinate(grid, BoatType.TORPILLEUR, new CoordinateDTO(2, 1))
        );

    }

}
