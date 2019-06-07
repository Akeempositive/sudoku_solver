package sudoku;
public class NotASudokuFormatException extends Exception {
        private static final long serialVersionUID = 1L;

        public NotASudokuFormatException() {
            super("Not A Sudoku Format Exception");
        }
    }