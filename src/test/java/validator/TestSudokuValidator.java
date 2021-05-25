package validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestSudokuValidator {

    @Test
    public void validSudoku() {

        String[][] in = {
                {"3","5","9","4","8","1","7","6","2"},
                {"7","6","4","9","2","3","8","5","1"},
                {"1","2","8","5","6","7","3","4","9"},
                {"2","1","5","6","7","8","4","9","3"},
                {"4","7","3","2","5","9","1","8","6"},
                {"8","9","6","1","3","4","2","7","5"},
                {"9","4","2","7","1","6","5","3","8"},
                {"6","8","1","3","4","5","9","2","7"},
                {"5","3","7","8","9","2","6","1","4"}
        };

        SudokuValidator s = new SudokuValidator(in);

        assertEquals(s.getErrors().size(),0);
    }

    @Test
    public void invalidSudoku1() {
        //cell 0,4 and 0,5 are same causing 3 different errors below
        String[][] in = {
                {"3","5","9","4","1","1","7","6","2"},
                {"7","6","4","9","2","3","8","5","1"},
                {"1","2","8","5","6","7","3","4","9"},
                {"2","1","5","6","7","8","4","9","3"},
                {"4","7","3","2","5","9","1","8","6"},
                {"8","9","6","1","3","4","2","7","5"},
                {"9","4","2","7","1","6","5","3","8"},
                {"6","8","1","3","4","5","9","2","7"},
                {"5","3","7","8","9","2","6","1","4"}
        };

        SudokuValidator s = new SudokuValidator(in);
        System.out.println(s.getErrors());
        assertTrue(s.getErrors().indexOf("Cell(0,4) has a duplicated value with Cell(0,5)")>=0);
        assertTrue(s.getErrors().indexOf("Cell(0,4) has a duplicated value with Cell(6,4)")>=0);
        assertTrue(s.getErrors().indexOf("in 0,3 sub matrix, Cell(0,4) has a duplicated value with Cell(0,5)")>=0);
    }

    @Test
    public void invalidSudoku2() {

        //cell 5,4 and 5,5 are same causing 3 different errors below
        String[][] in = {
                {"3","5","9","4","8","1","7","6","2"},
                {"7","6","4","9","2","3","8","5","1"},
                {"1","2","8","5","6","7","3","4","9"},
                {"2","1","5","6","7","8","4","9","3"},
                {"4","7","3","2","5","9","1","8","6"},
                {"8","9","6","1","3","3","2","7","5"},
                {"9","4","2","7","1","6","5","3","8"},
                {"6","8","1","3","4","5","9","2","7"},
                {"5","3","7","8","9","2","6","1","4"}
        };

        SudokuValidator s = new SudokuValidator(in);
        for (String ss: s.getErrors())
            System.out.println(ss);
        assertTrue(s.getErrors().indexOf("Cell(5,4) has a duplicated value with Cell(5,5)")>=0);
        assertTrue(s.getErrors().indexOf("Cell(1,5) has a duplicated value with Cell(5,5)")>=0);
        assertTrue(s.getErrors().indexOf("in 3,3 sub matrix, Cell(5,4) has a duplicated value with Cell(5,5)")>=0);
    }
}