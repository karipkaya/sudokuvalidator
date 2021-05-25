package validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SudokuValidator {

    // Cell will be used for holding coordinates of numbers from 0,..,9  in a 9x9 matrix.
    private class Cell{
        private int x,y;
        private Cell(){}
        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString(){
            return String.format("Cell(%d,%d)",x,y);
        }
    }

    List<String> errors = new ArrayList<>();
    String[][] s;

    private SudokuValidator(){}
    public SudokuValidator(String[][] s) {
        this.s =s;
        List<String> errors = new ArrayList<>();
        validate();
    }

    private void addErrors(String err){
        errors.add(err);
    }

    public List<String> getErrors(){
        return errors;
    }


    // this method will control 9 3x3 matrices, if they have duplicate values
    private void controlSub(int x, int y) {
        /*
         *
         *  This map will hold keys (1,2,3,4,5,6,7,8,9) and  their places in the matris.
         *  Map<Integer,Cell> cells = new HashMap<>();
         *  When you try to add same key with different Cell value,
         *  It will allow you to tell duplicating Cell places
         *
         * */
        Map<Integer,Cell> cells = new HashMap<>();

        for( int i = x; i<x+3; i++  )
            for( int j = y; j<y+3; j++  ) {
                if(cells.containsKey(Integer.parseInt(s[i][j]))) {
                    // if the number is in the map, add a duplicate error to the error list
                    addErrors(String.format("in %d,%d sub matrix, %s has a duplicated value with %s",x,y,cells.get(Integer.parseInt(s[i][j])).toString(), new Cell(i,j).toString())   );
                } else {
                    // if number is not in the cells then append its place to the map.
                    cells.put(Integer.parseInt(s[i][j]),new Cell(i,j));
                }
            }
    }

    private void validate() {
        Map<Integer,Map<Integer,Cell>> rowCells = new HashMap<>();
        Map<Integer,Map<Integer,Cell>> colCells = new HashMap<>();
        for(int i = 0; i<9; i++) {
            rowCells.put(i,new HashMap<Integer,Cell>());
            for (int j = 0; j < 9; j++) {
                colCells.put(Integer.valueOf(j),colCells.getOrDefault(Integer.valueOf(j),new HashMap<Integer,Cell>()));
                if(! s[i][j].matches("\\d")) {
                    addErrors(String.format("Invalid or Non-numeric value at cell(%d,%d)",i,j));
                }
                else if(Integer.parseInt(s[i][j]) <1 || Integer.parseInt(s[i][j]) >9)
                    addErrors(String.format("Invalid number at cell(%d,%d)",i,j));
                else {
                    // To show column and row duplications separately,
                    // row and column duplications are controlled by different if statements

                    if (rowCells.get(Integer.valueOf(i)).containsKey(Integer.parseInt(s[i][j]))) {
                        addErrors(String.format("%s has a duplicated value with %s",
                                rowCells.get(Integer.valueOf(i)).get(Integer.parseInt(s[i][j])).toString(),
                                new Cell(i, j).toString()));
                    }

                    if (colCells.get(Integer.valueOf(j)).containsKey(Integer.parseInt(s[i][j]))) {
                        addErrors(String.format("%s has a duplicated value with %s",
                                colCells.get(Integer.valueOf(j)).get(Integer.parseInt(s[i][j])).toString(),
                                new Cell(i, j).toString()));
                    }

                    // This is for saving column cell values that are not duplicated.
                    if (!colCells.get(Integer.valueOf(j)).containsKey(Integer.parseInt(s[i][j]))) {
                        colCells.get(Integer.valueOf(j)).put(Integer.parseInt(s[i][j]), new Cell(i, j));
                    }

                    // This is for saving row cell values that are not duplicated.
                    if(!rowCells.get(Integer.valueOf(i)).containsKey(Integer.parseInt(s[i][j])))
                    {
                        rowCells.get(Integer.valueOf(i)).put(Integer.parseInt(s[i][j]), new Cell(i, j));

                    }
                }
            }
        }
        // After chacking whole matrix. We are checking 9 individual 3*3 matrices
        for(int i=0; i<7; i+=3)
            for(int j=0; j<7; j+=3) {
                controlSub(i,j);
            }

    }

    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("File name is not given");
        } else {
            try{
                // Sudoku file reading process
                File f = new File(args[0]);
                Scanner scanner = new Scanner(f);
                // Sudoku will contain a 9x9 matris
                String[][] sudoku = new String[9][9];
                int i=0;
                while(scanner.hasNextLine()) {
                    String str = scanner.nextLine();
                    String[] values = str.split(",");
                    if(values.length >9) {
                        throw new Exception("Sudoku can not contain more than 9 columns");
                    }
                    // sudoku matris is filled
                    int j=0;
                    for(String v : values) {
                        sudoku[i][j] =v;
                        j++;
                    }
                    i++;
                    if(i>9) {
                        throw new Exception("Sudoku can not contain more than 9 rows");
                    }
                }

                // create a SudokuValidator class for validating the sudoku
                SudokuValidator s = new SudokuValidator(sudoku);
                if(s.getErrors().size() ==0) {
                    System.out.println("VALID");
                } else {
                    System.out.println("INVALID");
                    for(String err : s.getErrors())
                        System.out.println(err);
                }

            }catch (FileNotFoundException e){
                System.out.println("File "+ args[0] + " not Found");
            }catch(Exception e) {
                // Sudoku size errors
                e.printStackTrace();
            }

        }

    }


}

