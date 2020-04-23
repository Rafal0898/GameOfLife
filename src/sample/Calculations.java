package sample;

public class Calculations {
    public static boolean[][] calculateNextTimeStep(boolean[][] currentTimeStep, boolean ifPeriodicBoundaryConditions) {
        int xSize = currentTimeStep.length;
        int ySize = currentTimeStep[0].length;
        boolean[][] nextGeneration = new boolean[xSize][ySize];

        if (ifPeriodicBoundaryConditions) {
            nextGeneration[0][0] = checkFirstCorner(currentTimeStep, currentTimeStep[0][0]);
            nextGeneration[xSize - 1][0] = checkSecondCorner(currentTimeStep, currentTimeStep[xSize - 1][0]);
            nextGeneration[xSize - 1][ySize - 1] = checkThirdCorner(currentTimeStep, currentTimeStep[xSize - 1][ySize - 1]);
            nextGeneration[0][ySize - 1] = checkFourthCorner(currentTimeStep, currentTimeStep[0][ySize - 1]);
            for (int i = 1; i < xSize - 1; i++) {
                nextGeneration[i][0] = checkUpperEdge(currentTimeStep, currentTimeStep[i][0], i);
                nextGeneration[i][ySize - 1] = checkBottomEdge(currentTimeStep, currentTimeStep[i][ySize - 1], i);
            }
            for (int i = 1; i < ySize - 1; i++) {
                nextGeneration[0][i] = checkLeftEdge(currentTimeStep, currentTimeStep[0][i], i);
                nextGeneration[xSize - 1][i] = checkRightEdge(currentTimeStep, currentTimeStep[xSize - 1][i], i);
            }
        }

        for (int i = 1; i < xSize - 1; i++) {
            for (int j = 1; j < ySize - 1; j++) {
                int numberOfNeighbours = countNeighbours(currentTimeStep, i, j);
                nextGeneration[i][j] = checkRule(currentTimeStep[i][j], numberOfNeighbours);
            }
        }
        return nextGeneration;
    }

    private static int countNeighbours(boolean[][] array, int x, int y) {
        int numberOfNeighbours = 0;
        if (array[x - 1][y - 1]) numberOfNeighbours++;
        if (array[x][y - 1]) numberOfNeighbours++;
        if (array[x + 1][y - 1]) numberOfNeighbours++;
        if (array[x - 1][y]) numberOfNeighbours++;
        if (array[x + 1][y]) numberOfNeighbours++;
        if (array[x - 1][y + 1]) numberOfNeighbours++;
        if (array[x][y + 1]) numberOfNeighbours++;
        if (array[x + 1][y + 1]) numberOfNeighbours++;
        return numberOfNeighbours;
    }

    private static boolean checkRule(boolean singleCell, int numberOfNeighbours) {
        if (singleCell) {
            return numberOfNeighbours > 1 && numberOfNeighbours < 4;
        } else return numberOfNeighbours == 3;
    }

    private static boolean checkFirstCorner(boolean[][] array, boolean singleCell) {
        int numberOfNeighbours = 0;
        int xSize = array.length;
        int ySize = array[0].length;
        if (array[xSize - 1][ySize - 1]) numberOfNeighbours++;
        if (array[0][ySize - 1]) numberOfNeighbours++;
        if (array[1][ySize - 1]) numberOfNeighbours++;
        if (array[xSize - 1][0]) numberOfNeighbours++;
        if (array[1][0]) numberOfNeighbours++;
        if (array[xSize - 1][1]) numberOfNeighbours++;
        if (array[0][1]) numberOfNeighbours++;
        if (array[1][1]) numberOfNeighbours++;

        return checkRule(singleCell, numberOfNeighbours);
    }

    private static boolean checkSecondCorner(boolean[][] array, boolean singleCell) {
        int numberOfNeighbours = 0;
        int xSize = array.length;
        int ySize = array[0].length;
        if (array[xSize - 2][ySize - 1]) numberOfNeighbours++;
        if (array[xSize - 1][ySize - 1]) numberOfNeighbours++;
        if (array[0][ySize - 1]) numberOfNeighbours++;
        if (array[0][0]) numberOfNeighbours++;
        if (array[0][1]) numberOfNeighbours++;
        if (array[xSize - 1][1]) numberOfNeighbours++;
        if (array[xSize - 2][1]) numberOfNeighbours++;
        if (array[xSize - 1][0]) numberOfNeighbours++;

        return checkRule(singleCell, numberOfNeighbours);
    }

