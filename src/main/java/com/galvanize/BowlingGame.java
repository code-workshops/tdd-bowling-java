package com.galvanize;

public class BowlingGame {
    private int pinsPerRoll[] = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        // Track the pins for each roll.
        pinsPerRoll[currentRoll++] = pins;
    }

    public int getScore() {
        // Calculating score each call is what makes it easy to count
        // bonus points for spares and strikes.
        int score = 0;
        int frameIndex = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(frameIndex)) {
                score += 10 + strikeBonus(frameIndex);
                frameIndex++;
            }
            else if (isSpare(frameIndex)) // spare
            {
                // initial value of each roll is 0; avoids index errors
                score += 10 + spareBonus(frameIndex);
                frameIndex += 2;
            }
            else {
                score += getFrameTotal(frameIndex);
                frameIndex += 2;
            }
        }
        return score;

    }

    public int getCurrentFrame() {
        if (currentRoll % 2 == 0)
            return currentRoll/2 + 1;
        return currentRoll/2;
    }

    private boolean isSpare(int frameIndex) {
        return pinsPerRoll[frameIndex] +
                pinsPerRoll[frameIndex + 1] == 10;
    }

    private boolean isStrike(int frameIndex) {
        return pinsPerRoll[frameIndex] == 10;
    }

    private int getFrameTotal(int frameIndex) {
        return pinsPerRoll[frameIndex]+pinsPerRoll[frameIndex+1];
    }

    private int spareBonus(int frameIndex) {
        return pinsPerRoll[frameIndex + 2];
    }

    private int strikeBonus(int frameIndex) {
        return pinsPerRoll[frameIndex+1]+pinsPerRoll[frameIndex+2];
    }

}