    private static boolean checkThirdCorner(boolean[][] array, boolean singleCell) {
        int numberOfNeighbours = 0;
        int xSize = array.length;
        int ySize = array[0].length;
        if (array[xSize - 2][ySize - 1]) numberOfNeighbours++;
        if (array[xSize - 2][ySize - 2]) numberOfNeighbours++;
        if (array[xSize - 1][ySize - 2]) numberOfNeighbours++;
        if (array[0][ySize - 2]) numberOfNeighbours++;
        if (array[0][ySize - 1]) numberOfNeighbours++;
        if (array[0][0]) numberOfNeighbours++;
        if (array[xSize - 1][0]) numberOfNeighbours++;
        if (array[xSize - 2][0]) numberOfNeighbours++;

        return checkRule(singleCell, numberOfNeighbours);
    }

    private static boolean checkFourthCorner(boolean[][] array, boolean singleCell) {
        int numberOfNeighbours = 0;
        int xSize = array.length;
        int ySize = array[0].length;
        if (array[0][ySize - 2]) numberOfNeighbours++;
        if (array[1][ySize - 2]) numberOfNeighbours++;
        if (array[1][ySize - 1]) numberOfNeighbours++;
        if (array[0][1]) numberOfNeighbours++;
        if (array[0][0]) numberOfNeighbours++;
        if (array[xSize - 1][0]) numberOfNeighbours++;
        if (array[xSize - 1][ySize - 1]) numberOfNeighbours++;
        if (array[xSize - 1][ySize - 2]) numberOfNeighbours++;

        return checkRule(singleCell, numberOfNeighbours);
    }

    private static boolean checkUpperEdge(boolean[][] array, boolean singleCell, int index) {
        int numberOfNeighbours = 0;
        int ySize = array[0].length;
        if (array[index - 1][0]) numberOfNeighbours++;
        if (array[index - 1][1]) numberOfNeighbours++;
        if (array[index][1]) numberOfNeighbours++;
        if (array[index + 1][1]) numberOfNeighbours++;
        if (array[index + 1][0]) numberOfNeighbours++;
        if (array[index + 1][ySize - 1]) numberOfNeighbours++;
        if (array[index][ySize - 1]) numberOfNeighbours++;
        if (array[index - 1][ySize - 1]) numberOfNeighbours++;

        return checkRule(singleCell, numberOfNeighbours);
    }

    private static boolean checkBottomEdge(boolean[][] array, boolean singleCell, int index) {
        int numberOfNeighbours = 0;
        int ySize = array[0].length;
        if (array[index + 1][ySize - 1]) numberOfNeighbours++;
        if (array[index + 1][ySize - 2]) numberOfNeighbours++;
        if (array[index][ySize - 2]) numberOfNeighbours++;
        if (array[index - 1][ySize - 2]) numberOfNeighbours++;
        if (array[index - 1][ySize - 1]) numberOfNeighbours++;
        if (array[index - 1][0]) numberOfNeighbours++;
        if (array[index][0]) numberOfNeighbours++;
        if (array[index + 1][0]) numberOfNeighbours++;

        return checkRule(singleCell, numberOfNeighbours);
    }

    private static boolean checkLeftEdge(boolean[][] array, boolean singleCell, int index) {
        int numberOfNeighbours = 0;
        int xSize = array.length;
        if (array[0][index - 1]) numberOfNeighbours++;
        if (array[1][index - 1]) numberOfNeighbours++;
        if (array[1][index]) numberOfNeighbours++;
        if (array[1][index + 1]) numberOfNeighbours++;
        if (array[0][index + 1]) numberOfNeighbours++;
        if (array[xSize - 1][index + 1]) numberOfNeighbours++;
        if (array[xSize - 1][index]) numberOfNeighbours++;
        if (array[xSize - 1][index - 1]) numberOfNeighbours++;

        return checkRule(singleCell, numberOfNeighbours);
    }

    private static boolean checkRightEdge(boolean[][] array, boolean singleCell, int index) {
        int numberOfNeighbours = 0;
        int xSize = array.length;
        if (array[xSize - 1][index + 1]) numberOfNeighbours++;
        if (array[xSize - 2][index + 1]) numberOfNeighbours++;
        if (array[xSize - 2][index]) numberOfNeighbours++;
        if (array[xSize - 2][index - 1]) numberOfNeighbours++;
        if (array[xSize - 1][index - 1]) numberOfNeighbours++;
        if (array[0][index - 1]) numberOfNeighbours++;
        if (array[0][index]) numberOfNeighbours++;
        if (array[0][index + 1]) numberOfNeighbours++;

        return checkRule(singleCell, numberOfNeighbours);
    }
}